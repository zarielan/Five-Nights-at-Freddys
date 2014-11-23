package com.games.fnaf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class CameraButton
{
	private Vector2 position;
	private Texture bg, flash;
	private Texture buttonLabel;
	private boolean isSelected;
	private float timeElapsed = 0f;
	private Rectangle hitBox;

	public CameraButton(String id, float x, float y)
	{
		bg = Art.cameraButtonBG;
		flash = Art.cameraButtonFlash;
		buttonLabel = Art.cameraButtonTextures.get(id);
		position = new Vector2(x - (bg.getWidth() / 2), y - (bg.getHeight() / 2));
		hitBox = new Rectangle(position.x, position.y, bg.getWidth(), bg.getHeight());
	}

	public void render(SpriteBatch batch)
	{
		//Flicker effect
		if (isSelected)
		{
			timeElapsed += Gdx.graphics.getDeltaTime();

			if (timeElapsed >= 2f)
			{
				timeElapsed = 0f;
			}

			if (timeElapsed >= 1f)
			{
				batch.draw(flash, position.x, position.y);
			}
			else
			{
				batch.draw(bg, position.x, position.y);
			}
		}
		else
		{
			batch.draw(bg, position.x, position.y);
		}

		batch.draw(buttonLabel, position.x + 7f, position.y + 7f); //yay for hardcoded values
	}

	public void setSelected(boolean bool)
	{
		isSelected = bool;
	}

	public Rectangle getHitBox()
	{
		return hitBox;
	}
}
