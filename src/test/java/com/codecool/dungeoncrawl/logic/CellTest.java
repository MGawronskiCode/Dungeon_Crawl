package com.codecool.dungeoncrawl.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CellTest {
  GameMap map;

  @BeforeEach
  public void setMap() {
    map = new GameMap(3, 3, CellType.FLOOR);
  }

  @Test
  void getNeighbor() {
//      when
    Cell cell = map.getCell(1, 1);
    Cell neighbor = cell.getNeighbor(-1, 0);
//    then
    assertEquals(0, neighbor.getX());
    assertEquals(1, neighbor.getY());
  }

  @Test
  void cellOnEdgeHasNoNeighbor() {
    Cell cell = map.getCell(1, 0);
    assertNull(cell.getNeighbor(0, -1));

    cell = map.getCell(1, 2);
    assertNull(cell.getNeighbor(0, 1));
  }
}