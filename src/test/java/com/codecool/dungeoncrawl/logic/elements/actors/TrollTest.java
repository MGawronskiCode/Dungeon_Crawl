package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TrollTest {

  private static Troll troll;

  @BeforeAll
  public static void setTroll() {
    troll = new Troll(new Cell(new GameMap(10, 10, CellType.EMPTY), 0, 0, CellType.EMPTY));
  }

  @Test
  public void whenCreatingNewTroll_allFieldsHaveCorrectValues() {
    assertNotNull(troll);
    assertNotNull(troll.getCell());
    assertEquals(200, troll.getHealth());
    assertEquals(25, troll.getAttack());
    assertEquals(0, troll.getDefence());
  }

  @Test
  public void whenGetTileNameRun_returnCorrectValue() {
    //when
    String getTileNameReturn = troll.getTileName();
    //then
    assertEquals("troll", getTileNameReturn);
  }

}