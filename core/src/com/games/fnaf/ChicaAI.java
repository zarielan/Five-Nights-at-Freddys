package com.games.fnaf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

public class ChicaAI extends AI
{
	private boolean isDoorShut;
	private float waitCounter;

	public ChicaAI()
	{
		super();
		allowedRooms.put(Room.SHOW_STAGE, new Room[] {Room.SHOW_STAGE, Room.DINING_AREA});
		allowedRooms.put(Room.DINING_AREA, new Room[]{Room.DINING_AREA, Room.RESTROOMS, Room.EAST_HALL, Room.KITCHEN});
		allowedRooms.put(Room.KITCHEN, new Room[]{Room.KITCHEN, Room.DINING_AREA});
		allowedRooms.put(Room.RESTROOMS, new Room[]{Room.RESTROOMS, Room.DINING_AREA});
		allowedRooms.put(Room.EAST_HALL, new Room[]{Room.EAST_HALL, Room.DINING_AREA, Room.EAST_HALL_CORNER});
		allowedRooms.put(Room.EAST_HALL_CORNER, new Room[]{Room.EAST_HALL_CORNER, Room.EAST_HALL, Room.OFFICE});
		allowedRooms.put(Room.OFFICE, new Room[]{Room.OFFICE, Room.EAST_HALL_CORNER});
		isDoorShut = false;
		waitCounter = 0f;
	}

	public void setDoorShut(boolean bool)
	{
		isDoorShut = bool;
	}

	@Override
	public void updatePosition(Animatronic anim)
	{
		Room[] possibleRooms = allowedRooms.get(anim.getCurrentRoom());
		int chosen = MathUtils.random(0, possibleRooms.length - 1);

		//Check if Chica is going to the East Hall
		if (possibleRooms[chosen] == Room.EAST_HALL)
		{
			//Check if Freddy is there...
			if (Room.EAST_HALL.getVisitors()[Animatronic.FREDDY.ordinal()])
			{
				//Chica has a 25% chance of moving in and shoving Freddy out
				boolean chicaMovesIn = MathUtils.randomBoolean(0.25f);

				//If chica moves in...
				if (chicaMovesIn)
				{
					//Set Freddy to go to either the Dining Area or East Hall Corner
					Room freddyMovesAt;

					//If Chica isn't at the East Hall Corner and a random boolean...
					if (anim.getCurrentRoom() != Room.EAST_HALL_CORNER && MathUtils.randomBoolean())
					{
						//Put him there...
						freddyMovesAt = Room.EAST_HALL_CORNER;
					}
					else
					{
						//Else? Put him back in the Dining Area
						freddyMovesAt = Room.DINING_AREA;
					}

					System.out.println("Freddy, make way for Chica.");
					Animatronic.FREDDY.setCurrentRoom(freddyMovesAt);
				}
				else
				{
					//Else? Don't move at all. Let things be...
					System.out.println("Chica, don't move.");
					return;
				}
			}
		}

		//Check if Chica is headed to the East Hall Corner
		if (possibleRooms[chosen] == Room.EAST_HALL_CORNER)
		{
			//Check if Freddy is there...
			if (Room.EAST_HALL_CORNER.getVisitors()[Animatronic.FREDDY.ordinal()])
			{
				//Have a random boolean with 25% chance of Chica shoving him out
				boolean chicaMovesIn = MathUtils.randomBoolean(0.25f);

				//If Chica moves to the East Hall Corner
				if (chicaMovesIn)
				{
					//Send Freddy back
					//TODO Freddy goes to either the East Hall or in the Office
					System.out.println("Freddy, make way for Chica.");
					Animatronic.FREDDY.setCurrentRoom(Room.EAST_HALL);
				}
				else
				{
					//Else? Don't move.
					System.out.println("Chica, don't move.");
					return;
				}
			}
		}

		//If Chica is at the Office
		if (anim.getCurrentRoom() == Room.OFFICE)
		{
			//And the door is open...
			if (!isDoorShut)
			{
				//Move in
				anim.setCurrentRoom(Room.JUMPSCARE_TIME);
				return;
			}
		}

		//If she's about to move into the office
		if (possibleRooms[chosen] == Room.OFFICE)
		{
			//And she wasn't there before...
			if (anim.getCurrentRoom() != Room.OFFICE)
			{
				//Reset the wait counter.
				waitCounter = 0f;
			}
		}

		anim.setCurrentRoom(possibleRooms[chosen]);
	}

	public void updateDoorCounter(Animatronic anim)
	{
		//System.out.println(waitCounter);

		//Wait when the door is open
		if (!isDoorShut)
		{
			waitCounter += Gdx.graphics.getDeltaTime();
		}

		//If you've been waiting for more than 8 seconds
		if (waitCounter > 8f)
		{
			//Move in
			anim.setCurrentRoom(Room.JUMPSCARE_TIME);
		}
	}

	public static ChicaAI getInstance()
	{
		return (ChicaAI)Animatronic.CHICA.getAI();
	}
}
