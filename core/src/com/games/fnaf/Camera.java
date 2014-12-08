package com.games.fnaf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Camera
{
	private SpriteBatch batch;
	private Sprite map;
	private Room room;
	private Animation kitchenStatic;

	public Camera(SpriteBatch batch1)
	{
		this.batch = batch1;
		map = new Sprite(Art.map);
		map.setPosition(Gdx.graphics.getWidth() - map.getWidth(), 0f);

		kitchenStatic = new Animation(1/30f, Art.kitchenStatic);
		kitchenStatic.setPlayMode(Animation.PlayMode.LOOP);

		changeRoom(Room.SHOW_STAGE);
	}

	public void render()
	{
		room.updateCameraValues();
		if (room == Room.KITCHEN)
		{
			batch.draw(kitchenStatic.getKeyFrame(FNaF.getTimeElapsed()), 0f, 0f);
			batch.draw(Art.cameraDisabled, (Gdx.graphics.getWidth() - Art.cameraDisabled.getWidth()) / 2, 600f);
		}
		else
		{
			batch.draw(room.getCurrentTexture(), room.getCameraX(), 0f);
		}

		map.draw(batch);

		for (Room r : Room.values())
		{
			CameraButton cam = r.getCamButton();

			if (cam == null)
				continue;

			cam.render(batch);

			boolean collision = cam.getHitBox().contains(Gdx.input.getX(), MathStuff.reverseYCoords(Gdx.input.getY()));

			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT))
			{
				if (collision)
				{
					changeRoom(r);
				}
			}
		}

		Texture label = Art.roomLabels.get(room.getName());
		batch.draw(label, Gdx.graphics.getWidth() - 290f - (label.getWidth() / 2), 400f);
	}

	public void changeRoom(Room room)
	{
		if (this.room == room)
		{
			return;
		}

		if (this.room != null)
		{
			this.room.getCamButton().setSelected(false);
		}

		this.room = room;
		//this.room.changeRoomTexture();
		this.room.getCamButton().setSelected(true);
	}
}
