package com.queasy.model.quiz;

import java.util.List;

public class Answer {
    private int id;
    private String text;
    private int questionId;

    private List<Picture> pictures;

    private String isRightAnswer;

    public Answer(int id, String text, String isRightAnswer, int questionId, List<Picture> pictures) {
        this.id = id;
        this.text = text;
        this.questionId = questionId;
        this.pictures = pictures;
        this.isRightAnswer = isRightAnswer;
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

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public String getIsRightAnswer() {
        return isRightAnswer;
    }

    public void setIsRightAnswer(String isRightAnswer) {
        this.isRightAnswer = isRightAnswer;
    }
}
