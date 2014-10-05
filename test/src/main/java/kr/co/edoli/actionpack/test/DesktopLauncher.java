package kr.co.edoli.actionpack.test;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * Created by edoli on 14. 5. 21.
 */
public class DesktopLauncher {
    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.useGL30 = true;
        cfg.title = "Action Pack Test";
        cfg.width = 800;
        cfg.height = 480;
        cfg.resizable = false;

        new LwjglApplication(new TestGame(), cfg);
    }
}
