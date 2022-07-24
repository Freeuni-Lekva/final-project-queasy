package com.queasy.dao.interfaces;

import com.queasy.model.user.User;

import java.util.List;



public interface FollowingDao {

    List<User> getFriendsOf(String userName);

    //List<User> getFriendsOf(String mail);

    List<User> getSentRequestsOf(String userName);

    //List<User> getSentRequestsOf(String mail);

    boolean sendRequest(String userName);
}
