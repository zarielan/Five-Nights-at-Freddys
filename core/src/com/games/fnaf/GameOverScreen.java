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
	private float staticCounter;
	private float fadeTime;

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
		renderStatic(delta);
	}

	private void renderStatic(float delta)
	{
		if (staticCounter >= 10f)
		{
			fadeTime += delta;
			if (1f - fadeTime > 0f)
				batch.setColor(1f, 1f, 1f, 1f - fadeTime);
			else
			{
				batch.setColor(1f, 1f, 1f, 1f);
				return;
			}
		}
		else
		{
			staticCounter += delta;
		}

		batch.draw(endStatic.getKeyFrame(staticCounter), 0f, 0f);
	}

	@Override
	public void show()
	{
		endStatic = new Animation(1/30f, Art.kitchenStatic);
		endStatic.setPlayMode(Animation.PlayMode.LOOP);
		staticCounter = 0f;
		fadeTime = 0f;
	}
}
