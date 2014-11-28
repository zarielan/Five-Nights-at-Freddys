package com.games.fnaf;

public abstract class AI
{
	private final int FREQUENCY;
	private final float TIME = 90f;
	private final float MOVEMENT_TIME = 30f;
	private final float FREQ_MOVEMENT;
	private int timeElapsed;
	private int timer;

	public AI(int freq)
	{
		FREQUENCY = freq;
		FREQ_MOVEMENT = MOVEMENT_TIME / (float)FREQUENCY;
		timer = 0;
	}

	public final void update()
	{
		if ((int)(FNaF.getTimeElapsed() / FREQ_MOVEMENT) == timer)
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
