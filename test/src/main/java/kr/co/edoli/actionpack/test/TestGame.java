package kr.co.edoli.actionpack.test;

import com.badlogic.gdx.Game;

/**
 * Created by edoli on 14. 5. 21.
 */
public class TestGame extends Game {
    @Override
    public void create() {
        setScreen(new TestScreen(this));
    }
}
