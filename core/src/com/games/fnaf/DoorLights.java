package com.games.fnaf;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;

public class DoorLights
{
	private final Polygon RIGHT_DOOR_HITBOX;
	private final Polygon RIGHT_LIGHT_HITBOX;
	private final Polygon LEFT_DOOR_HITBOX;
	private final Polygon LEFT_LIGHT_HITBOX;
	private boolean leftDoor;
	private boolean leftLight;
	private boolean rightDoor;
	private boolean rightLight;
	private SpriteBatch batch;
	private Texture rightDoorLight, leftDoorLight;

	public DoorLights(SpriteBatch batch1)
	{
		RIGHT_DOOR_HITBOX = new Polygon();
		RIGHT_LIGHT_HITBOX = new Polygon();
		LEFT_DOOR_HITBOX = new Polygon(new float[]{27 - 160, 350, 27 - 160, 403, 67 - 160, 400, 67 - 160, 347});
		LEFT_LIGHT_HITBOX = new Polygon(new float[]{27 - 160, 350 - 80, 27 - 160, 402 - 80, 67 - 160, 401 - 80, 67 - 160, 349 - 80});

		leftDoor = false;
		leftLight = false;
		rightDoor = false;
		rightLight = false;

		batch = batch1;
		rightDoorLight = Art.doorLights.get("Right");
		leftDoorLight = Art.doorLights.get("Left");
	}

	public void render()
	{
		batch.draw(leftDoorLight, 12f - 160f, 240f);
		batch.draw(rightDoorLight, 1600f - 160f - rightDoorLight.getWidth() - 24f, 240f);
	}
}
