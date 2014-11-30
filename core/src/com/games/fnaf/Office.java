package com.games.fnaf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class Office implements Disposable
{
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private final float SIGHT_MOVEMENT = 300f;
	private final int CURSORX = 300;
	private Animation officeFan;

	public Office()
	{
		this.batch = new SpriteBatch();
		this.camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		officeFan = new Animation(1/30f, Art.officeFan);
		officeFan.setPlayMode(Animation.PlayMode.LOOP);
	}

	public void render()
	{
		batch.setProjectionMatrix(camera.combined);
		camera.update();
		batch.begin();

		batch.draw(Art.officeTextures.get("Office"), -160, 0f);
		batch.draw(officeFan.getKeyFrame(FNaF.getTimeElapsed()), 640f, 300f);

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

		batch.end();
	}

	@Override
	public void dispose()
	{
		batch.dispose();
	}
}
