package com.codecool.dungeoncrawl.logic.dao;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.elements.actors.Player;
import com.codecool.dungeoncrawl.logic.model.GameState;
import com.codecool.dungeoncrawl.logic.model.PlayerModel;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class GameDatabaseManager {

    private PlayerDao playerDao;
    private GameStateDao gameStateDao;



    public void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
        gameStateDao = new GameStateDaoJdbc(dataSource);
    }

    public void saveGame(GameMap map, Player player) {
        PlayerModel playerModel = new PlayerModel(player);
        playerDao.add(playerModel);
        System.out.println("Player ID is " + playerModel.getId());
        String currentMap = map.getCurrentMap();
        GameState state = new GameState(currentMap,null, playerModel);
        gameStateDao.add(state);
    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = "java_crawl";
        String user = "postgres";
        String password = "692926997";

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }
}
