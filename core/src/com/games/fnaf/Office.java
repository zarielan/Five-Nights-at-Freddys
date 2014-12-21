package com.games.fnaf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Office
{
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private final float SIGHT_MOVEMENT = 400f;
	private Animation officeFan, leftDoor, rightDoor;
	private DoorLights doorLights;
	private boolean[] playDoorAnimation;
	private boolean[] previousDoorShut;

	public Office(SpriteBatch batch)
	{
		this.batch = batch;
		this.camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		officeFan = new Animation(1/30f, Art.officeFan);
		officeFan.setPlayMode(Animation.PlayMode.LOOP);
		doorLights = new DoorLights(batch, camera);

		leftDoor = new Animation(1/30f, Art.leftDoor);
		leftDoor.setPlayMode(Animation.PlayMode.NORMAL);
		rightDoor = new Animation(1/30f, Art.rightDoor);
		rightDoor.setPlayMode(Animation.PlayMode.NORMAL);

		playDoorAnimation = new boolean[]{false, false};
		previousDoorShut = new boolean[]{false, false};
	}

	public void render()
	{
		batch.setProjectionMatrix(camera.combined);
		camera.update();

		batch.draw(Art.officeTextures.get("Office"), -160, 0f);
		renderLightsOverlay();
		renderDoors();
		batch.draw(officeFan.getKeyFrame(FNaF.getTimeElapsed()), 620f, 221f); //these x,y values are merely from trial and error xD
		doorLights.render();

		float step = Gdx.graphics.getDeltaTime() * SIGHT_MOVEMENT;
		//I honestly don't have any idea where I got 480 and 800, something with 640 +- 160? Wut? o.O
		if (Gdx.input.getX() <= 200 && camera.position.x - step >= 480)
		{
			camera.position.x -= step;
		}
		if (Gdx.input.getX() >= Gdx.graphics.getWidth() - 200 && camera.position.x + step <= 800)
		{
			camera.position.x += step;
		}
	}

	private void renderDoors()
	{
		if (doorLights.isLeftDoor())
			batch.draw(Art.leftDoor.peek().getTexture(), -100f, 0f);
		if (doorLights.isRightDoor())
			batch.draw(Art.rightDoor.peek().getTexture(), 1080f, 0f);
	}

	private void renderLightsOverlay()
	{
		if (doorLights.isLeftLight())
		{
			if (Room.OFFICE.getVisitors()[Animatronic.BONNIE.ordinal()])
				batch.draw(Art.officeTextures.get("OfficeBonnie"), -160f, 0f);
			else
				batch.draw(Art.officeTextures.get("OfficeLeftLight"), -160f, 0f);
		}
		if (doorLights.isRightLight())
		{
			if (Room.OFFICE.getVisitors()[Animatronic.CHICA.ordinal()])
				batch.draw(Art.officeTextures.get("OfficeChica"), -160f, 0f);
			else
				batch.draw(Art.officeTextures.get("OfficeRightLight"), -160f, 0f);
		}
	}
}
