package com.nuclearw.friends.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import com.nuclearw.friends.Friends;

public class BaseCommandExecutor extends FriendsCommand implements CommandExecutor {
	private final String NO_PERMISSION = ChatColor.RED + "You do not have permission to use that command!";

	private CommandExecutor addCommand    = new AddCommandExecutor(plugin);
	private CommandExecutor removeCommand = new RemoveCommandExecutor(plugin);
	private CommandExecutor listCommand   = new ListCommandExecutor(plugin);

	public BaseCommandExecutor(Friends plugin) {
		super(plugin);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 0) {
			printHelp(sender, label);
			return true;
		}

		if(sender instanceof ConsoleCommandSender) {
			sender.sendMessage("This command does not support console usage.");
			return true;
		}

		if(args[0].equalsIgnoreCase("add")) {
			if(!sender.hasPermission("friends.add")) {
				sender.sendMessage(NO_PERMISSION);
				return true;
			}
			addCommand.onCommand(sender, cmd, label, args);
		} else if(args[0].equalsIgnoreCase("remove")) {
			if(!sender.hasPermission("friends.remove")) {
				sender.sendMessage(NO_PERMISSION);
				return true;
			}
			removeCommand.onCommand(sender, cmd, label, args);
		} else if(args[0].equalsIgnoreCase("list")) {
			if(!sender.hasPermission("friends.list")) {
				sender.sendMessage(NO_PERMISSION);
				return true;
			}
			listCommand.onCommand(sender, cmd, label, args);
		} else {
			printHelp(sender, label);
		}

		return true;
	}
}
