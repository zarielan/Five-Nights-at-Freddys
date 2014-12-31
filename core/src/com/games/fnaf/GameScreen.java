package com.games.fnaf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class GameScreen extends ScreenAdapter
{
	private Camera camera;
	private Office office;
	private boolean lookingAtCamera;
	private boolean toggling;
	private boolean hasCollided;
	private boolean renderOffice;
	private SpriteBatch batch;
	private OrthographicCamera ortho;
	private Night night;
	private Rectangle cameraToggleHitbox;
	private Animation cameraToggle;
	private Animation cameraBars;
	private float animStateTime = 0f;
	private boolean playCameraBars;
	private float cameraBarsTime = 0f;
	private boolean renderJumpScare;
	private boolean animatronicInside;
	private Animation jumpScare;
	private float jumpScareTimer;

	public GameScreen(SpriteBatch batch)
	{
		this.batch = batch;
		this.ortho = new OrthographicCamera();
		this.ortho.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.night = Night.TEST_NIGHT;
		this.lookingAtCamera = false;
		this.cameraToggleHitbox = new Rectangle(255f, 27f, Art.cameraToggle.getWidth(), Art.cameraToggle.getHeight());
		this.toggling = false;
		this.cameraToggle = new Animation(1/35f, Art.cameraPopup);
		this.hasCollided = false;
		this.renderOffice = true;
		this.cameraBars = new Animation(1/30f, Art.cameraBars);
		this.cameraBars.setPlayMode(Animation.PlayMode.NORMAL);
		this.playCameraBars = false;
		this.renderJumpScare = false;
		this.animatronicInside = false;
		this.jumpScareTimer = 0f;
	}

	@Override
	public void render(float delta)
	{
		this.night.incrementNightCounter(delta);

		if (lookingAtCamera && !renderOffice && !renderJumpScare)
		{
			camera.render();
		}
		else if (renderJumpScare)
		{
			jumpScareTimer += delta;
			office.renderJumpscare(jumpScare.getKeyFrame(jumpScareTimer));

			if (jumpScareTimer >= 3f)
			{
				((FNaF)Gdx.app.getApplicationListener()).setScreen(new GameOverScreen(batch));
			}
		}
		else
		{
			office.render();
			FoxyAI foxyAI = (FoxyAI) Animatronic.FOXY.getAI();
			foxyAI.setNonViewingTime(foxyAI.getNonViewingTime() + delta);
		}

		batch.setProjectionMatrix(ortho.combined);
		ortho.update();

		if (!renderJumpScare)
		{
			batch.draw(Art.cameraToggle, 255f, 27f);

			for (Animatronic a : Animatronic.values())
			{
				a.getAI().update(a);

				if (a.getCurrentRoom() == Room.JUMPSCARE_TIME)
				{
					animatronicInside = true;
				}
			}
		}

		cameraToggling(delta);
	}

	private void readyJumpscare()
	{
		renderJumpScare = true;
		jumpScareTimer = 0f;

		String jumpScarer = "Bonnie";
		for (int i = 0; i < 4; i++)
		{
			if (Room.JUMPSCARE_TIME.getVisitors()[i])
			{
				jumpScarer = Animatronic.values()[i].getName();
				break;
			}
		}

		jumpScare = new Animation(1/30f, Art.jumpScares.get(jumpScarer));
		jumpScare.setPlayMode(Animation.PlayMode.LOOP);
	}

	private void cameraToggling(float delta)
	{
		boolean collision = !renderJumpScare && this.cameraToggleHitbox.contains(Gdx.input.getX(), MathStuff.reverseYCoords(Gdx.input.getY()));

		//The cursor has collided with the toggle button
		if (!hasCollided && collision && !toggling)
		{
			//It's toggling now
			toggling = true;

			//Was the player not looking at the camera?
			if (!lookingAtCamera)
			{
				//Set the animation to when the camera is raised up
				this.cameraToggle.setPlayMode(Animation.PlayMode.NORMAL);
			}
			else
			{
				//Else? Reverse the animation; put the camera down
				this.cameraToggle.setPlayMode(Animation.PlayMode.REVERSED);

				//Is there an animatronic?
				if (animatronicInside)
					//Prepare the jumpscare
					readyJumpscare();
			}
		}

		//Are we toggling?
		if (toggling)
		{
			//Start the animation counter
			animStateTime += delta;

			//Is the animation done?
			if (this.cameraToggle.isAnimationFinished(animStateTime))
			{
				//Not toggling anymore
				toggling = false;

				//Were we not looking at the camera before?
				if (!lookingAtCamera)
				{
					//We are now
					lookingAtCamera = true;

					//Not the office, but the camera
					renderOffice = false;

					//Render the white camera bars we see after raising the camera
					playCameraBars = true;
				}
				else
				{
					//Were we looking at the camera before?
					//Now we're not
					lookingAtCamera = false;

					//Check if someone isn't there
					if (!animatronicInside)
						//And we're looking at the office instead
						renderOffice = true;
				}
			}
			else
			{
				//Is the animation not done?
				//Were we looking at the camera?
				if (lookingAtCamera)
				{
					//Is nobody there?
					if (!animatronicInside)
						//Immediately render the office behind the transparent camera texture
						renderOffice = true;
				}
			}

			//Since we're toggling, render the camera pull up/down animation
			batch.draw(cameraToggle.getKeyFrame(animStateTime), 0f, 0f, 1280f, 720f);
		}
		else
		{
			//Since we're not toggling, reset the animation counter to 0
			animStateTime = 0f;
		}

		//Playing the camera bars animation?
		if (playCameraBars)
		{
			//Start the animation counter
			cameraBarsTime += delta;

			//Render frames
			batch.draw(cameraBars.getKeyFrame(cameraBarsTime), 0f, 0f);

			//Is it done?
			if (cameraBars.isAnimationFinished(cameraBarsTime))
			{
				//We're not rendering it anymore
				playCameraBars = false;
				//Reset the counter to 0
				cameraBarsTime = 0f;
			}
		}

		hasCollided = collision;
	}

	@Override
	public void show()
	{
		this.camera = new Camera(batch);
		this.office = new Office(batch);
		//Set their starting areas
		Animatronic.FREDDY.setCurrentRoom(Room.SHOW_STAGE);
		Animatronic.BONNIE.setCurrentRoom(Room.SHOW_STAGE);
		Animatronic.CHICA.setCurrentRoom(Room.SHOW_STAGE);
		Animatronic.FOXY.setCurrentRoom(Room.PIRATE_COVE);
		this.night.doNight();
	}

	@Override
	public void hide()
	{
	}

	@Override
	public void dispose()
	{
	}
}
