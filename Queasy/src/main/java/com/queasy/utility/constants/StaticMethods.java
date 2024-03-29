package com.queasy.utility.constants;

import com.queasy.dao.interfaces.ConnectionPool;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class StaticMethods {


    public static boolean isEmail(String value){
        if (value.contains(MyConstants.EMAIL_MUST_CONTAIN))
            return true;
        return false;
    }

    public static String apostropheString(String str) {
        return "'" + str + "'";
    }

    public static String selectQuery(String tableName, String[] columns, String condition) {
        String query = "SELECT ";
        if(columns.length == 0)
            query += " * ";
        else{
            for(int i = 0;i < columns.length;i++)
            {
                query += columns[i];
                if(i != columns.length - 1)
                    query += ", ";
            }
        }
        query += " FROM " + tableName;
        if(condition != null && condition != MyConstants.emptyString)
        {
            query += " WHERE " + condition;
        }
        query += ";";
        return query;
    }

    public static String insertQuery(String tableName, String[] columns, String[] values) {
        String query = "INSERT INTO " + tableName + " ";
        if(columns.length != values.length && columns.length == 0)
            return MyConstants.emptyQuery;
        query += "( ";
        for(int i = 0; i < columns.length;i++) {
            query += columns[i];
            if(i != columns.length - 1)
                query += ", ";
        }
        query += " ) VALUES ( ";
        for(int i = 0; i < values.length;i++) {
            query += values[i];
            if(i != values.length - 1)
                query += ", ";
        }
        query += " );";
        return query;
    }

    public static String updateQuery(String tableName, String[] columns, String[] values, String condition) {
        String query = "UPDATE " + tableName + " ";
        if(columns.length != values.length && columns.length == 0)
            return MyConstants.emptyQuery;
        query += "SET ";
        for(int i = 0;i < columns.length;i++)
        {
            query += columns[i] + " = " + values[i];
            if(i != columns.length - 1)
                query += ", ";
        }
        if (condition != MyConstants.emptyString && condition != null){
            query += " WHERE ";
            query += condition;
        }
        query += ";";
        return query;
    }

    public static String deleteQuery(String tableName, String condition) {
        String query = "DELETE FROM " + tableName + " ";
        if (!condition.equals(MyConstants.emptyString)) {
            query += " WHERE " + condition;
        }
        query += ";";
        return query;
    }

    public static boolean executeUpdateQuery(ConnectionPool connectionPool, String query) {
        Connection con = connectionPool.acquireConnection();
        try {
            Statement statement = con.createStatement();
            System.out.println(query);
            if(statement.executeUpdate(query) > 0) {
                connectionPool.releaseConnection(con);
                return true;
            }


        } catch (SQLException e) {
            e.printStackTrace();

        }
        connectionPool.releaseConnection(con);
        return false;
    }
    public static java.sql.Timestamp returnJavaSqlDate(java.util.Date date) {
        Long millis = date.getTime();
        java.sql.Timestamp sqlDate = new java.sql.Timestamp(millis);
        return sqlDate;
    }

    public static String hexToString(byte[] bytes) {
        StringBuffer buff = new StringBuffer();
        for (int i=0; i<bytes.length; i++) {
            int val = bytes[i];
            val = val & 0xff;  // remove higher bits, sign
            if (val<16) buff.append('0'); // leading 0
            buff.append(Integer.toString(val, 16));
        }
        return buff.toString();
    }

    public static String returnEncryptedPassword(String password) {
        String encryptedPassword = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");//MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(password.getBytes());
            return hexToString(messageDigest);

        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        return null;
    }

}
