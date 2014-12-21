package com.games.fnaf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
	private boolean mouseClicked;

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
		rightDoorLight = getLightTexture(1);
		leftDoorLight = getLightTexture(0);

		mouseCoords = new Vector3(Gdx.input.getX(), MathStuff.reverseYCoords(Gdx.input.getY()), 0f);
		mouseClicked = false;
	}

	public void render()
	{
		rightDoorLight = getLightTexture(1);
		leftDoorLight = getLightTexture(0);

		batch.draw(rightDoorLight, 1600f - 160f - rightDoorLight.getWidth() - 24f, 240f);
		batch.draw(leftDoorLight, 12f - 160f, 240f);

		mouseCoords.set(Gdx.input.getX(), Gdx.input.getY(), 0f);
		mouseCoords = camera.unproject(mouseCoords);
		//System.out.println(mouseCoords);

		for (int i = 0; i < HITBOXES.length; i++)
		{
			Polygon poly = HITBOXES[i];
			boolean collision = poly.contains(mouseCoords.x, mouseCoords.y);
			if (collision)
			{
				if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && !mouseClicked)
				{
					mouseClicked = true;
					switch (i)
					{
					case LEFT_LIGHT_HITBOX:
						leftLight = !leftLight;
						break;
					case LEFT_DOOR_HITBOX:
						leftDoor = !leftDoor;
						break;
					case RIGHT_LIGHT_HITBOX:
						rightLight = !rightLight;
						break;
					case RIGHT_DOOR_HITBOX:
						rightDoor = !rightDoor;
						break;
					}
				}
			}
		}

		mouseClicked = Gdx.input.isButtonPressed(Input.Buttons.LEFT);
	}

	public boolean isRightLight()
	{
		return rightLight;
	}

	public boolean isLeftDoor()
	{
		return leftDoor;
	}

	public boolean isLeftLight()
	{
		return leftLight;
	}

	public boolean isRightDoor()
	{
		return rightDoor;
	}

	private Texture getLightTexture(int i)
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
