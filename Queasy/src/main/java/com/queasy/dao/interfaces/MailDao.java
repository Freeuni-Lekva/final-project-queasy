package com.queasy.dao.interfaces;

import com.queasy.model.user.Mail;

import java.util.List;

public interface MailDao {

    List<Mail> getSentMails(String username);

    List<Mail> getReceivedMails(String username);

    List<Mail> getSentMails(String fromUserName, String toUserName);

    List<Mail> getReceivedMails(String fromUserName,String toUserName);

    List<Mail> getEmailById(int id);

    // List<Mail> getSentMails(String email);

    //List<Mail> getReceivedMails(String email);


}
