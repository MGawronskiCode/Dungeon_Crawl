package com.codecool.dungeoncrawl.logic;

import lombok.Getter;

import java.util.Random;

public enum CellType{
	EMPTY("empty"), FLOOR("floor"), WALL("wall"), STAIRS("stairs"),
	
	SWORD("sword"), KEY("key"), POTION("potion"), HAUBERK("hauberk"),
	
	DECORATION0("decoration0"), DECORATION1("decoration1"), DECORATION2("decoration2"), DECORATION3("decoration3"), DECORATION4("decoration4"), DECORATION5("decoration5"), DECORATION6("decoration6"), DECORATION7("decoration7"), DECORATION8("decoration8"), DECORATION9("decoration9"), DECORATION10("decoration10"), DECORATION11("decoration11"), DECORATION12("decoration12"), DECORATION13("decoration13"), DECORATION14("decoration14"), DECORATION15("decoration15"), DECORATION16("decoration17"), DECORATION17("decoration18"), DECORATION18("decoration19"), DECORATION19("decoration20"), DECORATION20("decoration21"), DECORATION21("decoration22"), DECORATION22("decoration23");
	
	@Getter
	private final String tileName;
	
	CellType(String tileName){
		this.tileName = tileName;
	}
	
	public static CellType getRandomDecoration(){
		Random r = new Random();
		int randomDecorationInt = r.nextInt(24) + 8;//random from 3 to 27 - decorations indexes
		int i = 0;
		for(CellType celltype : CellType.values()){
			if(i == randomDecorationInt)
				return celltype;
			i++;
		}
		return DECORATION0;
	}
}
