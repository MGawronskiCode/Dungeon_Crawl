package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Bat extends Enemy{
	public Bat(Cell cell){
		super(cell);
		setHealth(40);
		setAttack(5);
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
	
	@Override
	protected boolean isPlayerNear(){
		for(int dx = -3;dx < 5;dx++){//check cells in range 3
			for(int dy = -3;dy < 5;dy++){
				if(isPlayerInCell(dx, dy)){
					return true;
				}
			}
		}
		return false;
	}
}
