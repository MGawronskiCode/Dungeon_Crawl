package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MagTest {

  private static Mag mag;

  @BeforeAll
  public static void setMag() {
    mag = new Mag(new Cell(new GameMap(10, 10, CellType.EMPTY), 0, 0, CellType.EMPTY));
  }

  @Test
  public void whenCreatingNewMag_allFieldsHaveCorrectValues() {
    assertNotNull(mag);
    assertNotNull(mag.getCell());
    assertEquals(50, mag.getHealth());
    assertEquals(20, mag.getAttack());
    assertEquals(0, mag.getDefence());
  }

  @Test
  public void whenGetTileNameRun_returnCorrectValue() {
    //when
    String getTileNameReturn = mag.getTileName();
    //then
    assertEquals("mag", getTileNameReturn);
  }

}