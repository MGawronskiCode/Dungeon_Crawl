package com.codecool.dungeoncrawl.logic.elements;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public abstract class Element implements Drawable, Serializable {
  @Getter
  @Setter
  protected transient Cell cell;

  public Element(Cell cell) {
    this.cell = cell;
  }
}
