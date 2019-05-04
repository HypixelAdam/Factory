package me.hype.factory.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.hype.factory.Core;
import me.hype.factory.managers.ConfigManager;
import me.hype.factory.managers.ScoreboardManager;

public class PlayerStats implements CommandExecutor {
	
	Core plugin = Core.getInstance();
	String prefix = Core.getInstance().getConfig().getString("Settings.prefix");
	ConfigManager cm = new ConfigManager();
	ScoreboardManager sbm = new ScoreboardManager();
	int timer = 5;
	int task = 0;

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] a) {
		if (prefix == null) {prefix = Core.getInstance().getConfig().getString("Settings.prefix");}
		if (cm == null) {cm = new ConfigManager();}
		if (sbm == null) {sbm = new ScoreboardManager();}
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a 'Player' to run this command!");
			return true;
		}
		Player p = (Player) sender;
		int length = a.length;
		if (cmd.getName().equalsIgnoreCase("fstats")) {
			if (!p.hasPermission("factory.command.stats")) {
				p.sendMessage(format(prefix + "&cYou don't have permission to run this command."));
				return true;
			}
			if (length == 0) {
				if (!cm.doesPlayerExist(p)) {
					p.sendMessage(format(prefix+"&cCouldn't load your stats. Check with a admin.&c."));
					return true;
				}
				List<String> stats = cm.getPlayerStats(p);
				sbm.playerStatsScoreboard(p,stats);
				runScoreboardStatsTimer(p);
				return true;
			} else if (length == 1) {
				String tname = a[0];
				if (!cm.doesPlayerExist(Bukkit.getPlayer(tname))) {
					p.sendMessage(format(prefix+"&cThat player doesn't exist."));
					return true;
				}
				List<String> stats = cm.getPlayerStats(Bukkit.getPlayer(tname));
				p.sendMessage(format(prefix+"&aScoreboard changed, it will be visible for &e5 &aseconds."));
				sbm.playerStatsScoreboard(p,Bukkit.getPlayer(tname),stats);
				runScoreboardStatsTimer(p);
				return true;
			} else if (length >= 2) {
				p.sendMessage(format(prefix+"&c"));
				return true;
			}
		}
		return true;
	}
	
	public void runScoreboardStatsTimer(Player p) {
		task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
			public void run() {
				if (timer == 0) {
					sbm.factoryScoreboard(p, cm.getIdFromString(p.getWorld().getName()));
					stopTimer(task);
				}
				timer--;
			}
		}, 1L, 20L);
		return;
	}
	public void stopTimer(int task) {
		Bukkit.getServer().getScheduler().cancelTask(task);
	}
	
	public String format(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
}
