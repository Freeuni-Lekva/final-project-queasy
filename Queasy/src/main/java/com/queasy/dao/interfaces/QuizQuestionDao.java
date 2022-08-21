package com.queasy.dao.interfaces;

import com.queasy.model.quiz.Question;
import com.queasy.model.quiz.Quiz;

import java.util.List;

public interface QuizQuestionDao {

    List<Question> getQuestionsOf(int quizId);

    Quiz getQuizOfQuestion(int questionId);
}
