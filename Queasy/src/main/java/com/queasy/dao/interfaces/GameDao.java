package com.queasy.dao.interfaces;

import com.queasy.model.game.Game;

import java.util.List;

public interface GameDao {
    List<Game>getAllGames();

    List<Game> getAllGamesOf(String userName);

    List<Game> getAllGames(int quizId);
    //TODO: need to test
    List<Game> getAllGamesOrderedForScoring(int quizId);
    Game getGame(int gameId);

    int getScore(int gameId);

    boolean addGame(Game game);
}
