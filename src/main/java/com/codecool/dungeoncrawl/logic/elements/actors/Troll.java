package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Troll extends Enemy{
	private boolean canMove = true;
	
	public Troll(Cell cell){
		super(cell);
		setHealth(200);
		setAttack(25);
		setDefence(0);
	}
	
	@Override
	public String getTileName(){
		return "troll";
	}
	
	@Override
	public void move(){
		if(canMove){
			super.move();
			canMove = false;
		}else
			canMove = true;
	}
}
