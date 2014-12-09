package com.games.fnaf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Office
{
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private final float SIGHT_MOVEMENT = 400f;
	private Animation officeFan;
	private Texture rightDoorLight, leftDoorLight;

	public Office(SpriteBatch batch)
	{
		this.batch = batch;
		this.camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		officeFan = new Animation(1/30f, Art.officeFan);
		officeFan.setPlayMode(Animation.PlayMode.LOOP);
		rightDoorLight = Art.doorLights.get("Right");
		leftDoorLight = Art.doorLights.get("Left");
	}

	public void render()
	{
		batch.setProjectionMatrix(camera.combined);
		camera.update();

		batch.draw(Art.officeTextures.get("Office"), -160, 0f);
		batch.draw(officeFan.getKeyFrame(FNaF.getTimeElapsed()), 620f, 221f); //these x,y values are merely from trial and error xD
		batch.draw(rightDoorLight, 1600f - 160f - rightDoorLight.getWidth() - 24f, 240f);
		batch.draw(leftDoorLight, 12f - 160f, 240f);

		float step = Gdx.graphics.getDeltaTime() * SIGHT_MOVEMENT;
		//I honestly don't have any idea where I got 480 and 800, something with 640 +- 160? Wut? o.O
		if (Gdx.input.getX() <= 200 && camera.position.x - step >= 480)
		{
			camera.position.x -= step;
		}
		if (Gdx.input.getX() >= Gdx.graphics.getWidth() - 200 && camera.position.x + step <= 800)
		{
			camera.position.x += step;
		}
	}
}
