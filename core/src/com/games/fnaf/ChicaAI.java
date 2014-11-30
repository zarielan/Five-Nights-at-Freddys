package com.games.fnaf;

import com.badlogic.gdx.math.MathUtils;

public class ChicaAI extends AI
{
	public ChicaAI()
	{
		super();
		allowedRooms.put(Room.SHOW_STAGE, new Room[] {Room.DINING_AREA});
		allowedRooms.put(Room.DINING_AREA, new Room[]{Room.RESTROOMS, Room.EAST_HALL, Room.KITCHEN});
		allowedRooms.put(Room.KITCHEN, new Room[]{Room.DINING_AREA});
		allowedRooms.put(Room.RESTROOMS, new Room[]{Room.DINING_AREA});
		allowedRooms.put(Room.EAST_HALL, new Room[]{Room.DINING_AREA, Room.EAST_HALL_CORNER});
		allowedRooms.put(Room.EAST_HALL_CORNER, new Room[]{Room.EAST_HALL});
	}

	@Override
	public void updatePosition(Animatronic anim)
	{
		System.out.println(anim.getName());
		System.out.println("Currently in: " + anim.getCurrentRoom().getName());

		Room[] possibleRooms = allowedRooms.get(anim.getCurrentRoom());
		int chosen = MathUtils.random(0, possibleRooms.length - 1);
		anim.setCurrentRoom(possibleRooms[chosen]);

		System.out.println("Now in: " + anim.getCurrentRoom().getName());
	}
}
