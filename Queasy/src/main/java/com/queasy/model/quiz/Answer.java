package com.queasy.model.quiz;

public class Answer {
    private int id;
    private String text;
    private int questionId;

    public Answer(int id, String text, int questionId) {
        this.id = id;
        this.text = text;
        this.questionId = questionId;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
}
