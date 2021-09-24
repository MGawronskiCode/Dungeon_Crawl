package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;
import lombok.Getter;

@Getter
public abstract class Item implements Drawable {
  private final Cell cell;
  private final String name;

  public Item(Cell cell, String name) {
    this.cell = cell;
    this.cell.setItem(this);
    this.name = name;
  }
//todo itemType enum

}
