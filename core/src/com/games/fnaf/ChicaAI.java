package com.games.fnaf;

import com.badlogic.gdx.math.MathUtils;

public class ChicaAI extends AI
{
	public ChicaAI()
	{
		super();
		allowedRooms.add(Room.DINING_AREA);
		allowedRooms.add(Room.SHOW_STAGE);
		allowedRooms.add(Room.RESTROOMS);
		allowedRooms.add(Room.EAST_HALL);
		allowedRooms.add(Room.EAST_HALL_CORNER);
	}

	@Override
	public void updatePosition(Animatronic anim)
	{
		System.out.println(anim.getName());
		System.out.println("Currently in: " + anim.getCurrentRoom().getName());
		anim.setCurrentRoom(allowedRooms.get(MathUtils.random(0, allowedRooms.size - 1)));
		System.out.println("Now in: " + anim.getCurrentRoom().getName());
	}
}
