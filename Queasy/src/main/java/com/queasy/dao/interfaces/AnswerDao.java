package com.queasy.dao.interfaces;

import com.queasy.model.quiz.Answer;

import java.util.List;

public interface AnswerDao {

    List<Answer> getAllAnswersOf(int questionId);

    List<Answer> getAllRightAnswersOf(int questionId);

    int addAnswer(Answer answer);

}
