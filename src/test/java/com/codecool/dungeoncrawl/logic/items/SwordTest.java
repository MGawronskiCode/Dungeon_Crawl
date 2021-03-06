package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwordTest {

  private Sword sword;

  @BeforeEach
  private void setSword() {
    sword = new Sword(new Cell(new GameMap(10, 10, CellType.EMPTY), 0, 0, CellType.EMPTY));
  }

  @Test
  public void whenCreatingNewSword_allItsFieldsHaveCorrectValues() {
    assertNotNull(sword);
    assertNotNull(sword.getCell());
    int swordAttack = sword.getAttack();
    assertTrue(swordAttack >= 5 && swordAttack <= 10);
    assertNotNull(sword.toString());
  }

  @Test
  public void swordToStringWorksCorrectly() {
    assertEquals("SWORD +" + sword.getAttack(), sword.toString());
  }
}