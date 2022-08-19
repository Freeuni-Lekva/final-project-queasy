package com.queasy.dao.implementation;

import com.queasy.dao.interfaces.ConnectionPool;
import com.queasy.dao.interfaces.MailDao;
import com.queasy.model.user.Mail;
import com.queasy.utility.constants.MyConstants;
import com.queasy.utility.constants.StaticMethods;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        try {
            Connection con = connectionPool.acquireConnection();
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

    @Override
    public List<Mail> getEmailById(int id) {
        String[] columns = {};
        String condition = MyConstants.ID + " = " + Integer.toString(id);
        String query = StaticMethods.selectQuery(MyConstants.MAILS_DATABASE,
                columns,
                condition);

        return getMailsHelper(query);
    }

    @Override
    public boolean addMail(Mail mail) {
        String[] columns = {MyConstants.MAILS_SUBJECT,
                            MyConstants.MAILS_TEXT,
                            MyConstants.MAILS_FROM_USER_NAME,
                            MyConstants.MAILS_TO_USER_NAME};
        String[] values = {StaticMethods.apostropheString(mail.getSubject()),
                StaticMethods.apostropheString(mail.getText()),
                StaticMethods.apostropheString(mail.getFrom()),
                StaticMethods.apostropheString(mail.getTo())};
        String query = StaticMethods.insertQuery(MyConstants.MAILS_DATABASE,
                                                columns,
                                                values);

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
}
