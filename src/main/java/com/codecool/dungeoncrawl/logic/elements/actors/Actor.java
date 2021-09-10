package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.elements.Element;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

public abstract class Actor extends Element{
	protected Random random = new Random();
	@Getter
	@Setter
	protected int health = -1;
	@Getter
	@Setter
	protected int attack = -1;
	@Setter
	@Getter
	protected int defence = -1;
	
	public Actor(Cell cell){
		this.cell = cell;
		this.cell.setActor(this);
	}
	
	public void attack(Player player){
		int playerHealth = player.getHealth();
		player.setHealth(playerHealth - this.attack);
		if(player.getHealth() <= 0){
			System.out.println("game over");
//			todo: make game over
		}
	}
	
	public Cell getCell(){
		return cell;
	}
	
	public int getX(){
		return cell.getX();
	}
	
	public int getY(){
		return cell.getY();
	}
}
