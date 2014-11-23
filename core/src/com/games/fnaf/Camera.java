package com.games.fnaf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Camera
{
	private SpriteBatch batch;
	private Sprite map;
	private Room room;

	public Camera(SpriteBatch batch1)
	{
		this.batch = batch1;
		map = new Sprite(Art.map);
		map.setPosition(Gdx.graphics.getWidth() - map.getWidth(), 0f);
		changeRoom(Room.SHOW_STAGE);
		Animatronic.FREDDY.setCurrentRoom(room);
		Animatronic.BONNIE.setCurrentRoom(room);
		Animatronic.CHICA.setCurrentRoom(room);
	}

	public void render()
	{
		room.updateCameraValues();
		batch.draw(room.getCurrentTexture(), room.getCameraX(), 0f);
		map.draw(batch);

		for (Room r : Room.values())
		{
			CameraButton cam = r.getCamButton();
			cam.render(batch);

			boolean collision = cam.getHitBox().contains(Gdx.input.getX(), MathStuff.reverseYCoords(Gdx.input.getY()));

			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT))
			{
				if (collision)
				{
					System.out.println("HIT " + System.currentTimeMillis());
					//changeRoom(r);
				}
			}
		}
	}

	public void changeRoom(Room room)
	{
		System.out.println(this.room + " -> " + room);
		if (this.room == room)
		{
			throw new IllegalArgumentException("Lol you can't switch to the same rooms");
		}

		if (this.room != null)
		{
			this.room.getCamButton().setSelected(false);
		}

		this.room = room;
		this.room.getCamButton().setSelected(true);
	}
}
