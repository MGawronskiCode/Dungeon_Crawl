package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles{
	private static final Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
	private static final Map<String, Tile> tileMap = new HashMap<>();
	public static int TILE_WIDTH = 32;
	
	static{
		//map
		tileMap.put("empty", new Tile(0, 0));
		tileMap.put("wall", new Tile(10, 17));
		tileMap.put("floor", new Tile(2, 0));
		//actors
		tileMap.put("player", new Tile(27, 0));
		tileMap.put("skeleton", new Tile(29, 6));
		tileMap.put("ghost", new Tile(27, 8));
		tileMap.put("warrior", new Tile(31, 0));
		tileMap.put("spider", new Tile(28, 5));
		tileMap.put("bat", new Tile(26, 8));
		tileMap.put("troll", new Tile(30, 6));
		tileMap.put("mag", new Tile(24, 1));
		tileMap.put("echinops", new Tile(24, 0));
		//items
		tileMap.put("sword", new Tile(0, 30));
		tileMap.put("key", new Tile(16, 23));
		tileMap.put("closedDoors", new Tile(0, 9));
		tileMap.put("openedDoors", new Tile(2, 9));
		tileMap.put("heart", new Tile(27, 9));
		tileMap.put("potion", new Tile(17, 25));
		tileMap.put("hauberk", new Tile(3, 23));
		//decorations
		tileMap.put("decoration0", new Tile(1, 0));
		tileMap.put("decoration1", new Tile(2, 0));
		tileMap.put("decoration2", new Tile(3, 0));
		tileMap.put("decoration3", new Tile(4, 0));
		tileMap.put("decoration4", new Tile(5, 0));
		tileMap.put("decoration5", new Tile(6, 0));
		tileMap.put("decoration6", new Tile(7, 0));
		tileMap.put("decoration7", new Tile(1, 1));
		tileMap.put("decoration8", new Tile(2, 1));
		tileMap.put("decoration9", new Tile(3, 1));
		tileMap.put("decoration10", new Tile(4, 1));
		tileMap.put("decoration11", new Tile(5, 1));
		tileMap.put("decoration12", new Tile(6, 1));
		tileMap.put("decoration13", new Tile(7, 1));
		tileMap.put("decoration14", new Tile(0, 2));
		tileMap.put("decoration15", new Tile(1, 2));
		tileMap.put("decoration16", new Tile(2, 2));
		tileMap.put("decoration17", new Tile(3, 2));
		tileMap.put("decoration18", new Tile(4, 2));
		tileMap.put("decoration19", new Tile(5, 2));
		tileMap.put("decoration20", new Tile(6, 2));
		tileMap.put("decoration21", new Tile(7, 2));
		tileMap.put("decoration22", new Tile(3, 2));
		tileMap.put("decoration23", new Tile(4, 2));

	}
	
	public static void drawTile(GraphicsContext context, Drawable d, int x, int y){
		Tile tile = tileMap.get(d.getTileName());
		context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h, x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
	}
	
	public static class Tile{
		public final int x, y, w, h;
		
		Tile(int i, int j){
			x = i * (TILE_WIDTH + 2);
			y = j * (TILE_WIDTH + 2);
			w = TILE_WIDTH;
			h = TILE_WIDTH;
		}
	}
}