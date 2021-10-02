package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.elements.Door;
import com.codecool.dungeoncrawl.logic.items.Hauberk;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.items.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

  private Player player;

  @BeforeEach
  public void setPlayer() {
    GameMap gameMap = new GameMap(10, 10, CellType.EMPTY);
    player = new Player(new Cell(gameMap, 0, 0, CellType.EMPTY), gameMap);
  }

  @Test
  public void whenCreatingNewPlayerWithFirstConstructor_allItsFieldsHaveCorrectValues() {
    assertNotNull(player);
    assertNotNull(player.getInventory());
    assertNotNull(player.getMap());
    assertNull(player.getName());
    assertEquals(300, player.getHealth());
    assertEquals(20, player.getAttack());
    assertEquals(0, player.getDefence());
  }

  @Test
  public void whenCreatingNewPlayerWithSecondConstructor_allItsFieldsHaveCorrectValues() {
//    when
    player = new Player(new Cell(new GameMap(10, 10, CellType.EMPTY), 0, 0, CellType.EMPTY), 100, "name");
//    then
    assertNotNull(player);
    assertNotNull(player.getInventory());
    assertNull(player.getMap());
    assertEquals(100, player.getHealth());
    assertEquals("name", player.getName());
  }

  @Test
  public void getAttackReturnsCorrectValuesDependsOnInventory() {
    assertEquals(0, player.getInventory().getItems().size());
    assertEquals(20, player.getAttack());

    Sword sword = new Sword(new Cell(new GameMap(10, 10, CellType.EMPTY), 0, 0, CellType.EMPTY));
    player.getInventory().addItem(sword);
    assertEquals(20 + sword.getAttack(), player.getAttack());
  }

  @Test
  public void getDefenceReturnsCorrectValuesDependsOnInventory() {
    assertEquals(0, player.getInventory().getItems().size());
    assertEquals(0, player.getDefence());

    Hauberk hauberk = new Hauberk(new Cell(new GameMap(10, 10, CellType.EMPTY), 0, 0, CellType.EMPTY));
    player.getInventory().addItem(hauberk);
    assertEquals(hauberk.getDefence(), player.getDefence());
  }

  @Test
  public void getTileNameReturnsCorrectValue() {
    assertEquals("player", player.getTileName());
  }

  @Test
  public void whenPlayerMoveIntoValidCell_ItsLocalisationChangesCorrectly() {
//    when
    player.move(1, 0);
//    then
    assertEquals(1, player.getX());
    assertEquals(0, player.getY());
  }

  @Test
  public void whenPlayerTriesToMoveOutOfMap_ItsLocalisationDoesntChange() {
    //    when
    player.move(-1, 0);
//    then
    assertEquals(0, player.getX());
    assertEquals(0, player.getY());
  }

  @Test
  public void whenTheNextMoveIntoEmptySpace_isMovementOkReturnsTrue() {
//    given
    GameMap gameMap = new GameMap(10, 10, CellType.EMPTY);
    player = new Player(gameMap.getCell(0, 0), gameMap);
//    when
    Cell nextCell = new Cell(gameMap, 1, 0, CellType.EMPTY);
//then
    assertTrue(player.isMovementOk(nextCell));
  }

  @Test
  public void whenTheNextMoveIntoEnemy_isMovementOkReturnsFalse() {
//    given
    GameMap gameMap = new GameMap(10, 10, CellType.EMPTY);
    player = new Player(gameMap.getCell(0, 0), gameMap);
//    when
    Cell nextCell = new Cell(gameMap, 1, 0, CellType.EMPTY);
    Enemy enemy = new Bat(nextCell);
//then
    assertFalse(player.isMovementOk(nextCell));
  }

  @Test
  public void whenTheNextMoveIntoWall_isMovementOkReturnsFalse() {
//    given
    GameMap gameMap = new GameMap(10, 10, CellType.EMPTY);
    player = new Player(gameMap.getCell(0, 0), gameMap);
//    when
    Cell nextCell = new Cell(gameMap, 1, 0, CellType.WALL);
//then
    assertFalse(player.isMovementOk(nextCell));
  }

  @Test
  public void whenTheNextMoveIntoDoorsWithoutKey_isMovementOkReturnsFalse() {
//    given
    GameMap gameMap = new GameMap(10, 10, CellType.EMPTY);
    player = new Player(gameMap.getCell(0, 0), gameMap);
//    when
    Cell nextCell = new Cell(gameMap, 1, 0, CellType.EMPTY);
    Door door = new Door(nextCell);
//then
    assertFalse(player.isMovementOk(nextCell));
  }

  @Test
  public void whenTheNextMoveIntoDoorsWithKey_isMovementOkReturnsTrue() {
//    given
    GameMap gameMap = new GameMap(10, 10, CellType.EMPTY);
    player = new Player(gameMap.getCell(0, 0), gameMap);
//    when
    Cell nextCell = new Cell(gameMap, 1, 0, CellType.EMPTY);
    Door door = new Door(nextCell);
    player.getInventory().addItem(new Key(new Cell(gameMap, 2, 2, CellType.EMPTY)));
//then
    assertTrue(player.isMovementOk(nextCell));
  }

  @Test
  public void whenPlayerMoves_isEnemyReturnsCorrectValue_dependingOnNextCellOfMap() {
//    when
    GameMap gameMap = new GameMap(10, 10, CellType.EMPTY);
    player = new Player(new Cell(gameMap, 0, 0, CellType.EMPTY), gameMap);
    Bat bat = new Bat(new Cell(gameMap, 1, 0, CellType.EMPTY));
    gameMap.getCell(0, 0).setActor(player);
    gameMap.getCell(1, 0).setActor(bat);
//    then
    assertFalse(player.isEnemy(0, 1));
    assertTrue(player.isEnemy(1, 0));
  }

  @Test
  public void whenPlayerMoves_isStairsReturnsCorrectValue_dependingOnNextCellOfMap() {
//    when
    GameMap gameMap = new GameMap(10, 10, CellType.EMPTY);
    player = new Player(new Cell(gameMap, 0, 0, CellType.EMPTY), gameMap);
    gameMap.getCell(0, 0).setActor(player);
    gameMap.getCell(1, 0).setType(CellType.STAIRS);
//    then
    assertFalse(player.isStairs(0, 1));
    assertTrue(player.isStairs(1, 0));
  }

  @Test
  public void whenPlayerAttackEnemyAndDoesntKillHim_EnemyHealthIsReducedByPlayerAttack() {
//    given
    GameMap gameMap = new GameMap(10, 10, CellType.EMPTY);
    player = new Player(new Cell(gameMap, 0, 0, CellType.EMPTY), gameMap);
    Enemy enemy = new Skeleton(new Cell(gameMap, 1, 0, CellType.EMPTY));
    gameMap.getCell(0, 0).setActor(player);
    gameMap.getCell(1, 0).setActor(enemy);
    ArrayList<Enemy> enemies = new ArrayList<>();
    enemies.add(enemy);
//    when
    player.attack(1, 0, enemies);
//    then
    assertEquals(10, enemy.getHealth());
  }

  @Test
  public void whenPlayerAttackEnemyAndKillHim_EnemyIsRemovedFromMapAndEnemiesList() {
//    given
    GameMap gameMap = new GameMap(10, 10, CellType.EMPTY);
    player = new Player(new Cell(gameMap, 0, 0, CellType.EMPTY), gameMap);
    Enemy enemy = new Skeleton(new Cell(gameMap, 1, 0, CellType.EMPTY));
    gameMap.getCell(0, 0).setActor(player);
    gameMap.getCell(1, 0).setActor(enemy);
    ArrayList<Enemy> enemies = new ArrayList<>();
    enemies.add(enemy);
//    when
    player.attack(1, 0, enemies);
    player.attack(1, 0, enemies);
    player.attack(1, 0, enemies);
//    then
    assertEquals(0, enemies.size());
    assertNull(gameMap.getCell(1, 0).getActor());
  }
}