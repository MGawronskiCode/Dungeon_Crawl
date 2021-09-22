package com.codecool.dungeoncrawl.UI;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

public class SaveGameEvent implements EventHandler<KeyEvent> {

    private final KeyCombination saveGameShortcut;

//    argument do eventu który przeniesie dane do zapisu
    public SaveGameEvent() {
        this.saveGameShortcut = new KeyCodeCombination(KeyCode.S, KeyCodeCombination.CONTROL_DOWN);
    }


    @Override
    public void handle(KeyEvent keyEvent) {
        if (saveGameShortcut.match(keyEvent)) {
            System.out.println("Shortcut works!");
            SaveGamePopup saveGamePopup = new SaveGamePopup();
//			saveGamePopup.showAndwait();
        }

    }
}
