package me.hype.factory.events;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import me.hype.factory.Core;
import me.hype.factory.managers.ConfigManager;
import me.hype.factory.managers.InventoryManager;

public class OnClick implements Listener {
	
	Core plugin = Core.getInstance();
	ConfigManager cm = new ConfigManager();
	InventoryManager im = new InventoryManager();
	InventoryClick ic = new InventoryClick();
	
	@EventHandler
	public void onRightClick(PlayerInteractAtEntityEvent e) {
		if (cm == null) {cm = new ConfigManager();}
		if (im == null) {im	= new InventoryManager();}
		Player p = e.getPlayer();
		String wn = p.getWorld().getName();
		if (!cm.getFactoryWorlds().contains(wn)) {
			return;
		}
		if (e.getRightClicked() instanceof ArmorStand) {
			ArmorStand as = (ArmorStand) e.getRightClicked();
			String name = as.getCustomName();
			if (name.contains("Slot Number:")) {
				int slotnumber = getSlotNumber(name);
				int facid = cm.getIdFromString(wn);
				plugin.setFactoryId(facid);
				plugin.setSlotId(slotnumber);
				p.openInventory(im.confirmSlotPurchase(p, slotnumber));
				return;
			}
		}
	}
	// TODO OTHER
	public int getSlotNumber(String s) {
		char[] chars = s.toCharArray();
		int length = chars.length;
		if (length == 18) {
			return Integer.parseInt(String.valueOf(chars[length-1]));
		} else if (length == 19) {
			return Integer.parseInt(String.valueOf(chars[length-2]+""+chars[length-1]));
		} else {
			return 0;
		}
	}
	
	
}
