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

/** Moves an actor along given path.
 * @author Daniel Jeon
 */
public class LabelTickerAction extends TemporalAction {
    private CharSequence text;
    private Label label;
    private int currentPos;
    private int lastPos;
    private int textLength;

    @Override
    protected void update(float percent) {
        currentPos = (int) (textLength * percent);

        if (currentPos != lastPos) {
            if (currentPos < 0) {
                currentPos = 0;
            }
            if (currentPos > textLength) {
                currentPos = textLength;
            }
            label.setText(text.subSequence(0, currentPos));
        }
    }

    @Override
    public void setActor(Actor actor) {
        if (!(actor instanceof Label) && actor != null) {
            throw new IllegalArgumentException("LabelTicker uses only Labels");
        }

        label = (Label) actor;
        super.setActor(actor);
    }


    public CharSequence getText() {
        return text;
    }

    public void setText(CharSequence text) {
        textLength = text.length();
        currentPos = 0;
        lastPos = -1;
        this.text = text;
    }
}
