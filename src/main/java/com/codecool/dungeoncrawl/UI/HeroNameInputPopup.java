package com.codecool.dungeoncrawl.UI;

import javafx.scene.control.TextInputDialog;
import javafx.stage.Popup;

public class HeroNameInputPopup extends TextInputDialog {

    String heroName;

    public HeroNameInputPopup(String text) {
        super();
        this.setHeaderText("Enter name of your hero!");
    }

    public String getHeroNameEntered() {
        this.heroName = this.getEditor().getText();
        return heroName;
    }
}
