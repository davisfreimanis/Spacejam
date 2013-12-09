package com.pappgaming.gamejam.other;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.pappgaming.gamejam.screens.Credits;
import com.pappgaming.gamejam.screens.Level1;
import com.pappgaming.gamejam.screens.Level2;
import com.pappgaming.gamejam.screens.Level3;
import com.pappgaming.gamejam.screens.Level4;
import com.pappgaming.gamejam.screens.Level5;
import java.lang.Math;

public class Player extends Sprite implements InputProcessor
{
	/* movement velocity */
	private Vector2 velocity = new Vector2();
	private float speed = 60 * 4;
	private float gravity = 60 * 10;
	private boolean canJump;
	private boolean starting = true;
	private int level;
	
	private TiledMapTileLayer collisionLayer;
	
	public Player(Sprite sprite, TiledMapTileLayer collisionLayer, int level)
	{
		super(sprite);
		this.collisionLayer = collisionLayer;
		this.level = level;
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch)
	{
		update(Gdx.graphics.getDeltaTime());
		super.draw(spriteBatch);
	}
	
	public void update(float delta)
	{
		if(starting)
		{
			velocity.x = 250;
			starting = false;
		}
		//Gravity
		velocity.y -= gravity * delta;
		
		//Clamp velocity
		if(velocity.y > speed)
		{
			velocity.y = speed;
		}
		else if(velocity.y < -speed)
		{
			velocity.y = -speed;
		}
		
		float oldX = getX();
		float oldY = getY();
		float tileWidth = collisionLayer.getTileWidth();
		float tileHeight = collisionLayer.getTileHeight();
		boolean collisionX = false;
		boolean collisionY = false;
		boolean goal = false;
		boolean die = false;
		boolean speedBoost = false;
		boolean speedBoostLeft = false;
		
		setX(getX() + velocity.x * delta);
		if(velocity.x > 0)
		{
			//going right
			if(!collisionX)
			{
				collisionX = collisionLayer.getCell((int)((getX()+getWidth())/tileWidth), (int)((getY() + getHeight())/tileHeight))
						.getTile().getProperties().containsKey("blocked");
				goal = collisionLayer.getCell((int)((getX()+getWidth())/tileWidth), (int)((getY() + getHeight())/tileHeight))
						.getTile().getProperties().containsKey("goal");
				speedBoost = collisionLayer.getCell((int)((getX()+getWidth())/tileWidth), (int)((getY() + getHeight())/tileHeight))
						.getTile().getProperties().containsKey("speedBoost");
			}
			if(!collisionX)
			{
				collisionX = collisionLayer.getCell((int)((getX()+getWidth())/tileWidth), (int)((getY() + getHeight() / 2)/tileHeight))
						.getTile().getProperties().containsKey("blocked");				
				goal = collisionLayer.getCell((int)((getX()+getWidth())/tileWidth), (int)((getY() + getHeight() / 2)/tileHeight))
						.getTile().getProperties().containsKey("goal");		
				speedBoost = collisionLayer.getCell((int)((getX()+getWidth())/tileWidth), (int)((getY() + getHeight() / 2)/tileHeight))
						.getTile().getProperties().containsKey("speedBoost");	
			}
			
			if(!collisionX)
			{
				collisionX = collisionLayer.getCell((int)(getX()/tileWidth), (int)(getY()/tileHeight))
						.getTile().getProperties().containsKey("blocked");
				goal = collisionLayer.getCell((int)(getX()/tileWidth), (int)(getY()/tileHeight))
						.getTile().getProperties().containsKey("goal");
				speedBoost = collisionLayer.getCell((int)(getX()/tileWidth), (int)(getY()/tileHeight))
						.getTile().getProperties().containsKey("speedBoost");
			}
		}
		else if(velocity.x < 0)
		{
			//going left top
			collisionX = collisionLayer.getCell((int)(getX()/tileWidth), (int)((getY() + getHeight())/tileHeight))
					.getTile().getProperties().containsKey("blocked");
			goal = collisionLayer.getCell((int)(getX()/tileWidth), (int)((getY() + getHeight())/tileHeight))
					.getTile().getProperties().containsKey("goal");
			speedBoostLeft = collisionLayer.getCell((int)(getX()/tileWidth), (int)((getY() + getHeight())/tileHeight))
					.getTile().getProperties().containsKey("speedBoost");
			
			//going left mid
			if(!collisionX)
			{
				collisionX = collisionLayer.getCell((int)(getX()/tileWidth), (int)((getY() + getHeight()/2)/tileHeight))
						.getTile().getProperties().containsKey("blocked");				
				goal = collisionLayer.getCell((int)(getX()/tileWidth), (int)((getY() + getHeight()/2)/tileHeight))
						.getTile().getProperties().containsKey("goal");			
				speedBoostLeft = collisionLayer.getCell((int)(getX()/tileWidth), (int)((getY() + getHeight()/2)/tileHeight))
						.getTile().getProperties().containsKey("speedBoost");	
			}
			
			//going left bot
			if(!collisionX)
			{
				collisionX = collisionLayer.getCell((int)(getX()/tileWidth), (int)(getY()/tileHeight))
						.getTile().getProperties().containsKey("blocked");				
				goal = collisionLayer.getCell((int)(getX()/tileWidth), (int)(getY()/tileHeight))
						.getTile().getProperties().containsKey("goal");		
				speedBoostLeft = collisionLayer.getCell((int)(getX()/tileWidth), (int)(getY()/tileHeight))
						.getTile().getProperties().containsKey("speedBoost");	
			}
		}
		//collision x
		if(collisionX)
		{
			setX(oldX);
			velocity.x = -velocity.x;
		}
		
		if(goal)
		{
			Gdx.app.log("level", ""+level);
			if(level == 1)
			{
				level++;
				((Game)Gdx.app.getApplicationListener()).setScreen(new Level2());
			}
			else if(level == 2)
			{
				level++;
				((Game)Gdx.app.getApplicationListener()).setScreen(new Level3());
			}
			else if(level == 3)
			{
				level++;
				((Game)Gdx.app.getApplicationListener()).setScreen(new Level4());
			}
			else if(level == 4)
			{
				level++;
				((Game)Gdx.app.getApplicationListener()).setScreen(new Level4());
			}
			else if(level == 5)
			{
				level++;
				((Game)Gdx.app.getApplicationListener()).setScreen(new Credits());
			}
		}
		if(speedBoost)
		{
			if(velocity.x <= 2000)
			{
				velocity.x += 50;
				speedBoost = false;
			}
			
		}
		else if(speedBoostLeft)
		{
			
			if(velocity.x >= -2000)
			{
				Gdx.app.log("hello", ""+velocity.x);
				velocity.x -= 50;
				speedBoost = false;
			}
		}
		
		setY(getY() + velocity.y * delta);
		
		if(velocity.y > 0)
		{
			//going up
			collisionY = collisionLayer.getCell((int)(getX()/tileWidth), (int)((getY()+getHeight())/tileHeight))
					.getTile().getProperties().containsKey("blocked");
			
			if(!collisionY)
			{
				collisionY = collisionLayer.getCell((int)((getX()+tileWidth/2)/tileWidth), (int)((getY()+getHeight())/tileHeight))
						.getTile().getProperties().containsKey("blocked");				
			}
			
			if(!collisionY)
			{
				collisionY = collisionLayer.getCell((int)((getX() + getWidth())/tileWidth), (int)((getY()+getHeight())/tileHeight))
						.getTile().getProperties().containsKey("blocked");	
			}
		}
		else if(velocity.y < 0)
		{
			//going down
			collisionY = collisionLayer.getCell((int)(getX()/tileWidth), (int)(getY()/tileHeight))
					.getTile().getProperties().containsKey("blocked");
			
			goal = collisionLayer.getCell((int)(getX()/tileWidth), (int)(getY()/tileHeight))
					.getTile().getProperties().containsKey("goal");
			die = collisionLayer.getCell((int)(getX()/tileWidth), (int)(getY()/tileHeight))
					.getTile().getProperties().containsKey("die");
			
			if(!collisionY)
			{
				collisionY = collisionLayer.getCell((int)((getX()+getWidth()/2)/tileWidth), (int)(getY()/tileHeight))
						.getTile().getProperties().containsKey("blocked");				
				goal = collisionLayer.getCell((int)((getX()+getWidth()/2)/tileWidth), (int)(getY()/tileHeight))
						.getTile().getProperties().containsKey("goal");				
				die = collisionLayer.getCell((int)((getX()+getWidth()/2)/tileWidth), (int)(getY()/tileHeight))
						.getTile().getProperties().containsKey("die");				
			}
			
			if(!collisionY)
			{
				collisionY = collisionLayer.getCell((int)((getX() + getWidth())/tileWidth), (int)(getY()/tileHeight))
						.getTile().getProperties().containsKey("blocked");				
				goal = collisionLayer.getCell((int)((getX() + getWidth())/tileWidth), (int)(getY()/tileHeight))
						.getTile().getProperties().containsKey("goal");				
				die = collisionLayer.getCell((int)((getX() + getWidth())/tileWidth), (int)(getY()/tileHeight))
						.getTile().getProperties().containsKey("die");				
			}
			canJump = collisionY;
		}
		//collision y
		if(collisionY)
		{
			setY(oldY);
			velocity.y = 0;
		}
		
		if(goal)
		{
			Gdx.app.log("level", ""+level);
			if(level == 1)
			{
				level++;
				((Game)Gdx.app.getApplicationListener()).setScreen(new Level2());
			}
			else if(level == 2)
			{
				level++;
				((Game)Gdx.app.getApplicationListener()).setScreen(new Level3());
			}
			else if(level == 3)
			{
				level++;
				((Game)Gdx.app.getApplicationListener()).setScreen(new Level4());
			}
			else if(level == 4)
			{
				level++;
				((Game)Gdx.app.getApplicationListener()).setScreen(new Level5());
			}
		}
		if(die)
		{
			if(level == 1)
			{
				((Game)Gdx.app.getApplicationListener()).setScreen(new Level1());
			}
			else if(level == 2)
			{
				((Game)Gdx.app.getApplicationListener()).setScreen(new Level2());
			}
			else if(level == 3)
			{
				((Game)Gdx.app.getApplicationListener()).setScreen(new Level3());
			}
			else if(level == 4)
			{
				((Game)Gdx.app.getApplicationListener()).setScreen(new Level4());
			}
			else if(level == 5)
			{
				((Game)Gdx.app.getApplicationListener()).setScreen(new Level5());
			}
		}
		
	}

