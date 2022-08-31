package com.queasy.dao.implementation;

import com.queasy.dao.interfaces.ConnectionPool;
import com.queasy.dao.interfaces.PictureDao;
import com.queasy.model.quiz.Picture;
import com.queasy.utility.constants.MyConstants;
import com.queasy.utility.constants.StaticMethods;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PictureDaoImpl implements PictureDao {
    private final ConnectionPool connectionPool;
    public PictureDaoImpl(ConnectionPool connectionPool){
        this.connectionPool = connectionPool;
    }
    @Override
    public Picture getPictureOf(int pictureId) {
        String query = "SELECT * FROM " + MyConstants.PicturesDatabaseConstants.DATABASE +
                       " WHERE " + MyConstants.ID + " = " + Integer.toString(pictureId) + ";";
        Connection con = connectionPool.acquireConnection();
        Picture picture = null;
        try {
            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery(query);
            if(res.next()) {
                picture = new Picture(res.getInt(MyConstants.ID),res.getString(MyConstants.PicturesDatabaseConstants.PICTURE));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.releaseConnection(con);

        return picture;
    }

    @Override
    public Picture getPictureOf(String pictureUrl) {
        String query = "SELECT * FROM " + MyConstants.PicturesDatabaseConstants.DATABASE +
                " WHERE " + MyConstants.PicturesDatabaseConstants.PICTURE + " = " +
                StaticMethods.apostropheString(pictureUrl) + ";";
        Connection con = connectionPool.acquireConnection();
        Picture picture = null;
        try {
            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery(query);
            if(res.next()) {
                picture = new Picture(res.getInt(MyConstants.ID),res.getString(MyConstants.PicturesDatabaseConstants.PICTURE));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.releaseConnection(con);

        return picture;
    }
}
