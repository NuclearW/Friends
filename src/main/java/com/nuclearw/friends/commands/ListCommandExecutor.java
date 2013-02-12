package com.nuclearw.friends.commands;

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
		// TODO Auto-generated method stub
		return false;
	}
}
