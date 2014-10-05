package kr.co.edoli.actionpack;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.DelegateAction;

/**
 * Created by edoli on 14. 5. 21.
 */
public class ConditionAction extends DelegateAction {
    private Condition condition;

    @Override
    protected boolean delegate(float delta) {
        if (condition.evaluate(actor)) {
            return action.act(delta);
        }
        return false;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public interface Condition {
        public boolean evaluate(Actor actor);
    }
}
