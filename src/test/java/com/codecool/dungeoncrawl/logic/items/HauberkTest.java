package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HauberkTest {

  private Hauberk hauberk;

  @BeforeEach
  private void setHauberk() {
    hauberk = new Hauberk(new Cell(new GameMap(10, 10, CellType.EMPTY), 0, 0, CellType.EMPTY));
  }

  @Test
  public void whenCreatingNewHauberk_allItsFieldsHaveCorrectValues() {
    assertNotNull(hauberk);
    assertNotNull(hauberk.getCell());
    int hauberkDefence = hauberk.getDefence();
    assertTrue(hauberkDefence >= 5 && hauberkDefence <= 10);
    assertNotNull(hauberk.toString());
  }

  @Test
  public void hauberkToStringWorksCorrectly() {
    assertEquals("HAUBERK +" + hauberk.getDefence(), hauberk.toString());
  }
}