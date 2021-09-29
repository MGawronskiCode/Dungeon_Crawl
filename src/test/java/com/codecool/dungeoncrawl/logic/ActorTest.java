package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.elements.actors.Player;
import com.codecool.dungeoncrawl.logic.elements.actors.Skeleton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ActorTest {
  GameMap gameMap;

  @BeforeEach
  public void setGameMap() {
    gameMap = new GameMap(3, 3, CellType.FLOOR);
  }

  @Test
  void moveUpdatesCells() {
//      when
    Player player = new Player(gameMap.getCell(1, 1), gameMap);
    player.move(1, 0);
//      then
    assertEquals(2, player.getX());
    assertEquals(1, player.getY());
    assertNull(gameMap.getCell(1, 1).getActor());
    assertEquals(player, gameMap.getCell(2, 1).getActor());
  }

  @Test
  void cannotMoveIntoWall() {
//    when
    gameMap.getCell(2, 1).setType(CellType.WALL);
    Player player = new Player(gameMap.getCell(1, 1), gameMap);
    player.move(1, 0);
//    then
    assertEquals(1, player.getX());
    assertEquals(1, player.getY());
  }

  @Test
  void cannotMoveOutOfMap() {
//    when
    Player player = new Player(gameMap.getCell(2, 1), gameMap);
    player.move(1, 0);
//    then
    assertEquals(2, player.getX());
    assertEquals(1, player.getY());
  }

  @Test
  void cannotMoveIntoAnotherActor() {
//    when
    Player player = new Player(gameMap.getCell(1, 1), gameMap);
    Skeleton skeleton = new Skeleton(gameMap.getCell(2, 1));
    player.move(1, 0);
//    then
    assertEquals(1, player.getX());
    assertEquals(1, player.getY());
    assertEquals(2, skeleton.getX());
    assertEquals(1, skeleton.getY());
    assertEquals(skeleton, gameMap.getCell(2, 1).getActor());
  }
}