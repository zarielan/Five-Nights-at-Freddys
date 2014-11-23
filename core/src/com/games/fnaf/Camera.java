package com.games.fnaf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Camera
{
	private SpriteBatch batch;
	private Sprite map;
	private Room room;
	private Animation kitchenStatic;
	private float timeElapsed = 0f;

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
		timeElapsed += Gdx.graphics.getDeltaTime();
		room.updateCameraValues();
		if (room == Room.KITCHEN)
		{
			batch.draw(kitchenStatic.getKeyFrame(timeElapsed), 0f, 0f);
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
	}

	public void changeRoom(Room room)
	{
		if (this.room != null)
		{
			this.room.getCamButton().setSelected(false);
		}

		this.room = room;
		this.room.getCamButton().setSelected(true);
	}
}
