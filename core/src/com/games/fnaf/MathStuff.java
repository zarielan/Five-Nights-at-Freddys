package com.games.fnaf;

import com.badlogic.gdx.Gdx;

public class MathStuff
{
	public static int reverseYCoords(float y)
	{
		return (int)Math.abs(y - Gdx.graphics.getHeight());
	}
}
