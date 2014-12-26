package com.games.fnaf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOverScreen extends ScreenAdapter
{
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Animation endStatic;
	private Animation cameraBars;
	private float animCounter;
	private float fadeTime;
	private boolean renderStatic;
	private float gameOverCounter;

	public GameOverScreen(SpriteBatch batch1)
	{
		super();
		batch = batch1;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void render(float delta)
	{
		renderGameOver(delta);
		if (renderStatic)
			renderStatic(delta);
	}

	private void renderGameOver(float delta)
	{
		batch.setColor(1f, 1f, 1f, 1f);
		gameOverCounter += delta;
		batch.draw(Art.gameOverScreen, 0f, 0f);
		batch.draw(Art.gameOverText, Gdx.graphics.getWidth() - 20f - Art.gameOverText.getWidth(), 20f);

		if (gameOverCounter >= 10f)
		{
			//main menu
		}
	}

	private void renderStatic(float delta)
	{
		if (animCounter >= 10f)
		{
			fadeTime += delta;
			if (1f - fadeTime > 0f)
				batch.setColor(1f, 1f, 1f, 1f - fadeTime);
			else
			{
				batch.setColor(1f, 1f, 1f, 1f);
				renderStatic = false;
				return;
			}
		}
		else
		{
			animCounter += delta;
		}

		batch.draw(endStatic.getKeyFrame(animCounter), 0f, 0f);
		batch.draw(cameraBars.getKeyFrame(animCounter), 0f, 0f);
	}

	@Override
	public void show()
	{
		endStatic = new Animation(1/30f, Art.kitchenStatic);
		endStatic.setPlayMode(Animation.PlayMode.LOOP);
		animCounter = 0f;
		fadeTime = 0f;
		renderStatic = true;
		gameOverCounter = 0f;
		cameraBars = new Animation(1/30f, Art.cameraBars);
		cameraBars.setPlayMode(Animation.PlayMode.NORMAL);
	}
}
