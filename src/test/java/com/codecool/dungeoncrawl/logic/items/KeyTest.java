package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class KeyTest {

  private Key key;

  @BeforeEach
  public void setKey() {
    key = new Key(new Cell(new GameMap(10, 10, CellType.EMPTY), 0, 0, CellType.EMPTY));
  }

  @Test
  public void whenCreateNewKey_allItsFieldsHaveCorrectValues() {
    assertNotNull(key);
    assertNotNull(key.getCell());
  }

  @Test
  public void keyGetTileNameReturnsCorrectValue() {
    assertEquals("key", key.getTileName());
  }

  @Test
  public void keyToStringReturnsCorrectValue() {
    assertEquals("KEY", key.toString());
  }
}