package com.codecool.dungeoncrawl.logic.elements;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoorTest {

  private static Door door;

  //todo jak ustawić kolejność wywoływania testów
  @Test
  public void whenCreatingNewDoor_allFieldsHaveCorrectValues() {
    //when
    setDoor();
    //then
    assertNotNull(door);
    assertNotNull(door.getCell());
    assertFalse(door.isOpen());
  }

  private void setDoor() {
    door = new Door(new Cell(new GameMap(10, 10, CellType.EMPTY), 0, 0, CellType.EMPTY));
  }

  @Test
  public void whenOpenDoors_isOpenedReturnsTrue() {
    //when
    setDoor();
    if (!door.isOpen())
      door.open();
    //then
    assertTrue(door.isOpen());
  }

  @Test
  public void whenDoorsOpened_getTileNameReturnsCorrectValue() {
    //when
    setDoor();
//    then
    if (door.isOpen())
      assertEquals("openedDoor", door.getTileName());
  }

  @Test
  public void whenDoorsClosed_getTileNameReturnsCorrectValue() {
    //when
    setDoor();
//    then
    if (!door.isOpen())
      assertEquals("closedDoor", door.getTileName());
  }

}