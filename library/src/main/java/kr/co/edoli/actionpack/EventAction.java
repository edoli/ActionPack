package kr.co.edoli.actionpack;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.actions.DelegateAction;
import com.badlogic.gdx.utils.reflect.ClassReflection;

/**
 * Created by edoli on 14. 5. 21.
 */
public class EventAction extends DelegateAction {
    private boolean isStarted = false;
    private Class<? extends Event> eventClass;

    private final EventListener listener = new EventListener() {
        @Override
        public boolean handle(Event event) {
            if (ClassReflection.isInstance(eventClass, event)) {
                isStarted = true;
                actor.removeListener(listener);
            }
            return false;
        }
    };


    @Override
    protected boolean delegate(float delta) {
        if (isStarted) {
            return action.act(delta);
        }
        return false;
    }

    @Override
    public void setActor(Actor actor) {
        if (actor != null) {
            actor.removeListener(listener);
        }
        super.setActor(actor);
        if (actor != null) {
            actor.addListener(listener);
        }
    }

    @Override
    public void restart() {
        isStarted = false;
        actor.addListener(listener);
        super.restart();
    }


    public Class<? extends Event> getEventClass() {
        return eventClass;
    }

    public void setEventClass(Class<? extends Event> eventClass) {
        this.eventClass = eventClass;
    }
}
