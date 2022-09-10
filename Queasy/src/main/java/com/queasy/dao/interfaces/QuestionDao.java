package com.queasy.dao.interfaces;

import com.queasy.model.quiz.Question;
import com.queasy.utility.enums.QuestionType;

import java.util.List;
//TODO: უნდა დავამატო პირველი  n  ცალი რომ ამოიღოს ან რაღაც რეინჯში

public interface QuestionDao {
//    List<Question> getAllQuestions();
//    List<Question> getAllQuestionsOf(QuestionType qt);
//    List<Question> getAllQuestionsOf(int quizId);

    int addQuestion(Question question);
    boolean removeQuestion(int questionId);
}