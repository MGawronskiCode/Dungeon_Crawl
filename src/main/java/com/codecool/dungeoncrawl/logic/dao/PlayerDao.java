package com.codecool.dungeoncrawl.logic.dao;

import com.codecool.dungeoncrawl.logic.model.PlayerModel;

import java.util.List;

public interface PlayerDao {
    void add(PlayerModel player);
    void update(PlayerModel player);
    PlayerModel get(int id);
    List<PlayerModel> getAll();
}
