package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

import static java.lang.Math.abs;

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
		Cell nextCell = bestMoveGetCloserPlayer(player);
		CellType nextCellType = nextCell.getType();
		boolean validMove = false;
		if(isValidMove(validMove, nextCell, nextCellType)){
			System.out.println("not random");
			move(nextCell);
		}else{
			randomMove();
		}
	}
	
	private Cell bestMoveGetCloserPlayer(Player player){//todo return correct cell
		int dx = 0;
		int dy = -1;
		Cell bestCell = cell.getNeighbor(dx, dy);
		Integer bestCellDistanceFromPlayer = countCellDistanceFromPlayer(bestCell, player);
		
		dy = 1;
		checkIfCellIsBetter(player, dx, dy, bestCellDistanceFromPlayer, bestCell);
		
		dx = -1;
		dy = 0;
		checkIfCellIsBetter(player, dx, dy, bestCellDistanceFromPlayer, bestCell);
		
		dx = 1;
		checkIfCellIsBetter(player, dx, dy, bestCellDistanceFromPlayer, bestCell);
		
		return bestCell;
	}
	
	private void checkIfCellIsBetter(Player player, int dx, int dy, Integer bestCellDistanceFromPlayer, Cell bestCell){
		Cell actualCell = cell.getNeighbor(dx, dy);
		int actualCellDistanceFromPlayer = countCellDistanceFromPlayer(actualCell, player);
		
		if(actualCellDistanceFromPlayer < bestCellDistanceFromPlayer){//todo dlaczego się nie nadpisuje!!!!!
			bestCell = actualCell;
			bestCellDistanceFromPlayer = actualCellDistanceFromPlayer;
		}
	}
	
	private int countCellDistanceFromPlayer(Cell cell, Player player){
		//todo implement counting distance from cell with player
		int cellX = cell.getX();
		int cellY = cell.getY();
		int playerX = player.getCell().getX();
		int playerY = player.getCell().getY();
		
		int xAbsoluteDifference = abs(cellX - playerX);
		int yAbsoluteDifference = abs(cellY - playerY);
		
		return xAbsoluteDifference + yAbsoluteDifference;
	}
	
	private void randomMove(){
		int limit = 30;
		int counter = 0;
		boolean validMove = false;
		do{
			validMove = moveRandomDirection(validMove);
			counter++;//prevents infinite loop
		}while(!validMove && counter < limit);
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
	
	private boolean isPlayerInCell(int dx, int dy){
		
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
