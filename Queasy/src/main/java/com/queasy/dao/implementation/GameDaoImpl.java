package com.queasy.dao.implementation;

import com.queasy.dao.interfaces.ConnectionPool;
import com.queasy.dao.interfaces.GameDao;
import com.queasy.model.game.Game;

import java.util.List;

public class GameDaoImpl implements GameDao {

    private ConnectionPool connectionPool;

    public GameDaoImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<Game> getAllGamesOf(String userName) {
        return null;
    }

    @Override
    public List<Game> getAllGames(int quizId) {
        return null;
    }

    @Override
    public Game getGame(int gameId) {
        return null;
    }

    @Override
    public int getScore(int gameId) {
        return 0;
    }

    @Override
    public boolean addGame(Game game) {
        return false;
    }
}
