package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Spider extends Enemy{
	public Spider(Cell cell){
		super(cell);
	}
	
	@Override
	public String getTileName(){
		return "spider";
	}
	
}
