package me.hype.factory.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.hype.factory.Core;
import me.hype.factory.managers.ConfigManager;
import me.hype.factory.managers.InventoryManager;

public class OnInteract implements Listener {
	
	Core plugin = Core.getInstance();
	ConfigManager cm = new ConfigManager();
	InventoryManager im = new InventoryManager();
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (cm == null) {cm = new ConfigManager();}
		if (im == null) {im	= new InventoryManager();}
		Player p = e.getPlayer();
		Action a = e.getAction();
		
		if ((a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK) 
				&& p.getInventory().getItemInMainHand().getType() == Material.EMERALD
				&& p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(format("&aBuy Factory Equipment"))) {
			p.openInventory(im.buyEquipmentInventory());
			return;
		}

	}
	
	public String format(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
}
