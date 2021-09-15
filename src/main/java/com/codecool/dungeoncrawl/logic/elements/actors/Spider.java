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
	
	@Override
	public void makeMove(Player player){
		if(isPlayerNextTo()){
			attack(player);
			int actualAttack = getAttack();
			setAttack(actualAttack + 5);
		}else if(isPlayerNear()){
			setAttack(20);
			moveToPlayer(player);
		}else{
			setAttack(20);
			randomMove();
		}
	}
	
	@Override
	public void attack(Player player){
		super.attack(player);
	}
}
