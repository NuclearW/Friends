package com.nuclearw.friends.listeners;

import java.util.Iterator;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.nuclearw.friends.Friends;

public class PlayerListener implements Listener {
	private Friends plugin;

	public PlayerListener(Friends plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority=EventPriority.MONITOR, ignoreCancelled=true)
	public void onPlayerJoin(PlayerJoinEvent event) {
		if(event.getPlayer().hasPermission("friends.loginlist")) {
			Set<String> friends = plugin.getManager().getFriends(event.getPlayer().getName());

			StringBuilder builder = new StringBuilder();
			builder.append(plugin.getLocale().getString("friends-online"));

			Iterator<String> iterator = friends.iterator();

			while(iterator.hasNext()) {
				String friend = iterator.next();
				if(plugin.getServer().getPlayer(friend) == null) {
					iterator.remove();
				}
			}

			iterator = friends.iterator();

			if(friends.isEmpty()) {
				builder.append(plugin.getLocale().getString("no-friends-online"));
			} else {
				while(iterator.hasNext()) {
					String friend = iterator.next();

					String format = "friends-list";
					if(plugin.getServer().getPlayer(friend) != null) {
						format += "-online-format";
					} else {
						continue;
					}
					if(!iterator.hasNext()) {
						format += "-last";
					}

					builder.append(plugin.getLocale().getString(format, friend));
				}
			}

			event.getPlayer().sendMessage(builder.toString());
		}

		Set<String> friends = plugin.getManager().getOwners(event.getPlayer().getName());

		for(String friend : friends) {
			Player other = plugin.getServer().getPlayer(friend);

			if(other != null && other.hasPermission("friends.joinmessage")) {
				other.sendMessage(plugin.getLocale().getString("friend-login", event.getPlayer().getName()));
			}
		}
	}

	@EventHandler(priority=EventPriority.MONITOR, ignoreCancelled=true)
	public void onPlayerQuit(PlayerQuitEvent event) {
		logoutMessage(event.getPlayer());
	}

	@EventHandler(priority=EventPriority.MONITOR, ignoreCancelled=true)
	public void onPlayerKick(PlayerKickEvent event) {
		logoutMessage(event.getPlayer());
	}

	private void logoutMessage(Player player) {
		Set<String> friends = plugin.getManager().getOwners(player.getName());

		for(String friend : friends) {
			Player other = plugin.getServer().getPlayer(friend);

			if(other != null && other.hasPermission("friends.partmessage")) {
				other.sendMessage(plugin.getLocale().getString("friend-logout", player.getName()));
			}
		}
	}
}
