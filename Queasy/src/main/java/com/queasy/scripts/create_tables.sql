CREATE DATABASE IF NOT EXISTS queasy_database;
USE queasy_database;


/* Drop Tables */
DROP TABLE IF EXISTS mails;
DROP TABLE IF EXISTS followers;
DROP TABLE IF EXISTS answer_pictures;
DROP TABLE IF EXISTS answers;
DROP TABLE IF EXISTS question_pictures;
DROP TABLE IF EXISTS quiz_question;
DROP TABLE IF EXISTS questions;
DROP TABLE IF EXISTS games;
DROP TABLE IF EXISTS quizzes;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS pictures;

/* Create Tables */


CREATE TABLE users(
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      user_name VARCHAR(64) NOT NULL,
                      user_password VARCHAR(64) NOT NULL,
                      mail VARCHAR(64),
    /*isAdmin can be added*/
                      CONSTRAINT user_name_unique_constraint UNIQUE(user_name),
                      CONSTRAINT mail_unique_constraint UNIQUE(mail)
);

CREATE TABLE mails(
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      from_user_name VARCHAR(64) NOT NULL,
                      to_user_name VARCHAR(64) NOT NULL,
                      date DATETIME DEFAULT(CURRENT_TIMESTAMP),
                      subject VARCHAR(128),
                      text VARCHAR(1024),
                      FOREIGN KEY (from_user_name) REFERENCES users(user_name) ON DELETE CASCADE,
                      FOREIGN KEY (to_user_name) REFERENCES users(user_name) ON DELETE CASCADE
);

CREATE TABLE followers(
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          first_user_username VARCHAR(64) NOT NULL,
                          second_user_username VARCHAR(64) NOT NULL,
                          FOREIGN KEY (first_user_username) REFERENCES users(user_name) ON DELETE CASCADE,
                          FOREIGN KEY (second_user_username) REFERENCES users(user_name) ON DELETE CASCADE
);

CREATE TABLE quizzes(
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        quiz_name VARCHAR(64),
                        creator_id INT,
                        description VARCHAR(256),
                        CONSTRAINT quiz_name_unique_constraint UNIQUE(quiz_name),
                        FOREIGN KEY (creator_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE questions(
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          text VARCHAR(512),
                          question_type VARCHAR(32) NOT NULL,
                          creator_id INT,
                          FOREIGN KEY (creator_id) REFERENCES users(id) ON DELETE CASCADE

);

CREATE TABLE pictures(
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         picture VARCHAR(1024) NOT NULL
);

CREATE TABLE answers(
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        text varchar(512),
                        question_id INT NOT NULL,
                        is_right_answer VARCHAR(1)  NOT NULL,
                        FOREIGN KEY (question_id) REFERENCES questions(id) ON DELETE CASCADE
);

CREATE TABLE question_pictures(
                                  id INT PRIMARY KEY AUTO_INCREMENT,
                                  picture_id INT NOT NULL,
                                  question_id INT NOT NULL,
                                  FOREIGN KEY (question_id) REFERENCES questions(id) ON DELETE CASCADE,
                                  FOREIGN KEY (picture_id) REFERENCES pictures(id) ON DELETE CASCADE

);

CREATE TABLE quiz_question(
                              id INT PRIMARY KEY AUTO_INCREMENT,
                              quiz_id INT NOT NULL,
                              question_id INT NOT NULL,
                              FOREIGN KEY (quiz_id) REFERENCES quizzes(id) ON DELETE CASCADE,
                              FOREIGN KEY (question_id) REFERENCES questions(id) ON DELETE CASCADE
);

CREATE TABLE games(
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      score INT,
                      start_date DATETIME NOT NULL,
                      end_date DATETIME DEFAULT(CURRENT_TIMESTAMP),
                      user_name VARCHAR(64) NOT NULL,
                      quiz_id INT NOT NULL,
                      FOREIGN KEY (quiz_id) REFERENCES quizzes(id) ON DELETE CASCADE,
                      FOREIGN KEY (user_name) REFERENCES users(user_name) ON DELETE CASCADE
);

CREATE TABLE answer_pictures(
                                id INT PRIMARY KEY AUTO_INCREMENT,
                                picture_id INT NOT NULL,
                                answer_id INT NOT NULL,
                                FOREIGN KEY (answer_id) REFERENCES answers(id) ON DELETE CASCADE,
                                FOREIGN KEY (picture_id) REFERENCES pictures(id) ON DELETE CASCADE
);