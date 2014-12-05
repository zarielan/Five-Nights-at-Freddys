package com.games.fnaf;

import com.badlogic.gdx.utils.ArrayMap;

public abstract class AI
{
	private int frequency;
	private final float TIME = 90f;
	private final float MOVEMENT_TIME = 30f;
	private float freqMovement;
	private int timer;
	private boolean isMoving;
	protected ArrayMap<Room, Room[]> allowedRooms;

	public AI(int startingTimer)
	{
		frequency = -1;
		freqMovement = MOVEMENT_TIME / (float)frequency;
		timer = startingTimer;
		isMoving = false;
		allowedRooms = new ArrayMap<Room, Room[]>();
	}

	public void setFrequency(int i)
	{
		this.frequency = i;
		this.freqMovement = MOVEMENT_TIME / (float)frequency;
	}

	public void setMoving(boolean bool)
	{
		isMoving = bool;
	}

	public final void update(Animatronic anim)
	{
		if (isMoving && (int)(FNaF.getTimeElapsed() / freqMovement) == timer)
		{
			if (timer > 0)
				updatePosition(anim);
			timer++;
		}
	}

	public abstract void updatePosition(Animatronic anim);
}
