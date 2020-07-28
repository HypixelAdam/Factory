package me.hype.factory.events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import me.hype.factory.Core;
import me.hype.factory.managers.ConfigManager;

public class OnBlockPlace implements Listener {
	
	Core plugin = Core.getInstance();
	ConfigManager cm = new ConfigManager();
	String prefix = Core.getInstance().getConfig().getString("Settings.prefix");
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if (cm == null) {cm = new ConfigManager();}
		if (plugin == null) {plugin = Core.getInstance();}
		Player p = e.getPlayer();
		Location loc = e.getBlockPlaced().getLocation();
		if (!cm.getFactoryWorlds().contains(p.getWorld().getName())) {
			return;
		} else {
			if (loc.getBlockY() <= 40 || loc.getBlockY() >= 42) {e.setCancelled(true);p.sendMessage(format(prefix+"&cYou can't place that here."));return;}
			if (p.getInventory().getItemInMainHand().getType() == Material.DISPENSER
					&& p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(format("&6Buyer")) 
					&& !(loc.getBlockY() <= 40 || loc.getBlockY() >= 42)) {
				p.getWorld().getBlockAt(loc.getBlockX(),loc.getBlockY()-1,loc.getBlockZ()).setType(Material.IRON_BLOCK);
				p.sendMessage(format(prefix+"&6Buyer &asuccessfully placed."));
				BlockFace bf = ((org.bukkit.material.Dispenser)e.getBlock().getState().getData()).getFacing();
				if (bf == BlockFace.UP || bf == BlockFace.DOWN) {e.setCancelled(true);p.sendMessage(format(prefix+"&cInvalid placement."));}
				return;
			} else if (p.getInventory().getItemInMainHand().getType() == Material.CHEST 
					&& p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(format("&aEquipment Storage"))) {
				p.sendMessage(format(prefix+"&cTry right clicking the air."));
				e.setCancelled(true);
			} else if (p.getInventory().getItemInMainHand().getType() == Material.BARRIER
					&& p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(format("&cExit"))) {
				p.sendMessage(format(prefix+"&cTry right clicking the air."));
				e.setCancelled(true);
			}
		}
	}
	
	public String format(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
}
