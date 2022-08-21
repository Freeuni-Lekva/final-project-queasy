package com.queasy.dao.implementation;

import com.queasy.dao.interfaces.ConnectionPool;
import com.queasy.dao.interfaces.FollowingDao;
import com.queasy.model.user.User;
import com.queasy.utility.constants.MyConstants;
import com.queasy.utility.constants.StaticMethods;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FollowingDaoImpl implements FollowingDao {
    private ConnectionPool connectionPool;


    public FollowingDaoImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }
    private List<User> getFollowingHelper(String query) {
        List<User> users = new ArrayList();
        try {
            Connection con = connectionPool.acquireConnection();
            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery(query);
            while(res.next()) {
                users.add(new User(res.getString(MyConstants.USER_NAME),
                        res.getString(MyConstants.USER_PASSWORD),
                        res.getString(MyConstants.USER_MAIL)));
            }
            connectionPool.releaseConnection(con);
            return users;
        } catch (SQLException e) {
            return users;
        }
    }
    @Override
    public List<User> getFriendsOf(String userName) {
        List<User> users1 = new ArrayList();
        List<User> users2 = new ArrayList();

        String[] columns = {};

        String condition1 = MyConstants.FOLLOWERS_FIRST_USER_USERNAME + " = " + userName;
        String condition2 = MyConstants.FOLLOWERS_SECOND_USER_USERNAME + " = " + userName;

        String query1 = StaticMethods.selectQuery(MyConstants.FOLLOWERS_DATABASE,columns,condition1);
        String query2 = StaticMethods.selectQuery(MyConstants.FOLLOWERS_DATABASE,columns,condition2);

        users1 = getFollowingHelper(query1);
        users2 = getFollowingHelper(query2);
        users1.retainAll(users1);

        return users1;
    }

    @Override
    public List<User> getSentRequestsOf(String userName) {
        List<User> users = new ArrayList<>();
        String[] columns = {};
        String condition = MyConstants.FOLLOWERS_FIRST_USER_USERNAME + " = " + userName;
        String query = StaticMethods.selectQuery(MyConstants.FOLLOWERS_DATABASE,columns,condition);

        users = getFollowingHelper(query);
        users.retainAll(getFriendsOf(userName));
        return users;
    }

    private boolean executeUpdating(String query) {
        Connection con = connectionPool.acquireConnection();
        try {
            Statement statement = con.createStatement();
            if(statement.executeUpdate(query) > 0) {
                connectionPool.releaseConnection(con);
                return true;
            }
            connectionPool.releaseConnection(con);

        } catch (SQLException e) {
            return false;
        }
        return false;
    }

    @Override
    public boolean sendRequest(String fromUserName, String toUserName){

        String[] columns = {MyConstants.FOLLOWERS_FIRST_USER_USERNAME,MyConstants.FOLLOWERS_SECOND_USER_USERNAME};
        String[] values = {StaticMethods.apostropheString(fromUserName),StaticMethods.apostropheString(toUserName)};
        String condition = MyConstants.FOLLOWERS_FIRST_USER_USERNAME  + " = " +
                StaticMethods.apostropheString(fromUserName) + " AND " + MyConstants.FOLLOWERS_SECOND_USER_USERNAME +
                " = " + StaticMethods.apostropheString(toUserName);
        String checkQuery = StaticMethods.selectQuery(MyConstants.FOLLOWERS_DATABASE,columns,condition);
        if(getFollowingHelper(checkQuery).size() > 0 )
            return false;

        String query = StaticMethods.insertQuery(MyConstants.FOLLOWERS_DATABASE,columns,values);

        return executeUpdating(query);
    }
}
