package me.hype.factory.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.hype.factory.Core;
import me.hype.factory.managers.HelpManager;

public class Help implements CommandExecutor {
	
	Core plugin = Core.getInstance();
	String prefix = Core.getInstance().getConfig().getString("Settings.prefix");
	HelpManager hm = new HelpManager();

	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] a) {
		if (prefix == null) {prefix = Core.getInstance().getConfig().getString("Settings.prefix");}
		if (hm == null) { hm = new HelpManager();}
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a 'Player' to run this command!");
			return true;
		}
		Player p = (Player) sender;
		int length = a.length;
		if (cmd.getName().equalsIgnoreCase("fhelp")) {
			if (!p.hasPermission("factory.command.help")) {
				p.sendMessage(format(prefix + "&cYou don't have permission to run this command."));
				return true;
			}
			if (length == 0) {
				List<String> help = hm.getPageHelp(1);
				p.sendMessage(format(prefix+"&aOne moment..."));
				p.sendMessage(format("&7&m--[&r &aHelp for Factorys &7/ &aPage 1 &7&m]--"));
				for (int i = 0;i<help.size();i++) {
					p.sendMessage(format(help.get(i)));
				}
				if (!hm.isPageAvailable(2)) {
					return true;
				} else {
					p.sendMessage(format(prefix+"&aFor more help do &e/fhelp 2&a."));
					return true;
				}
			} else if (length == 1 && isInt(a[0])) {
				int page = Integer.parseInt(a[0]);
				if (!hm.isPageAvailable(page)) {
					p.sendMessage(format(prefix+"&cThat page doesn't exist."));
					return true;
				}
				List<String> help = hm.getPageHelp(page);
				p.sendMessage(format(prefix+"&aOne moment..."));
				p.sendMessage(format("&7&m--[&r &aHelp for Factorys &7/ &aPage "+page+" &7&m]--"));
				for (int i = 0;i<help.size();i++) {
					p.sendMessage(format(help.get(i)));
				}
				p.sendMessage(format("&7&m--[&r &e[] &a- Optional &7/ &e<> &a- Required &7&m]--"));
				if (!hm.isPageAvailable(page+1)) {
					return true;
				} else {
					p.sendMessage(format(prefix+"&aFor more help do &e/fhelp "+(page+1)+"&a."));
					return true;
				}
			} else if (length == 1 && !isInt(a[0])) {
				String command = a[0];
				if (!hm.isCommandAvailable(command)) {
					p.sendMessage(format(prefix+"&cThat command doesn't exist."));
					return true;
				}
				p.sendMessage(format(prefix+"&aOne moment..."));
				List<String> cmdinfo = hm.getCommandHelp(command);
				hm.printCommandHelp(p,cmdinfo,command);
				return true;
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
