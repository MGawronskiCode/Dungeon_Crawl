package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GhostTest {

  private static Ghost ghost;

  @BeforeAll
  public static void setGhost() {
    ghost = new Ghost(new Cell(new GameMap(10, 10, CellType.EMPTY), 0, 0, CellType.EMPTY));
  }

  @Test
  public void whenCreatingNewGhost_allFieldsHaveCorrectValues() {
    assertNotNull(ghost);
    assertNotNull(ghost.getCell());
    assertEquals(50, ghost.getHealth());
    assertEquals(1, ghost.getAttack());
    assertEquals(10, ghost.getDefence());
  }

  @Test
  public void whenGetTileNameRun_returnCorrectValue() {
    //when
    String getTileNameReturn = ghost.getTileName();
    //then
    assertEquals("ghost", getTileNameReturn);
  }

}