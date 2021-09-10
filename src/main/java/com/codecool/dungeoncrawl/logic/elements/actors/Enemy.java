package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public abstract class Enemy extends Actor{
	public Enemy(Cell cell){
		super(cell);
	}
	
	public void makeMove(Player player){
		if(isPlayerNextTo()){
			attack(player);
		}else{
			boolean validMove = false;
			do{
				validMove = moveRandomDirection(validMove);
			}while(!validMove);
		}
	}
	
	protected boolean moveRandomDirection(boolean validMove){
		int dx = random.nextInt(3) - 1;
		int dy = random.nextInt(3) - 1;
		Cell nextCell = cell.getNeighbor(dx, dy);
		CellType nextCellType = nextCell.getType();
		validMove = isValidMove(validMove, nextCell, nextCellType);
		return validMove;
	}
	
	protected boolean isValidMove(boolean validMove, Cell nextCell, CellType nextCellType){
		if((nextCellType == CellType.EMPTY || nextCellType == CellType.FLOOR) && nextCell.getActor() == null){
			validMove = true;
			cell.setActor(null);
			nextCell.setActor(this);
			cell = nextCell;
		}
		return validMove;
	}
	
	protected boolean isPlayerNextTo(){
		int dx = 0;
		int dy = -1;
		if(isPlayerInCell(dx, dy))
			return true;
		dy = 1;
		if(isPlayerInCell(dx, dy))
			return true;
		dx = -1;
		dy = 0;
		if(isPlayerInCell(dx, dy))
			return true;
		dx = 1;
		return isPlayerInCell(dx, dy);
	}
	
	private boolean isPlayerInCell(int dx, int dy){
		Cell nextToCell = cell.getNeighbor(dx, dy);
		Actor nextToCellActor = nextToCell.getActor();
		return nextToCellActor instanceof Player;
	}
}
