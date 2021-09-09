package com.codecool.dungeoncrawl.logic.elements.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Doors extends Item{
	private boolean isOpen = false;
	
	public Doors(Cell cell){
		super(cell);
	}
	
	@Override
	public String getTileName(){
		
		if(isOpen)
			return "openedDoors";
		else
			return "closedDoors";
	}
	
	public void openDoors(){
		isOpen = true;
	}
	
}
