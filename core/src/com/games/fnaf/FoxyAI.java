package com.games.fnaf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

public class FoxyAI extends AI
{
	private int stage;
	private float viewingTime;
	private float watchMeTime;
	private float nonViewingTime;
	private float cooldownTime;

	public FoxyAI()
	{
		super();
		stage = 1;
		reset();
		cooldownTime = 0f;
	}

	public void setViewingTime(float time)
	{
		viewingTime = time;
	}

	public void setNonViewingTime(float time)
	{
		nonViewingTime = time;
	}

	public float getViewingTime()
	{
		return viewingTime;
	}

	public float getNonViewingTime()
	{
		return nonViewingTime;
	}

	public void reset()
	{
		viewingTime = 0f;
		nonViewingTime = 0f;
		watchMeTime = 12 - ((getFrequency() / 20) * 12) + 13;
		System.out.println("Foxy's watching time is " + watchMeTime);
	}

	public int getStage()
	{
		return stage;
	}

	@Override
	public void updatePosition(Animatronic anim)
	{
		System.out.println("Viewing time: " + getViewingTime() + ", Non viewing time: " + getNonViewingTime());
		if (nonViewingTime > watchMeTime || viewingTime > watchMeTime)
		{
			System.out.print(anim.getName() + ": Stage " + getStage());

			stage++;
			if (stage <= 3)
				Room.PIRATE_COVE.changeRoomTexture();

			System.out.print(" -> Stage " + getStage());
			System.out.println();
			nonViewingTime = 0f;
			viewingTime = 0f;
		}

		//If Foxy is no longer at stages 1, 2, or 3, this means he's running. For you.
		if (3 < stage)
		{
			cooldownTime += Gdx.graphics.getDeltaTime();

			//Check if Bonnie is in the West Hall and Foxy isn't there yet
			if (!Room.WEST_HALL.getVisitors()[Animatronic.FOXY.ordinal()] && Room.WEST_HALL.getVisitors()[Animatronic.BONNIE.ordinal()])
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
			if (anim.getCurrentRoom() != Room.WEST_HALL)
				anim.setCurrentRoom(Room.WEST_HALL);
		}
	}
}
