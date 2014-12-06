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

		//The other two (Bonnie and Chica) needs to get off the show stage first before Freddy comes out
		if (anim.getCurrentRoom() == Room.SHOW_STAGE)
		{
			if (Room.SHOW_STAGE.getVisitors()[Animatronic.BONNIE.ordinal()] && !Room.SHOW_STAGE.getVisitors()[Animatronic.CHICA.ordinal()])
			{
				anim.setCurrentRoom(Room.DINING_AREA);
			}
		}
		else
		{
			Room[] possibleRooms = allowedRooms.get(anim.getCurrentRoom());
			int chosen = MathUtils.random(0, possibleRooms.length - 1);

			//Checks if Freddy is in the East Hall and will be walking towards the East Hall Corner
			if (anim.getCurrentRoom() == Room.EAST_HALL && possibleRooms[chosen] == Room.EAST_HALL_CORNER)
			{
				//Checks if Chica is in the East Hall Corner...
				if (Room.EAST_HALL_CORNER.getVisitors()[Animatronic.CHICA.ordinal()])
				{
					//Randomly choses who goes
					boolean freddyMoves = MathUtils.randomBoolean();
					if (freddyMoves)
					{
						Animatronic.CHICA.setCurrentRoom(Room.EAST_HALL);
					}
				}
			}

			anim.setCurrentRoom(possibleRooms[chosen]);
		}

		System.out.print(anim.getCurrentRoom().getName());
		System.out.println();
	}
}
