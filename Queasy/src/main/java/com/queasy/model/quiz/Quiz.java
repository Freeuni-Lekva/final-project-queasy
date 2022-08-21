package com.queasy.model.quiz;

public class Quiz {

    private int id;
    private String quizName;
    private int creatorId;
    private String description;


    public Quiz(int id, String quizName, int creatorId, String description) {
        this.id = id;
        this.quizName = quizName;
        this.creatorId = creatorId;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getQuizName() {
        return quizName;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public String getDescription() {
        return description;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
