package me.hype.factory.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.hype.factory.Core;
import me.hype.factory.managers.ConfigManager;
import me.hype.factory.managers.InventoryManager;

public class BuyEquipment implements CommandExecutor {
	
	Core plugin = Core.getInstance();
	String prefix = Core.getInstance().getConfig().getString("Settings.prefix");
	ConfigManager cm = new ConfigManager();
	InventoryManager im = new InventoryManager();

	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] a) {
		if (cm == null) {cm = new ConfigManager();}
		if (im == null) {im = new InventoryManager();}
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a 'Player' to run this command!");
			return true;
		}
		Player p = (Player) sender;
		int length = a.length;
		if (cmd.getName().equalsIgnoreCase("fbuyequipment")) {
			if (!p.hasPermission("factory.commands.buyequipment")) {
				p.sendMessage(format(prefix + "&cYou don't have permission to run this command."));
				return true;
			}
			if (length == 0) {
				if (!cm.doesPlayerExist(p)) {
					p.sendMessage(format(prefix+"&cYou aren't in the config. Contact a staff member immediately."));
					return true;
				}
				p.openInventory(im.buyEquipmentInventory());
				return true;
			} else if (length >= 1) {
				p.sendMessage(format(prefix+"&cToo many arguments. &6Use /fhelp for proper commands."));
				return true;
			}
		}
		return true;
	}

	public String format(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
}
