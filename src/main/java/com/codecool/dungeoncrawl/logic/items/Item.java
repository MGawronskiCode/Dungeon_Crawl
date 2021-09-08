package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Item implements Drawable {
    private Cell cell;
    private final String name;

    public Item(Cell cell, String name) {
        this.cell = cell;
        this.cell.setItem(this);
        this.name = name;
    }

    public void pick() {
//        public void pick(inventory)
//        TODO cell.setItem(null);
//        TODO this.cell = null;
//        TODO inventory.put(this);
//        TODO print inventory
    }

    public Cell getCell() {
        return cell;
    }


}
