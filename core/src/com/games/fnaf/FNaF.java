package com.games.fnaf;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FNaF extends Game
{
	private SpriteBatch batch;
	private static float timeElapsed;

	@Override
	public void create()
	{
		Art.loadTextures();
		timeElapsed = 0f;

		batch = new SpriteBatch();
		this.setScreen(new GameScreen(batch));
	}

	@Override
	public void render()
	{
		timeElapsed += Gdx.graphics.getDeltaTime();

		System.out.println(timeElapsed);

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);

		batch.begin();

		if (this.getScreen() != null)
			this.getScreen().render(Gdx.graphics.getDeltaTime());

		batch.end();
	}

	public static float getTimeElapsed()
	{
		return timeElapsed;
	}

	@Override
	public void dispose()
	{
		batch.dispose();
		Art.disposeTextures();
	}
}

