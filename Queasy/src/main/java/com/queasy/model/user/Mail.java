package com.queasy.model.user;

import java.util.Date;

public class Mail {

    private int id;
    private String subject;
    private String text;
    private Date date;

    private String from;
    private String to;


    public Mail(int id, String subject, String text, Date date, String from, String to) {
        this.id = id;
        this.subject = subject;
        this.text = text;
        this.date = date;
        this.from = from;
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
    public int getId() {
        return id;
    }

}
