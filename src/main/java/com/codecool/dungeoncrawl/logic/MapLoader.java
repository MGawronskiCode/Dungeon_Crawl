package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.elements.Door;
import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.logic.elements.actors.*;
import com.codecool.dungeoncrawl.logic.items.*;
import lombok.Getter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MapLoader{
	@Getter
	private static final ArrayList<Enemy> enemies = new ArrayList<>();
	@Getter
	private static final ArrayList<Item> items = new ArrayList<>();

	public static GameMap loadMap(String mapName){
		InputStream is = MapLoader.class.getResourceAsStream(mapName);
		assert is != null;
		Scanner scanner = new Scanner(is);
		int width = scanner.nextInt();
		int height = scanner.nextInt();
		
		scanner.nextLine(); // empty line
		
		GameMap map = new GameMap(width, height, CellType.EMPTY);
		for(int y = 0;y < height;y++){
			String line = scanner.nextLine();
			for(int x = 0;x < width;x++){
				if(x < line.length()){
					Cell cell = map.getCell(x, y);
					switch(line.charAt(x)){
						case ' ':
							cell.setType(CellType.EMPTY);
							break;
						case '#':
							cell.setType(CellType.WALL);
							break;
						case '.':
							cell.setType(CellType.FLOOR);
							break;
						case 'D':
							cell.setType(CellType.FLOOR);
							new Door(cell);
							break;
						case 'K':
							cell.setType(CellType.KEY);
							new Key(cell);
							break;
						case 'P':
							cell.setType(CellType.POTION);
							new Potion(cell);
							break;
						case '!':
							cell.setType(CellType.SWORD);
							new Sword(cell);
							break;
						case 'H':
							cell.setType(CellType.HAUBERK);
							new Hauberk(cell);
							break;
						case 's':
							cell.setType(CellType.FLOOR);
							new Skeleton(cell);
							enemies.add((Enemy) cell.getActor());
							break;
						case 'd':
							cell.setType(CellType.FLOOR);
							new Demon(cell, map);
							enemies.add((Enemy) cell.getActor());
							break;
						case 'm':
							cell.setType(CellType.FLOOR);
							new Mag(cell);
							enemies.add((Enemy) cell.getActor());
							break;
						case 'S':
							cell.setType(CellType.FLOOR);
							new Spider(cell);
							enemies.add((Enemy) cell.getActor());
							break;
						case 'G':
							cell.setType(CellType.FLOOR);
							new Ghost(cell);
							enemies.add((Enemy) cell.getActor());
							break;
						case 'b':
							cell.setType(CellType.FLOOR);
							new Bat(cell);
							enemies.add((Enemy) cell.getActor());
							break;
						case 't':
							cell.setType(CellType.FLOOR);
							new Troll(cell);
							enemies.add((Enemy) cell.getActor());
							break;
						case 'e':
							cell.setType(CellType.FLOOR);
							new Echinops(cell);
							enemies.add((Enemy) cell.getActor());
							break;
						case 'w':
							cell.setType(CellType.FLOOR);
							new Warrior(cell);
							enemies.add((Enemy) cell.getActor());
							break;
						case 'q':
							cell.setType(CellType.getRandomDecoration());
							break;
						case '@':
							cell.setType(CellType.FLOOR);
							if(Main.getPlayer() == null)
								Main.setPlayer(new Player(cell));
							else
								Main.setPlayer(new Player(cell, Main.getPlayer().getHealth()));
							break;
						case '+':
							cell.setType(CellType.STAIRS);
							break;
						default:
							throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
					}
				}
			}
		}
		return map;
	}
}