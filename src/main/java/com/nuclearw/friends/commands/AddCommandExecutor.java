package com.nuclearw.friends.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.nuclearw.friends.Friends;

public class AddCommandExecutor extends FriendsCommand implements CommandExecutor {
	public AddCommandExecutor(Friends plugin) {
		super(plugin);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args.length != 3) {
			printArgsError(args);
			printHelp(sender, label);
			return true;
		}

		String target = findTarget(args[2]);

		if(plugin.getManager().isFriend(sender.getName(), target)) {
			sender.sendMessage(plugin.getLocale().getString("already-friends", target));
		} else {
			plugin.getManager().setFriend(sender.getName(), target);

			if(plugin.getManager().isFriend(sender.getName(), target)) {
				sender.sendMessage(plugin.getLocale().getString("new-friend", target));
			} else {
				sender.sendMessage(plugin.getLocale().getString("unspecified-error"));
				plugin.getLogger().severe("Could not add " + target + " as a friend of " + sender.getName());
			}
		}

		return true;
	}
}
