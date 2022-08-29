package com.queasy.utility.constants;

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
}
