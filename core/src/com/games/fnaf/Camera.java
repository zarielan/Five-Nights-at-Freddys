package com.games.fnaf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Camera
{
	private SpriteBatch batch;
	private Sprite map;
	private Room room;
	private Animation kitchenStatic;
	private Animation foxySprinting;
	private float foxyAnimCounter;
	private boolean showFoxySprinting;

	public Camera(SpriteBatch batch1)
	{
		this.batch = batch1;
		map = new Sprite(Art.map);
		map.setPosition(Gdx.graphics.getWidth() - map.getWidth(), 0f);

		kitchenStatic = new Animation(1/30f, Art.kitchenStatic);
		kitchenStatic.setPlayMode(Animation.PlayMode.LOOP);

		foxySprinting = new Animation(1/30f, Art.foxySprinting);
		foxySprinting.setPlayMode(Animation.PlayMode.NORMAL);
		foxyAnimCounter = 0f;
		showFoxySprinting = false;

		changeRoom(Room.SHOW_STAGE, Room.SHOW_STAGE.getCamButton());
	}

	public void render()
	{
		room.updateCameraValues();
		if (room == Room.KITCHEN)
		{
			batch.draw(kitchenStatic.getKeyFrame(FNaF.getTimeElapsed()), 0f, 0f);
			batch.draw(Art.cameraDisabled, (Gdx.graphics.getWidth() - Art.cameraDisabled.getWidth()) / 2, 600f);
		}
		else if (showFoxySprinting)
		{
			foxyAnimCounter += Gdx.graphics.getDeltaTime();
			batch.draw(foxySprinting.getKeyFrame(foxyAnimCounter), 0f, 0f);
		}
		else
		{
			batch.draw(room.getCurrentTexture(), room.getCameraX(), 0f);
		}

		FoxyAI foxyAI = (FoxyAI)Animatronic.FOXY.getAI();

		if (room == Room.PIRATE_COVE)
		{
			foxyAI.setNonViewingTime(0f);
			foxyAI.setViewingTime(foxyAI.getViewingTime() + Gdx.graphics.getDeltaTime());
		}
		else
			foxyAI.setNonViewingTime(foxyAI.getNonViewingTime() + Gdx.graphics.getDeltaTime());

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
					changeRoom(r, cam);
				}
			}
		}

		Texture label = Art.roomLabels.get(room.getName());
		batch.draw(label, Gdx.graphics.getWidth() - 290f - (label.getWidth() / 2), 400f);
	}

	public void changeRoom(Room room, CameraButton cam)
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
		cam.setFlickerTimeElapsed(0f);
		showFoxySprinting = (this.room == Room.WEST_HALL && Room.WEST_HALL.getVisitors()[Animatronic.FOXY.ordinal()]);

		changeRoomTextureIfNoVisitors();
		this.room.getCamButton().setSelected(true);
	}

	private void changeRoomTextureIfNoVisitors()
	{
		boolean changeRoomTextureRandomly = true;
		for (int i = 0; i < this.room.getVisitors().length; i++)
		{
			if (room.getVisitors()[i])
			{
				changeRoomTextureRandomly = false;
				break;
			}
		}

		if (changeRoomTextureRandomly && MathUtils.random(4) == 0)
		{
			this.room.changeRoomTexture();
		}
	}
}
