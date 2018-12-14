package me.hype.factory.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.hype.factory.Core;
import me.hype.factory.managers.ConfigManager;
import me.hype.factory.managers.ScoreboardManager;

public class OnJoin implements Listener {
	
	Core plugin = Core.getInstance();
	ConfigManager cm = new ConfigManager();
	ScoreboardManager sbm = new ScoreboardManager();
	String prefix = Core.getInstance().getConfig().getString("Settings.prefix");
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if (plugin == null) { plugin = Core.getInstance(); }
		if (cm == null) { cm = new ConfigManager(); }
		if (sbm == null) { sbm = new ScoreboardManager();}
		Player p = e.getPlayer();
		if (cm.doesPlayerExist(p)) { 
			sbm.spawnScoreboard(p); 
			return; 
		} else { 
			cm.addPlayer(p);
			sbm.spawnScoreboard(p); 
			return;
		}
	}
}
