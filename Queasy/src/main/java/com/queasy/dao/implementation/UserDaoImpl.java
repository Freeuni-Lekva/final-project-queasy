package com.queasy.dao.implementation;

import com.queasy.dao.interfaces.ConnectionPool;
import com.queasy.dao.interfaces.UserDao;
import com.queasy.model.user.User;
import com.queasy.utility.constants.MyConstants;
import com.queasy.utility.constants.StaticMethods;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private final ConnectionPool connectionPool;
    public UserDaoImpl(ConnectionPool connectionPool){
        this.connectionPool = connectionPool;
    }

    private User getUserHelper(Connection con, String query)
    {
        try {
            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery(query);
            if(res.next()) {
                return new User(res.getString(MyConstants.USER_NAME),
                        res.getString(MyConstants.USER_MAIL),
                        res.getString(MyConstants.USER_PASSWORD));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUser(int id) {
        Connection con = connectionPool.acquireConnection();
        String query = "SELECT * FROM " + MyConstants.USERS_DATABASE
                    + " WHERE " + MyConstants.ID + " = " + Integer.toString(id) + ";";
        User user = getUserHelper(con,query);
        connectionPool.releaseConnection(con);
        return user;
    }

    @Override
    public User getUser(String userNameOrEmail) {
        String searchFor = "";
        if(StaticMethods.isEmail(userNameOrEmail))
            searchFor = MyConstants.USER_MAIL;
        else
            searchFor = MyConstants.USER_NAME;

        Connection con = connectionPool.acquireConnection();
        String query = "SELECT * FROM " + MyConstants.USERS_DATABASE
                + " WHERE " + searchFor + " = " + StaticMethods.apostropheString(userNameOrEmail) + ";";
        User user = getUserHelper(con,query);
        connectionPool.releaseConnection(con);
        return user;
    }

    private boolean checkFreeColumnValueInUser(String columnName, String value)
    {
        Connection con = connectionPool.acquireConnection();
        String query = "SELECT * FROM " + MyConstants.USERS_DATABASE
                + " WHERE " + columnName + " = " + StaticMethods.apostropheString(value) + ";";
        User user = getUserHelper(con,query);
        connectionPool.releaseConnection(con);
        if(user == null)
            return true;
        return false;
    }

    private boolean checkFreeUserName(String userName) {
        return checkFreeColumnValueInUser(MyConstants.USER_NAME,userName);
    }

    private boolean checkFreeMail(String email) {
        return checkFreeColumnValueInUser(MyConstants.USER_MAIL,email);
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
    public boolean addUser(String userName, String password, String email) {
        if(checkFreeUserName(userName) && checkFreeMail(email)
                && !userName.contains(MyConstants.USERNAME_MUST_NOT_CONTAIN) && //these to may be put in other place
                email.contains(MyConstants.EMAIL_MUST_CONTAIN)){
            String apostropheUserName = StaticMethods.apostropheString(userName);
            String apostrophePassword = StaticMethods.apostropheString(password);
            String apostropheEmail = StaticMethods.apostropheString(email);

            String[] columns = {MyConstants.USER_NAME,MyConstants.USER_PASSWORD,MyConstants.USER_MAIL};
            String[] values = {apostropheUserName,apostrophePassword,apostropheEmail};

            String query = StaticMethods.insertQuery(MyConstants.USERS_DATABASE,
                                              columns,
                                              values);
            return executeUpdating(query);
        }
        return false;
    }

    @Override
    public boolean updatePassword(String userName, String newPassword) {
        if(!checkFreeUserName(userName)){
            String apostropheUserName = StaticMethods.apostropheString(userName);
            String apostrophePassword = StaticMethods.apostropheString(newPassword);

            String[] columns = {MyConstants.USER_PASSWORD};
            String[] values = {apostrophePassword};
            String condition =  MyConstants.USER_NAME + " = " + apostropheUserName;

            String query = StaticMethods.updateQuery(MyConstants.USERS_DATABASE,
                                                     columns,
                                                     values,
                                                     condition);

            return executeUpdating(query);
        }
        return false;
    }

    @Override
    public boolean updateEmail(String userName, String newEmail) {
        if(!checkFreeUserName(userName) && checkFreeMail(newEmail)){
            String apostropheUserName = StaticMethods.apostropheString(userName);
            String apostropheEmail = StaticMethods.apostropheString(newEmail);

            String[] columns = {MyConstants.USER_MAIL};
            String[] values = {apostropheEmail};
            String condition = MyConstants.USER_NAME + " = " + apostropheUserName;

            String query = StaticMethods.updateQuery(MyConstants.USERS_DATABASE,
                    columns,
                    values,
                    condition);

            return executeUpdating(query);
        }

        return false;
    }

    @Override
    public boolean updateUserName(String oldUserName, String newUserName) {
        if(!checkFreeUserName(oldUserName) && checkFreeUserName(newUserName)){
            String apostropheOldUserName = StaticMethods.apostropheString(oldUserName);
            String apostropheNewUserName = StaticMethods.apostropheString(newUserName);

            String[] columns = {MyConstants.USER_NAME};
            String[] values = {apostropheNewUserName};
            String condition = MyConstants.USER_NAME + " = " + apostropheOldUserName;

            String query = StaticMethods.updateQuery(MyConstants.USERS_DATABASE,
                    columns,
                    values,
                    condition);
            return executeUpdating(query);
        }
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        String[] columns = {};
        String query = StaticMethods.selectQuery(MyConstants.USERS_DATABASE,columns,MyConstants.emptyString);
        List<User> users = new ArrayList();
        try {
            Connection con = connectionPool.acquireConnection();
            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery(query);
            while(res.next()) {
                users.add(new User(res.getString(MyConstants.USER_NAME),
                        res.getString(MyConstants.USER_MAIL),
                        res.getString(MyConstants.USER_PASSWORD)));
            }
            connectionPool.releaseConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
