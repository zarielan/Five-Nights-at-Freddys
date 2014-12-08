package com.games.fnaf;

import com.badlogic.gdx.math.MathUtils;

public class FoxyAI extends AI
{
	private int stage;
	private float viewingTime;
	private float watchMeTime;
	private float nonViewingTime;

	public FoxyAI()
	{
		super();
		stage = 1;
		reset();
	}

	public void incrementViewingTime(float delta)
	{
		viewingTime += delta;
	}

	public void incrementNonViewingTime(float delta)
	{
		nonViewingTime += delta;
	}

	public void reset()
	{
		viewingTime = 0f;
		nonViewingTime = 0f;
		watchMeTime = 12 - ((1 / getFrequency() / 20) * 12) + 13;
	}

	public int getStage()
	{
		return stage;
	}

	@Override
	public void updatePosition(Animatronic anim)
	{
		System.out.print(anim.getName() + ": ");
		System.out.print(anim.getCurrentRoom().getName() + " -> ");

		//If Foxy is no longer at stages 1, 2, or 3, this means he's running. For you.
		if (3 < stage)
		{
			//Check if Bonnie is in the West Hall
			if (Room.WEST_HALL.getVisitors()[Animatronic.BONNIE.ordinal()])
			{
				//Move that guy out of the way to either the West Hall Corner, Supply Closet, or Dining Area
				Room putBonnieIn;
				switch (MathUtils.random(2))
				{
				case 0:
					putBonnieIn = Room.WEST_HALL_CORNER;
					break;
				case 1:
					putBonnieIn = Room.SUPPLY_CLOSET;
					break;
				case 2:
					putBonnieIn = Room.DINING_AREA;
					break;
				default:
					putBonnieIn = Room.SUPPLY_CLOSET;
					break;
				}

				//Clear the way for Foxy!
				Animatronic.BONNIE.setCurrentRoom(putBonnieIn);
			}

			//Good luck.
			anim.setCurrentRoom(Room.WEST_HALL);
		}

		System.out.print(anim.getCurrentRoom().getName());
		System.out.println();
	}
}
