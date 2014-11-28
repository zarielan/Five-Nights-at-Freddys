package com.games.fnaf;

public abstract class AI
{
	private int FREQUENCY;
	private final float TIME = 90f;
	private final float MOVEMENT_TIME = 30f;
	private float FREQ_MOVEMENT;
	private int timeElapsed;
	private int timer;
	private boolean isMoving;

	public AI()
	{
		FREQUENCY = -1;
		FREQ_MOVEMENT = MOVEMENT_TIME / (float)FREQUENCY;
		timer = 0;
		isMoving = false;
	}

	public void setFrequency(int i)
	{
		this.FREQUENCY = i;
	}

	public void setMoving(boolean bool)
	{
		isMoving = bool;
	}

	public boolean isMoving()
	{
		return isMoving;
	}

	public final void update()
	{
		if (isMoving && (int)(FNaF.getTimeElapsed() / FREQ_MOVEMENT) == timer)
		{
			timer++;
			updatePosition();
		}
	}

	public abstract void updatePosition();

	public int getFrequency()
	{
		return FREQUENCY;
	}
}
