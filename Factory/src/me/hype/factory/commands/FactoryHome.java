package me.hype.factory.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.hype.factory.Core;
import me.hype.factory.managers.ConfigManager;

public class FactoryHome implements CommandExecutor {
	
	Core plugin = Core.getInstance();
	String prefix = Core.getInstance().getConfig().getString("Settings.prefix");
	ConfigManager cm = new ConfigManager();

	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] a) {
		if (cm == null) {cm = new ConfigManager();}
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a 'Player' to run this command!");
			return true;
		}
		Player p = (Player) sender;
		int length = a.length;
		if (cmd.getName().equalsIgnoreCase("fhome")) {
			if (!p.hasPermission("factory.commands.home")) {
				p.sendMessage(format(prefix + "&cYou don't have permission to run this command."));
				return true;
			}
			if (length == 0) {
				if (!cm.isFactoryOwned(p, 1)) {
					p.sendMessage(format(prefix+"&cYou don't own this factory."));
					return true;
				}
				p.sendMessage(format(prefix+"&aTeleporting to Factory 1..."));
				despawnFactoryStands(p.getName(),1);
				spawnFactoryStands(p.getName(),1);
				cm.tpToFactoryHome(p, 1);
				return true;
			} else if (length == 1) {
				if (isInt(a[0])) {
					int facid = Integer.parseInt(a[0]);
					if (!cm.isFactoryOwned(p, facid)) {
						p.sendMessage(format(prefix+"&cYou don't own this factory."));
						return true;
					}
					p.sendMessage(format(prefix+"&aTeleporting to Factory "+facid+"..."));
					despawnFactoryStands(p.getName(),facid);
					spawnFactoryStands(p.getName(),facid);
					cm.tpToFactoryHome(p, facid);
					return true;
				} else {
					p.sendMessage(format(prefix+"&cInvaild arugment &e"+a[0]+"&c. Must be a Integer."));
					return true;
				}
			} else if (length >= 2) {
				p.sendMessage(format(prefix+"&cToo many arguments! &6Do /fhelp for proper commands."));
				return true;
			}
		}
		return true;
	}

	public boolean isInt(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	public void spawnFactoryStands(String pname, int facid) {
		plugin.spawnArmorStands(pname+facid);
		return;
	}
	
	public void despawnFactoryStands(String pname, int facid) {
		plugin.despawnArmorStands(pname+facid);
		return;
	}
	
	public String format(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
}
