package com.queasy.model.quiz;

import com.queasy.utility.enums.QuestionType;

import java.util.List;

public class Question {

    private int id;
    private String text;
    private QuestionType questionType;
    private int creatorId;
    private List<Pictures> pictures;

    public Question(int id, String text, QuestionType questionType, int creatorId, List<Pictures> pictures ) {
        this.id = id;
        this.text = text;
        this.questionType = questionType;
        this.creatorId = creatorId;
        this.pictures = pictures;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public List<Pictures> getPictures() {
        return pictures;
    }

    public void setPictures(List<Pictures> pictures) {
        this.pictures = pictures;
    }
}
