package com.nuclearw.friends.api;

import java.util.Set;

public interface FriendManager {
	public enum NotifyMode {
		NONE,
		OWNER,
		OTHER,
		BOTH
	}

	public Set<String> getFriends(String owner);

	public boolean isFriend(String owner, String other);

	public void setFriend(String owner, String other);

	public void unsetFriend(String owner, String other);

	public void setFriend(String owner, String other, NotifyMode notify);

	public void unsetFriend(String owner, String other, NotifyMode notify);
}
