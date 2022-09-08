package com.queasy.dao.implementation;

import com.queasy.dao.interfaces.ConnectionPool;
import com.queasy.dao.interfaces.GameDao;
import com.queasy.model.game.Game;
import com.queasy.utility.constants.MyConstants;
import com.queasy.utility.constants.StaticMethods;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDaoImpl implements GameDao {

    private ConnectionPool connectionPool;
    public GameDaoImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    private List<Game> getListFunc(String query) {
        List<Game> games = new ArrayList();
        Connection con = connectionPool.acquireConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery(query);
            while(res.next()) {
                games.add(new Game(res.getInt(MyConstants.ID),
                                   res.getInt(MyConstants.GameDatabaseConstants.SCORE),
                                   res.getDate(MyConstants.GameDatabaseConstants.START_DATE),
                                   res.getDate(MyConstants.GameDatabaseConstants.END_DATE),
                                   res.getString(MyConstants.GameDatabaseConstants.USER_NAME),
                                   res.getInt(MyConstants.GameDatabaseConstants.QUIZ_ID)

                        ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.releaseConnection(con);
        return games;
    }

    @Override
    public List<Game> getAllGamesOrderedForScoring(int quizId) {
        String[] columns = {};
        String condition = MyConstants.GameDatabaseConstants.QUIZ_ID + " = " + quizId +
                " ORDER BY " + MyConstants.GameDatabaseConstants.SCORE + " DESC , " +
                "TIME_TO_SEC(TIMEDIFF(" + MyConstants.GameDatabaseConstants.END_DATE + " , " +
                MyConstants.GameDatabaseConstants.START_DATE + " ))";
        String query = StaticMethods.selectQuery(MyConstants.GameDatabaseConstants.DATABASE,columns,condition);
//        System.out.println(query);
        List<Game> answer = getListFunc(query);

        return answer;
    }

    @Override
    public List<Game> getAllGamesOf(String userName) {
        String[] columns = {};
        String condition = MyConstants.GameDatabaseConstants.USER_NAME +
                           " = " + StaticMethods.apostropheString(userName);
        String query = StaticMethods.selectQuery(MyConstants.GameDatabaseConstants.DATABASE,columns,condition);
        List<Game> answer = getListFunc(query);
        return answer;
    }
    @Override
    public List<Game> getAllGames() {
        String[] columns = {};
        String condition = "";
        String query = StaticMethods.selectQuery(MyConstants.GameDatabaseConstants.DATABASE,columns,condition);
        List<Game> answer = getListFunc(query);
        return answer;
    }

    @Override
    public List<Game> getAllGames(int quizId) {
        String[] columns = {};
        String condition = MyConstants.GameDatabaseConstants.QUIZ_ID + " = " + quizId;
        String query = StaticMethods.selectQuery(MyConstants.GameDatabaseConstants.DATABASE,columns,condition);
        List<Game> answer = getListFunc(query);
        return answer;
    }

    @Override
    public Game getGame(int gameId) {
        String[] columns = {};
        String condition = MyConstants.ID + " = " + gameId;
        String query = StaticMethods.selectQuery(MyConstants.GameDatabaseConstants.DATABASE,columns,condition);
        List<Game> answer = getListFunc(query);
        return answer.size() > 0 ? answer.get(0) : null;
    }

    @Override
    public int getScore(int gameId) {
        Game game = getGame(gameId);
        if (game == null) {
            return -1;
        }
        return game.getScore();
    }

    @Override
    public boolean addGame(Game game) {
        String sql = "INSERT INTO " + MyConstants.GameDatabaseConstants.DATABASE + " ( "+
                MyConstants.GameDatabaseConstants.QUIZ_ID + " , " +
                MyConstants.GameDatabaseConstants.SCORE + " , " +
                MyConstants.GameDatabaseConstants.USER_NAME + " , " +
                MyConstants.GameDatabaseConstants.START_DATE + " , " +
                MyConstants.GameDatabaseConstants.END_DATE + " ) "  + "  " +
                "VALUES ( ? , ? , ? , ? , ? );";

        Connection con = connectionPool.acquireConnection();

        try (PreparedStatement statement = con.prepareStatement(sql)){
            statement.setInt(1,game.getQuizId());
            statement.setInt(2,game.getScore());
            statement.setString(3,game.getUserName());
            statement.setTimestamp(4, StaticMethods.returnJavaSqlDate(game.getStartDate()));
            statement.setTimestamp(5, StaticMethods.returnJavaSqlDate(game.getEndDate()));

            if(statement.executeUpdate() > 0) {
                connectionPool.releaseConnection(con);
                return true;
            }
        } catch (SQLException e) {
            connectionPool.releaseConnection(con);
            return false;
        }
        connectionPool.releaseConnection(con);
        return false;
    }
}
