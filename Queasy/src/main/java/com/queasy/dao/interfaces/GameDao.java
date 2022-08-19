package com.queasy.dao.interfaces;

import com.queasy.model.game.Game;

import java.util.List;

public interface GameDao {

    List<Game> getAllGamesOf(int userId);

    List<Game> getAllGamesOf(String userName);

    List<Game> getAllGames(int quizId);

    Game getGame(int gameId);

    int getScore(int gameId);

    boolean addGame(Game game);
}