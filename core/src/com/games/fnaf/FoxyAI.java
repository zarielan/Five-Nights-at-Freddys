package com.games.fnaf;

import com.badlogic.gdx.math.MathUtils;

public class FoxyAI extends AI
{
	public FoxyAI()
	{
		super(MathUtils.random(-30, 0));
	}

	@Override
	public void updatePosition(Animatronic anim)
	{
		System.out.println(anim.getName());
	}
}
