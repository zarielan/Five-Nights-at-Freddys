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
	private boolean isDoorShut;

	public FoxyAI()
	{
		super();
		stage = 1;
		reset();
		cooldownTime = 0f;
		isDoorShut = false;
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

	public void setDoorShut(boolean bool)
	{
		isDoorShut = bool;
	}

	public void reset()
	{
		viewingTime = 0f;
		nonViewingTime = 0f;
		watchMeTime = 12f - ((getFrequency() / 20f) * 12f) + 13f;
		cooldownTime = 0f;
	}

	public int getStage()
	{
		return stage;
	}

	public float getCooldown()
	{
		return cooldownTime;
	}

	public static FoxyAI getInstance()
	{
		return (FoxyAI)Animatronic.FOXY.getAI();
	}

	public void doneSprinting()
	{
		//Done sprinting?
		//Is the door shut?
		if (isDoorShut)
		{
			//Go back.
			Animatronic.FOXY.setCurrentRoom(Room.PIRATE_COVE);
			stage = MathUtils.randomBoolean(0.90f) ? 1 : 2;
			reset();
		}
		else
		{
			//Check if someone's already inside
			for (int i = 0; i < 4; i++)
			{
				//Yes? Don't go in anymore.
				if (Room.JUMPSCARE_TIME.getVisitors()[i])
				{
					return;
				}
			}

			//Move in!
			Animatronic.FOXY.setCurrentRoom(Room.JUMPSCARE_TIME);
		}
	}

	@Override
	public void updatePosition(Animatronic anim)
	{
		if (anim.getCurrentRoom() == Room.JUMPSCARE_TIME)
			return;

		//System.out.println("Viewing time: " + getViewingTime() + ", Non viewing time: " + getNonViewingTime());
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

			if (cooldownTime >= 10f)
			{
				doneSprinting();
				return;
			}

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

			//Check if Bonnie is at the door
			if (Room.OFFICE.getVisitors()[Animatronic.BONNIE.ordinal()])
			{
				//Move him out of the way!
				Room bonnieMovesAt = Room.WEST_HALL_CORNER;

				//If by some random boolean, and the left door is open...
				if (MathUtils.randomBoolean() && !isDoorShut)
				{
					//Move him in instead..
					bonnieMovesAt = Room.JUMPSCARE_TIME;
				}

				//Go!
				Animatronic.BONNIE.setCurrentRoom(bonnieMovesAt);
			}
		}
	}
}
