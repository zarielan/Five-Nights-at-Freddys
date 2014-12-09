package com.games.fnaf;

public enum Animatronic
{
	BONNIE(0, "Bonnie", new BonnieAI()),
	CHICA(1, "Chica", new ChicaAI()),
	FREDDY(2, "Freddy", new FreddyAI()),
	FOXY(3, "Foxy", new FoxyAI()),
	GOLDEN_FREDDY(4, "GoldenFreddy", new GoldenFreddyAI());

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

	public AI getAI()
	{
		return ai;
	}

	public Room getCurrentRoom()
	{
		return currentRoom;
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
			System.out.println(this.getName() + ": " + this.currentRoom.getName() + " -> " + currentRoom.getName());
		}

		this.currentRoom = currentRoom;
		this.currentRoom.setVisiting(true, ID);
	}
}
