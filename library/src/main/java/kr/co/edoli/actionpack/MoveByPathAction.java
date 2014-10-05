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

import com.badlogic.gdx.math.Path;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;

/** Moves an actor along given path.
 * @author Daniel Jeon
 */
public class MoveByPathAction extends TemporalAction {
    private Path<Vector2> path;
    private Vector2 pos = new Vector2();

    private float initX;
    private float initY;

    @Override
    protected void begin() {
        initX = actor.getX();
        initY = actor.getY();
    }

    @Override
    protected void update(float percent) {
        path.valueAt(pos, percent);
        actor.setPosition(pos.x + initX, pos.y + initY);
    }

    public void setPath(Path<Vector2> path) {
        this.path = path;
    }

    public Path getPath() {
        return path;
    }
}
