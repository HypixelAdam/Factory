package me.hype.factory.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.hype.factory.Core;
import me.hype.factory.managers.InventoryManager;

public class BuyFactory implements CommandExecutor {
	
	Core plugin = Core.getInstance();
	String prefix = Core.getInstance().getConfig().getString("Settings.prefix");
	InventoryManager im = new InventoryManager();

	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] a) {
		if (im == null) {
			im = new InventoryManager();
		}
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a 'Player' to run this command!");
			return true;
		}
		Player p = (Player) sender;
		int length = a.length;
		if (cmd.getName().equalsIgnoreCase("fbuyfactory")) {
			if (!p.hasPermission("factory.command.buyfactory")) {
				p.sendMessage(format(prefix + "&cYou don't have permission to run this command."));
				return true;
			}
			if (length == 0) {
				p.sendMessage(format(prefix+"&aOne moment..."));
				p.openInventory(im.buyFactory(p));
				return true;
			} else if (length >= 1) {
				p.sendMessage(format(prefix+"&cToo many arguments! &6Use /fhelp for proper commands."));
				return true;
			}
		}
		return true;
	}

	public String format(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
}
