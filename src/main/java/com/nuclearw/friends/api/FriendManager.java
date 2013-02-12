package com.nuclearw.friends.api;

import java.util.Set;

public interface FriendManager {
	public Set<String> getOwners(String friend);

	public Set<String> getFriends(String owner);

	public boolean isFriend(String owner, String other);

	public void setFriend(String owner, String other);

	public void unsetFriend(String owner, String other);
}
