package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ItemTest {

  private Item item;

  @Test
  public void whenNewItemCreated_allItsFieldsHaveCorrectValues() {
//    when
    setItem();
//    then
    assertNotNull(item);
    assertNotNull(item.getCell());
    assertEquals("item", item.getName());
    assertEquals(item, item.getCell().getItem());
  }

  private void setItem() {
    item = new Item(new Cell(new GameMap(10, 10, CellType.EMPTY), 0, 0, CellType.EMPTY), "item") {
      @Override
      public String getTileName() {
        return null;
      }
    };
  }

}