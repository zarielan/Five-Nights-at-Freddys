package com.games.fnaf;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends ScreenAdapter
{
	private Camera camera;
	private SpriteBatch batch;
	private OrthographicCamera ortho;

	public GameScreen(SpriteBatch batch, OrthographicCamera ortho)
	{
		this.batch = batch;
		this.ortho = ortho;
	}

	@Override
	public void render(float delta)
	{
		camera.render();
	}

	@Override
	public void show()
	{
		this.camera = new Camera(batch);
	}

	@Override
	public void hide()
	{
	}

	@Override
	public void dispose()
	{
	}
}
