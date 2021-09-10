package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Bat extends Enemy{
	public Bat(Cell cell){
		super(cell);
		setHealth(40);
		setAttack(10);
		setDefence(0);
	}
	
	@Override
	public String getTileName(){
		return "bat";
	}
	
	@Override
	public void makeMove(Player player){
		super.makeMove(player);
		super.makeMove(player);
	}
}
