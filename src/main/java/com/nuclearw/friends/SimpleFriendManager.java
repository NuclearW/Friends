package com.nuclearw.friends;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.nuclearw.friends.api.FriendManager;

public class SimpleFriendManager implements FriendManager {
	private Friends plugin;

	public SimpleFriendManager(Friends plugin) {
		this.plugin = plugin;
	}

	@Override
	public Set<String> getFriends(String owner) {
		Set<Friend> friends = plugin.getDatabase().find(Friend.class).where().ieq("owner", owner).findSet();
		Set<String> names = new HashSet<String>();
		for(Friend friend : friends) {
			names.add(friend.getFriend());
		}
		return Collections.unmodifiableSet(names);
	}

	@Override
	public boolean isFriend(String owner, String other) {
		return !plugin.getDatabase().find(Friend.class).where().ieq("owner", owner).ieq("friend", other).findSet().isEmpty();
	}

	@Override
	public void setFriend(String owner, String other) {
		// Do not set friend if they already are
		if(isFriend(owner, other)) return;

		Friend friend = new Friend();
		friend.setOwner(owner);
		friend.setFriend(other);

		plugin.getDatabase().save(friend);
	}

	@Override
	public void unsetFriend(String owner, String other) {
		// Don't try to remove what's not there
		if(!isFriend(owner, other)) return;

		plugin.getDatabase().delete(plugin.getDatabase().find(Friend.class).where().ieq("owner", owner).ieq("friend", other).findList());
	}
}
