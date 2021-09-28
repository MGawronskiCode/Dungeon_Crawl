package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EchinopsTest {

  private Echinops echinops;

  @BeforeEach
  public void setEchinops() {
    echinops = new Echinops(new Cell(null, 0, 0, CellType.EMPTY));
  }

  @Test
  public void whenCreatingNewEchinops_allFieldsHaveCorrectValues() {
    assertNotNull(echinops);
    assertNotNull(echinops.getCell());
    assertEquals(150, echinops.getHealth());
    assertEquals(5, echinops.getAttack());
    assertEquals(0, echinops.getDefence());
  }

  @Test
  public void whenGetTileNameRun_returnCorrectValue() {
    //when
    String getTileNameReturn = echinops.getTileName();
    //then
    assertEquals("echinops", getTileNameReturn);
  }

}