package com.games.fnaf;

public abstract class AI
{
	private final int FREQUENCY;
	private final float TIME = 90f;
	private final float MOVEMENT_TIME = 30f;
	private final float FREQ_MOVEMENT;
	private int timeElapsed;

	public AI(int freq)
	{
		FREQUENCY = freq;
		FREQ_MOVEMENT = MOVEMENT_TIME / (float)FREQUENCY;
	}

	public final void update(float delta)
	{

	}

	public abstract void updatePosition();

	public int getFrequency()
	{
		return FREQUENCY;
	}
}
