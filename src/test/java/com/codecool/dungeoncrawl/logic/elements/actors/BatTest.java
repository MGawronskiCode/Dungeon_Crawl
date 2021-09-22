package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BatTest {

  private Bat bat;

  @BeforeEach
  public void setBat() {
    bat = new Bat(new Cell(null, 0, 0, null));
  }

  @Test
  public void whenCreateNewBat_allFieldsHaveCorrectValues() {
    assertNotNull(bat);
    assertNotNull(bat.getCell());
    assertEquals(40, bat.getHealth());
    assertEquals(5, bat.getAttack());
    assertEquals(0, bat.getDefence());
  }

  @Test
  public void whenGetTileNameRun_returnCorrectValue() {
    //when
    String getTileNameReturn = bat.getTileName();
    //then
    assertEquals("bat", getTileNameReturn);
  }

  @Test
  public void whenPlayerNear_runningPlayerNearReturnsTrue() {
//    given
    GameMap gameMap = new GameMap(10, 10, CellType.EMPTY);
    bat.setCell(new Cell(gameMap, 0, 0, CellType.EMPTY));
    Cell playerCell = gameMap.getCell(1, 0);
    playerCell.setActor(new Player(new Cell(gameMap, 1, 0, CellType.EMPTY), gameMap));
//    when
    boolean playerNear = bat.isPlayerNear();
//    then
    assertTrue(playerNear);
  }

  @Test
  public void whenPlayerFar_runningPlayerNearReturnsFalse() {
//    given
    GameMap gameMap = new GameMap(10, 10, CellType.EMPTY);
    bat.setCell(new Cell(gameMap, 0, 0, CellType.EMPTY));
    Cell playerCell = gameMap.getCell(9, 0);
    playerCell.setActor(new Player(new Cell(gameMap, 1, 0, CellType.EMPTY), gameMap));
//    when
    boolean playerNear = bat.isPlayerNear();
//    then
    assertFalse(playerNear);
  }

}