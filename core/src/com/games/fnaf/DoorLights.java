package com.games.fnaf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector3;

public class DoorLights
{
	private final int RIGHT_DOOR_HITBOX = 0;
	private final int RIGHT_LIGHT_HITBOX = 1;
	private final int LEFT_DOOR_HITBOX = 2;
	private final int LEFT_LIGHT_HITBOX = 3;
	private final Polygon[] HITBOXES;
	private boolean leftDoor;
	private boolean leftLight;
	private boolean rightDoor;
	private boolean rightLight;
	private SpriteBatch batch;
	private Texture rightDoorLight, leftDoorLight;
	private OrthographicCamera camera;
	private Vector3 mouseCoords;

	public DoorLights(SpriteBatch batch1, OrthographicCamera cam)
	{
		HITBOXES = new Polygon[4];
		HITBOXES[RIGHT_DOOR_HITBOX] = new Polygon(new float[]{1600 - 40 - 160, 350, 1600 - 40 - 160, 402, 1600 - 78 - 160, 400, 1600 - 78 - 160, 348});
		HITBOXES[RIGHT_LIGHT_HITBOX] = new Polygon(new float[]{1600 - 38 - 160, 350 - 80, 1600 - 38 - 160, 402 - 80, 1600 - 75 - 160, 401 - 80, 1600 - 75 - 160, 349 - 80});
		HITBOXES[LEFT_DOOR_HITBOX] = new Polygon(new float[]{27 - 160, 350, 27 - 160, 403, 67 - 160, 400, 67 - 160, 347});
		HITBOXES[LEFT_LIGHT_HITBOX] = new Polygon(new float[]{27 - 160, 350 - 80, 27 - 160, 402 - 80, 67 - 160, 401 - 80, 67 - 160, 349 - 80});

		leftDoor = false;
		leftLight = false;
		rightDoor = false;
		rightLight = false;

		batch = batch1;
		camera = cam;
		rightDoorLight = getDoorLightTexture(1);
		leftDoorLight = getDoorLightTexture(0);

		mouseCoords = new Vector3(Gdx.input.getX(), MathStuff.reverseYCoords(Gdx.input.getY()), 0f);
	}

	public void render()
	{
		batch.draw(rightDoorLight, 1600f - 160f - rightDoorLight.getWidth() - 24f, 240f);
		batch.draw(leftDoorLight, 12f - 160f, 240f);

		mouseCoords.set(Gdx.input.getX(), Gdx.input.getY(), 0f);
		mouseCoords = camera.unproject(mouseCoords);
		System.out.println(mouseCoords);

		for (Polygon poly : HITBOXES)
		{
			boolean collision = poly.contains(mouseCoords.x, mouseCoords.y);
			if (collision) System.out.println("HITBOX ON " + poly + ", " + FNaF.getTimeElapsed());
		}
	}

	private Texture getDoorLightTexture(int i)
	{
		StringBuilder doorLightFName;

		switch (i)
		{
		//Left doorlight
		case 0:
			doorLightFName = new StringBuilder("Left");
			if (leftDoor)
				doorLightFName.append("Door");
			if (leftLight)
				doorLightFName.append("Light");
			return Art.doorLights.get(doorLightFName.toString());
		//Right doorlight
		case 1:
			doorLightFName = new StringBuilder("Right");
			if (rightDoor)
				doorLightFName.append("Door");
			if (rightLight)
				doorLightFName.append("Light");
			return Art.doorLights.get(doorLightFName.toString());
		default:
			return null;
		}
	}
}
