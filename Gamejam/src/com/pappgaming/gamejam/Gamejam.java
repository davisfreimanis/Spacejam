package com.pappgaming.gamejam;

import com.badlogic.gdx.Game;
import com.pappgaming.gamejam.screens.SplashScreen;

public class Gamejam extends Game
{
	public static final String TAG = "Gamejam";
	public static final String TITLE = "Spacejam";
	public static final String VERSION = "0.3";

	@Override
	public void create()
	{
		setScreen(new SplashScreen());
	}

	@Override
	public void dispose()
	{
		super.dispose();
	}

	@Override
	public void render()
	{
		super.render();
	}

	@Override
	public void resize(int width, int height)
	{
		super.resize(width, height);
	}

	@Override
	public void pause()
	{
		super.pause();
	}

	@Override
	public void resume()
	{
		super.resume();
	}
}
