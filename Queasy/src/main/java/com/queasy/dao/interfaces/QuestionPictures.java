package com.queasy.dao.interfaces;

import com.queasy.model.quiz.Pictures;

import java.util.List;

public interface QuestionPictures {
    List<Pictures> getPicturesOfQuestion(int questionId);

}
