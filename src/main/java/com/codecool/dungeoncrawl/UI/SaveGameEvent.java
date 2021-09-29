package com.codecool.dungeoncrawl.UI;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.elements.actors.Player;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

public class SaveGameEvent implements EventHandler<KeyEvent> {

    private final KeyCombination saveGameShortcut;
    private GameMap map;
    private Player player;

    //    argument do eventu który przeniesie dane do zapisu
    public SaveGameEvent(GameMap map, Player player) {
        this.map = map;
        this.player = player;
        this.saveGameShortcut = new KeyCodeCombination(KeyCode.S, KeyCodeCombination.CONTROL_DOWN);
    }


    @Override
    public void handle(KeyEvent keyEvent) {
        if (saveGameShortcut.match(keyEvent)) {
            System.out.println("Shortcut works!");
            SaveGamePopup saveGamePopup = new SaveGamePopup(map,player);
			saveGamePopup.showAndWait();
        }

    }
}
