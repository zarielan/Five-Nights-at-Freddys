package com.games.fnaf;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ArrayMap;

/*  Credits to Scott Cawthon for creating and developing Five Nights at Freddy's and Five Nights at Freddy's 2 */
public class Art
{
	public static Texture map;
	public static Texture cameraButtonBG;
	public static Texture cameraButtonFlash;
	public static ArrayMap<String, Texture> roomTextures;
	public static ArrayMap<String, Texture> cameraButtonTextures;

	public static void loadTextures()
	{
		//General Stuff
		map = new Texture("map.png");
		cameraButtonBG = new Texture("CameraButtonBG.png");
		cameraButtonFlash = new Texture("CameraButtonFlash.png");

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
		roomTextures.put("BackstageBonnie2", new Texture("Backstage_Bonnie2.png"));

		//Camera buttons
		cameraButtonTextures = new ArrayMap<String,Texture>();
		cameraButtonTextures.put("1A", new Texture("1A.png"));
		cameraButtonTextures.put("1B", new Texture("1B.png"));
		cameraButtonTextures.put("1C", new Texture("1C.png"));
		cameraButtonTextures.put("5", new Texture("5.png"));
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

		for (Texture t : roomTextures.values())
		{
			t.dispose();
		}

		for (Texture t : cameraButtonTextures.values())
		{
			t.dispose();
		}
	}
}
