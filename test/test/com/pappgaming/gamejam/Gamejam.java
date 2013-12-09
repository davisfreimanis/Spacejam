package com.pappgaming.gamejam;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pappgaming.gamejam.screens.Level1;
import com.pappgaming.gamejam.screens.Level3;
import com.pappgaming.gamejam.screens.Level5;
import com.pappgaming.gamejam.screens.SplashScreen;

public class Gamejam extends Game
{
	public static final String TAG = "Gamejam";
	public static final String TITLE = "Spacejam";
	public static final String VERSION = "0.1";

	@Override
	public void create()
	{
		Gdx.app.log(TAG, "Create");
		//setScreen(new SplashScreen());
		setScreen(new Level5());
	}

	@Override
	public void dispose()
	{
		Gdx.app.log(TAG, "Dispose");
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
		Gdx.app.log(TAG, "Resize");
		super.resize(width, height);
	}

	@Override
	public void pause()
	{
		Gdx.app.log(TAG, "Pause");
		super.pause();
	}

	@Override
	public void resume()
	{
		Gdx.app.log(TAG, "Resume");
		super.resume();
	}
}
