package kr.co.edoli.actionpack;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Path;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.action;

/**
 * Created by edoli on 14. 5. 21.
 */
public class ActionPack {
    public static MovePathAction movePath(Path<Vector2> path) {
        return movePath(path, 0, null);
    }

    public static MovePathAction movePath(Path<Vector2> path, float duration) {
        return movePath(path, duration, null);
    }

    public static MovePathAction movePath(Path<Vector2> path, float duration, Interpolation interpolation) {
        MovePathAction action = action(MovePathAction.class);
        action.setPath(path);
        action.setDuration(duration);
        action.setInterpolation(interpolation);
        return action;
    }

    public static MoveByPathAction moveByPath(Path<Vector2> path) {
        return moveByPath(path, 0, null);
    }

    public static MoveByPathAction moveByPath(Path<Vector2> path, float duration) {
        return moveByPath(path, duration, null);
    }

    public static MoveByPathAction moveByPath(Path<Vector2> path, float duration, Interpolation interpolation) {
        MoveByPathAction action = action(MoveByPathAction.class);
        action.setPath(path);
        action.setDuration(duration);
        action.setInterpolation(interpolation);
        return action;
    }


    public static LabelTickerAction labelTicker(String text) {
        return labelTicker(text, 0, null);
    }

    public static LabelTickerAction labelTicker(String text, float duration) {
        return labelTicker(text, duration, null);
    }

    public static LabelTickerAction labelTicker(String text, float duration, Interpolation interpolation) {
        LabelTickerAction action = action(LabelTickerAction.class);
        action.setText(text);
        action.setDuration(duration);
        action.setInterpolation(interpolation);
        return action;
    }

    public static ScrollAction scroll(float scrollX, float scrollY) {
        return scroll(scrollX, scrollY, 0, null);
    }

    public static ScrollAction scroll(float scrollX, float scrollY, float duration) {
        return scroll(scrollX, scrollY, duration, null);
    }

    public static ScrollAction scroll(float scrollX, float scrollY, float duration, Interpolation interpolation) {
        ScrollAction action = action(ScrollAction.class);
        action.setScrollX(scrollX);
        action.setScrollY(scrollY);
        action.setDuration(duration);
        action.setInterpolation(interpolation);
        return action;
    }

    public static TemporalAction reverse(TemporalAction action) {
        action.setReverse(true);
        return action;
    }

    public static ConditionAction condition(ConditionAction.Condition condition, Action nextAction) {
        ConditionAction action = action(ConditionAction.class);
        action.setCondition(condition);
        action.setAction(nextAction);
        return action;
    }

    public static EventAction event(Class<? extends Event> eventClass, Action nextAction) {
        EventAction action = action(EventAction.class);
        action.setEventClass(eventClass);
        action.setAction(nextAction);
        return action;
    }
}
