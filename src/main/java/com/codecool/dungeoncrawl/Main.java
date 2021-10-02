package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.UI.EndGamePopup;
import com.codecool.dungeoncrawl.UI.HeroNameInputPopup;
import com.codecool.dungeoncrawl.UI.SaveGameEvent;
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
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Main<T> extends Application {
  @Setter
  @Getter
  static private Player player;
  private final Label nameLabel = new Label();
  private final Label healthLabel = new Label();
  Label inventoryLabel = new Label();
  Label attackLabel = new Label();
  Label defenceLabel = new Label();
  private GameMap map = MapLoader.loadMap("/map.txt");
  private final Canvas canvas = new Canvas(map.getWidth() * Tiles.TILE_WIDTH, map.getHeight() * Tiles.TILE_WIDTH);
  private final GraphicsContext context = canvas.getGraphicsContext2D();
  private int mapNameIncrementer = 1;
  private Stage stage;
  private String heroName;

  public static void main(String[] args) {
    launch(args);
  }

  public void newGame() throws Exception {
    start(stage);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    this.stage = primaryStage;
    GridPane ui = new GridPane();
    ui.setPrefWidth(120);
    ui.setPadding(new Insets(10));
    player.setName(this.heroName);
    nameLabel.setText("" + player.getName());
    ui.add(new Label("Name: "), 0, 0);
    ui.add(new Label("Health: "), 0, 1);
    ui.add(new Label("Attack: "), 0, 2);
    ui.add(new Label("Defence: "), 0, 3);
    ui.add(new Label("Inventory: "), 0, 4);
    ui.add(nameLabel, 1, 0);
    ui.add(healthLabel, 1, 1);
    ui.add(attackLabel, 1, 2);
    ui.add(defenceLabel, 1, 3);
    ui.add(inventoryLabel, 0, 5);

    BorderPane borderPane = new BorderPane();

    borderPane.setCenter(canvas);
    borderPane.setRight(ui);

    Scene scene = new Scene(borderPane);
    primaryStage.setScene(scene);
    refresh();
    scene.setOnKeyPressed(this::onKeyPressed);
    scene.addEventFilter(KeyEvent.KEY_PRESSED, new SaveGameEvent(map, player));
    primaryStage.setTitle("Dungeon Crawl");
    primaryStage.show();
    setHeroName();
  }

  private void refresh() {
    context.setFill(Color.BLACK);
    context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    for (int x = 0; x < map.getWidth(); x++) {
      for (int y = 0; y < map.getHeight(); y++) {
        Cell cell = map.getCell(x, y);
        if (cell.getActor() != null) {
          Tiles.drawTile(context, cell.getActor(), x, y);
        } else if (cell.getDoor() != null) {
          Tiles.drawTile(context, cell.getDoor(), x, y);
        } else {
          Tiles.drawTile(context, cell, x, y);
        }
      }
    }
    healthLabel.setText("" + player.getHealth());
    inventoryLabel.setText(player.getInventory().toString());
    attackLabel.setText("" + player.getAttack());
    defenceLabel.setText("" + player.getDefence());
  }

  private void onKeyPressed(KeyEvent keyEvent) {
    ArrayList<Enemy> enemies = MapLoader.getEnemies();
//		if (heroName == null) {setHeroName();}
    try {
      int dx = 0;
      int dy = 0;
      switch (keyEvent.getCode()) {
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
        case SPACE:
          player.pickItem();
          break;
      }
    } catch (Exception ignored) {
    }
    for (Enemy enemy : enemies) {
      try {
        enemy.makeMove(player);
      } catch (Exception ignored) {
      }
    }
    if (player.getHealth() <= 0) {
      EndGamePopup gameOver = new EndGamePopup(this);
      gameOver.show(stage);
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    } else {
      refresh();
    }
  }

  private void setHeroName() {
    HeroNameInputPopup heroInputPopup = new HeroNameInputPopup("Hero name");
    heroInputPopup.showAndWait();
    player.setName(heroInputPopup.getHeroNameEntered());
    nameLabel.setText(heroInputPopup.getHeroNameEntered());
  }

  //todo move to the Player class
  private void makeMove(Player player, int dx, int dy, ArrayList<Enemy> enemies) {
    if (player.isEnemy(dx, dy))
      player.attack(dx, dy, enemies);
    else if (player.isStairs(dx, dy)) {
//			clearMap(enemies, null);
      clearElements((ArrayList<T>) enemies);
      enemies.clear();
      loadNextMap(enemies);
    } else
      player.move(dx, dy);
  }

  private void clearElements(ArrayList<T> elements) {
    for (int i = 0; i < elements.size(); i++) {
      elements.set(i, null);
    }
  }

  private void loadNextMap(ArrayList<Enemy> enemies) {
    String nextMapName = String.format("/map%d.txt", mapNameIncrementer);
    mapNameIncrementer++;
    map = MapLoader.loadMap(nextMapName);
    enemies = MapLoader.getEnemies();
  }
}
