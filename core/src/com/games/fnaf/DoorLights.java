package com.games.fnaf;

import com.badlogic.gdx.math.Polygon;

public class DoorLights
{
	private final Polygon RIGHT_DOOR_HITBOX;
	private final Polygon RIGHT_LIGHT_HITBOX;
	private final Polygon LEFT_DOOR_HITBOX;
	private final Polygon LEFT_LIGHT_HITBOX;

	public DoorLights()
	{
		RIGHT_DOOR_HITBOX = new Polygon();
		RIGHT_LIGHT_HITBOX = new Polygon();
		LEFT_DOOR_HITBOX = new Polygon(new float[]{27 - 160, 350, 27 - 160, 403, 67 - 160, 400, 67 - 160, 347});
		LEFT_LIGHT_HITBOX = new Polygon(new float[]{27 - 160, 350 - 80, 27 - 160, 402 - 80, 67 - 160, 401 - 80, 67 - 160, 349 - 80});
	}

	public void render()
	{

	}
}
