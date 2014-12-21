package com.games.fnaf;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;

/*  Credits to Scott Cawthon for creating and developing Five Nights at Freddy's and Five Nights at Freddy's 2 */
public class Art
{
	public static Texture map;
	public static Texture cameraButtonBG;
	public static Texture cameraButtonFlash;
	public static Texture cameraDisabled;
	public static Texture cameraToggle;
	public static ArrayMap<String, Texture> officeTextures;
	public static ArrayMap<String, Texture> roomTextures;
	public static ArrayMap<String, Texture> cameraButtonTextures;
	public static ArrayMap<String, Texture> roomLabels;
	public static ArrayMap<String, Array<TextureRegion>> jumpScares;
	public static ArrayMap<String, Texture> doorLights;
	public static Array<TextureRegion> kitchenStatic;
	public static Array<TextureRegion> officeFan;
	public static Array<TextureRegion> cameraPopup;
	public static Array<TextureRegion> cameraBars;
	public static Array<TextureRegion> foxySprinting;

	public static void loadTextures()
	{

		//General Stuff
		map = new Texture("map.png");
		cameraButtonBG = new Texture("CameraButtonBG.png");
		cameraButtonFlash = new Texture("CameraButtonFlash.png");
		cameraDisabled = new Texture("CameraDisabled.png");
		cameraToggle = new Texture("CameraToggle.png");

		//Jumpsares
		jumpScares = new ArrayMap<String, Array<TextureRegion>>();
		jumpScares.put("Chica", new Array<TextureRegion>());
		for (int i = 1; i <= 16; i++)
		{
			jumpScares.get("Chica").add(new TextureRegion(new Texture("Jumpscares/Chica (" + i + ").png")));
		}

		//Rooms
		//Animatronic combination naming must be in the order of Bonnie -> Chica -> Freddy -> Foxy
		//Maximum permutations must be 7, plus one for the empty room
		roomTextures = new ArrayMap<String, Texture>();

		
		roomTextures.put("ShowStage", new Texture("ShowStage.png"));
		roomTextures.put("ShowStageBonnieChicaFreddy1", new Texture("ShowStage_BonnieChicaFreddy1.png"));
		roomTextures.put("ShowStageBonnieChicaFreddy2", new Texture("ShowStage_BonnieChicaFreddy2.png"));
		roomTextures.put("ShowStageBonnieFreddy", new Texture("ShowStage_BonnieFreddy.png"));
		roomTextures.put("ShowStageChicaFreddy", new Texture("ShowStage_ChicaFreddy.png"));
		roomTextures.put("ShowStageFreddy1", new Texture("ShowStage_Freddy1.png"));
		roomTextures.put("ShowStageFreddy2", new Texture("ShowStage_Freddy2.png"));

		roomTextures.put("DiningArea", new Texture("DiningArea.png"));
		roomTextures.put("DiningAreaFreddy", new Texture("DiningArea_Freddy.png"));
		roomTextures.put("DiningAreaBonnie1", new Texture("DiningArea_Bonnie1.png"));
		roomTextures.put("DiningAreaBonnie2", new Texture("DiningArea_Bonnie2.png"));
		roomTextures.put("DiningAreaChica1", new Texture("DiningArea_Chica1.png"));
		roomTextures.put("DiningAreaChica2", new Texture("DiningArea_Chica2.png"));
		roomTextures.put("DiningAreaBonnieChica", new Texture("DiningArea_BonnieChica.png"));
		roomTextures.put("DiningAreaChicaFreddy", new Texture("DiningArea_ChicaFreddy.png"));
		roomTextures.put("DiningAreaBonnieFreddy", new Texture("DiningArea_BonnieFreddy.png"));
		roomTextures.put("DiningAreaBonnieChicaFreddy", new Texture("DiningArea_BonnieChicaFreddy.png"));

		roomTextures.put("PirateCove1", new Texture("PirateCove1.png"));
		roomTextures.put("PirateCove2", new Texture("PirateCove2.png"));
		roomTextures.put("PirateCoveFoxy1", new Texture("PirateCove_Foxy1.png"));
		roomTextures.put("PirateCoveFoxy2", new Texture("PirateCove_Foxy2.png"));
		roomTextures.put("PirateCoveFoxy3", new Texture("PirateCove_Foxy3.png"));

		roomTextures.put("Backstage1", new Texture("Backstage1.png"));
		roomTextures.put("Backstage2", new Texture("Backstage2.png"));
		roomTextures.put("BackstageBonnie1", new Texture("Backstage_Bonnie1.png"));
		roomTextures.put("BackstageBonnie2", new Texture("Backstage_Bonnie1.png")); //<-- this specifically
		// TODO change this to Backstage_Bonnie2, too scared shitless to see Bonnie starting at the camera closely

		roomTextures.put("Restrooms", new Texture("Restrooms.png"));
		roomTextures.put("RestroomsChica1", new Texture("Restrooms_Chica1.png"));
		roomTextures.put("RestroomsChica2", new Texture("Restrooms_Chica2.png"));
		roomTextures.put("RestroomsFreddy", new Texture("Restrooms_Freddy.png"));
		roomTextures.put("RestroomsChicaFreddy1", new Texture("Restrooms_ChicaFreddy1.png"));
		roomTextures.put("RestroomsChicaFreddy2", new Texture("Restrooms_ChicaFreddy2.png"));

		roomTextures.put("SupplyCloset", new Texture("SupplyCloset.png"));
		roomTextures.put("SupplyClosetBonnie", new Texture("SupplyCloset_Bonnie.png"));

		roomTextures.put("WestHall", new Texture("WestHall.png"));
		roomTextures.put("WestHallBonnie", new Texture("WestHall_Bonnie.png"));

		roomTextures.put("WestHallCor1", new Texture("WestHallCor1.png"));
		roomTextures.put("WestHallCor2", new Texture("WestHallCor2.png"));
		roomTextures.put("WestHallCorBonnie", new Texture("WestHallCor_Bonnie.png"));
		roomTextures.put("WestHallCorGoldenFreddy", new Texture("WestHallCor_GoldenFreddy.png"));

		roomTextures.put("EastHall1", new Texture("EastHall1.png"));
		roomTextures.put("EastHall2", new Texture("EastHall2.png"));
		roomTextures.put("EastHall3", new Texture("EastHall3.png"));
		roomTextures.put("EastHallFreddy", new Texture("EastHall_Freddy.png"));
		roomTextures.put("EastHallChica1", new Texture("EastHall_Chica1.png"));
		roomTextures.put("EastHallChica2", new Texture("EastHall_Chica2.png"));

		roomTextures.put("EastHallCor1", new Texture("EastHallCor1.png"));
		roomTextures.put("EastHallCor2", new Texture("EastHallCor2.png"));
		roomTextures.put("EastHallCor3", new Texture("EastHallCor3.png"));
		roomTextures.put("EastHallCor4", new Texture("EastHallCor4.png"));
		roomTextures.put("EastHallCor5", new Texture("EastHallCor5.png"));
		roomTextures.put("EastHallCorFreddy", new Texture("EastHallCor_Freddy.png"));
		roomTextures.put("EastHallCorChica", new Texture("EastHallCor_Chica.png"));


		officeTextures = new ArrayMap<String, Texture>();
		officeTextures.put("Office", new Texture("Office.png"));
		officeTextures.put("OfficeLeftLight", new Texture("OfficeLeftLight.png"));
		officeTextures.put("OfficeRightLight", new Texture("OfficeRightLight.png"));
		officeTextures.put("OfficeBonnie", new Texture("OfficeBonnie.png"));
		officeTextures.put("OfficeChica", new Texture("OfficeChica.png"));

		//The door lights
		doorLights = new ArrayMap<String, Texture>();
		doorLights.put("Left", new Texture("DoorLightLeft.png"));
		doorLights.put("LeftDoor", new Texture("DoorLightLeftDoor.png"));
		doorLights.put("LeftLight", new Texture("DoorLightLeftLight.png"));
		doorLights.put("LeftDoorLight", new Texture("DoorLightLeftDoorLight.png"));

		doorLights.put("Right", new Texture("DoorLightRight.png"));
		doorLights.put("RightDoor", new Texture("DoorLightRightDoor.png"));
		doorLights.put("RightLight", new Texture("DoorLightRightLight.png"));
		doorLights.put("RightDoorLight", new Texture("DoorLightRightDoorLight.png"));

		kitchenStatic = new Array<TextureRegion>();
		for (int i = 1; i <= 8; i++)
		{
			kitchenStatic.add(new TextureRegion(new Texture("KitchenFrames/" + i + ".png")));
		}

		officeFan = new Array<TextureRegion>();
		for (int i = 1; i <= 3; ++i)
		{
			officeFan.add(new TextureRegion(new Texture("OfficeFan" + i + ".png")));
		}

		cameraPopup = new Array<TextureRegion>();
		for (int i = 1; i <= 11; i++)
		{
			cameraPopup.add(new TextureRegion(new Texture("CameraFrames/" + i + ".png")));
		}

		cameraBars = new Array<TextureRegion>();
		for (int i = 1; i <= 4; i++)
		{
			cameraBars.add(new TextureRegion(new Texture("CameraBars/" + i + ".png")));
		}

		foxySprinting = new Array<TextureRegion>();
		for (int i = 1; i <= 31; i++)
		{
			foxySprinting.add(new TextureRegion(new Texture("FoxySprinting/" + i + ".png")));
		}

		//Camera buttons
		cameraButtonTextures = new ArrayMap<String,Texture>();
		cameraButtonTextures.put("1A", new Texture("1A.png"));
		cameraButtonTextures.put("1B", new Texture("1B.png"));
		cameraButtonTextures.put("1C", new Texture("1C.png"));
		cameraButtonTextures.put("5", new Texture("5.png"));
		cameraButtonTextures.put("7", new Texture("7.png"));
		cameraButtonTextures.put("3", new Texture("3.png"));
		cameraButtonTextures.put("2A", new Texture("2A.png"));
		cameraButtonTextures.put("2B", new Texture("2B.png"));
		cameraButtonTextures.put("6", new Texture("6.png"));
		cameraButtonTextures.put("4A", new Texture("4A.png"));
		cameraButtonTextures.put("4B", new Texture("4B.png"));

		roomLabels = new ArrayMap<String, Texture>();
		roomLabels.put("ShowStage", new Texture("ShowStageLabel.png"));
		roomLabels.put("SupplyCloset", new Texture("SupplyClosetLabel.png"));
		roomLabels.put("PirateCove", new Texture("PirateCoveLabel.png"));
		roomLabels.put("Backstage", new Texture("BackstageLabel.png"));
		roomLabels.put("DiningArea", new Texture("DiningAreaLabel.png"));
		roomLabels.put("WestHall", new Texture("WestHallLabel.png"));
		roomLabels.put("EastHallCor", new Texture("EastHallCorLabel.png"));
		roomLabels.put("WestHallCor", new Texture("WestHallCorLabel.png"));
		roomLabels.put("Restrooms", new Texture("RestroomsLabel.png"));
		roomLabels.put("Kitchen", new Texture("KitchenLabel.png"));
		roomLabels.put("EastHall", new Texture("EastHallLabel.png"));
	}

