package com.games.fnaf;

import com.badlogic.gdx.math.MathUtils;

public class BonnieAI extends AI
{
	public BonnieAI()
	{
		super();
		allowedRooms.put(Room.SHOW_STAGE, new Room[]{Room.DINING_AREA, Room.SHOW_STAGE});
		allowedRooms.put(Room.DINING_AREA, new Room[]{Room.DINING_AREA, Room.BACKSTAGE, Room.WEST_HALL});
		allowedRooms.put(Room.BACKSTAGE, new Room[]{Room.BACKSTAGE, Room.DINING_AREA});
		allowedRooms.put(Room.WEST_HALL, new Room[]{Room.WEST_HALL, Room.WEST_HALL_CORNER, Room.SUPPLY_CLOSET, Room.DINING_AREA});
		allowedRooms.put(Room.SUPPLY_CLOSET, new Room[]{Room.SUPPLY_CLOSET, Room.WEST_HALL});
		allowedRooms.put(Room.WEST_HALL_CORNER, new Room[]{Room.WEST_HALL_CORNER, Room.WEST_HALL, Room.OFFICE});
		allowedRooms.put(Room.OFFICE, new Room[]{Room.OFFICE, Room.WEST_HALL_CORNER});
	}

	@Override
	public void updatePosition(Animatronic anim)
	{
		Room[] possibleRooms = allowedRooms.get(anim.getCurrentRoom());
		int chosen = MathUtils.random(0, possibleRooms.length - 1);

		//Check if Bonnie is going to the West Hall
		if (possibleRooms[chosen] == Room.WEST_HALL)
		{
			//Check if Foxy's sprinting there
			if (Room.WEST_HALL.getVisitors()[Animatronic.FOXY.ordinal()])
			{
				//If yes, get out of the way!
				System.out.println("Bonnie, don't move.");
				return;
			}
		}

		anim.setCurrentRoom(possibleRooms[chosen]);
	}
}
