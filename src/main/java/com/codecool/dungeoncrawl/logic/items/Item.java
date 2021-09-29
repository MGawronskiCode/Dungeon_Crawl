package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;
import lombok.Getter;

@Getter
public abstract class Item implements Drawable {
  private final Cell cell;
  private final ItemType type;

  public Item(Cell cell, ItemType type) {
    this.cell = cell;
    this.cell.setItem(this);
    this.type = type;
  }
}
