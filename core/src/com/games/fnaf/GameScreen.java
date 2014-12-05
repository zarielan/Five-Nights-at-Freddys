package com.games.fnaf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class GameScreen extends ScreenAdapter
{
	private Camera camera;
	private Office office;
	private boolean lookingAtCamera;
	private boolean toggling;
	private SpriteBatch batch;
	private OrthographicCamera ortho;
	private Night night;
	private Rectangle cameraToggleHitbox;

	public GameScreen(SpriteBatch batch)
	{
		this.batch = batch;
		this.ortho = new OrthographicCamera();
		this.ortho.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.night = Night.NIGHT_1;
		this.lookingAtCamera = false;
		this.cameraToggleHitbox = new Rectangle(255f, 27f, Art.cameraToggle.getWidth(), Art.cameraToggle.getHeight());
		this.toggling = false;
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

		batch.draw(Art.cameraToggle, 255f, 27f);

		for (Animatronic a : Animatronic.values())
		{
			a.getAI().update(a);
		}

		boolean collision = this.cameraToggleHitbox.contains(Gdx.input.getX(), MathStuff.reverseYCoords(Gdx.input.getY()));

		if (collision && !toggling)
		{
			toggling = true;

			if (!lookingAtCamera)
			{

			}
			else
			{

			}
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
