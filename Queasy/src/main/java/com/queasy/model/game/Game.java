package com.queasy.model.game;

import java.util.Date;

public class Game {
    private int id;
    private int score;
    private Date startDate;
    private Date endDate;
    private String userName;
    private int quizId;


    public Game(int id, int score, Date startDate, Date endDate, String userName, int quizId) {
        this.id = id;
        this.score = score;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userName = userName;
        this.quizId = quizId;
    }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }
}
