package me.hype.factory.managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import me.hype.factory.Core;

public class ScoreboardManager {
	
	Core plugin = Core.getInstance();
	ConfigManager cm = null;
	// Name: Factory
	// -----
	// 4 Factory > (#)
	// 3 MPS > (money-per-sec)
	// 2 Slots Owned > (# of slots owned)
	// 1 (blank)
	// 0 Money > (player's money)
	//
	public Scoreboard factoryScoreboard(Player p, int id) {
		if (cm == null) {cm = new ConfigManager();}
		String uuid = p.getUniqueId().toString();
		Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective o = sb.registerNewObjective("dummy", "dummy");
		o.setDisplayName(format("&3&lFactory"));
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		o.getScore(format("&aFactory # &7> &e"+id)).setScore(5);
		o.getScore(format("&aMPS &7> &e"+plugin.getPlayersConfig().getDouble("Players."+uuid+".factories."+String.valueOf(id)+".money-per-sec"))).setScore(3);
		o.getScore(format("&aSlots Owned &7> &e"+(plugin.getPlayersConfig().getIntegerList("Players."+uuid+".factories."+String.valueOf(id)+".slots-owned").size()+1)+"&a/&e25")).setScore(2);
		o.getScore(format("&7")).setScore(1);
		o.getScore(format("&aMoney &7> &e"+plugin.getPlayersConfig().getInt("Players."+uuid+".stats.money"))).setScore(0);
		p.setScoreboard(sb);
		return sb;
	}
	// Name: Factory
	// -----
	// 6 Name > (players name)
	// 5 Factories Owned > (players total factories)
	// 4 Money > (players money)
	// 3 [ Money ]
	// 2 Total MPS > (players total mps)
	// 1 Total Earned > (players total earned)
	// 0 Total Spent > (players total spent)
	public Scoreboard spawnScoreboard(Player p) {
		if (cm == null) {cm = new ConfigManager();}
		String uuid = p.getUniqueId().toString();
		if (cm == null) {cm = new ConfigManager();}
		Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective o = sb.registerNewObjective("dummy", "dummy");
		o.setDisplayName(format("&3&lFactory"));
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		o.getScore(format("&aName &7> &e"+p.getName())).setScore(6);
		o.getScore(format("&aFactories Owned &7> &e"+cm.totalFactoriesOwned(p))).setScore(5);
		o.getScore(format("&aMoney &7> &e"+plugin.getPlayersConfig().getInt("Players."+uuid+".stats.money"))).setScore(4);
		o.getScore(format("&3[ &2Money Stats &3]")).setScore(3);
		o.getScore(format("&aTotal MPS &7> &e"+cm.totalFactoriesMPS(p))).setScore(2);
		o.getScore(format("&aTotal Earned &7> &e"+plugin.getPlayersConfig().getInt("Players."+uuid+".stats.moneyearned"))).setScore(1);
		o.getScore(format("&aTotal Spent &7> &e"+plugin.getPlayersConfig().getInt("Players."+uuid+".stats.moneyspent"))).setScore(0);
		p.setScoreboard(sb);
		return sb;
	}
	
	
	
	public String format(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
}
