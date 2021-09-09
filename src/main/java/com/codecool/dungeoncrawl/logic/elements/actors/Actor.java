package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.elements.Element;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

public abstract class Actor extends Element implements Movable{
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
	
	public void move(){
		boolean validMove = false;
		do{
			validMove = randomMoveDirection(validMove);
		}while(!validMove);
	}
	
	private boolean randomMoveDirection(boolean validMove){
		int dx = random.nextInt(3) - 1;
		int dy = random.nextInt(3) - 1;
		Cell nextCell = cell.getNeighbor(dx, dy);
		CellType nextCellType = nextCell.getType();
		validMove = isValidMove(validMove, nextCell, nextCellType);
		return validMove;
	}
	
	private boolean isValidMove(boolean validMove, Cell nextCell, CellType nextCellType){
		if((nextCellType == CellType.EMPTY || nextCellType == CellType.FLOOR) && nextCell.getActor() == null){
			validMove = true;
			cell.setActor(null);
			nextCell.setActor(this);
			cell = nextCell;
		}
		return validMove;
	}
	
	@Override
	public void attack(){
		//todo: implement attack
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
