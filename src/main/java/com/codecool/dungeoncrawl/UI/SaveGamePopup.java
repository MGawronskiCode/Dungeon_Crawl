package com.codecool.dungeoncrawl.UI;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.elements.actors.Player;
import com.codecool.dungeoncrawl.logic.model.GameState;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import lombok.SneakyThrows;

import java.sql.SQLException;
import java.util.Date;
import java.sql.Timestamp;

public class SaveGamePopup extends TextInputDialog {

    String name;

    public SaveGamePopup(GameMap map, Player player) {
        super();
        this.setHeaderText("Enter your name to save the game:");
        this.setContentText("Name");
        this.name = super.getEditor().getText();

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        this.getDialogPane().getButtonTypes().setAll(saveButtonType, cancelButtonType);


        final Button save = (Button) this.getDialogPane().lookupButton(saveButtonType);
        save.addEventFilter(ActionEvent.ACTION, event -> {
            player.setName(this.getEditor().getText());
            GameDatabaseManager manager = new GameDatabaseManager();
            try {
                manager.setup();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            manager.saveGame(map,player);
            //TODO if id in database then(next popup)
                    //TODO gdzie trzymac managera
        });


    }

}

//        EventHandler<ActionEvent> saveEvent = new EventHandler<ActionEvent>() {
//            @SneakyThrows
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                GameDatabaseManager manager = new GameDatabaseManager();
//                manager.setup();
//                manager.saveGame(map,player);
//            }
//        };
//        save.setOnAction(saveEvent);