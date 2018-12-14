package me.hype.factory.managers;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;

import me.hype.factory.Core;

public class ArmorstandManager {
	
	Core plugin = Core.getInstance();
	String prefix = Core.getInstance().getConfig().getString("Settings.prefix");
	
	// TODO METHODS FOR ARMORSTANDS
	public void createArmorStand(Location loc, String name) {
		ArmorStand as = loc.getWorld().spawn(loc, ArmorStand.class);
		setArmorStandVariables(as,name);
		return;
	}
	public void despawnArmorStand(Location loc) {
		for (Entity e : loc.getWorld().getEntities()) {
			if (e instanceof ArmorStand) {
				Location eloc = e.getLocation();
				if (eloc.getBlockX() == loc.getBlockX() && eloc.getBlockY() == loc.getBlockY() && eloc.getBlockZ() == loc.getBlockZ()) {
					ArmorStand as = (ArmorStand) e;
					as.remove();
					return;
				}
			}
		}
	}
	public void despawnArmorStand(Location loc, double radius) {
		for (Entity e : loc.getWorld().getNearbyEntities(loc, radius, radius, radius)) {
			if (e instanceof ArmorStand) {
				ArmorStand as = (ArmorStand) e;
				as.remove();
			}
		}
	}
	public void setArmorStandVariables(ArmorStand as,String name) {
		as.setVisible(false);
		as.setBasePlate(false);
		as.setCollidable(false);
		as.setInvulnerable(true);
		as.setGravity(false);
		as.setCustomNameVisible(true);
		as.setCustomName(format(name));
		return;
	}
	// TODO CREATE RELOAD FACTORY STANDS
	public void createFactorySlotNumberArmorStandsR(World w, int facid, List<Integer> skipSlots) {
		for (int i = 1;i<25;i++) {
			if (i > 24) {
				break;
			}
			if (skipSlots.contains(i)) {continue;}
			int x = plugin.getFactoryConfig().getInt("FactorySettings.factoryslots.factorys."+facid+".slots.slot"+i+".locationc.x");
			int y = 42;
			int z = plugin.getFactoryConfig().getInt("FactorySettings.factoryslots.factorys."+facid+".slots.slot"+i+".locationc.z");
			Location center = new Location(w,x,y,z);
			createArmorStand(center, "&aSlot Number: &e"+(i+1));
		}
	}
	public void createFactorySlotCostArmorStandsR(World w, int facid, List<Integer> skipSlots) {
		for (int i = 1;i<25;i++) {
			if (i > 24) {
				break;
			}
			if (skipSlots.contains(i)) {continue;}
			int x = plugin.getFactoryConfig().getInt("FactorySettings.factoryslots.factorys."+facid+".slots.slot"+i+".locationc.x");
			double y = 41.5;
			int z = plugin.getFactoryConfig().getInt("FactorySettings.factoryslots.factorys."+facid+".slots.slot"+i+".locationc.z");
			int cost = plugin.getFactoryConfig().getInt("FactorySettings.factoryslots.factorys."+facid+".slots.slot"+i+".cost");
			Location center = new Location(w,x,y,z);
			createArmorStand(center, "&aCost: &e$"+cost);
		}
	}
	public void createFactorySlotInformationStandsR(World w, int facid, List<Integer> skipSlots) {
		for (int i = 1;i<25;i++) {
			if (i > 24) {
				break;
			}
			if (skipSlots.contains(i)) {continue;}
			int x = plugin.getFactoryConfig().getInt("FactorySettings.factoryslots.factorys."+facid+".slots.slot"+i+".locationc.x");
			int y = 41;
			int z = plugin.getFactoryConfig().getInt("FactorySettings.factoryslots.factorys."+facid+".slots.slot"+i+".locationc.z");
			Location center = new Location(w,x,y,z);
			createArmorStand(center,"&6Right click me to buy!");
		}
	}
	// TODO CREATE NORMAL FACTORY STANDS
	public void createFactorySlotNumberArmorStands(World w, int facid) {
		for (int i = 1;i<25;i++) {
			if (i > 24) {
				break;
			}
			int x = plugin.getFactoryConfig().getInt("FactorySettings.factoryslots.factorys."+facid+".slots.slot"+i+".locationc.x");
			int y = 42;
			int z = plugin.getFactoryConfig().getInt("FactorySettings.factoryslots.factorys."+facid+".slots.slot"+i+".locationc.z");
			Location center = new Location(w,x,y,z);
			createArmorStand(center, "&aSlot Number: &e"+(i+1));
		}
	}
	
	public void createFactorySlotCostArmorStands(World w, int facid) {
		for (int i = 1;i<25;i++) {
			if (i > 24) {
				break;
			}
			int x = plugin.getFactoryConfig().getInt("FactorySettings.factoryslots.factorys."+facid+".slots.slot"+i+".locationc.x");
			double y = 41.5;
			int z = plugin.getFactoryConfig().getInt("FactorySettings.factoryslots.factorys."+facid+".slots.slot"+i+".locationc.z");
			int cost = plugin.getFactoryConfig().getInt("FactorySettings.factoryslots.factorys."+facid+".slots.slot"+i+".cost");
			Location center = new Location(w,x,y,z);
			createArmorStand(center, "&aCost: &e$"+cost);
		}
	}
	
	public void createFactorySlotInformationStands(World w, int facid) {
		for (int i = 1;i<25;i++) {
			if (i > 24) {
				break;
			}
			int x = plugin.getFactoryConfig().getInt("FactorySettings.factoryslots.factorys."+facid+".slots.slot"+i+".locationc.x");
			int y = 41;
			int z = plugin.getFactoryConfig().getInt("FactorySettings.factoryslots.factorys."+facid+".slots.slot"+i+".locationc.z");
			Location center = new Location(w,x,y,z);
			createArmorStand(center,"&6Right click me to buy!");
		}
	}
	// TODO OTHER
	public String format(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
}
