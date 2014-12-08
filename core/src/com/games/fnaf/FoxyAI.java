package com.games.fnaf;

import com.badlogic.gdx.math.MathUtils;

public class FoxyAI extends AI
{
	public FoxyAI()
	{
		super(Animatronic.FOXY);
	}

	@Override
	public float getMovementOffset()
	{
		return MathUtils.random(-10f, 0f);
	}

	@Override
	public void updatePosition(Animatronic anim)
	{
		System.out.println(anim.getName());
	}
}
