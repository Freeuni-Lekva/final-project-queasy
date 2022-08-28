package com.queasy.dao.implementation;

import com.queasy.dao.interfaces.ConnectionPool;
import com.queasy.dao.interfaces.MailDao;
import com.queasy.model.user.Mail;
import com.queasy.utility.constants.MyConstants;
import com.queasy.utility.constants.StaticMethods;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MailDaoImpl implements MailDao {
    private ConnectionPool connectionPool;

    public MailDaoImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    private List<Mail> getMailsHelper(String query)
    {
        List<Mail> mails = new ArrayList();
        Connection con = connectionPool.acquireConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery(query);
            while(res.next()) {
                mails.add(new Mail(res.getInt(MyConstants.ID),
                                   res.getString(MyConstants.MAILS_SUBJECT),
                                   res.getString(MyConstants.MAILS_TEXT),
                                   res.getDate(MyConstants.MAILS_DATE),
                                   res.getString(MyConstants.MAILS_FROM_USER_NAME),
                                   res.getString(MyConstants.MAILS_TO_USER_NAME)));
            }
            connectionPool.releaseConnection(con);
            return mails;
        } catch (SQLException e) {
            connectionPool.releaseConnection(con);
            return mails;
        }
    }


    @Override
    public List<Mail> getSentMails(String username) {
        String[] columns = {};
        String condition = MyConstants.MAILS_FROM_USER_NAME + " = " + StaticMethods.apostropheString(username);
        String query = StaticMethods.selectQuery(MyConstants.MAILS_DATABASE,
                                                 columns,
                                                 condition);

        return getMailsHelper(query);
    }

    @Override
    public List<Mail> getReceivedMails(String username) {
        String[] columns = {};
        String condition = MyConstants.MAILS_TO_USER_NAME + " = " + StaticMethods.apostropheString(username);
        String query = StaticMethods.selectQuery(MyConstants.MAILS_DATABASE,
                columns,
                condition);

        return getMailsHelper(query);
    }

    @Override
    public List<Mail> getSentMails(String fromUserName, String toUserName) {
        String[] columns = {};
        String condition = MyConstants.MAILS_FROM_USER_NAME + " = " + StaticMethods.apostropheString(fromUserName) +
                " AND " + MyConstants.MAILS_TO_USER_NAME + " = " + StaticMethods.apostropheString(toUserName);
        String query = StaticMethods.selectQuery(MyConstants.MAILS_DATABASE,
                columns,
                condition);

        return getMailsHelper(query);
    }

    @Override
    public List<Mail> getReceivedMails(String fromUserName, String toUserName) {
        return getSentMails(toUserName,fromUserName);
    }


    //TODO: List არ უნდა იყო Mail უნდა ბრუნდებოდეს
    @Override
    public Mail getEmailById(int id) {
        String[] columns = {};
        String condition = MyConstants.ID + " = " + Integer.toString(id);
        String query = StaticMethods.selectQuery(MyConstants.MAILS_DATABASE,
                columns,
                condition);
        List<Mail> mails = getMailsHelper(query);
        return mails.size() != 0 ? mails.get(0) : null;
    }

    //TODO: to add date
    @Override
    public boolean addMail(Mail mail) {

        String sql = "INSERT INTO " + MyConstants.MAILS_DATABASE + " ( "+
                                 MyConstants.MAILS_FROM_USER_NAME + " , " +
                                 MyConstants.MAILS_TO_USER_NAME + " , " +
                                 MyConstants.MAILS_DATE + " , " +
                                 MyConstants.MAILS_SUBJECT + " , " +
                                 MyConstants.MAILS_TEXT + " ) "  + "  " +
                     "VALUES ( ? , ? , ? , ? , ? );";

        Connection con = connectionPool.acquireConnection();

        try (PreparedStatement statement = con.prepareStatement(sql)){
            statement.setString(1,mail.getFrom());
            statement.setString(2,mail.getTo());
            statement.setDate(3, StaticMethods.returnJavaSqlDate(mail.getDate()));
            statement.setString(4,mail.getSubject());
            statement.setString(5,mail.getText());
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
