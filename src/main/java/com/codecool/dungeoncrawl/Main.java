package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.elements.actors.Enemy;
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
	GameMap map = MapLoader.loadMap();
	Canvas canvas = new Canvas(map.getWidth() * Tiles.TILE_WIDTH, map.getHeight() * Tiles.TILE_WIDTH);
	GraphicsContext context = canvas.getGraphicsContext2D();
	Label healthLabel = new Label();
	
	public static void main(String[] args){
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception{//todo throw Exc
		GridPane ui = new GridPane();
		ui.setPrefWidth(200);
		ui.setPadding(new Insets(10));
		
		ui.add(new Label("Health: "), 0, 0);
		ui.add(healthLabel, 1, 0);
		
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
		try{
			switch(keyEvent.getCode()){
				//todo ograniczyć ruch przez ściany
				case UP:
					map.getPlayer().move(0, -1);
					break;
				case DOWN:
					map.getPlayer().move(0, 1);
					break;
				case LEFT:
					map.getPlayer().move(-1, 0);
					break;
				case RIGHT:
					map.getPlayer().move(1, 0);
					break;
			}
		}catch(Exception ignored){
		}
		ArrayList<Enemy> enemies = MapLoader.getEnemies();
		for(Enemy enemy : enemies){
			try{
				enemy.move();
			}catch(Exception ignored){
			}
		}
		refresh();
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
