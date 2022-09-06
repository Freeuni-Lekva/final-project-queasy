package com.queasy.utility.constants;

import com.queasy.utility.enums.QuestionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class MyConstants {

    public static final String dateFormat = "yyyy-mm-dd";

    public static final String emptyString = "";
    public static final String emptyQuery = "";

    public static final String ID = "id";

    public static final String USERNAME_MUST_NOT_CONTAIN = "@";

    public static final String EMAIL_MUST_CONTAIN = "@";
    //users
    public static final String USERS_DATABASE = "users";
    public static final String USER_NAME = "user_name";
    public static final String USER_PASSWORD = "user_password";
    public static final String USER_MAIL = "mail";

    //mails
    public static final String MAILS_DATABASE = "mails";
    public static final String MAILS_FROM_USER_NAME = "from_user_name";
    public static final String MAILS_TO_USER_NAME = "to_user_name";
    public static final String MAILS_DATE = "date";
    public static final String MAILS_SUBJECT = "subject";
    public static final String MAILS_TEXT = "text";

    //following
    public static final String FOLLOWERS_DATABASE = "followers";
    public static final String FOLLOWERS_FIRST_USER_USERNAME = "first_user_username";
    public static final String FOLLOWERS_SECOND_USER_USERNAME = "second_user_username";

    public static class AnswersDatabaseConstants {
        public static String DATABASE = "answers";
        public static String TEXT = "text";
        public static String QUESTION_ID = "question_id";
        public static String IS_RIGHT_ANSWER = "is_right_answer";
    }

    public static class GameDatabaseConstants {
        public static String DATABASE = "games";
        public static String SCORE = "score";
        public static String START_DATE = "start_date";
        public static String END_DATE = "end_date";
        public static String USER_NAME = "user_name";
        public static String QUIZ_ID = "quiz_id";
    }

    public static class QuizDatabaseConstants {
        public static String DATABASE = "quizzes";
        public static String QUIZ_NAME = "quiz_name";
        public static String CREATOR_ID = "creator_id";
        public static String DESCRIPTION = "description";
    }

    public static class QuizQuestionsDatabaseConstants {
        public static String DATABASE = "quiz_question";
        public static String QUIZ_ID = "quiz_id";
        public static String QUESTION_ID = "question_id";
    }

    public static class QuestionsDatabaseConstants {
        public static String DATABASE = "questions";
        public static String TEXT = "text";
        public static String TYPE = "question_type";
        public static String CREATOR_ID = "creator_id";
    }

    public static class QuestionsPicturesDatabaseConstants {
        public static String DATABASE = "question_pictures";
        public static String PICTURE_ID = "picture_id";
        public static String QUESTION_ID = "question_id";
    }

    public static class AnswersPicturesDatabaseConstants {
        public static String DATABASE = "answer_pictures";
        public static String PICTURE_ID = "picture_id";
        public static String ANSWER_ID = "answer_id";
    }

    public static class PicturesDatabaseConstants {
        public static String DATABASE = "pictures";
        public static String PICTURE = "picture";
    }

    public static class ContextAttributes {
        public static String USER_DAO = "CONTEXT_ATTRIBUTE_USER_DAO";
        public static String QUIZ_QUESTION_DAO = "CONTEXT_ATTRIBUTE_QUIZ_QUESTION_DAO";
        public static String QUIZ_DAO = "CONTEXT_ATTRIBUTE_QUIZ_DAO";
        public static String QUESTION_PICTURES_DAO = "CONTEXT_ATTRIBUTE_QUESTION_PICTURES_DAO";
        public static String QUESTION_DAO = "CONTEXT_ATTRIBUTE_QUESTION_DAO";
        public static String PICTURE_DAO = "CONTEXT_ATTRIBUTE_PICTURE_DAO";
        public static String MAIL_DAO = "CONTEXT_ATTRIBUTE_MAIL_DAO";
        public static String GAME_DAO = "CONTEXT_ATTRIBUTE_GAME_DAO";
        public static String FOLLOWING_DAO = "CONTEXT_ATTRIBUTE_FOLLOWING_DAO";
        public static String ANSWER_PICTURES_DAO = "CONTEXT_ATTRIBUTE_ANSWER_PICTURES_DAO";
        public static String ANSWER_DAO = "CONTEXT_ATTRIBUTE_ANSWER_DAO";
    }

    public static class Servlets {

        public static List<String> NOT_LOGGED_URLS = new ArrayList<>(Arrays.asList(
                "/login/createAccount.jsp",
                "/login/creationFailed.jsp",
                "/login/incorrectInfo.jsp",
                "/login/login.jsp",
                "/",
                "register",
                "incorrect",
                "creationFailed",
                "/LoginServlet",
                "/AddAccountServlet"
        ));

        public static int MAX_INACTIVE_TIME = 600;

        public static String LOGIN_SERVLET = "/login/LoginServlet";
        public static String LOGINED_USER = "loginedUser";
        public static String USERNAME = "username";
        public static String PASSWORD = "password";
        public static String QUIZZES = "quizzes";
        public static String CURR_QUIZ = "currQuiz";
        public static String QUIZ_ID = "quizId";
        public static String CREATOR_USER = "creatorUser";
        public static String GAMES = "games";
        public static String LAST_DAY_GAMES = "lastDayGames";
        public static String GAMES_SORTED_BY_SCORE = "gamesByScore";
        public static String GAMES_SORTED_BY_DATE = "gamesByDate";
        public static String QUESTION_RESPONSE = QuestionType.QUESTION_RESPONSE.name();
        public static String PICTURE_RESPONSE = QuestionType.PICTURE_RESPONSE.name();
        public static String FILL_IN_THE_BLANK = QuestionType.FILL_IN_THE_BLANK.name();
        public static String FILL_BLANK_STRING = "$___$";
        public static String MULTIPLE_CHOICE = QuestionType.MULTIPLE_CHOICE.name();

        public static String MULTIPLE_CHOICE_ANSWER = "multipleChoiceAnswers";

        public static long MILLISECONDS_IN_DAY = 3600000 * 24;

        public static String QUIZ_ANSWER = "answer-";
        public static String SCORE = "score";
        public static String QUIZ_START_TIME = "startTime";
        public static String QUIZ_END_TIME = "endTime";

    }
}
