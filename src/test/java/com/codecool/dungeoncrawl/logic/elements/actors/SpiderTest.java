package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SpiderTest {

  private static Spider spider;

  @BeforeAll
  public static void setSpider() {
    spider = new Spider(new Cell(new GameMap(10, 10, CellType.EMPTY), 0, 0, CellType.EMPTY));
  }

  @Test
  public void whenCreatingNewSpider_allFieldsHaveCorrectValues() {
    assertNotNull(spider);
    assertNotNull(spider.getCell());
    assertEquals(30, spider.getHealth());
    assertEquals(20, spider.getAttack());
    assertEquals(5, spider.getDefence());
  }

  @Test
  public void whenGetTileNameRun_returnCorrectValue() {
    //when
    String getTileNameReturn = spider.getTileName();
    //then
    assertEquals("spider", getTileNameReturn);
  }

}