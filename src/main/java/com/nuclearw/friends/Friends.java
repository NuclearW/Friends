package com.nuclearw.friends;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

import com.avaje.ebean.EbeanServer;
import com.lennardf1989.bukkitex.MyDatabase;
import com.nuclearw.friends.api.FriendManager;
import com.nuclearw.friends.commands.BaseCommandExecutor;
import com.nuclearw.friends.locale.LocaleManager;

public class Friends extends JavaPlugin {
	private static MyDatabase database;
	private static LocaleManager locale;
	private static FriendManager manager;

	@Override
	public void onEnable() {
		Config.load(this);

		initDatabase();

		try {
			locale = new LocaleManager(this);
		} catch (IOException e) {
			// We could not load locale, this is an error we cannot get around.
			getLogger().severe("Could not load Locale!  This is a non-recoverable error!");
			e.printStackTrace();
			getPluginLoader().disablePlugin(this);
			return;
		}

		manager = new SimpleFriendManager(this);

		getCommand("friends").setExecutor(new BaseCommandExecutor(this));

		metrics();

		getLogger().info("Finished loading " + getDescription().getFullName());
	}

	@Override
	public void onDisable() {
		getLogger().info("Finished unloading " + getDescription().getFullName());
	}

	@Override
	public EbeanServer getDatabase() {
		return database.getDatabase();
	}

	public LocaleManager getLocale() {
		return locale;
	}

	public FriendManager getManager() {
		return manager;
	}

	private void initDatabase() {
		database = new MyDatabase(this) {
			protected List<Class<?>> getDatabaseClasses() {
				List<Class<?>> list = new ArrayList<Class<?>>();
				list.add(Friend.class);

				return list;
			};
		};

		database.initializeDatabase(Config.dbDriver, Config.dbURL, Config.dbUsername, Config.dbPassword, Config.dbIsolation, Config.dbLogging, Config.dbRebuild);
	}

	private void metrics() {
		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
		} catch (IOException e) { }
	}
}
