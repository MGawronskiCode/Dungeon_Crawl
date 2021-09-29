package com.codecool.dungeoncrawl.logic.elements;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ElementTest {

  private Element element;

  @BeforeEach
  public void setElement() {
    element = new Element(new Cell(new GameMap(10, 10, CellType.EMPTY), 0, 0, CellType.EMPTY)) {
      @Override
      public String getTileName() {
        return null;
      }
    };
  }

  @Test
  public void whenCreateNewElement_allItsFieldsHaveCorrectValues() {
    assertNotNull(element);
    assertNotNull(element.getCell());
  }
}