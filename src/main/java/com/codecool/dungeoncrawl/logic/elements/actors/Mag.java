package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Mag extends Enemy{
	public Mag(Cell cell){
		super(cell);
		setHealth(50);
		setAttack(20);
		setDefence(0);
	}
	
	@Override
	public String getTileName(){
		return "mag";
	}
	
	@Override
	public void makeMove(Player player){
		if(isPlayerNextTo() || isPlayerNear()){
			attack(player);
		}else{
			randomMove();
		}
	}
}
