package com.queasy.dao.interfaces;

import com.queasy.model.quiz.Question;
import com.queasy.utility.enums.QuestionType;

import java.util.List;

public interface QuestionDao {
    List<Question> getAllQuestions();
    List<Question> getAllQuestionsOf(QuestionType qt);
    List<Question> getAllQuestionsOf(int quizId);

    boolean addQuestion(Question question);
}