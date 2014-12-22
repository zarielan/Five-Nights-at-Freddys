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
	private float[] doorAnimCounter;

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
		doorAnimCounter = new float[]{0f, 0f};
	}

	public void render()
	{
		batch.setProjectionMatrix(camera.combined);
		camera.update();

		batch.draw(Art.officeTextures.get("Office"), -160, 0f);
		renderLightsOverlay();
		renderDoors();

		BonnieAI.getInstance().setDoorShut(doorLights.isLeftDoor());

		batch.draw(officeFan.getKeyFrame(FNaF.getTimeElapsed()), 620f, 221f); //these x,y values are merely from trial and error xD
		doorLights.render(playDoorAnimation);
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
		//LEFT DOOR
		if (!previousDoorShut[0])
		{
			if (doorLights.isLeftDoor())
			{
				previousDoorShut[0] = true;
				playDoorAnimation[0] = true;
				leftDoor.setPlayMode(Animation.PlayMode.NORMAL);
			}
		}
		else
		{
			if (!doorLights.isLeftDoor())
			{
				previousDoorShut[0] = false;
				playDoorAnimation[0] = true;
				leftDoor.setPlayMode(Animation.PlayMode.REVERSED);
			}
		}

		if (playDoorAnimation[0])
		{
			doorAnimCounter[0] += Gdx.graphics.getDeltaTime();

			if (leftDoor.isAnimationFinished(doorAnimCounter[0]))
			{
				playDoorAnimation[0] = false;
				doorAnimCounter[0] = 0f;
			}
			else
				batch.draw(leftDoor.getKeyFrame(doorAnimCounter[0]), -100f, 0f);
		}

		//RIGHT DOOR
		if (!previousDoorShut[1])
		{
			if (doorLights.isRightDoor())
			{
				previousDoorShut[1] = true;
				playDoorAnimation[1] = true;
				rightDoor.setPlayMode(Animation.PlayMode.NORMAL);
			}
		}
		else
		{
			if (!doorLights.isRightDoor())
			{
				previousDoorShut[1] = false;
				playDoorAnimation[1] = true;
				rightDoor.setPlayMode(Animation.PlayMode.REVERSED);
			}
		}

		if (playDoorAnimation[1])
		{
			doorAnimCounter[1] += Gdx.graphics.getDeltaTime();

			if (rightDoor.isAnimationFinished(doorAnimCounter[1]))
			{
				playDoorAnimation[1] = false;
				doorAnimCounter[1] = 0f;
			}
			else
				batch.draw(rightDoor.getKeyFrame(doorAnimCounter[1]), 1200f, 0f);
		}

		if (doorLights.isLeftDoor() && !playDoorAnimation[0])
			batch.draw(Art.leftDoor.peek(), -100f, 0f);
		if (doorLights.isRightDoor() && !playDoorAnimation[1])
			batch.draw(Art.rightDoor.peek(), 1200f, 0f);

		previousDoorShut[0] = doorLights.isLeftDoor();
		previousDoorShut[1] = doorLights.isRightDoor();
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
