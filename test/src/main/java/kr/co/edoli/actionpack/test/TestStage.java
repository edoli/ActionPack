package kr.co.edoli.actionpack.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by edoli on 14. 5. 21.
 */
public class TestStage extends Stage {
    Skin skin;
    WidgetGroup playground;
    Actor currentActor;
    Action currentAction;
    float currentDuration = 1f;
    Interpolation currentInterpolation;

    public TestStage() {

        skin = new Skin(Gdx.files.internal("Holo-dark-mdpi.json"));

        Table table = new Table(skin);

        Array<Tests.ActionWrapper> actions = buildActionList();

        final List<Tests.ActionWrapper> actionList = new List<>(skin);

        actionList.setItems(actions);
        actionList.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Tests.ActionWrapper actionWrapper = actionList.getItems().get(actionList.getSelectedIndex());
                currentActor = actionWrapper.actor;
                currentAction = actionWrapper.action;
                startTest();
            }
        });

        final List<Tests.InterpolationWrapper> interpolationList = new List<>(skin);
        interpolationList.setItems(buildInterpolationList());
        interpolationList.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                currentInterpolation = interpolationList.getItems().get(interpolationList.getSelectedIndex()).interpolation;
                startTest();
            }
        });


        final Slider durationSlider = new Slider(0.1f, 3f, 0.1f, true, skin);
        durationSlider.setValue(currentDuration);
        durationSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                currentDuration = durationSlider.getValue();
                startTest();
            }
        });


        playground = new WidgetGroup();

        table.add("Action");
        table.add("Interpolation");

        table.row();

        table.add(scrollPane(actionList, skin)).width(150).expandY().fillY();
        table.add(scrollPane(interpolationList, skin)).width(150).expandY().fillY();
        table.add(playground).expandX().fill();
        table.add(durationSlider).expandY().fillY();

        table.setFillParent(true);

        addActor(table);

        Tests.ActionWrapper actionWrapper = actionList.getItems().get(actionList.getSelectedIndex());
        currentActor = actionWrapper.actor;
        currentAction = actionWrapper.action;
        currentInterpolation = interpolationList.getItems().get(interpolationList.getSelectedIndex()).interpolation;
        startTest();

    }

    private ScrollPane scrollPane(Widget widget, Skin skin) {
        ScrollPane pane = new ScrollPane(widget, skin);
        pane.setFadeScrollBars(false);
        pane.setScrollingDisabled(true, false);
        pane.setScrollbarsOnTop(false);

        return pane;
    }

    private Array<Tests.ActionWrapper> buildActionList() {
        Tests tests = new Tests(skin);
        return tests.getTests();
    }

    private Array<Tests.InterpolationWrapper> buildInterpolationList() {
        Array<Tests.InterpolationWrapper> interpolations = new Array<>();

        for (Field field : Interpolation.class.getFields()) {
            if (Modifier.isStatic(field.getModifiers())) {
                try {
                    Interpolation interpolation = (Interpolation) field.get(null);
                    interpolations.add(new Tests.InterpolationWrapper(interpolation, field.getName()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return interpolations;
    }


    public void startTest() {
        Actor actor = currentActor;
        Action action = currentAction;

        if (action == null) {
            return;
        }

        setupTest(action);

        playground.clear();
        actor.setVisible(true);

        actor.getActions().clear();
        actor.addAction(Actions.repeat(RepeatAction.FOREVER, action));

        playground.addActor(actor);
    }

    public void setupTest(Action action) {
        if (action instanceof TemporalAction && action.getClass().getPackage().getName().equals("kr.co.edoli.actionpack")) {
            TemporalAction temporalAction = ((TemporalAction) action);
            temporalAction.setInterpolation(currentInterpolation);
            temporalAction.setDuration(currentDuration);
        }

        if (action instanceof DelegateAction) {
            DelegateAction delegateAction = (DelegateAction) action;
            setupTest(delegateAction.getAction());
        }

        if (action instanceof ParallelAction) {
            ParallelAction parallelAction = (ParallelAction) action;
            for (Action childAction : parallelAction.getActions()) {
                setupTest(childAction);
            }
        }
    }

}
