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
	public static ArrayMap<String, Texture> roomTextures;
	public static ArrayMap<String, Texture> cameraButtonTextures;
	public static Array<TextureRegion> kitchenStatic;

	public static void loadTextures()
	{
		//General Stuff
		map = new Texture("map.png");
		cameraButtonBG = new Texture("CameraButtonBG.png");
		cameraButtonFlash = new Texture("CameraButtonFlash.png");
		cameraDisabled = new Texture("CameraDisabled.png");

		//Rooms
		//Animatronic combination naming must be in the order of Bonnie -> Chica -> Freddy -> Foxy
		//Maximum permutations must be 7, plus one for the empty room
		roomTextures = new ArrayMap<String, Texture>();

		roomTextures.put("ShowStage", new Texture("ShowStage.png"));
		roomTextures.put("ShowStageBonnieChicaFreddy", new Texture("ShowStage_BonnieChicaFreddy.png"));
		roomTextures.put("ShowStageBonnieFreddy", new Texture("ShowStage_BonnieFreddy.png"));
		roomTextures.put("ShowStageChicaFreddy", new Texture("ShowStage_ChicaFreddy.png"));
		roomTextures.put("ShowStageFreddy", new Texture("ShowStage_Freddy.png"));

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

		roomTextures.put("PirateCoveFoxy", new Texture("PirateCove_Foxy.png"));

		roomTextures.put("Backstage", new Texture("Backstage.png"));
		roomTextures.put("BackstageBonnie1", new Texture("Backstage_Bonnie1.png"));
		roomTextures.put("BackstageBonnie2", new Texture("Backstage_Bonnie1.png")); //<-- this specifically
		/* TODO change this to Backstage_Bonnie2, too scared shitless to see Bonnie starting at the camera closely */

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

		kitchenStatic = new Array<TextureRegion>();
		for (int i = 1; i <= 8; i++)
		{
			kitchenStatic.add(new TextureRegion(new Texture("KitchenFrames/" + i + ".png")));
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
	}
}