	/* Support for rooms having multiple textures. They're not controlled though, it will just pick one image at random
	   for that room */
	public static Texture getRoomTexture(String room)
	{
		if (room.contains(","))
		{
			String[] args = room.split(",");
			String key = args[0];
			int frames = Integer.parseInt(args[1]);
			int chosen = MathUtils.random(1, frames);
			String name = key.concat(String.valueOf(chosen));
			return roomTextures.get(name);
		}
		else
		{
			return roomTextures.get(room);
		}
	}

	public static void disposeTextures()
	{
		map.dispose();
		cameraButtonBG.dispose();
		cameraButtonFlash.dispose();
		cameraDisabled.dispose();
		cameraToggle.dispose();

		for (Array<TextureRegion> t : jumpScares.values())
		{
			for (TextureRegion tr : t)
			{
				tr.getTexture().dispose();
			}
		}

		for (Texture t : doorLights.values())
		{
			t.dispose();
		}

		for (Texture t : roomTextures.values())
		{
			t.dispose();
		}

		for (Texture t : cameraButtonTextures.values())
		{
			t.dispose();
		}

		for (TextureRegion t : kitchenStatic)
		{
			t.getTexture().dispose();
		}

		for (TextureRegion t : foxySprinting)
		{
			t.getTexture().dispose();
		}

		for (TextureRegion t : cameraPopup)
		{
			t.getTexture().dispose();
		}

		for (TextureRegion t: officeFan)
		{
			t.getTexture().dispose();
		}

		for (TextureRegion t : cameraBars)
		{
			t.getTexture().dispose();
		}

		for (Texture t : roomLabels.values())
		{
			t.dispose();
		}

		for (Texture t : officeTextures.values())
		{
			t.dispose();
		}
	}
}
