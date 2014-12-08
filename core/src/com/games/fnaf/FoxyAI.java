package com.games.fnaf;

public class FoxyAI extends AI
{
	public FoxyAI()
	{
		super();
	}

	@Override
	public void updatePosition(Animatronic anim)
	{
		System.out.println(anim.getName());
	}
}
