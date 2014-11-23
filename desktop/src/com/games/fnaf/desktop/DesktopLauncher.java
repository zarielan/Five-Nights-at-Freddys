package com.games.fnaf.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.games.fnaf.FNaF;

public class DesktopLauncher
{
	public static void main(String[] arg)
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.backgroundFPS = 60;
		config.foregroundFPS = 60;
		config.fullscreen = false;
		config.height = 720;
		config.initialBackgroundColor = Color.BLACK;
		config.resizable = false;
		config.title = "Five Nights at Freddy's";
		config.vSyncEnabled = false;
		config.width = 1280;
		new LwjglApplication(new FNaF(), config);
	}
}
