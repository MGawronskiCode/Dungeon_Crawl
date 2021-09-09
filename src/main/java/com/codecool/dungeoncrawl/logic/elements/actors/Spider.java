package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Spider extends Enemy{
	public Spider(Cell cell){
		super(cell);
		setHealth(30);
		setAttack(20);
		setDefence(5);
	}
	
	@Override
	public String getTileName(){
		return "spider";
	}
	
}
