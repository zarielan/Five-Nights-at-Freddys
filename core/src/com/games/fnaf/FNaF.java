package com.games.fnaf;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FNaF extends ApplicationAdapter
{
	private SpriteBatch batch;
	private OrthographicCamera ortho;
	private Camera camera;

	@Override
	public void create()
	{
		Art.loadTextures();

		//Set their starting areas
		Animatronic.FREDDY.setCurrentRoom(Room.SHOW_STAGE);
		Animatronic.BONNIE.setCurrentRoom(Room.DINING_AREA);
		Animatronic.CHICA.setCurrentRoom(Room.DINING_AREA);
		Animatronic.FOXY.setCurrentRoom(Room.PIRATE_COVE);

		batch = new SpriteBatch();
		ortho = new OrthographicCamera();
		ortho.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera = new Camera(batch);
	}

	@Override
	public void render()
	{
		batch.setProjectionMatrix(ortho.combined);
		ortho.update();

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);

		batch.begin();

		camera.render();

		batch.end();
	}

	@Override
	public void dispose()
	{
		batch.dispose();
		Art.disposeTextures();
	}
}

