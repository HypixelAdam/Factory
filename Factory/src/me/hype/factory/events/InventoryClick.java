package me.hype.factory.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import me.hype.factory.Core;
import me.hype.factory.managers.ConfigManager;
import me.hype.factory.managers.InventoryManager;
import me.hype.factory.managers.ItemManager;

public class InventoryClick implements Listener {
	
	InventoryManager im = new InventoryManager();
	ItemManager itm = new ItemManager();
	ConfigManager cm = new ConfigManager();
	Core plugin = Core.getInstance();
	String prefix = Core.getInstance().getConfig().getString("Settings.prefix");
	
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (plugin == null) { plugin = Core.getInstance(); }
		if (cm == null) { cm = new ConfigManager(); }
		if (im == null) { im = new InventoryManager(); }
		// TODO BUY FACTORY INVENTORY
		Inventory inv = e.getInventory();
		Player p = (Player) e.getWhoClicked();
		String dn = null;
		if (inv.getName().equals(format("&6Select a Factory"))) {
			if (dn == null) {dn = format(e.getCurrentItem().getItemMeta().getDisplayName());}
			if (dn.equals(format("&f&lFactory 1")) && e.getCurrentItem().getType() == Material.COAL_BLOCK) {
				e.setCancelled(true);
				p.closeInventory();
				p.sendMessage(format(prefix+"&aOne moment please while the world generates..."));
				cm.createStartingFactoryWorld(p);
				return;
			} else if (dn.equals(format("&f&lFactory 1")) && e.getCurrentItem().getType() == Material.IRON_BLOCK) {
				e.setCancelled(true);
				p.sendMessage(format(prefix+"&aTeleporting..."));
				cm.tpToFactoryHome(p, 1);
				return;
			} else if (!dn.equals(format("&f&lFactory 1"))) {
				for (int i = 2;i<3;i++) {
					if (i > 2) {break;}
					if (dn.equals(format("&f&lFactory "+i)) && e.getCurrentItem().getType() == Material.COAL_BLOCK) {
						e.setCancelled(true);
						p.closeInventory();
						p.openInventory(im.confirmFactoryPurchase(p, i));
						plugin.setFactoryId(i);
						break;
					} else if (dn.equals(format("&f&lFactory "+i)) && e.getCurrentItem().getType() == Material.IRON_BLOCK) {
						e.setCancelled(true);
						p.sendMessage(format(prefix+"&aTeleporting..."));
						cm.tpToFactoryHome(p, i);
						continue;
					}
				}
			}
		}
		// TODO BUY SLOT INVENTORY
		if (inv.getName().equals(format("&6Select a slot"))) {
			if (dn == null) {dn = format(e.getCurrentItem().getItemMeta().getDisplayName());}
			for (int i = 1;i<25;i++) {
				if (i > 24) {break;}
				if (dn.equals(format("&f&lSlot "+i)) && e.getCurrentItem().getType() == Material.COAL_BLOCK) {
					e.setCancelled(true);
					plugin.setFactoryId(cm.getIdFromString(p.getWorld().getName()));
					plugin.setSlotId(i);
					p.closeInventory();
					p.openInventory(im.confirmSlotPurchase(p, i));
					break;
				} else if (dn.equals(format("&f&lSlot "+i)) && e.getCurrentItem().getType() == Material.IRON_BLOCK) {
					e.setCancelled(true);
					continue;
				} else {
					continue;
				}
			}
			return;
		}
		// TODO CONFIRM FACTORY PURCHASE INVENTORY
		if (inv.getName().equals(format("&aConfirm your Factory purchase..."))) {
			if (dn == null) {dn = format(e.getCurrentItem().getItemMeta().getDisplayName());}
			if (dn.equals(format("&a&lYES"))) {
				e.setCancelled(true);
				int pmoney = plugin.getPlayersConfig().getInt("Players."+p.getUniqueId().toString()+".stats.money");
				int cost = cm.getFactoryCost(plugin.getFactoryId());
				if (pmoney < cost) {
					p.closeInventory();
					p.sendMessage(format(prefix+"&cYou don't have enough money to buy this factory. You need &6"+(cost-pmoney)+"&c."));
					return;
				}
				pmoney -= cost;
				plugin.getPlayersConfig().set("Players."+p.getUniqueId().toString()+".stats.money", pmoney);
				plugin.saveConfigToFile(plugin.playersFile, plugin.playersConfig);
				p.closeInventory();
				p.sendMessage(format(prefix+"&aFactory purchase successful. One moment please while the world generates..."));
				cm.createFactoryWorld(p, plugin.getFactoryId());
				return;
			} else if (dn.equals(format("&7Information"))) {
				e.setCancelled(true);
				return;
			} else if (dn.equals(format("&c&lNO"))) {
				e.setCancelled(true);
				p.closeInventory();
				p.sendMessage(format(prefix+"&cYou have denied the factory purchase."));
				return;
			}
		}
		// TODO CONFIRM SLOT PURCHASE INVENTORY
		if (inv.getName().equals(format("&aConfirm your Slot purchase..."))) {
			if (dn == null) {dn = format(e.getCurrentItem().getItemMeta().getDisplayName());}
			if (dn.equals(format("&a&lYES"))) {
				e.setCancelled(true);
				int pmoney = plugin.getPlayersConfig().getInt("Players."+p.getUniqueId().toString()+".stats.money");
				int cost = cm.getSlotCost(p.getWorld().getName(), plugin.getSlotId()-1);
				if (pmoney < cost) {
					p.closeInventory();
					p.sendMessage(format(prefix+"&cYou dont have enough money to buy this slot. You need &6"+(cost-pmoney)+"&c."));
					return;
				}
				pmoney -= cost;
				plugin.getPlayersConfig().set("Players."+p.getUniqueId().toString()+".stats.money", pmoney);
				plugin.saveConfigToFile(plugin.playersFile, plugin.playersConfig);
				p.closeInventory();
				p.sendMessage(format(prefix+"&aSlot purchase successful. One moment..."));
				cm.addBoughtSlot(p,plugin.getFactoryId(),(plugin.getSlotId()-1));
				return;
			} else if (dn.equals(format("&7Information"))) {
				e.setCancelled(true);
				return;
			} else if (dn.equals(format("&c&lNO"))) {
				e.setCancelled(true);
				p.closeInventory();
				p.sendMessage(format(prefix+"&cYou have denided the slot purchase."));
				return;
			}
		}
		// TODO BUY FACTORY EQUIPMENT INVENTORY
		if (inv.getName().equals(format("&6Factory Equipment"))) {
			if (dn == null) {dn = format(e.getCurrentItem().getItemMeta().getDisplayName());}
			if (dn.equals(format("&6Buyer"))) {
				e.setCancelled(true);
				int pmoney = plugin.getPlayersConfig().getInt("Players."+p.getUniqueId().toString()+".stats.money");
				int cost = plugin.getFactoryConfig().getInt("FactorySettings.factory-equipment.buyer.cost");
				if (pmoney < cost) {
					p.sendMessage(format(prefix+"&cYou dont have enough money to buy this equipment."));
					return;
				}
				pmoney -= cost;
				plugin.getPlayersConfig().set("Players."+p.getUniqueId().toString()+".stats.money", pmoney);
				plugin.saveConfigToFile(plugin.playersFile, plugin.playersConfig);
				cm.addItemToStock(p, "buyer", 1);
				p.closeInventory();
				p.sendMessage(format(prefix+"&6Buyer &apurchase successful. Check your stock (chest)."));
				return;
			} else if (dn.equals(format("&6Seller"))) {
				e.setCancelled(true);
				int pmoney = plugin.getPlayersConfig().getInt("Players."+p.getUniqueId().toString()+".stats.money");
				int cost = plugin.getFactoryConfig().getInt("FactorySettings.factory-equipment.seller.cost");
				if (pmoney < cost) {
					p.sendMessage(format(prefix+"&cYou dont have enough money to buy this equipment."));
					return;
				}
				pmoney -= cost;
				plugin.getPlayersConfig().set("Players."+p.getUniqueId().toString()+".stats.money", pmoney);
				plugin.saveConfigToFile(plugin.playersFile, plugin.playersConfig);
				cm.addItemToStock(p, "seller", 1);
				p.closeInventory();
				p.sendMessage(format(prefix+"&6Seller &apurchase successful. Check your stock (chest)."));
				return;
			} else if (dn.equals(format("&6Conveyor Belt"))) {
				e.setCancelled(true);
				int pmoney = plugin.getPlayersConfig().getInt("Players."+p.getUniqueId().toString()+".stats.money");
				int cost = plugin.getFactoryConfig().getInt("FactorySettings.factory-equipment.conveyorbelt.cost");
				if (pmoney < cost) {
					p.sendMessage(format(prefix+"&cYou dont have enough money to buy this equipment."));
					return;
				}
				pmoney -= cost;
				plugin.getPlayersConfig().set("Players."+p.getUniqueId().toString()+".stats.money", pmoney);
				plugin.saveConfigToFile(plugin.playersFile, plugin.playersConfig);
				cm.addItemToStock(p, "conveyorbelt", 1);
				p.closeInventory();
				p.sendMessage(format(prefix+"&6Conveyor Belt &apurchase successful. Check your stock (chest)."));
				return;
			}
		}
	}
	// TODO OTHER
	public String format(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
}
