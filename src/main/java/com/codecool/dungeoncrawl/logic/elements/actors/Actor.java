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
	protected int health;
	@Getter
	@Setter
	protected int attack;
	@Setter
	@Getter
	protected int defence;
	
	public Actor(Cell cell){
		super(cell);
		this.cell.setActor(this);
	}
	
	public void attack(Player player){
		int playerHealth = player.getHealth();
		int playerDefence = player.getDefence();
		reducePlayerHealth(player, playerHealth, playerDefence);
		if(playerKilled(player)){
			System.out.println("game over");
//			todo: game over
		}
	}
	
	private void reducePlayerHealth(Player player, int playerHealth, int playerDefence){
		player.setHealth(playerHealth + playerDefence - this.attack);
	}
	
	private boolean playerKilled(Player player){
		return player.getHealth() <= 0;
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
