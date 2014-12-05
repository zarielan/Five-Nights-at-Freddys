package com.games.fnaf;

import com.badlogic.gdx.math.MathUtils;

public class BonnieAI extends AI
{
	public BonnieAI()
	{
		super(MathUtils.random(-30, 0));
		allowedRooms.put(Room.SHOW_STAGE, new Room[]{Room.DINING_AREA});
		allowedRooms.put(Room.DINING_AREA, new Room[]{Room.BACKSTAGE, Room.WEST_HALL});
		allowedRooms.put(Room.BACKSTAGE, new Room[]{Room.DINING_AREA});
		allowedRooms.put(Room.WEST_HALL, new Room[]{Room.WEST_HALL_CORNER, Room.SUPPLY_CLOSET, Room.DINING_AREA});
		allowedRooms.put(Room.SUPPLY_CLOSET, new Room[]{Room.WEST_HALL});
		allowedRooms.put(Room.WEST_HALL_CORNER, new Room[]{Room.WEST_HALL});
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
