package com.queasy.dao.implementation;

import com.queasy.dao.interfaces.ConnectionPool;
import com.queasy.dao.interfaces.MailDao;
import com.queasy.model.user.Mail;

import java.util.List;

public class MailDaoImpl implements MailDao {
    private ConnectionPool connectionPool;

    public MailDaoImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }


    @Override
    public List<Mail> getSentMails(String username) {
        return null;
    }

    @Override
    public List<Mail> getReceivedMails(String username) {
        return null;
    }

    @Override
    public List<Mail> getSentMails(String fromUserName, String toUserName) {
        return null;
    }

    @Override
    public List<Mail> getReceivedMails(String fromUserName, String toUserName) {
        return null;
    }

    @Override
    public List<Mail> getEmailById(int id) {
        return null;
    }

    @Override
    public boolean addMail(Mail mail) {
        return false;
    }
}
