package com.queasy.dao.interfaces;

import com.queasy.model.user.User;

import java.util.List;

public interface UserDao {
    User getUser(String userName);

    //User getUser(String mail);

    boolean addUser(String userName, String password, String email);

    boolean updatePassword(String userName, String newPassword);

    boolean updateEmail(String userName, String newEmail);

    List<User> getAllUsers();

}
