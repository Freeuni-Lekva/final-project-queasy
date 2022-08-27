package com.queasy.dao.implementation;

import com.queasy.dao.interfaces.ConnectionPool;
import com.queasy.dao.interfaces.QuestionDao;
import com.queasy.model.quiz.Question;
import com.queasy.utility.enums.QuestionType;

import java.util.List;

public class QuestionDaoImpl implements QuestionDao {

    private final ConnectionPool connectionPool;
    public QuestionDaoImpl(ConnectionPool connectionPool){
        this.connectionPool = connectionPool;
    }

    @Override
    public List<Question> getAllQuestions() {
        return null;
    }

    @Override
    public List<Question> getAllQuestionsOf(QuestionType qt) {
        return null;
    }

    @Override
    public List<Question> getAllQuestionsOf(int quizId) {
        return null;
    }

    @Override
    public boolean addQuestion(Question question) {
        return false;
    }
}
