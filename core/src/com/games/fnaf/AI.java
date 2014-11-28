package com.games.fnaf;

import com.badlogic.gdx.utils.Array;

public abstract class AI
{
	private int frequency;
	private final float TIME = 90f;
	private final float MOVEMENT_TIME = 30f;
	private float freqMovement;
	private int timer;
	private boolean isMoving;
	protected Array<Room> allowedRooms;

	public AI()
	{
		frequency = -1;
		freqMovement = MOVEMENT_TIME / (float)frequency;
		timer = 0;
		isMoving = false;
		allowedRooms = new Array<Room>();
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
