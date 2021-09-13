package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Warrior extends Enemy{
	public Warrior(Cell cell){
		super(cell);
		setHealth(50);
		setAttack(30);
		setDefence(20);
	}
	
	@Override
	public String getTileName(){
		return "warrior";
	}
}
