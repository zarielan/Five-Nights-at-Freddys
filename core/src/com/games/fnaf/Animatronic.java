package com.games.fnaf;

public enum Animatronic
{
	BONNIE(0, "Bonnie"),
	CHICA(1, "Chica"),
	FREDDY(2, "Freddy"),
	FOXY(3, "Foxy");

	private Room currentRoom;
	private final String name;
	private final int ID;

	private Animatronic(int ID, String name)
	{
		this.ID = ID;
		this.name = name;
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
