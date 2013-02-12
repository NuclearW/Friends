package com.nuclearw.friends.commands;

import java.util.Iterator;
import java.util.Set;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.nuclearw.friends.Friends;

public class ListCommandExecutor extends FriendsCommand implements CommandExecutor {
	public ListCommandExecutor(Friends plugin) {
		super(plugin);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Set<String> friends = plugin.getManager().getFriends(sender.getName());

		StringBuilder builder = new StringBuilder();
		builder.append(plugin.getLocale().getString("friends-list-header"));

		if(friends.isEmpty()) {
			builder.append(plugin.getLocale().getString("no-friends-online"));
		} else {
			Iterator<String> iterator = friends.iterator();
			while(iterator.hasNext()) {
				String friend = iterator.next();

				String format = "friends-list";
				if(plugin.getServer().getPlayer(friend) != null) {
					format += "-online-format";
				} else {
					format += "-offline-format";
				}
				if(!iterator.hasNext()) {
					format += "-last";
				}

				builder.append(plugin.getLocale().getString(format, friend));
			}
		}

		sender.sendMessage(builder.toString());

		return true;
	}
}
