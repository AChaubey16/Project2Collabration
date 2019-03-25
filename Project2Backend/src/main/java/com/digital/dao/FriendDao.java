package com.digital.dao;

import java.util.List;

import com.digital.models.Friend;
import com.digital.models.User;

public interface FriendDao {
	List<User> getAllSuggestedUsers(String email);
	List<Friend> pendingRequests(String email);
	void friendRequest(Friend friend);
	void acceptFriendRequest(Friend friend);
	void deleteFriendRequest(Friend friend);
	List<User> listOfFriends(String email);
}
