package com.games.fnaf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ArrayMap;

public abstract class AI
{
	private int frequency;
	private final float TIME = 90f;
	private final float MOVEMENT_TIME = 30f;
	private float freqMovement;
	private int timer;
	private boolean isMoving;
	private float movementOffset;
	protected ArrayMap<Room, Room[]> allowedRooms;

	public AI()
	{
		frequency = -1;
		freqMovement = MOVEMENT_TIME / (float)frequency;
		allowedRooms = new ArrayMap<Room, Room[]>();
	}

	public void reset(float offset)
	{
		timer = 0;
		isMoving = false;
		movementOffset = offset;
	}

	public void setMovementDelay(float movementDelay)
	{
		this.movementOffset -= movementDelay;
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
		if (movementOffset >= 0 && isMoving && (int)(FNaF.getTimeElapsed() / freqMovement) == timer)
		{
			if (timer > 0)
				updatePosition(anim);
			timer++;
		}

		if (movementOffset <= 10)
		{
			movementOffset += Gdx.graphics.getDeltaTime();
		}
	}

	public abstract void updatePosition(Animatronic anim);
}
