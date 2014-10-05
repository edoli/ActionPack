/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package kr.co.edoli.actionpack;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;

/** Moves an actor along given path.
 * @author Daniel Jeon
 */
public class ScrollAction extends TemporalAction {
    private ScrollPane scrollPane;
    private float scrollInitX;
    private float scrollInitY;
    private float scrollX;
    private float scrollY;

    @Override
    protected void begin() {
        scrollInitX = scrollPane.getScrollX();
        scrollInitY = scrollPane.getScrollY();
    }

    @Override
    protected void update(float percent) {
        scrollPane.setScrollX((scrollX - scrollInitX) * percent + scrollInitX);
        scrollPane.setScrollY((scrollY - scrollInitY) * percent + scrollInitY);
    }

    @Override
    public void setActor(Actor actor) {
        if (!(actor instanceof ScrollPane) && actor != null) {
            throw new IllegalArgumentException("ScrollAction uses only ScrollPane");
        }

        scrollPane = (ScrollPane) actor;
        super.setActor(actor);
    }


    public float getScrollX() {
        return scrollX;
    }

    public void setScrollX(float scrollX) {
        this.scrollX = scrollX;
    }

    public float getScrollY() {
        return scrollY;
    }

    public void setScrollY(float scrollY) {
        this.scrollY = scrollY;
    }
}
