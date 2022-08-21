package com.queasy.dao.interfaces;

import com.queasy.model.user.User;

import java.util.List;

//TODO: უნდა დავამატო პირველი  n  ცალი რომ ამოიღოს ან რაღაც რეინჯში

public interface UserDao {

    User getUser(int id);
    User getUser(String userNameOrEmail);

    //User getUser(String email);

    boolean addUser(String userName, String password, String email);

    boolean updatePassword(String userName, String newPassword);

    boolean updateEmail(String userName, String newEmail);

    boolean updateUserName(String oldUserName, String newUserName);

    //TODO: უნდა დავამატო პირველი  n  ცალი რომ ამოიღოს ან რაღაც რეინჯში
    List<User> getAllUsers();

}
