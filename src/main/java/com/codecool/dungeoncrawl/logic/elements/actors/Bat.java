package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Bat extends Enemy{
	public Bat(Cell cell){
		super(cell);
	}
	
	@Override
	public String getTileName(){
		return "bat";
	}
	
	@Override
	public void move(){
		super.move();
		super.move();
	}
}
