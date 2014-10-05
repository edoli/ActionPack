package kr.co.edoli.actionpack.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import kr.co.edoli.actionpack.ActionPack;
import kr.co.edoli.actionpack.ConditionAction;

/**
 * Created by edoli on 14. 5. 21.
 */
public class Tests {

    private Skin skin;

    public Tests(Skin skin) {
        this.skin = skin;
    }

    public Array<ActionWrapper> getTests() {
        Array<ActionWrapper> actionTests = new Array<>();

        actionTests.add(test(
                new TestActor(region("actor.png")),
                ActionPack.movePath(new Bezier<>(new Vector2(100, 400), new Vector2(500, 300), new Vector2(100, 200))),
                "MovePath"));

        actionTests.add(test(
                new TestActor(region("actor.png")),
                Actions.sequence(
                        Actions.moveTo(100, 400),
                        ActionPack.moveByPath(new Bezier<>(new Vector2(0, 0), new Vector2(400, -100), new Vector2(0, -200))),
                        ActionPack.moveByPath(new Bezier<>(new Vector2(0, 0), new Vector2(200, 100), new Vector2(0, 200)))),
                "MoveByPath"));

        actionTests.add(test(
                new Label("", skin),
                Actions.sequence(
                        Actions.moveTo(100, 400),
                        ActionPack.labelTicker("This is a test sentence of a label ticker action.")),
                "LabelTicker"));


        actionTests.add(test(new ScrollPane(testList()),
                Actions.sequence(
                        ActionPack.scroll(10, 64),
                        ActionPack.scroll(0, 0)),
                "Scroll"));

        actionTests.add(test(
                new TestActor(region("actor.png")),
                Actions.sequence(
                        ActionPack.movePath(new Bezier<>(new Vector2(100, 400), new Vector2(500, 300), new Vector2(100, 200))),
                        ActionPack.reverse(ActionPack.movePath(new Bezier<>(new Vector2(100, 400), new Vector2(500, 300), new Vector2(100, 200))))
                        ),
                "Reverse"));

        actionTests.add(test(
                new TestActor(region("actor.png")),
                Actions.sequence(
                        Actions.moveTo(100, 400),
                        ActionPack.condition(new ConditionAction.Condition() {
                            @Override
                            public boolean evaluate(Actor actor) {
                                return Gdx.input.isTouched();
                            }
                        }, ActionPack.movePath(new Bezier<>(new Vector2(100, 400), new Vector2(500, 300), new Vector2(100, 200))))),
                "Condition"));

        actionTests.add(test(
                new TestActor(region("actor.png")),
                Actions.sequence(
                        Actions.moveTo(100, 400),
                        ActionPack.event(InputEvent.class, ActionPack.movePath(new Bezier<>(new Vector2(100, 400), new Vector2(500, 300), new Vector2(100, 200))))),
                "Event"));
        return actionTests;
    }

    private ActionWrapper test(Actor actor, Action action, String name) {
        return new ActionWrapper(actor, action, name);
    }

    private List testList() {
        List list = new List(skin);

        Array<String> a = new Array<>();
        for (int i = 0; i < 32; i++) {
            a.add("Something" + i);
        }

        list.setItems(a);

        return list;
    }

    private static TextureRegion region(String name) {
        return new TextureRegion(new Texture(Gdx.files.internal(name)));
    }

    public static class ActionWrapper {
        Actor actor;
        Action action;
        String name;

        ActionWrapper(Actor actor, Action action, String name) {
            this.actor = actor;
            this.action = action;
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static class InterpolationWrapper {
        Interpolation interpolation;
        private String name;

        InterpolationWrapper(Interpolation interpolation, String name) {
            this.interpolation = interpolation;
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
