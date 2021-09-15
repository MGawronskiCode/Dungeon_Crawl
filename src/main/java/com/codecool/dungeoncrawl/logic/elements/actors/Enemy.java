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
		}else if(isPlayerNear()){
			moveToPlayer(player);
		}else{
			randomMove();
		}
	}
	
	protected void moveToPlayer(Player player){
		int thisX = this.getX();
		int thisY = this.getY();
		int playerX = player.getCell().getX();
		int playerY = player.getCell().getY();
		Cell nextCell;
		CellType nextCellType;
		int dx, dy;
		
		if(thisX < playerX){
			dx = 1;
			dy = 0;
			nextCell = cell.getNeighbor(dx, dy);
			nextCellType = nextCell.getType();
			if(isValidMove(nextCell, nextCellType)){
				move(nextCell);
			}else{
				moveCloserPlayerVertically(thisY, playerY);
			}
			
		}else if(thisX > playerX){
			dx = -1;
			dy = 0;
			nextCell = cell.getNeighbor(dx, dy);
			nextCellType = nextCell.getType();
			if(isValidMove(nextCell, nextCellType)){
				move(nextCell);
			}else{
				moveCloserPlayerVertically(thisY, playerY);
			}
		}else{
			moveCloserPlayerVertically(thisY, playerY);
		}
		
	}
	
	private void moveCloserPlayerVertically(int thisY, int playerY){
		int dx;
		int dy;
		Cell nextCell;
		CellType nextCellType;
		if(thisY < playerY){
			dx = 0;
			dy = 1;
			nextCell = cell.getNeighbor(dx, dy);
			nextCellType = nextCell.getType();
			if(isValidMove(nextCell, nextCellType)){
				move(nextCell);
			}
		}else{
			dx = 0;
			dy = -1;
			nextCell = cell.getNeighbor(dx, dy);
			nextCellType = nextCell.getType();
			if(isValidMove(nextCell, nextCellType)){
				move(nextCell);
			}
		}
	}
	
	protected boolean isValidMove(Cell nextCell, CellType nextCellType){
		return (nextCellType == CellType.EMPTY || nextCellType == CellType.FLOOR) && nextCell.getActor() == null;
	}
	
	protected void randomMove(){
		int limit = 30;
		int counter = 0;
		boolean validMove = false;
		do{
			validMove = moveRandomDirection(validMove);
			counter++;//prevents infinite loop
		}while(!validMove && counter < limit);
	}
	
	protected boolean moveRandomDirection(boolean validMove){
		int dx;
		int dy;
		do{
			dx = random.nextInt(3) - 1;
			dy = random.nextInt(3) - 1;
		}while((dx == -1 && dy == -1) || (dx == -1 && dy == 1) || (dx == 1 && dy == 1) || (dx == 1 && dy == -1));//prevent move diagonally
		Cell nextCell = cell.getNeighbor(dx, dy);
		CellType nextCellType = nextCell.getType();
		validMove = isValidMove(validMove, nextCell, nextCellType);
		return validMove;
	}
	
	protected boolean isValidMove(boolean validMove, Cell nextCell, CellType nextCellType){
		if((nextCellType == CellType.EMPTY || nextCellType == CellType.FLOOR) && nextCell.getActor() == null){
			validMove = true;
			move(nextCell);
		}
		return validMove;
	}
	
	private void move(Cell nextCell){
		cell.setActor(null);
		nextCell.setActor(this);
		cell = nextCell;
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
	
	protected boolean isPlayerInCell(int dx, int dy){
		
		Cell nextToCell = cell.getNeighbor(dx, dy);
		if(nextToCell != null){
			Actor nextToCellActor = nextToCell.getActor();
			return nextToCellActor instanceof Player;
		}
		return false;
	}
	
	protected boolean isPlayerNear(){
		for(int dx = -3;dx < 4;dx++){//check cells in range 3
			for(int dy = -3;dy < 4;dy++){
				if(isPlayerInCell(dx, dy)){
					return true;
				}
			}
		}
		return false;
	}
}
