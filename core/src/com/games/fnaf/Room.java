package com.games.fnaf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ArrayMap;

public enum Room
{
	SHOW_STAGE("ShowStage", new CameraButton("1A", Gdx.graphics.getWidth() - 260f, 360f), true),
	PIRATE_COVE("PirateCove", new CameraButton("1C", Gdx.graphics.getWidth() - 320f, 225f), true),
	DINING_AREA("DiningArea", new CameraButton("1B", Gdx.graphics.getWidth() - 280f, 304f), true),
	BACKSTAGE("Backstage", new CameraButton("5", Gdx.graphics.getWidth() - 390f, 280f), true),
	RESTROOMS("Restrooms", new CameraButton("7", Gdx.graphics.getWidth() - 55f, 275f), true),
	SUPPLY_CLOSET("SupplyCloset", new CameraButton("3", Gdx.graphics.getWidth() - 350f, 130f), false),
	WEST_HALL("WestHall", new CameraButton("2A", Gdx.graphics.getWidth() - 263f, 110f), true),
	WEST_HALL_CORNER("WestHallCor", new CameraButton("2B", Gdx.graphics.getWidth() - 263f, 68f), true),
	EAST_HALL("EastHall", new CameraButton("4A", Gdx.graphics.getWidth() - 160f, 110f), true),
	EAST_HALL_CORNER("EastHallCor", new CameraButton("4B", Gdx.graphics.getWidth() - 160f, 68f), true),
	KITCHEN("Kitchen", new CameraButton("6", Gdx.graphics.getWidth() - 65f, 145f), false),
	OFFICE("Office", null, false);

	private final String name;
	private boolean[] visitors;
	private Texture texture;
	private float cameraX = 0f, xVel = 0f;
	private boolean isCameraMoving = true;
	private float timePassed = 0f;
	private CameraButton camButton;
	private ArrayMap<String, Integer> multiplePos;
	private boolean moving;

	private Room(String name, CameraButton camButton, boolean movingCam)
	{
		moving = movingCam;
		this.name = name;
		this.visitors = new boolean[4];
		texture = null;
		this.camButton = camButton;
		cameraX = moving ? MathUtils.random(-320f, 0f) : 0f;
		xVel = 2f * (float)MathUtils.randomSign();

		//Rooms that have multiple positions in them. Yay for hardcoded values! :D
		multiplePos = new ArrayMap<String, Integer>();
		multiplePos.put("ShowStageBonnieChicaFreddy", 2);
		multiplePos.put("ShowStageFreddy", 2);
		multiplePos.put("Backstage", 2);
		multiplePos.put("DiningAreaBonnie", 2);
		multiplePos.put("DiningAreaChica", 2);
		multiplePos.put("BackstageBonnie", 2);
		multiplePos.put("RestroomsChica", 2);
		multiplePos.put("RestroomsChicaFreddy", 2);
		multiplePos.put("WestHallCor", 2);
		multiplePos.put("EastHall", 3);
		multiplePos.put("EastHallChica", 2);
		multiplePos.put("EastHallCor", 5);
	}

	public String getName()
	{
		return name;
	}

	public CameraButton getCamButton()
	{
		return camButton;
	}

	//Move camera left and right
	public void updateCameraValues()
	{
		if (moving)
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

		for (int i = 0; i < multiplePos.size; i++)
		{
			String s = multiplePos.getKeyAt(i);
			int scope = multiplePos.getValueAt(i);
			if (s.equals(fName))
			{
				fName += ",".concat(String.valueOf(scope));
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
			changeRoomTexture();
		}

		return texture;
	}

	public void changeRoomTexture()
	{
		texture = getTexture();
	}

	public void setVisiting(boolean visit, int ID)
	{
		visitors[ID] = visit;
		changeRoomTexture();
	}

	public boolean[] getVisitors()
	{
		return visitors;
	}
}
