package com.uppet.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.uppet.MainGame;

public class DesktopLauncher {
	public static void main(String[] arg){
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = MainGame.WIDTH;
		config.height = MainGame.HEIGHT;
		config.title = MainGame.TITLE;
		config.resizable = false;

		new LwjglApplication(new MainGame(), config);
	}
}
