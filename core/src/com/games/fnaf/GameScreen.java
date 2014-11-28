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
		Animatronic.CHICA.getAI().setFrequency(20);
		Animatronic.CHICA.setMoving(true);
		for (Animatronic a : Animatronic.values())
		{
			a.updateAI();
		}
	}

	@Override
	public void show()
	{
		this.camera = new Camera(batch);
		//Set their starting areas
		Animatronic.FREDDY.setCurrentRoom(Room.SHOW_STAGE);
		Animatronic.BONNIE.setCurrentRoom(Room.SHOW_STAGE);
		Animatronic.CHICA.setCurrentRoom(Room.SHOW_STAGE);
		Animatronic.FOXY.setCurrentRoom(Room.PIRATE_COVE);
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
