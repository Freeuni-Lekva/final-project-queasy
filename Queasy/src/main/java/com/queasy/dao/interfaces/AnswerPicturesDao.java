package com.queasy.dao.interfaces;

import com.queasy.model.quiz.Picture;

import java.util.List;

public interface AnswerPicturesDao {
    List<Picture> getPicturesOfAnswer(int answerId);
}
