package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Warrior extends Enemy{
	public Warrior(Cell cell){
		super(cell);
	}
	
	@Override
	public String getTileName(){
		return "warrior";
	}
	
}
