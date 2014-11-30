package com.games.fnaf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class Office implements Disposable
{
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private final float SIGHT_MOVEMENT = 200f;

	public Office()
	{
		this.batch = new SpriteBatch();
		this.camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	public void render()
	{
		batch.setProjectionMatrix(camera.combined);
		camera.update();
		batch.begin();

		batch.draw(Art.officeTextures.get("Office"), -160, 0f);

		if (Gdx.input.getX() <= 200)
		{
			camera.position.x -= Gdx.graphics.getDeltaTime() * SIGHT_MOVEMENT;
		}
		if (Gdx.input.getX() >= Gdx.graphics.getWidth() - 200)
		{
			camera.position.x += Gdx.graphics.getDeltaTime() * SIGHT_MOVEMENT;
		}

		System.out.println(camera.position.x);

		batch.end();
	}

	@Override
	public void dispose()
	{
		batch.dispose();
	}
}
