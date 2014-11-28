package com.games.fnaf;

public abstract class AI
{
	public int frequency;
	public final float TIME = 90f;
	public final float MOVEMENT_TIME = 30f;
	public float freqMovement;
	public int timer;
	public boolean isMoving;

	public AI()
	{
		frequency = -1;
		freqMovement = MOVEMENT_TIME / (float)frequency;
		timer = 0;
		isMoving = false;
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

	public final void update()
	{
		if (isMoving && (int)(FNaF.getTimeElapsed() / freqMovement) == timer)
		{
			timer++;
			updatePosition();
		}
	}

	public abstract void updatePosition();
}
