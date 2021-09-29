package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

  Inventory inventory = new Inventory();

  @Test
  public void whenCreatingNewInventory_allItsFieldsHaveCorrectValues() {
    assertNotNull(inventory);
    assertNotNull(inventory.getItems());
    assertEquals(0, inventory.getItems().size());
  }

  @Test
  public void whenAddItemLaunchedOnInventory_newObjectIsAddedCorrectly() {
//    when
    inventory.addItem(new Hauberk(new Cell(new GameMap(10, 10, CellType.EMPTY), 0, 0, CellType.EMPTY)));
//    then
    assertNotNull(inventory.getItems());
    assertEquals(1, inventory.getItems().size());
    assertNotNull(inventory.getItems().get(0));
  }

  @Test
  public void whenItemInInventoryAndLaunchedRemoveItem_itemIsCorrectlyRemoved() {
//    when
    if (inventory.getItems().size() == 0)
      inventory.addItem(new Hauberk(new Cell(new GameMap(10, 10, CellType.EMPTY), 0, 0, CellType.EMPTY)));
    inventory.removeItem(inventory.getItems().get(0));
//    then
    assertNotNull(inventory.getItems());
    try {
      assertEquals(0, inventory.getItems().size());
    } catch (IndexOutOfBoundsException ignored) {
    }
  }

  @Test
  public void whenRemoveItemLaunchedWithNullArgument_theresNoErrorAndNoElementRemoved() {
//    when
    int inventorySizeBeforeDeleting = inventory.getItems().size();
    inventory.removeItem(null);
//    then
    assertEquals(inventorySizeBeforeDeleting, inventory.getItems().size());
  }

  @Test
  public void whenRemoveItemLaunchedWithObjectNotInInventory_theresNoErrorAndNoElementRemoved() {
//    when
    int inventorySizeBeforeDeleting = inventory.getItems().size();
    inventory.removeItem(new Hauberk(new Cell(new GameMap(10, 10, CellType.EMPTY), 0, 0, CellType.EMPTY)));
//    then
    assertEquals(inventorySizeBeforeDeleting, inventory.getItems().size());
  }

  @Test
  public void inventoryToStringReturnsCorrectValuesInEveryCase() {
    inventory = new Inventory();
    assertEquals("", inventory.toString());
    Hauberk hauberk = new Hauberk(new Cell(new GameMap(10, 10, CellType.EMPTY), 0, 0, CellType.EMPTY));
    inventory.addItem(hauberk);
    assertEquals("HAUBERK +" + hauberk.getDefence() + "\n", inventory.toString());
    inventory.addItem(hauberk);
    assertEquals("HAUBERK +" + hauberk.getDefence() + "\nHAUBERK +" + hauberk.getDefence() + "\n", inventory.toString());
  }

  @Test
  public void inventoryGetAttackReturnsCorrectValue() {
    inventory = new Inventory();
    assertEquals(0, inventory.getAttack());
    inventory.addItem(new Sword(new Cell(new GameMap(10, 10, CellType.EMPTY), 0, 0, CellType.EMPTY)));
    int attackAfterAddingSword = inventory.getAttack();
    assertTrue(attackAfterAddingSword > 0 && attackAfterAddingSword <= 10);
  }

  @Test
  public void inventoryGetDefenceReturnsCorrectValue() {
    inventory = new Inventory();
    assertEquals(0, inventory.getDefence());
    inventory.addItem(new Hauberk(new Cell(new GameMap(10, 10, CellType.EMPTY), 0, 0, CellType.EMPTY)));
    int attackAfterAddingHauberk = inventory.getDefence();
    assertTrue(attackAfterAddingHauberk > 0 && attackAfterAddingHauberk <= 10);
  }

  @Test
  public void hasKeyReturnsCorrectValue_dependingOnInventory() {
    assertFalse(inventory.hasKey());
    inventory.addItem(new Key(new Cell(new GameMap(10, 10, CellType.EMPTY), 0, 0, CellType.EMPTY)));
    assertTrue(inventory.hasKey());
  }
}