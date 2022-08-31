package com.queasy.dao.interfaces;

import com.queasy.model.user.User;

import java.util.List;


//TODO: უნდა დავამატო პირველი  n  ცალი რომ ამოიღოს ან რაღაც რეინჯში

public interface FollowingDao {

    List<User> getFriendsOf(String userName);

    //List<User> getFriendsOf(String mail);

    List<User> getSentRequestsOf(String userName);

    //List<User> getSentRequestsOf(String mail);

    List<User> getReceivedRequestsOf(String userName);

    boolean sendRequest(String fromUserName, String toUserName);
}
