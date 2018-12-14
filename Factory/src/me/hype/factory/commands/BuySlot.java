package me.hype.factory.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.hype.factory.Core;
import me.hype.factory.managers.ConfigManager;
import me.hype.factory.managers.InventoryManager;

public class BuySlot implements CommandExecutor {
	
	Core plugin = Core.getInstance();
	String prefix = Core.getInstance().getConfig().getString("Settings.prefix");
	ConfigManager cm = new ConfigManager();
	InventoryManager im = new InventoryManager();

	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] a) {
		if (prefix == null) {prefix = Core.getInstance().getConfig().getString("Settings.prefix");}
		if (im == null) {im = new InventoryManager();}
		if (cm == null) {cm = new ConfigManager();}
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a 'Player' to run this command!");
			return true;
		}
		Player p = (Player) sender;
		int length = a.length;
		if (cmd.getName().equalsIgnoreCase("fbuyslot")) {
			if (!p.hasPermission("factory.command.buyslot")) {
				p.sendMessage(format(prefix + "&cYou don't have permission to run this command."));
				return true;
			}
			if (length == 0) {
				p.sendMessage(format(prefix+"&cMissing arguments! &6Use /fhelp for proper commands."));
				return true;
			} else if (length == 1) {
				if (isInt(a[0])) {
					int facid = Integer.parseInt(a[0]);
					if (!cm.doesPlayerExist(p)) {
						p.sendMessage(format(prefix+"&cYou aren't in the config. Contact a staff member immediately."));
						return true;
					}
					if (!cm.isFactoryOwned(p, facid)) {
						p.sendMessage(format(prefix+"&cYou don't own this factory."));
						return true;
					}
					p.sendMessage(format(prefix+"&aOne moment..."));
					p.openInventory(im.buySlot(p, facid));
					return true;
				} else {
					p.sendMessage(format(prefix+"&cInvaild arugment &e"+a[0]+"&c. Must be a Integer."));
					return true;
				}
			} else if (length >= 2) {
				p.sendMessage(format(prefix+"&cToo many arguments! &6Use /fhelp for proper commands."));
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
	
	public String format(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
}
