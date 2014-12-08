package com.games.fnaf;

public enum Night
{
	NIGHT_1(3, 5, 5, 0, 30f);

	private int bonnie = -1;
	private int chica = -1;
	private int freddy = -1;
	private int foxy = -1;
	private float movementDelay;

	private Night(int bonnie, int chica, int freddy, int foxy, float movementDelay)
	{
		this.bonnie = bonnie;
		this.chica = chica;
		this.foxy = foxy;
		this.freddy = freddy;
		this.movementDelay = movementDelay;
	}

	public void doNight()
	{
		if (bonnie > 0)
		{
			Animatronic.BONNIE.getAI().setFrequency(bonnie);
			Animatronic.BONNIE.getAI().setMovementDelay(movementDelay);
			Animatronic.BONNIE.getAI().setMoving(true);
		}

		if (chica > 0)
		{
			Animatronic.CHICA.getAI().setFrequency(chica);
			Animatronic.CHICA.getAI().setMovementDelay(movementDelay);
			Animatronic.CHICA.getAI().setMoving(true);
		}

		if (freddy > 0)
		{
			Animatronic.FREDDY.getAI().setFrequency(freddy);
			Animatronic.FREDDY.getAI().setMovementDelay(movementDelay);
			Animatronic.FREDDY.getAI().setMoving(true);
		}

		if (foxy > 0)
		{
			Animatronic.FOXY.getAI().setFrequency(foxy);
			Animatronic.FOXY.getAI().setMovementDelay(movementDelay);
			Animatronic.FOXY.getAI().setMoving(true);
		}
	}
}
