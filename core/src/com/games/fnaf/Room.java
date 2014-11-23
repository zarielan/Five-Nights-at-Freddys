package com.games.fnaf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public enum Room
{
	SHOW_STAGE("ShowStage", new CameraButton("1A", Gdx.graphics.getWidth() - 260f, 360f)),
	PIRATE_COVE("PirateCove", new CameraButton("1C", Gdx.graphics.getWidth() - 320f, 225f)),
	DINING_AREA("DiningArea", new CameraButton("1B", Gdx.graphics.getWidth() - 280f, 304f));
	/*BACKSTAGE("Backstage"),
	RESTROOMS("Restrooms"),
	SUPPLY_CLOSET("SupplyCloset"),
	WEST_HALL("WestHall"),
	WEST_HALL_CORNER("WestHallCor"),
	EAST_HALL("EastHall"),
	EAST_HALL_CORNER("EastHallCor");*/

	private final String name;
	private boolean[] visitors;
	private Texture texture;
	private float cameraX = 0f, xVel = 0f;
	private boolean isCameraMoving = true;
	private float timePassed = 0f;
	private CameraButton camButton;
	private Array<String> multiplePos;

	private Room(String name, CameraButton camButton)
	{
		this.name = name;
		this.visitors = new boolean[4];
		texture = null;
		this.camButton = camButton;
		cameraX = MathUtils.random(-320f, 0f);
		xVel = 2f * (float)MathUtils.randomSign();

		//Rooms that have multiple positions in them. Yay for hardcoded values! :D
		multiplePos = new Array<String>();
		multiplePos.add("DiningAreaBonnie");
		multiplePos.add("DiningAreaChica");
	}

	public CameraButton getCamButton()
	{
		return camButton;
	}

	//Move camera left and right
	public void updateCameraValues()
	{
		if (timePassed >= 3f && !isCameraMoving)
		{
			isCameraMoving = true;
		}

		if (isCameraMoving)
		{
			timePassed = 0f;
			cameraX += xVel;

			if ((xVel == -2f && cameraX + getTexture().getWidth() <= 1280f) || (xVel == 2f && cameraX >= 0f))
			{
				xVel *= -1f;
				isCameraMoving = false;
			}
		}

		if (!isCameraMoving)
		{
			timePassed += Gdx.graphics.getDeltaTime();
		}
	}

	public float getCameraX()
	{
		return cameraX;
	}

	//Gets texture dependent on its name hehe
	private Texture getTexture()
	{
		StringBuilder args = new StringBuilder(name);
		for (int i = 0; i < 4; i++)
		{
			if (getVisitors()[i])
			{
				args.append(Animatronic.values()[i].getName());
			}
		}

		String fName = args.toString();

		for (String s : multiplePos)
		{
			if (s.equals(fName))
			{
				fName = fName.concat(",2");
				break;
			}
		}

		Texture tex = Art.getRoomTexture(fName);
		if (tex == null)
		{
			System.err.println(fName + " doesn't exist.");
			return null;
		}

		return tex;
	}

	public Texture getCurrentTexture()
	{
		if (texture == null)
		{
			texture = getTexture();
		}

		return texture;
	}

	public void setVisiting(boolean visit, int ID)
	{
		visitors[ID] = visit;
		texture = getTexture();
	}

	public boolean[] getVisitors()
	{
		return visitors;
	}
}
