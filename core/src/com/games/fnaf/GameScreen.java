package com.games.fnaf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends ScreenAdapter
{
	private Camera camera;
	private Office office;
	private boolean lookingAtCamera;
	private SpriteBatch batch;
	private OrthographicCamera ortho;
	private Night night;

	public GameScreen(SpriteBatch batch)
	{
		this.batch = batch;
		this.ortho = new OrthographicCamera();
		this.ortho.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.night = Night.NIGHT_1;
		this.lookingAtCamera = false;
	}

	@Override
	public void render(float delta)
	{
		if (lookingAtCamera)
		{
			camera.render();
		}
		else
		{
			office.render();
		}

		batch.setProjectionMatrix(ortho.combined);
		ortho.update();

		//Holy fuck the button doesn't show up. Okay, TODO
		//but for next time :)
		batch.draw(Art.cameraToggle, 600f, 600f);

		for (Animatronic a : Animatronic.values())
		{
			a.getAI().update(a);
		}
	}

	@Override
	public void show()
	{
		this.camera = new Camera(batch);
		this.office = new Office(batch);
		//Set their starting areas
		Animatronic.FREDDY.setCurrentRoom(Room.SHOW_STAGE);
		Animatronic.BONNIE.setCurrentRoom(Room.SHOW_STAGE);
		Animatronic.CHICA.setCurrentRoom(Room.SHOW_STAGE);
		Animatronic.FOXY.setCurrentRoom(Room.PIRATE_COVE);
		this.night.doNight();
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
