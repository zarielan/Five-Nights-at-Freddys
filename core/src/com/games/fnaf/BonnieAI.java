package com.games.fnaf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

public class BonnieAI extends AI
{
	private float doorCounter;
	private boolean isOnDoor;
	private boolean isDoorShut;

	public BonnieAI()
	{
		super();
		allowedRooms.put(Room.SHOW_STAGE, new Room[]{Room.DINING_AREA, Room.SHOW_STAGE});
		allowedRooms.put(Room.DINING_AREA, new Room[]{Room.DINING_AREA, Room.BACKSTAGE, Room.WEST_HALL});
		allowedRooms.put(Room.BACKSTAGE, new Room[]{Room.BACKSTAGE, Room.DINING_AREA});
		allowedRooms.put(Room.WEST_HALL, new Room[]{Room.WEST_HALL, Room.WEST_HALL_CORNER, Room.SUPPLY_CLOSET, Room.DINING_AREA});
		allowedRooms.put(Room.SUPPLY_CLOSET, new Room[]{Room.SUPPLY_CLOSET, Room.WEST_HALL});
		allowedRooms.put(Room.WEST_HALL_CORNER, new Room[]{Room.WEST_HALL_CORNER, /*Room.WEST_HALL,*/ Room.OFFICE}); //TEMPORARY
		allowedRooms.put(Room.OFFICE, new Room[]{Room.OFFICE, Room.WEST_HALL_CORNER});
		doorCounter = 0f;
		isOnDoor = false;
		isDoorShut = false;
	}

	public boolean isOnDoor()
	{
		return isOnDoor;
	}

	@Override
	public void updatePosition(Animatronic anim)
	{
		System.out.println(doorCounter);
		if (!isOnDoor || isDoorShut)
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

			if (possibleRooms[chosen] == Room.OFFICE)
			{
				doorCounter = 0f;
				isOnDoor = true;
			}

			anim.setCurrentRoom(possibleRooms[chosen]);
		}
		else
		{
			doorCounter += Gdx.graphics.getDeltaTime();

			//Check if Bonnie has been at the door for more than 8 seconds
			if (doorCounter >= 8f)
			{
				//Check if someone's currently inside the office
				for (int i = 0; i < 4; i++)
				{
					if (Room.JUMPSCARE_TIME.getVisitors()[i])
					{
						return;
					}

					//No one's there? Get in.
					anim.setCurrentRoom(Room.JUMPSCARE_TIME);
				}
			}
		}
	}

	public void setDoorShut(boolean bool)
	{
		isDoorShut = bool;
	}

	public static BonnieAI getInstance()
	{
		return (BonnieAI)Animatronic.BONNIE.getAI();
	}
}
