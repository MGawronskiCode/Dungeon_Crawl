package com.codecool.dungeoncrawl.logic.elements;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;
import lombok.Getter;
import lombok.Setter;

public abstract class Element implements Drawable {
  @Getter
  @Setter
  protected Cell cell;

  public Element(Cell cell) {
    this.cell = cell;
  }
}
