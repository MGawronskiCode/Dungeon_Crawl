package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DemonTest {
  private Demon demon;

  @BeforeEach
  public void setDemon() {
    demon = new Demon(new Cell(null, 0, 0, CellType.EMPTY), new GameMap(10, 10, CellType.EMPTY));
  }

  @Test
  public void whenCreatingNewDemon_allFieldsHaveCorrectValues() {
    assertNotNull(demon);
    assertNotNull(demon.getCell());
    assertEquals(999, demon.getHealth());
    assertEquals(20, demon.getAttack());
    assertEquals(999, demon.getDefence());
    assertNotNull(demon.getActualMap());
    assertEquals(10, demon.getTeleportCountdown());
  }

  @Test
  public void whenGetTileNameRun_returnCorrectValue() {
    //when
    String getTileNameReturn = demon.getTileName();
    //then
    assertEquals("demon", getTileNameReturn);
  }

}