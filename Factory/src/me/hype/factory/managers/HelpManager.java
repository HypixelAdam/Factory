package me.hype.factory.managers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.hype.factory.Core;

public class HelpManager {
	
	Core plugin = Core.getInstance();
	
	public List<String> getPageHelp(int page) {
		List<String> commands = new ArrayList<String>();
		for (String cmd : plugin.getHelpConfig().getConfigurationSection("Help.pages."+page).getKeys(false)) {
			String fullcmd = plugin.getHelpConfig().getString("Help.pages."+page+"."+cmd+".fullcmd");
			String description = plugin.getHelpConfig().getString("Help.pages."+page+"."+cmd+".description");
			commands.add("&a"+fullcmd+" &7- "+description);
		}
		return commands;
	}
	public List<String> getCommandHelp(String cmd) {
		List<String> info = new ArrayList<String>();
		int pfcmd = 0;
		int pages = plugin.getHelpConfig().getConfigurationSection("Help.pages").getKeys(false).size();
		for (int i = 0;i<pages;i++) {
			if (i > pages) {
				break;
			}
			if (plugin.getHelpConfig().getConfigurationSection("Help.pages."+String.valueOf(i+1)).contains(cmd)) {
				pfcmd = i+1;
				break;
			}
		}
		String fullcmd = plugin.getHelpConfig().getString("Help.pages."+String.valueOf(pfcmd)+"."+cmd+".fullcmd");
		String example = plugin.getHelpConfig().getString("Help.pages."+String.valueOf(pfcmd)+"."+cmd+".ex");
		String description = plugin.getHelpConfig().getString("Help.pages."+String.valueOf(pfcmd)+"."+cmd+".description");
		List<String> aliases = plugin.getHelpConfig().getStringList("Help.pages."+String.valueOf(pfcmd)+"."+cmd+".aliases");
		info.add(fullcmd); info.add(example); info.add(description);
		if (aliases.size() == 0) {
			info.add("false");
			return info;
		} else {
			addListToList(aliases,info);
			return info;
		}
	}
	public void printCommandHelp(Player p,List<String> list,String cmd) {
		p.sendMessage(format("&7&m--[&r &aHelp for &e/"+cmd+" &7&m]--"));
		for (int i = 0;i<list.size();i++) {
			if (i >= list.size()) {
				break;
			}
			String index = list.get(i);
			if (i == 0) {
				p.sendMessage(format("&aFull command &7- &e"+index));
			} else if (i == 1) {
				p.sendMessage(format("&aExample &7- &e"+index));
			} else if (i == 2) {
				p.sendMessage(format("&aDescription &7- &e"+index));
			} else if (i == 3 && index != "false") {
				p.sendMessage(format("&aAliases&7:"));
				p.sendMessage(format("   &7- &e"+index));
			} else if (i == 3 && index == "false") {
				p.sendMessage(format("&cNo aliases"));
			} else if (i >= 4) {
				p.sendMessage(format("   &7- &e"+index));
			}
		}
		p.sendMessage(format("&7&m--[&r &e[] &a- Optional &7/ &e<> &a- Required &7&m]--"));
		return;
	} 
	public void addListToList(List<String> from, List<String> to) {
		for (int i = 0;i<from.size();i++) {
			if (i >= from.size()) {
				break;
			}
			String index = from.get(i);
			to.add(index);
		}
		return;
	}
	public boolean isPageAvailable(int page) {
		if (plugin.getHelpConfig().getString("Help.pages."+page) != null) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isCommandAvailable(String cmd) {
		int pages = plugin.getHelpConfig().getConfigurationSection("Help.pages").getKeys(false).size();
		for (int i = 1;i<=pages;) {
			if (i > pages) {
				break;
			}
			if (plugin.getHelpConfig().getString("Help.pages."+String.valueOf(i)+"."+cmd) != null) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	public String format(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
}
