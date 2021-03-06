package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ActorTest {

  private Actor actor;

  @BeforeEach
  public void setActor() {
    actor = new Actor(new Cell(null, 0, 0, null)) {
      @Override
      public String getTileName() {
        return null;
      }
    };
  }

  @Test
  public void whenCreatingNewActor_NotNullAndAllFieldsInitializedCorrectly() {
    assertNotNull(actor);
    assertNotNull(actor.getCell());
    assertNotNull(actor.getRandom());
    assertEquals(-1, actor.getHealth());
    assertEquals(-1, actor.getAttack());
    assertEquals(-1, actor.getDefence());
  }
}