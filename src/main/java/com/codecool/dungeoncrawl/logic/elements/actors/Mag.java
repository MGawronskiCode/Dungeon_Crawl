package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Mag extends Enemy implements Movable{
	public Mag(Cell cell){
		super(cell);
		setHealth(50);
		setAttack(40);
		setDefence(0);
	}
	
	@Override
	public String getTileName(){
		return "mag";
	}
	
}
