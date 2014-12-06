package com.games.fnaf;

import com.badlogic.gdx.math.MathUtils;

public class FreddyAI extends AI
{
	public FreddyAI()
	{
		super(MathUtils.random(-30, 0));
		allowedRooms.put(Room.SHOW_STAGE, new Room[]{Room.DINING_AREA});
		allowedRooms.put(Room.DINING_AREA, new Room[]{Room.RESTROOMS, Room.KITCHEN, Room.EAST_HALL});
		allowedRooms.put(Room.RESTROOMS, new Room[]{Room.DINING_AREA});
		allowedRooms.put(Room.KITCHEN, new Room[]{Room.DINING_AREA});
		allowedRooms.put(Room.EAST_HALL, new Room[]{Room.DINING_AREA, Room.EAST_HALL_CORNER});
		allowedRooms.put(Room.EAST_HALL_CORNER, new Room[]{Room.EAST_HALL});
	}

	@Override
	public void updatePosition(Animatronic anim)
	{
		System.out.println(anim.getName() + " is moving!:");
		System.out.println(anim.getCurrentRoom().getName() + "-> ");

		if (anim.getCurrentRoom() == Room.SHOW_STAGE)
		{
			if (Room.SHOW_STAGE.getVisitors()[0])
		}

		System.out.print(anim.getCurrentRoom().getName());
		System.out.println();
	}
}
