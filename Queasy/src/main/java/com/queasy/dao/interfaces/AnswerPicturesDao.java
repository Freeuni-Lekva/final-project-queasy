package com.queasy.dao.interfaces;

import com.queasy.model.quiz.Pictures;

import java.util.List;

public interface AnswerPicturesDao {
    List<Pictures> getPicturesOfAnswer(int answerId);
}
