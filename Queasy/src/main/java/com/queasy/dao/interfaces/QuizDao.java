package com.queasy.dao.interfaces;

import com.queasy.model.quiz.Question;

import java.util.List;

public interface QuizDao {

    List<Question> getAllQuestions(int quiz_id);


}
