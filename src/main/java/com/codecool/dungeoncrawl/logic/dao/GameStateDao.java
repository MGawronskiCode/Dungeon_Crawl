package com.codecool.dungeoncrawl.logic.dao;

import com.codecool.dungeoncrawl.logic.model.GameState;

import java.util.List;

public interface GameStateDao {
    void add(GameState state);
    void update(GameState state);
    GameState get(int id);
    List<GameState> getAll();
}
