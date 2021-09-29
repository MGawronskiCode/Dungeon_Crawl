package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SkeletonTest {

  private Skeleton skeleton;

  @BeforeEach
  public void setSkeleton() {
    skeleton = new Skeleton(new Cell(new GameMap(10, 10, CellType.EMPTY), 0, 0, CellType.EMPTY));
  }

  @Test
  public void whenCreatingNewSkeleton_allFieldsHaveCorrectValues() {
    assertNotNull(skeleton);
    assertNotNull(skeleton.getCell());
    assertEquals(30, skeleton.getHealth());
    assertEquals(5, skeleton.getAttack());
    assertEquals(0, skeleton.getDefence());
  }

  @Test
  public void whenGetTileNameRun_returnCorrectValue() {
    //when
    String getTileNameReturn = skeleton.getTileName();
    //then
    assertEquals("skeleton", getTileNameReturn);
  }

}