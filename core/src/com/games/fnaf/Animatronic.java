package com.games.fnaf;

public enum Animatronic
{
	BONNIE(0, "Bonnie", new BonnieAI()),
	CHICA(1, "Chica", new ChicaAI()),
	FREDDY(2, "Freddy", new FreddyAI()),
	FOXY(3, "Foxy", new FoxyAI());

	private Room currentRoom;
	private final String name;
	private final int ID;
	private final AI ai;

	private Animatronic(int ID, String name, AI ai)
	{
		this.ID = ID;
		this.name = name;
		this.ai = ai;
	}

	public void updateAI()
	{
		ai.update();
	}

	public String getName()
	{
		return name;
	}

	public void setCurrentRoom(Room currentRoom)
	{
		if (this.currentRoom != null)
		{
			this.currentRoom.setVisiting(false, ID);
		}

		this.currentRoom = currentRoom;
		this.currentRoom.setVisiting(true, ID);
	}
}
