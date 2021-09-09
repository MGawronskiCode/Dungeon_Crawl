package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Enemy{
	public Skeleton(Cell cell){
		super(cell);
		setHealth(30);
		setAttack(5);
		setDefence(0);
	}
	
	@Override
	public String getTileName(){
		return "skeleton";
	}
	
}
