package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.elements.actors.Enemy;
import com.codecool.dungeoncrawl.logic.elements.actors.Player;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application{
	GameMap map = MapLoader.loadMap("/map3.txt");
	Canvas canvas = new Canvas(map.getWidth() * Tiles.TILE_WIDTH, map.getHeight() * Tiles.TILE_WIDTH);
	GraphicsContext context = canvas.getGraphicsContext2D();
	Label nameLabel = new Label();
	Label healthLabel = new Label();
	
	public static void main(String[] args){
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		GridPane ui = new GridPane();
		ui.setPrefWidth(200);
		ui.setPadding(new Insets(10));
		
		nameLabel.setText("" + map.getPlayer().getName());
		ui.add(new Label("Name: "), 0, 0);
		ui.add(new Label("Health: "), 0, 1);
		ui.add(nameLabel, 1, 0);
		ui.add(healthLabel, 1, 1);
		
		BorderPane borderPane = new BorderPane();
		
		borderPane.setCenter(canvas);
		borderPane.setRight(ui);
		
		Scene scene = new Scene(borderPane);
		primaryStage.setScene(scene);
		refresh();
		scene.setOnKeyPressed(this::onKeyPressed);
		
		primaryStage.setTitle("Dungeon Crawl");
		primaryStage.show();
	}
	
	private void onKeyPressed(KeyEvent keyEvent){
		ArrayList<Enemy> enemies = MapLoader.getEnemies();
		Player player = map.getPlayer();
		try{
			int dx = 0;
			int dy = 0;
			switch(keyEvent.getCode()){
				case UP:
					dy = -1;
					makeMove(player, dx, dy, enemies);
					break;
				case DOWN:
					dy = 1;
					makeMove(player, dx, dy, enemies);
					break;
				case LEFT:
					dx = -1;
					makeMove(player, dx, dy, enemies);
					break;
				case RIGHT:
					dx = 1;
					makeMove(player, dx, dy, enemies);
					break;
			}
		}catch(Exception ignored){
		}
		for(Enemy enemy : enemies){
			try{
				enemy.makeMove(player);
			}catch(Exception ignored){
			}
		}
		refresh();
	}
	
	private void makeMove(Player player, int dx, int dy, ArrayList<Enemy> enemies){
		if(player.isEnemy(dx, dy))
			player.attack(dx, dy, enemies);
		else if(player.isStairs(dx, dy)){
			map = MapLoader.loadMap("/map.txt");//todo map list, change map iterating on this list
		}else
			player.move(dx, dy);
	}
	
	private void refresh(){
		context.setFill(Color.BLACK);
		context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for(int x = 0;x < map.getWidth();x++){
			for(int y = 0;y < map.getHeight();y++){
				Cell cell = map.getCell(x, y);
				if(cell.getActor() != null){
					Tiles.drawTile(context, cell.getActor(), x, y);
//                } else if (cell.getItem() != null) {
//                    Tiles.drawTile(context, cell.getItem(), x, y);
				}else{
					Tiles.drawTile(context, cell, x, y);
				}
			}
		}
		healthLabel.setText("" + map.getPlayer().getHealth());
	}
}
