package com.nuclearw.friends;

import java.util.Set;

import com.nuclearw.friends.api.FriendManager;

public class SimpleFriendManager implements FriendManager {
	public SimpleFriendManager(Friends friends) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Set<String> getFriends(String owner) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isFriend(String owner, String other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setFriend(String owner, String other) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unsetFriend(String owner, String other) {
		// TODO Auto-generated method stub

	}
}
