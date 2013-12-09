package com.pappgaming.gamejam.screens;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.pappgaming.gamejam.Gamejam;

public class MainMenu implements Screen
{

	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private Table table;
	private TextButton bExit, bPlay, bToggleMusic;
	private BitmapFont white, black;
	private Label header;
	private Music music;
	private Boolean isPlaying = true;
	
	private TweenManager tm;
	
	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(1, 0.41f, 0.705f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		
		stage.draw();
	}

	@Override
	public void resize(int width, int height)
	{
		//Make window nice when resize
		stage.setViewport(width, height, true);
		table.setClip(true);
		table.setSize(width, height);
	}

	@Override
	public void show()
	{
		music = Gdx.audio.newMusic(Gdx.files.internal("sounds/song3.wav"));
		music.setLooping(true);
		music.play();
		
		
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		atlas = new TextureAtlas("ui/button.pack");
		skin = new Skin(atlas);
		
		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		//Fonts
		white = new BitmapFont(Gdx.files.internal("Font/white.fnt"), false);
		black = new BitmapFont(Gdx.files.internal("Font/black.fnt"), false);
		
		//Buttons
		TextButtonStyle tbs = new TextButtonStyle();
		tbs.up = skin.getDrawable("b2");
		tbs.down = skin.getDrawable("b1");
		tbs.pressedOffsetX = 1;
		tbs.pressedOffsetY = -1;
		tbs.font = black;
		
		bExit = new TextButton("Exit", tbs);
		bExit.addListener(new ClickListener()
		{
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				Gdx.app.exit();
			}
		});
		bExit.pad(20);
		bPlay = new TextButton("Play", tbs);
		bPlay.addListener(new ClickListener()
		{
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				((Game)Gdx.app.getApplicationListener()).setScreen(new Level1());
			}
		});
		bPlay.pad(20);
		bToggleMusic = new TextButton("Mute", tbs);
		bToggleMusic.addListener(new ClickListener()
		{
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				if(isPlaying)
				{
					music.pause();
					isPlaying = false;
				}
				else
				{
					music.play();
					isPlaying = true;
				}
			}
		});
		bToggleMusic.pad(20);
		
		//Header
		LabelStyle headingStyle = new LabelStyle(white, Color.WHITE);
		header = new Label(Gamejam.TITLE, headingStyle);
		header.setFontScale(2);
		
		//Add to table
		table.add(header);
		table.getCell(header).spaceBottom(100);
		table.row();
		table.add(bPlay);
		table.getCell(bPlay).spaceBottom(25);
		table.row();
		table.add(bToggleMusic);
		table.row();
		table.getCell(bToggleMusic).spaceBottom(25);
		table.add(bExit);
		stage.addActor(table);	
	}

	@Override
	public void hide()
	{
		
	}

	@Override
	public void pause()
	{
		
	}

	@Override
	public void resume()
	{
		
	}

	@Override
	public void dispose()
	{
		stage.dispose();
		atlas.dispose();
		skin.dispose();
		white.dispose();
		black.dispose();
		music.dispose();
	}
}
