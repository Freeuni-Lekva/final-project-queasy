package com.queasy.dao.interfaces;

import com.queasy.model.quiz.Question;
import com.queasy.utility.enums.QuestionType;

import java.util.List;

public interface QuizDao {

    List<Question> getAllQuestions(int quizId);
    String getDescription(int quizId);

    String getCreator(int quizId);
    List<Question> getTypedQuestions(QuestionType qt);

    //List<Quiz> getAllQuizzes();

}
