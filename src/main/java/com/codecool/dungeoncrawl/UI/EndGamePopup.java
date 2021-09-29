package com.codecool.dungeoncrawl.UI;

import com.codecool.dungeoncrawl.Main;
import javafx.scene.control.Label;
import javafx.stage.Popup;

public class EndGamePopup extends Popup {

    private Main main;

    public EndGamePopup(Main main) {
        super();
        this.main = main;
        Label label = new Label("GAME OVER!");
//        label.setStyle(" ");
        label.setStyle("-fx-font-size:50;-fx-font-weight:bold; -fx-background-color: red; -fx-alignment: center;");
        label.setMinWidth(500);
        label.setMinHeight(400);
        super.getContent().add(label);


        //
//        try {
//            main.newGame();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    }
}
