package com.nuclearw.friends.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.nuclearw.friends.Friends;

public class RemoveCommandExecutor extends FriendsCommand implements CommandExecutor {
	public RemoveCommandExecutor(Friends plugin) {
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

		if(!plugin.getManager().isFriend(sender.getName(), target)) {
			sender.sendMessage(plugin.getLocale().getString("not-friends", target));
		} else {
			plugin.getManager().unsetFriend(sender.getName(), target);

			if(!plugin.getManager().isFriend(sender.getName(), target)) {
				sender.sendMessage(plugin.getLocale().getString("no-longer-friends", target));
			} else {
				sender.sendMessage(plugin.getLocale().getString("unspecified-error"));
				plugin.getLogger().severe("Could not remove " + target + " as a friend of " + sender.getName());
			}
		}

		return true;
	}
}
