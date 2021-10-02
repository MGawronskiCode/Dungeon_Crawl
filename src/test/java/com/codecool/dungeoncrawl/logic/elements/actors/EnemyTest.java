package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EnemyTest {

  public Enemy enemy;

  @Test
  public void whenCreatingNewEnemy_allItsFieldsHaveCorrectValues() {

    enemy = new Enemy(new Cell(new GameMap(10, 10, CellType.EMPTY), 0, 0, CellType.EMPTY)) {
      @Override
      public String getTileName() {
        return null;
      }
    };
    assertNotNull(enemy);
    assertNotNull(enemy.getCell());
    assertNotNull(enemy.getRandom());
    assertEquals(enemy, enemy.getCell().getActor());
    assertEquals(-1, enemy.getHealth());
    assertEquals(-1, enemy.getAttack());
    assertEquals(-1, enemy.getDefence());
  }

//  todo enemy make move test
}