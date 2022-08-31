package com.queasy.dao;

import com.queasy.dao.implementation.DBConnectionPool;
import com.queasy.dao.implementation.FollowingDaoImpl;
import com.queasy.dao.implementation.GameDaoImpl;
import com.queasy.dao.interfaces.ConnectionPool;
import com.queasy.dao.interfaces.FollowingDao;
import com.queasy.dao.interfaces.GameDao;
import com.queasy.model.game.Game;
import com.queasy.utility.constants.StaticMethods;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TestGameDao {
    private static ConnectionPool connectionPool;
    private static GameDao gameDao;

    @Before
    public void setUp() {
        connectionPool = DBConnectionPool.getInstance(30);
        gameDao = new GameDaoImpl(connectionPool);
    }

    @Test
    public void testGetAllGamesOf() {
        Assert.assertTrue(gameDao.getAllGamesOf("user3").size() == 2);
        Assert.assertTrue(gameDao.getAllGamesOf("user2").get(0).getUserName().equals("user2"));
        Assert.assertTrue(gameDao.getAllGamesOf("user2").get(0).getScore() == 15);
        Assert.assertTrue(gameDao.getAllGamesOf("user2").get(0).getQuizId()== 1);
    }

    @Test
    public void testGetAllGames() {
        Assert.assertTrue(gameDao.getAllGames(1).size() == 3);
        Assert.assertTrue(gameDao.getAllGames(1).get(0).getUserName().contains("user"));
    }

    @Test
    public void testGetGame() throws ParseException {
        Assert.assertTrue(gameDao.getGame(0).getScore() == 10);
        Assert.assertTrue(gameDao.getGame(1).getScore() == 11);
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-DD-MM HH:MM:SS", Locale.ENGLISH);
        Assert.assertTrue(gameDao.getGame(1).getStartDate() == formatter.parse("1990-07-07 23:40:00"));
    }

    @Test
    public void testScore() {
        Assert.assertTrue(gameDao.getScore(1) == 10);
        Assert.assertTrue(gameDao.getScore(2) == 15);
        Assert.assertTrue(gameDao.getScore(3) == 11);
    }

    @Test
    public void testAdd() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-DD-MM", Locale.ENGLISH);
        Assert.assertTrue(gameDao.addGame(new Game(0,102,formatter.parse("1990-01-01"),
                formatter.parse("1990-02-02"),"user1",1)));
       // Assert.assertTrue(gameDao.getGame(4) != null);
    }


}