	public Vector2 getVelocity()
	{
		return velocity;
	}

	public void setVelocity(Vector2 velocity)
	{
		this.velocity = velocity;
	}

	public float getSpeed()
	{
		return speed;
	}

	public void setSpeed(float speed)
	{
		this.speed = speed;
	}

	public float getGravity()
	{
		return gravity;
	}

	public void setGravity(float gravity)
	{
		this.gravity = gravity;
	}

	public TiledMapTileLayer getCollisionLayer()
	{
		return collisionLayer;
	}

	public void setCollisionLayer(TiledMapTileLayer collisionLayer)
	{
		this.collisionLayer = collisionLayer;
	}

	@Override
	public boolean keyDown(int keycode)
	{
		switch (keycode)
		{
			case Keys.A:
				velocity.x = -speed;
				break;
			case Keys.D:
				velocity.x = speed;
				break;
			case Keys.SPACE:
				Gdx.app.log("aoeu", ""+canJump+" " + speed+" "+velocity.y);
				if(canJump)
				{
					velocity.y = speed*2;
					canJump = false;					
				}
				break;
			default:
				break;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode)
	{
		switch(keycode)
		{

		}
		return false;
	}

	@Override
	public boolean keyTyped(char character)
	{
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY)
	{
		return false;
	}

	@Override
	public boolean scrolled(int amount)
	{
		return false;
	}
}

