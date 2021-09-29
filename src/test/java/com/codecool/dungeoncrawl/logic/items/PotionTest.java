package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PotionTest {

  private Potion potion;

  @BeforeEach
  public void setPotion() {
    potion = new Potion(new Cell(new GameMap(10, 10, CellType.EMPTY), 0, 0, CellType.EMPTY));
  }

  @Test
  public void test() {
    assertNotNull(potion);
    assertNotNull(potion.getCell());
  }

  @Test
  public void keyGetTileNameReturnsCorrectValue() {
    assertEquals("potion", potion.getTileName());
  }

  @Test
  public void keyToStringReturnsCorrectValue() {
    assertEquals("POTION", potion.toString());
  }
}