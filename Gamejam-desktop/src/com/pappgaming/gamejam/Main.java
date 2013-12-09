package com.pappgaming.gamejam;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = Gamejam.TITLE + " v" + Gamejam.VERSION;
		cfg.vSyncEnabled = true;
		cfg.useGL20 = true;
		cfg.width = 1280;
		cfg.height = 720;
		cfg.addIcon("data/appicon2.png", Files.FileType.Internal);
		new LwjglApplication(new Gamejam(), cfg);
	}
}
