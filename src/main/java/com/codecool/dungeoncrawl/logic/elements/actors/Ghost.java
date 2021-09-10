package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class Ghost extends Enemy{
	
	public Ghost(Cell cell){
		super(cell);
		setHealth(50);
		setAttack(2);
		setDefence(18);
	}
	
	@Override
	public String getTileName(){
		return "ghost";
	}
	
	@Override
	public void makeMove(Player player){
		boolean validMove = false;
		do{
			validMove = prepareForMoveValidation(validMove);
		}while(!validMove);
	}
	
	protected boolean prepareForMoveValidation(boolean validMove){
		int dx = random.nextInt(3) - 1;
		int dy = random.nextInt(3) - 1;
		Cell nextCell = cell.getNeighbor(dx, dy);
		CellType nextCellType = nextCell.getType();
		validMove = isValidMove(validMove, nextCell, nextCellType);
		return validMove;
	}
	
	protected boolean isValidMove(boolean validMove, Cell nextCell, CellType nextCellType){
		if(nextCell.getActor() == null){
			validMove = true;
			cell.setActor(null);
			nextCell.setActor(this);
			cell = nextCell;
		}
		return validMove;
	}
}
