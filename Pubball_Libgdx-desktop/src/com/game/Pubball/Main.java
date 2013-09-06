package com.game.Pubball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Pubball_Libgdx";
		cfg.useGL20 = true;
		cfg.width = 420;
		cfg.height = 360;
		
		new LwjglApplication(new Pubball(), cfg);
	}
}
