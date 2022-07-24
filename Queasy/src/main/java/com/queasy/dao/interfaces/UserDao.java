package com.queasy.dao.interfaces;

import com.queasy.model.user.User;

import java.util.List;

public interface UserDao {

    User getUser(int id);
    User getUser(String userName);

    //User getUser(String email);

    boolean addUser(String userName, String password, String email);

    boolean updatePassword(String userName, String newPassword);

    boolean updateEmail(String userName, String newEmail);

    boolean updateUserName(String oldUserName, String newUserName);

    List<User> getAllUsers();

}
