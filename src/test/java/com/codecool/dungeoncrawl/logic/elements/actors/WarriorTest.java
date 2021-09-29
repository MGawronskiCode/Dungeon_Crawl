package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class WarriorTest {

  private Warrior warrior;

  @BeforeEach
  public void setWarrior() {
    warrior = new Warrior(new Cell(new GameMap(10, 10, CellType.EMPTY), 0, 0, CellType.EMPTY));
  }

  @Test
  public void whenCreatingNewWarrior_allFieldsHaveCorrectValues() {
    assertNotNull(warrior);
    assertNotNull(warrior.getCell());
    assertEquals(50, warrior.getHealth());
    assertEquals(30, warrior.getAttack());
    assertEquals(15, warrior.getDefence());
  }

  @Test
  public void whenGetTileNameRun_returnCorrectValue() {
    //when
    String getTileNameReturn = warrior.getTileName();
    //then
    assertEquals("warrior", getTileNameReturn);
  }

}