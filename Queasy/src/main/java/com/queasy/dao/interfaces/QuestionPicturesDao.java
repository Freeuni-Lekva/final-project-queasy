package com.queasy.dao.interfaces;

import com.queasy.model.quiz.Picture;

import java.util.List;

public interface QuestionPicturesDao {
    List<Picture> getPicturesOfQuestion(int questionId);

}
