package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.items.Hauberk;
import com.codecool.dungeoncrawl.logic.items.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    player = new Player(new Cell(new GameMap(10, 10, CellType.EMPTY), 0, 0, CellType.EMPTY), 100);
//    then
    assertNotNull(player);
    assertNotNull(player.getInventory());
    assertNull(player.getName());
    assertNull(player.getMap());
    assertEquals(100, player.getHealth());
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

//  todo rest of tests
}