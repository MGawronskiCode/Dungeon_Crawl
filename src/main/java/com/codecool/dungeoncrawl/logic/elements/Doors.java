package com.codecool.dungeoncrawl.logic.elements;

import com.codecool.dungeoncrawl.logic.Cell;

public class Doors extends Element{
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
