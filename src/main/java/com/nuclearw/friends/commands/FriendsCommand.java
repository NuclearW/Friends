package com.nuclearw.friends.commands;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import com.nuclearw.friends.Friends;

public class FriendsCommand {
	protected Friends plugin;

	public FriendsCommand(Friends plugin) {
		this.plugin = plugin;
	}

	protected String findTarget(String target) {
		OfflinePlayer search = plugin.getServer().getPlayer(target);

		if(search == null) {
			plugin.getServer().getOfflinePlayer(target);
		}

		if(search != null) {
			target = search.getName();
		}

		return target;
	}

	protected void printArgsError(String[] args) {
		StringBuilder sb = new StringBuilder();
		sb.append("Invalid arguments passed to ");
		sb.append(this.getClass().getName());
		sb.append(": ");
		for(String arg : args) {
			sb.append(arg);
			sb.append(" ");
		}
		plugin.getLogger().severe(sb.toString());
	}

	protected void printHelp(CommandSender sender, String label) {
		if(sender.hasPermission("friends.add")) {
			sender.sendMessage(ChatColor.GRAY + "/" + label + " add <player>");
		}
		if(sender.hasPermission("friends.remove")) {
			sender.sendMessage(ChatColor.GRAY + "/" + label + " remove <player>");
		}
		if(sender.hasPermission("friends.list")) {
			sender.sendMessage(ChatColor.GRAY + "/" + label + " list");
		}
	}
}
