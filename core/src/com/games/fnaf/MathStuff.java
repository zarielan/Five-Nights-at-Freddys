package com.games.fnaf;

import com.badlogic.gdx.Gdx;

public class MathStuff
{
	public static float reverseYCoords(float y)
	{
		return Math.abs(y - Gdx.graphics.getHeight());
	}
}
