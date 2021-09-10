package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Echinops extends Enemy{
	public Echinops(Cell cell){
		super(cell);
		setHealth(150);
		setAttack(5);
		setDefence(0);
	}
	
	@Override
	public String getTileName(){
		return "echinops";
	}
	
	@Override
	public void makeMove(){
	}
}
