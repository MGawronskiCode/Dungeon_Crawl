package com.codecool.dungeoncrawl.logic.elements;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoorTest {

  private Door door;

  @BeforeEach
  public void setDoor() {
    door = new Door(new Cell(new GameMap(10, 10, CellType.EMPTY), 0, 0, CellType.EMPTY));
  }

  @Test
  public void whenCreatingNewDoor_allFieldsHaveCorrectValues() {
    assertNotNull(door);
    assertNotNull(door.getCell());
    assertFalse(door.isOpen());
  }

  @Test
  public void whenOpenDoors_isOpenedReturnsTrue() {
    if (!door.isOpen())
      door.open();
    assertTrue(door.isOpen());
  }

  @Test
  public void whenDoorsOpened_getTileNameReturnsCorrectValue() {
    if (door.isOpen())
      assertEquals("openedDoor", door.getTileName());
  }

  @Test
  public void whenDoorsClosed_getTileNameReturnsCorrectValue() {
    if (!door.isOpen())
      assertEquals("closedDoor", door.getTileName());
  }
}