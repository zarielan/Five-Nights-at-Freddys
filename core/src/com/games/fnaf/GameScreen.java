package com.games.fnaf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class GameScreen extends ScreenAdapter
{
	private Camera camera;
	private Office office;
	private boolean lookingAtCamera;
	private boolean toggling;
	private boolean hasCollided;
	private SpriteBatch batch;
	private OrthographicCamera ortho;
	private Night night;
	private Rectangle cameraToggleHitbox;
	private Animation cameraToggle;
	private float animStateTime = 0f;

	public GameScreen(SpriteBatch batch)
	{
		this.batch = batch;
		this.ortho = new OrthographicCamera();
		this.ortho.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.night = Night.NIGHT_1;
		this.lookingAtCamera = false;
		this.cameraToggleHitbox = new Rectangle(255f, 27f, Art.cameraToggle.getWidth(), Art.cameraToggle.getHeight());
		this.toggling = false;
		this.cameraToggle = new Animation(1/15f, Art.cameraPopup);
		this.hasCollided = false;
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

		if (!hasCollided && collision && !toggling)
		{
			toggling = true;

			if (!lookingAtCamera)
			{
				this.cameraToggle.setPlayMode(Animation.PlayMode.NORMAL);
			}
			else
			{
				this.cameraToggle.setPlayMode(Animation.PlayMode.REVERSED);
			}
		}

		if (toggling)
		{
			animStateTime += delta;

			if (this.cameraToggle.isAnimationFinished(animStateTime))
			{
				toggling = false;
				lookingAtCamera = !lookingAtCamera;
			}

			batch.draw(cameraToggle.getKeyFrame(animStateTime), 0f, 0f, 1280f, 720f);
		}
		else
		{
			animStateTime = 0f;
		}

		hasCollided = collision;
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
