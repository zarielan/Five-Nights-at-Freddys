package com.games.fnaf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;

public class Camera implements Disposable
{
	private SpriteBatch batch;
	private Sprite map;
	private Room room;
	private ShapeRenderer debug;

	public Camera(SpriteBatch batch1)
	{
		this.batch = batch1;
		map = new Sprite(Art.map);
		map.setPosition(Gdx.graphics.getWidth() - map.getWidth(), 0f);
		changeRoom(Room.SHOW_STAGE);
		Animatronic.FREDDY.setCurrentRoom(room);
		Animatronic.BONNIE.setCurrentRoom(room);
		Animatronic.CHICA.setCurrentRoom(room);

		debug = new ShapeRenderer();
	}

	@Override
	public void dispose()
	{
		debug.dispose();
	}

	public void render()
	{
		room.updateCameraValues();
		batch.draw(room.getCurrentTexture(), room.getCameraX(), 0f);
		map.draw(batch);

		debug.begin(ShapeRenderer.ShapeType.Filled);
		for (Room r : Room.values())
		{
			CameraButton cam = r.getCamButton();
			cam.render(batch);
			debug.rect(cam.getHitBox().getX(), cam.getHitBox().getY(), cam.getHitBox().getWidth(), cam.getHitBox().getHeight());
			System.out.println(cam + ", " + cam.getHitBox() + ", " + System.currentTimeMillis());
			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT))
			{
				if (cam.getHitBox().contains(Gdx.input.getX(), Gdx.input.getY()))
				{
					System.out.println("HIT " + System.currentTimeMillis());
					//changeRoom(r);
				}
			}
		}
		debug.end();
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
