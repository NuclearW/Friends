package com.nuclearw.friends;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

public class Config {
	public static String dbDriver, dbURL, dbUsername, dbPassword, dbIsolation;
	public static boolean dbLogging, dbRebuild;

	public static void load(JavaPlugin plugin) {
		if(!new File(plugin.getDataFolder() , "config.yml").exists()) {
			plugin.saveDefaultConfig();
		}

		dbDriver = plugin.getConfig().getString("database.driver");
		dbURL = plugin.getConfig().getString("database.url");
		dbUsername = plugin.getConfig().getString("database.username");
		dbPassword = plugin.getConfig().getString("database.password");
		dbIsolation = plugin.getConfig().getString("database.isolation");

		dbLogging = plugin.getConfig().getBoolean("database.logging");
		dbRebuild = plugin.getConfig().getBoolean("database.rebuild");
	}
}

