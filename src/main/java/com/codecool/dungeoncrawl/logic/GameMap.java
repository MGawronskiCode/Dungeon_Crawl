package com.codecool.dungeoncrawl.logic;

import lombok.Getter;


@Getter
public class GameMap{
	private final int width;
	private final int height;
	private final Cell[][] cells;
	protected String currentMap;

	public void setCurrentMap(String currentMap) {
		this.currentMap = currentMap;
	}

	public String getCurrentMap() {
		return currentMap;
	}

	public GameMap(int width, int height, CellType defaultCellType){
		this.width = width;
		this.height = height;
		cells = new Cell[width][height];
		for(int x = 0;x < width;x++){
			for(int y = 0;y < height;y++){
				cells[x][y] = new Cell(this, x, y, defaultCellType);
			}
		}
	}
	
	public Cell getCell(int x, int y){
		return cells[x][y];
	}
	
}
