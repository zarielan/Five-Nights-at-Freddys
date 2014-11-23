package com.games.fnaf;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ArrayMap;

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
		roomTextures = new ArrayMap<String, Texture>();

		roomTextures.put("ShowStage", new Texture("ShowStage.png"));
		roomTextures.put("ShowStageBonnieChicaFreddy", new Texture("ShowStage_BonnieChicaFreddy.png"));
		roomTextures.put("ShowStageBonnieFreddy", new Texture("ShowStage_BonnieFreddy.png"));
		roomTextures.put("ShowStageChicaFreddy", new Texture("ShowStage_ChicaFreddy.png"));
		roomTextures.put("ShowStageFreddy", new Texture("ShowStage_Freddy.png"));

		roomTextures.put("DiningArea", new Texture("DiningArea.png"));
		roomTextures.put("DiningAreaFreddy", new Texture("DiningArea_Freddy.png"));
		roomTextures.put("DiningAreaBonnie", new Texture("DiningArea_Bonnie.png"));

		//Camera buttons
		cameraButtonTextures = new ArrayMap<String,Texture>();
		cameraButtonTextures.put("1A", new Texture("1A.png"));
		cameraButtonTextures.put("1B", new Texture("1B.png"));
		cameraButtonTextures.put("1C", new Texture("1C.png"));
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
