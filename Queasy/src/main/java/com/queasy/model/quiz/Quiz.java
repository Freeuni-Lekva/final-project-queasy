package com.queasy.model.quiz;

import java.util.List;

public class Quiz {

    private int id;
    private String quizName;
    private int creatorId;
    private String description;

    private List<Question> questions;

    public Quiz(int id, String quizName, int creatorId, String description , List<Question> questions ) {
        this.id = id;
        this.quizName = quizName;
        this.creatorId = creatorId;
        this.description = description;
        this.questions = questions;
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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }
}
