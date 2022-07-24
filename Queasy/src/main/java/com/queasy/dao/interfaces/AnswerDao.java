package com.queasy.dao.interfaces;

import com.queasy.model.quiz.Answer;

import java.util.List;

public interface AnswerDao {

    List<Answer> getAllAnswers(int questionId);
}
