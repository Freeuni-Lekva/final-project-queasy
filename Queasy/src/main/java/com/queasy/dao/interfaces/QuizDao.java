package com.queasy.dao.interfaces;

import com.queasy.model.quiz.Question;
import com.queasy.model.quiz.Quiz;
import com.queasy.model.user.User;
import com.queasy.utility.enums.QuestionType;

import java.util.List;

public interface QuizDao {

    List<Question> getAllQuestions(int quizId);
    String getDescription(int quizId);

    String getQuizName(int quizId);

    User getCreator(int quizId);

    Quiz getQuiz(int quizId);

    Quiz getQuiz(String quizName);

    boolean addQuiz(Quiz quiz);

    boolean removeQuiz(int quizId);

    //to get specific type of questions (might delete later...)
    //List<Question> getQuestionsByType(QuestionType qt);


    //TODO: უნდა დავამატო პირველი  n  ცალი რომ ამოიღოს ან რაღაც რეინჯში, WHERE უნდა დავუმატო კიდევ ყველას ამოღებისას ალბათ
    List<Quiz> getAllQuizzes();



}
