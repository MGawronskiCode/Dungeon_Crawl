package com.codecool.dungeoncrawl.logic.items;

import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
public class Inventory implements Serializable {
  private final ArrayList<Item> items = new ArrayList<>();

  public void addItem(Item item) {
    items.add(item);
  }

  public void removeItem(Item item) {
    items.remove(item);
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    for (Item i : items) {
      result.append(i.toString()).append("\n");
    }
    return result.toString();
  }

  public int getAttack() {
    int result = 0;
    for (Item i : items) {
      if (i instanceof Sword) {
        result += ((Sword) i).getAttack();
      }
    }
    return result;
  }

  public int getDefence() {
    int result = 0;
    for (Item i : items) {
      if (i instanceof Hauberk) {
        result += ((Hauberk) i).getDefence();
      }
    }
    return result;
  }

  public boolean hasKey() {
    for (Item item : items) {
      if (item.getType() == ItemType.KEY)
        return true;
    }
    return false;
  }
}
