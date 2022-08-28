/* users table */
INSERT INTO users (user_name, user_password,mail)
       VALUES ('user1','password','mail1@');
INSERT INTO users (user_name, user_password,mail)
    VALUES ('user2','password','mail2@');
INSERT INTO users (user_name, user_password,mail)
    VALUES ('user3','password','mail3@');

/* mails table */
INSERT INTO mails (from_user_name,to_user_name,date,subject,text)
       VALUES ('user1','user2','1990-09-01','OOP','start');
INSERT INTO mails (from_user_name,to_user_name,date,subject,text)
       VALUES ('user2','user3','2000-09-01','ქართული','დაწყება');

/* questions table */

INSERT INTO questions (text,question_type, creator_id)
       VALUES ('Who was the first President of America','QUESTION_RESPONSE',1);
INSERT INTO questions (text,question_type, creator_id)
       VALUES ('Who was the first President of GEORGIA','QUESTION_RESPONSE',1);

/* quizzes table */
INSERT INTO quizzes (quiz_name,creator_id, description)
       VALUES ('presidents', 1 , 'presidents names');

/* quiz_question table */
INSERT INTO quiz_question(quiz_id,question_id)
       VALUES (1,1);
INSERT INTO quiz_question(quiz_id,question_id)
       VALUES (1,2);

/* answers table */
INSERT INTO answers(text,question_id,is_right_answer)
       VALUES ('some Text',1,'Y');
INSERT INTO answers(text,question_id,is_right_answer)
       VALUES ('second Some Text',1,'N');

/* question_pictures */
INSERT INTO question_pictures(picture,question_id)
       VALUES ('https://www.thesprucepets.com/thmb/jwooso-kgF7r5gYS3H3p8fsbTHk=/941x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/GettyImages-135630198-5ba7d225c9e77c0050cff91b.jpg',1);
INSERT INTO question_pictures(picture,question_id)
       VALUES('https://cdn.vox-cdn.com/thumbor/I7I0t87KZ-vf_GSWrH118jwl6d0=/1400x0/filters:no_upscale()/cdn.vox-cdn.com/uploads/chorus_asset/file/23437452/The_Spy_x_Family_Anime_Succeeds_Because_of_Its_Characters_.jpg',1);

/* answer_pictures */
INSERT INTO answer_pictures(picture,answer_id)
       VALUES ('https://www.thesprucepets.com/thmb/jwooso-kgF7r5gYS3H3p8fsbTHk=/941x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/GettyImages-135630198-5ba7d225c9e77c0050cff91b.jpg',1);
INSERT INTO answer_pictures(picture,answer_id)
       VALUES ('https://qph.cf2.quoracdn.net/main-qimg-7503bd66ded5565e873c4c2f2ccbc8ad-lq',1);

/* games table */


/* following table */




