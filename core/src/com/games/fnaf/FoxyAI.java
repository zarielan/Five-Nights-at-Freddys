package com.games.fnaf;

public class FoxyAI extends AI
{
	private int stage;

	public FoxyAI()
	{
		super();
		stage = 4;
	}

	public int getStage()
	{
		return stage;
	}

	@Override
	public void updatePosition(Animatronic anim)
	{
		System.out.println(anim.getName());
	}
}
