package me.hype.factory.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;

import me.hype.factory.Core;

public class ConfigManager {
	
	private static ConfigManager configmanager;
	Core plugin = Core.getInstance();
	String prefix = Core.getInstance().getConfig().getString("Settings.prefix");
	ArmorstandManager asm = null;
	ScoreboardManager sbm = null;
	InventoryManager im = null;
	
	// TODO WORLD CREATION
	@SuppressWarnings("deprecation")
		// TODO CREATE FIRST FACTORY WORLD
	public void createStartingFactoryWorld(Player p) {
		if (asm == null || sbm == null) {sbm = new ScoreboardManager(); asm = new ArmorstandManager();}
		WorldCreator wc = new WorldCreator(p.getName()+"1");
		wc.type(WorldType.FLAT);
		wc.generatorSettings("2;0;1;");
		wc.createWorld();
		World w = Bukkit.getWorld(p.getName()+"1");
		w.setDifficulty(Difficulty.PEACEFUL);
		w.setGameRuleValue("doWeatherCycle", "false");
		w.setGameRuleValue("doDaylightCycle", "false");
		w.setGameRuleValue("doMobSpawning", "false");
		// TODO ALL SLOTS
		int y = 40;
		Location corner1 = new Location(w,-6,y,6);
		Location corner2 = new Location(w,58,y,-58);
		int minx = (corner1.getBlockX() < corner2.getBlockX() ? corner2.getBlockX() : corner1.getBlockX());
		int maxx = (corner1.getBlockX() > corner2.getBlockX() ? corner2.getBlockX() : corner1.getBlockX());
		int minz = (corner1.getBlockZ() < corner2.getBlockZ() ? corner2.getBlockZ() : corner1.getBlockZ());
		int maxz = (corner1.getBlockZ() > corner2.getBlockZ() ? corner2.getBlockZ() : corner1.getBlockZ());
		for (int x = maxx;x <= minx;x++) {
				for (int z = maxz;z <= minz;z++) {				
					Location bloc = new Location(w,x,y,z);
					bloc.getBlock().setType(Material.COAL_BLOCK);
				}
		}
		Bukkit.getConsoleSender().sendMessage(format(prefix+"&aFactory slots complete!"));
		Bukkit.getConsoleSender().sendMessage(format(prefix+"&6Starting first slot..."));
		// TODO FIRST SLOT
		corner1 = new Location(w,6,y,-6);
		corner2 = new Location(w,-6,y,6);
		minx = (corner1.getBlockX() < corner2.getBlockX() ? corner2.getBlockX() : corner1.getBlockX());
		maxx = (corner1.getBlockX() > corner2.getBlockX() ? corner2.getBlockX() : corner1.getBlockX());
		minz = (corner1.getBlockZ() < corner2.getBlockZ() ? corner2.getBlockZ() : corner1.getBlockZ());
		maxz = (corner1.getBlockZ() > corner2.getBlockZ() ? corner2.getBlockZ() : corner1.getBlockZ());
		for (int x = maxx;x <= minx;x++) {
				for (int z = maxz;z <= minz;z++) {
					Location bloc = new Location(w,x,y,z);
					bloc.getBlock().setType(Material.SMOOTH_BRICK);
				}
		}
		Bukkit.getConsoleSender().sendMessage(format(prefix+"&aFirst slot complete!"));
		Bukkit.getConsoleSender().sendMessage(format(prefix+"&6Starting factory border..."));
		// TODO BORDER
			// TODO SOUTH TO NORTH BORDER
		corner1 = new Location(w,-7,40,-59);
		corner2 = new Location(w,-7,40,7);
		minx = (corner1.getBlockX() < corner2.getBlockX() ? corner2.getBlockX() : corner1.getBlockX());
		maxx = (corner1.getBlockX() > corner2.getBlockX() ? corner2.getBlockX() : corner1.getBlockX());
		minz = (corner1.getBlockZ() < corner2.getBlockZ() ? corner2.getBlockZ() : corner1.getBlockZ());
		maxz = (corner1.getBlockZ() > corner2.getBlockZ() ? corner2.getBlockZ() : corner1.getBlockZ());
		for (int x = maxx;x <= minx;x++) {
			for (int z = maxz;z <= minz;z++) {
				Location bloc = new Location(w,x,y,z);
				bloc.getBlock().setType(Material.WOOL);
				bloc.getBlock().setData((byte)14);
			}
		}
			// TODO WEST TO EAST BORDER
		corner2 = new Location(w,59,40,-59);
		minx = (corner1.getBlockX() < corner2.getBlockX() ? corner2.getBlockX() : corner1.getBlockX());
		maxx = (corner1.getBlockX() > corner2.getBlockX() ? corner2.getBlockX() : corner1.getBlockX());
		minz = (corner1.getBlockZ() < corner2.getBlockZ() ? corner2.getBlockZ() : corner1.getBlockZ());
		maxz = (corner1.getBlockZ() > corner2.getBlockZ() ? corner2.getBlockZ() : corner1.getBlockZ());
		for (int x = maxx;x <= minx;x++) {
			for (int z = maxz;z <= minz;z++) {
				Location bloc = new Location(w,x,y,z);
				bloc.getBlock().setType(Material.WOOL);
				bloc.getBlock().setData((byte)14);
			}
		}
			// TODO NORTH TO SOUTH BORDER
		corner1 = new Location(w,59,40,7);
		minx = (corner1.getBlockX() < corner2.getBlockX() ? corner2.getBlockX() : corner1.getBlockX());
		maxx = (corner1.getBlockX() > corner2.getBlockX() ? corner2.getBlockX() : corner1.getBlockX());
		minz = (corner1.getBlockZ() < corner2.getBlockZ() ? corner2.getBlockZ() : corner1.getBlockZ());
		maxz = (corner1.getBlockZ() > corner2.getBlockZ() ? corner2.getBlockZ() : corner1.getBlockZ());
		for (int x = maxx;x <= minx;x++) {
			for (int z = maxz;z <= minz;z++) {
				Location bloc = new Location(w,x,y,z);
				bloc.getBlock().setType(Material.WOOL);
				bloc.getBlock().setData((byte)14);
			}
		}
			// TODO EAST TO WEST BORDER
		corner2 = new Location(w,-7,40,7);
		minx = (corner1.getBlockX() < corner2.getBlockX() ? corner2.getBlockX() : corner1.getBlockX());
		maxx = (corner1.getBlockX() > corner2.getBlockX() ? corner2.getBlockX() : corner1.getBlockX());
		minz = (corner1.getBlockZ() < corner2.getBlockZ() ? corner2.getBlockZ() : corner1.getBlockZ());
		maxz = (corner1.getBlockZ() > corner2.getBlockZ() ? corner2.getBlockZ() : corner1.getBlockZ());
		for (int x = maxx;x <= minx;x++) {
			for (int z = maxz;z <= minz;z++) {
				Location bloc = new Location(w,x,y,z);
				bloc.getBlock().setType(Material.WOOL);
				bloc.getBlock().setData((byte)14);
			}
		}
		Bukkit.getConsoleSender().sendMessage(format(prefix+"&aFactory slots complete!"));
		Bukkit.getConsoleSender().sendMessage(format(prefix+"&6Teleporting player..."));
		addPlayerStarting(p,w);
		asm.createFactorySlotNumberArmorStands(w, 1);
		asm.createFactorySlotCostArmorStands(w, 1);
		asm.createFactorySlotInformationStands(w, 1);
		addFactoryWorld(w.getName());
		sbm.factoryScoreboard(p, 1);
		p.sendMessage(format(prefix+"&aFactory Creation Complete! Teleporting..."));
		w.getBlockAt(new Location(w,0,0,0)).setType(Material.AIR);
		p.teleport(new Location(w,0,42,0));
		return;
	}
		// TODO CREATE OTHER FACTORY WORLDS
	@SuppressWarnings("deprecation")
	public void createFactoryWorld(Player p,int facid) {
		if (asm == null || sbm == null) {sbm = new ScoreboardManager(); asm = new ArmorstandManager();}
		WorldCreator wc = new WorldCreator(p.getName()+facid);
		wc.type(WorldType.FLAT);
		wc.generatorSettings("2;0;1;");
		wc.createWorld();
		World w = Bukkit.getWorld(p.getName()+facid);
		w.setDifficulty(Difficulty.PEACEFUL);
		w.setGameRuleValue("doWeatherCycle", "false");
		w.setGameRuleValue("doDaylightCycle", "false");
		w.setGameRuleValue("doMobSpawning", "false");
		// TODO ALL SLOTS
		int y = 40;
		Location corner1 = new Location(w,-6,y,6);
		Location corner2 = new Location(w,58,y,-58);
		int minx = (corner1.getBlockX() < corner2.getBlockX() ? corner2.getBlockX() : corner1.getBlockX());
		int maxx = (corner1.getBlockX() > corner2.getBlockX() ? corner2.getBlockX() : corner1.getBlockX());
		int minz = (corner1.getBlockZ() < corner2.getBlockZ() ? corner2.getBlockZ() : corner1.getBlockZ());
		int maxz = (corner1.getBlockZ() > corner2.getBlockZ() ? corner2.getBlockZ() : corner1.getBlockZ());
		for (int x = maxx;x <= minx;x++) {
				for (int z = maxz;z <= minz;z++) {				
					Location bloc = new Location(w,x,y,z);
					bloc.getBlock().setType(Material.COAL_BLOCK);
				}
		}
		Bukkit.getConsoleSender().sendMessage(format(prefix+"&aFactory slots complete!"));
		Bukkit.getConsoleSender().sendMessage(format(prefix+"&6Starting first slot..."));
		// TODO FIRST SLOT
		corner1 = new Location(w,6,y,-6);
		corner2 = new Location(w,-6,y,6);
		minx = (corner1.getBlockX() < corner2.getBlockX() ? corner2.getBlockX() : corner1.getBlockX());
		maxx = (corner1.getBlockX() > corner2.getBlockX() ? corner2.getBlockX() : corner1.getBlockX());
		minz = (corner1.getBlockZ() < corner2.getBlockZ() ? corner2.getBlockZ() : corner1.getBlockZ());
		maxz = (corner1.getBlockZ() > corner2.getBlockZ() ? corner2.getBlockZ() : corner1.getBlockZ());
		for (int x = maxx;x <= minx;x++) {
				for (int z = maxz;z <= minz;z++) {
					Location bloc = new Location(w,x,y,z);
					bloc.getBlock().setType(Material.SMOOTH_BRICK);
				}
		}
		Bukkit.getConsoleSender().sendMessage(format(prefix+"&aFirst slot complete!"));
		Bukkit.getConsoleSender().sendMessage(format(prefix+"&6Starting factory border..."));
		// TODO BORDER
			// TODO SOUTH TO NORTH BORDER
		corner1 = new Location(w,-7,40,-59);
		corner2 = new Location(w,-7,40,7);
		minx = (corner1.getBlockX() < corner2.getBlockX() ? corner2.getBlockX() : corner1.getBlockX());
		maxx = (corner1.getBlockX() > corner2.getBlockX() ? corner2.getBlockX() : corner1.getBlockX());
		minz = (corner1.getBlockZ() < corner2.getBlockZ() ? corner2.getBlockZ() : corner1.getBlockZ());
		maxz = (corner1.getBlockZ() > corner2.getBlockZ() ? corner2.getBlockZ() : corner1.getBlockZ());
		for (int x = maxx;x <= minx;x++) {
			for (int z = maxz;z <= minz;z++) {
				Location bloc = new Location(w,x,y,z);
				bloc.getBlock().setType(Material.WOOL);
				bloc.getBlock().setData((byte)14);
			}
		}
			// TODO WEST TO EAST BORDER
		corner2 = new Location(w,59,40,-59);
		minx = (corner1.getBlockX() < corner2.getBlockX() ? corner2.getBlockX() : corner1.getBlockX());
		maxx = (corner1.getBlockX() > corner2.getBlockX() ? corner2.getBlockX() : corner1.getBlockX());
		minz = (corner1.getBlockZ() < corner2.getBlockZ() ? corner2.getBlockZ() : corner1.getBlockZ());
		maxz = (corner1.getBlockZ() > corner2.getBlockZ() ? corner2.getBlockZ() : corner1.getBlockZ());
		for (int x = maxx;x <= minx;x++) {
			for (int z = maxz;z <= minz;z++) {
				Location bloc = new Location(w,x,y,z);
				bloc.getBlock().setType(Material.WOOL);
				bloc.getBlock().setData((byte)14);
			}
		}
			// TODO NORTH TO SOUTH BORDER
		corner1 = new Location(w,59,40,7);
		minx = (corner1.getBlockX() < corner2.getBlockX() ? corner2.getBlockX() : corner1.getBlockX());
		maxx = (corner1.getBlockX() > corner2.getBlockX() ? corner2.getBlockX() : corner1.getBlockX());
		minz = (corner1.getBlockZ() < corner2.getBlockZ() ? corner2.getBlockZ() : corner1.getBlockZ());
		maxz = (corner1.getBlockZ() > corner2.getBlockZ() ? corner2.getBlockZ() : corner1.getBlockZ());
		for (int x = maxx;x <= minx;x++) {
			for (int z = maxz;z <= minz;z++) {
				Location bloc = new Location(w,x,y,z);
				bloc.getBlock().setType(Material.WOOL);
				bloc.getBlock().setData((byte)14);
			}
		}
			// TODO EAST TO WEST BORDER
		corner2 = new Location(w,-7,40,7);
		minx = (corner1.getBlockX() < corner2.getBlockX() ? corner2.getBlockX() : corner1.getBlockX());
		maxx = (corner1.getBlockX() > corner2.getBlockX() ? corner2.getBlockX() : corner1.getBlockX());
		minz = (corner1.getBlockZ() < corner2.getBlockZ() ? corner2.getBlockZ() : corner1.getBlockZ());
		maxz = (corner1.getBlockZ() > corner2.getBlockZ() ? corner2.getBlockZ() : corner1.getBlockZ());
		for (int x = maxx;x <= minx;x++) {
			for (int z = maxz;z <= minz;z++) {
				Location bloc = new Location(w,x,y,z);
				bloc.getBlock().setType(Material.WOOL);
				bloc.getBlock().setData((byte)14);
			}
		}
		Bukkit.getConsoleSender().sendMessage(format(prefix+"&aFactory slots complete!"));
		Bukkit.getConsoleSender().sendMessage(format(prefix+"&6Teleporting player..."));
		addPlayerFactory(p,facid,w);
		asm.createFactorySlotNumberArmorStands(w, facid);
		asm.createFactorySlotCostArmorStands(w, facid);
		asm.createFactorySlotInformationStands(w, facid);
		sbm.factoryScoreboard(p, facid);
		addFactoryWorld(w.getName());
		p.sendMessage(format(prefix+"&aFactory Creation Complete! Teleporting..."));
		w.getBlockAt(new Location(w,0,0,0)).setType(Material.AIR);
		p.teleport(new Location(w,0,42,0));
		return;
	}
	public String format(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
	// TODO STATS (PLAYERS)
	public void addPlayer(Player p) {
		String uuid = p.getUniqueId().toString();
		plugin.getPlayersConfig().set("Players."+uuid+".info.uuid", uuid);
		plugin.getPlayersConfig().set("Players."+uuid+".info.name", p.getName());
		plugin.getPlayersConfig().set("Players."+uuid+".stats.money", 0);
		plugin.getPlayersConfig().set("Players."+uuid+".stats.moneyspent", 0);
		plugin.getPlayersConfig().set("Players."+uuid+".stats.moneyearned", 0);
		plugin.getPlayersConfig().set("Players."+uuid+".factories", "");
		plugin.getPlayersConfig().set("Players."+uuid+".equipmentstock", "");
		plugin.saveConfigToFile(plugin.playersFile, plugin.playersConfig);
		return;
	}
	public List<String> getPlayerStats(Player p) {
		String uuid = p.getUniqueId().toString();
		List<String> list = new ArrayList<String>();
		list.add(String.valueOf(getPlayerMoney(uuid)));
		list.add(String.valueOf(getPlayerMoneySpent(uuid)));
		list.add(String.valueOf(getPlayerMoneyEarned(uuid)));
		list.add(String.valueOf(getPlayerFactoriesOwned(uuid)));
		list.add(String.valueOf(getPlayerName(uuid)));
		return list;
	}
	public int getPlayerMoney(String uuid) {
		return plugin.getPlayersConfig().getInt("Players."+uuid+".stats.money");
	}
	public int getPlayerMoneySpent(String uuid) {
		return plugin.getPlayersConfig().getInt("Players."+uuid+".stats.moneyspent");
	}
	public int getPlayerMoneyEarned(String uuid) {
		return plugin.getPlayersConfig().getInt("Players."+uuid+".stats.moneyearned");
	}
	public int getPlayerFactoriesOwned(String uuid) {
		if (plugin.getPlayersConfig().getConfigurationSection("Players."+uuid+".factories").getKeys(false).size() == 0 || 
				plugin.getPlayersConfig().getConfigurationSection("Players."+uuid+".factories") == null) {
			return 0;
		} else {
			return plugin.getPlayersConfig().getConfigurationSection("Players."+uuid+".factories").getKeys(false).size();
		}
	}
	public String getPlayerName(String uuid) {
		return plugin.getPlayersConfig().getString("Players."+uuid+".info.name");
	}
	
	// TODO FACTORY (PLAYERS)
	public int totalFactoriesOwned(Player p) {
		if (!isFactoryOwned(p,1)) {
			return 0;
		}
		return plugin.getPlayersConfig().getConfigurationSection("Players."+p.getUniqueId().toString()+".factories").getKeys(false).size();
	}
	public int totalFactoriesMPS(Player p) {
		String uuid = p.getUniqueId().toString();
		List<Integer> mpspf = new ArrayList<Integer>();
		if (!isFactoryOwned(p,1)) {
			return 0;
		}
		for (String id : plugin.getPlayersConfig().getConfigurationSection("Players."+uuid+".factories").getKeys(false)) {
			int mps = plugin.getPlayersConfig().getInt("Players."+uuid+".factories."+id+".money-per-sec");
			mpspf.add(mps);
		}
		return addIntList(mpspf);
	}
	public void tpToFactoryHome(Player p, int facid) {
		if (sbm == null) {sbm = new ScoreboardManager();}
		if (im == null) {im = new InventoryManager();}
		sbm.factoryScoreboard(p, facid);
		im.factoryInventory(p);
		Location loc = new Location(Bukkit.getWorld(plugin.getPlayersConfig().getString("Players."+p.getUniqueId().toString()+".info.name")+facid),0,42,0);
		p.teleport(loc);
		return;
	}
	public String alreadyBoughtS(Player p, int facid) {
		String uuid = p.getUniqueId().toString();
		if (!doesPlayerExist(p)) {
			return "&c&l✘";
		}
		if (plugin.getPlayersConfig().getString("Players."+uuid+".factories."+facid) != null) {
			return "&a&l✔";
		} else {
			return "&c&l✘";
		}
	}
	public boolean alreadyBoughtB(Player p, int facid) {
		String uuid = p.getUniqueId().toString();
		if (!doesPlayerExist(p)) {
			return false;
		}
		if (plugin.getPlayersConfig().getString("Players."+uuid+".factories."+facid) != null) {
			return true;
		} else { 
			return false;
		}
	}
	public void addPlayerStarting(Player p, World w) {
		String uuid = p.getUniqueId().toString();
		if (doesPlayerExist(p)) {
			if (isFactoryOwned(p,1)) {
				return;
			} else {
				plugin.getPlayersConfig().set("Players."+uuid+".factories.1.slots-owned", new ArrayList<Integer>().add(0));
				plugin.getPlayersConfig().set("Players."+uuid+".factories.1.money-per-sec", 0);
				plugin.getPlayersConfig().set("Players."+uuid+".factories.1.money-earned", 0);
				plugin.getPlayersConfig().set("Players."+uuid+".factories.1.money-spent", 0);
				plugin.getPlayersConfig().set("Players."+uuid+".factories.1.worldname", w.getName());
				plugin.saveConfigToFile(plugin.playersFile,plugin.playersConfig);
				return;
			}
		} else {
			p.sendMessage(format(prefix+"&cThere seems to be a problem loading in your profile. Contact a staff member."));
			return;
		}
		
	}
	public void addPlayerFactory(Player p, int facid, World w) {
		String uuid = p.getUniqueId().toString();
		if (!doesPlayerExist(p)) {
			p.sendMessage(format(prefix+"&cThere seems to be a problem loading in your profile. Contact a staff member."));
			return;
		}
		if (isFactoryOwned(p,facid)) {
			p.sendMessage(format(prefix+"&cYou seem to already own this factory. If this is an error, contact a staff member."));
			return;
		}
		plugin.getPlayersConfig().set("Players."+uuid+".factories."+facid+".slots-owned", new ArrayList<Integer>().add(0));
		plugin.getPlayersConfig().set("Players."+uuid+".factories."+facid+".money-per-sec", 0);
		plugin.getPlayersConfig().set("Players."+uuid+".factories."+facid+".money-earned", 0);
		plugin.getPlayersConfig().set("Players."+uuid+".factories."+facid+".money-spent", 0);
		plugin.getPlayersConfig().set("Players."+uuid+".factories."+facid+".worldname", w.getName());
		plugin.saveConfigToFile(plugin.playersFile,plugin.playersConfig);
		return;
	}
	public void addSlotsOwned(Player p,int facid, int slotid) {
		String uuid = p.getUniqueId().toString();
		List<Integer> slotsowned = getPlayerSlotsOwned(p,facid);
		slotsowned.add(slotid);
		plugin.getPlayersConfig().set("Players."+uuid+".factories."+facid+".slots-owned", slotsowned);
		plugin.saveConfigToFile(plugin.playersFile, plugin.playersConfig);
		return;
	}
	public HashMap<String,List<Integer>> getPlayerSlotsOwnedUUID() {
		HashMap<String,List<Integer>> hash = new HashMap<String,List<Integer>>();
		List<Integer> slotsowned = null;
		for (String uuid : plugin.getPlayersConfig().getConfigurationSection("Players").getKeys(false)) {
			for (String facid : plugin.getPlayersConfig().getConfigurationSection("Players."+uuid+".factories").getKeys(false)) {
				slotsowned = plugin.getPlayersConfig().getIntegerList("Players."+uuid+".factories."+facid+".slots-owned");
				hash.put(plugin.getPlayersConfig().getString("Players."+uuid+".factories."+facid+".worldname"), slotsowned);
			}
		}
		if (slotsowned == null || hash == null || hash.isEmpty() || hash.size() == 0) {
			return null;
		}
		return hash;
	}
	public List<Integer> getPlayerSlotsOwned(Player p,int facid) {
		List<Integer> slotsowned = plugin.getPlayersConfig().getIntegerList("Players."+p.getUniqueId().toString()+".factories."+String.valueOf(facid)+".slots-owned");
		return slotsowned;
	}
	public void addItemToStock(Player p, String item, int amount) {
		String uuid = p.getUniqueId().toString();
		int amt = plugin.getPlayersConfig().getInt("Players."+uuid+".equipmentstock."+item);
		plugin.getPlayersConfig().set("Players."+uuid+".equipmentstock."+item, amt+amount);
		plugin.saveConfigToFile(plugin.playersFile, plugin.playersConfig);
		return;
	}
	// TODO FACTORY (OTHER)
	public int getFactoryCost(int facid) {
		return plugin.getFactoryConfig().getInt("FactorySettings.factorycost."+facid+".cost");
	}
	public void addFactoryWorld(String worldname) {
		List<String> worlds = plugin.getFactoryConfig().getStringList("FactorySettings.factoryworlds");
		worlds.add(worldname);
		plugin.getFactoryConfig().set("FactorySettings.factoryworlds", worlds);
		plugin.saveConfigToFile(plugin.factoryFile, plugin.factoryConfig);
		return;
	}
	public void removeFactoryWorld(String worldname) {
		List<String> worlds = plugin.getFactoryConfig().getStringList("FactorySettings.factoryworlds");
		worlds.remove(worldname);
		plugin.getFactoryConfig().set("FactorySettings.factoryworlds", worlds);
		plugin.saveConfigToFile(plugin.factoryFile, plugin.factoryConfig);
		return;
	}
	public List<String> getFactoryWorlds() {
		return plugin.getFactoryConfig().getStringList("FactorySettings.factoryworlds");
	}
	public void checkIfWorldsAreAdded() {
		List<String> uuids = getPlayerUUIDS();
		List<String> worlds = getFactoryWorlds();
		for (int i = 0;i<uuids.size();i++) {
			if (i > uuids.size()) {break;}
			String uuid = uuids.get(i);
			if (plugin.getPlayersConfig().getString("Players."+uuid+".factories.1") == null) {continue;}
			for (String facid : plugin.getPlayersConfig().getConfigurationSection("Players."+uuid+".factories").getKeys(false)) {
				String worldname = plugin.getPlayersConfig().getString("Players."+uuid+".factories."+facid+".worldname");
				if (worlds.contains(worldname)) {
					continue;
				}
				addFactoryWorld(worldname);
			}
		}
		return;
	}
	// TODO FACTORY SLOTS
	public boolean isSlotOwned(Player p, int facid, int slotid) {
		World w = p.getWorld();
		int x = plugin.getFactoryConfig().getInt("FactorySettings.factoryslots.factorys."+facid+".slots.slot"+slotid+".locationc.x");
		int y = plugin.getFactoryConfig().getInt("FactorySettings.factoryslots.factorys."+facid+".slots.slot"+slotid+".locationc.y");
		int z = plugin.getFactoryConfig().getInt("FactorySettings.factoryslots.factorys."+facid+".slots.slot"+slotid+".locationc.z");
		Location l = new Location(w,x,y,z);
		if (l.getBlock().getType() == Material.COAL_BLOCK) {
			return false;
		} else {
			return true;
		}
	}
	public void addBoughtSlot(Player p,int facid, int slotid) {
		if (asm == null) {asm = new ArmorstandManager();}
		if (sbm == null) {sbm = new ScoreboardManager();}
		if (!getFactoryWorlds().contains(p.getWorld().getName())) {p.sendMessage(format(prefix+"&cYou are not in a factory world."));}
		int x = plugin.getFactoryConfig().getInt("FactorySettings.factoryslots.factorys."+facid+".slots.slot"+slotid+".locationc.x");
		int z = plugin.getFactoryConfig().getInt("FactorySettings.factoryslots.factorys."+facid+".slots.slot"+slotid+".locationc.z");
		Location lsn = new Location(p.getWorld(),x,42,z);
		asm.despawnArmorStand(lsn, 5);
		setSlotMaterial(p.getWorld(),facid,slotid,Material.SMOOTH_BRICK);
		addSlotsOwned(p,facid,1);
		sbm.factoryScoreboard(p, facid);
		p.sendMessage(format(prefix+"&aSlot updated."));
		return;
	}
	public void setSlotMaterial(World w,int facid, int slotid, Material mat) {
		Location c1 = new Location(w,
				plugin.getFactoryConfig().getInt("FactorySettings.factoryslots.factorys."+facid+".slots.slot"+slotid+".location1.x"),
				plugin.getFactoryConfig().getInt("FactorySettings.factoryslots.factorys."+facid+".slots.slot"+slotid+".location1.y"),
				plugin.getFactoryConfig().getInt("FactorySettings.factoryslots.factorys."+facid+".slots.slot"+slotid+".location1.z"));
		Location c2 = new Location(w,
				plugin.getFactoryConfig().getInt("FactorySettings.factoryslots.factorys."+facid+".slots.slot"+slotid+".location2.x"),
				plugin.getFactoryConfig().getInt("FactorySettings.factoryslots.factorys."+facid+".slots.slot"+slotid+".location2.y"),
				plugin.getFactoryConfig().getInt("FactorySettings.factoryslots.factorys."+facid+".slots.slot"+slotid+".location2.z"));
		int minx = (c1.getBlockX() < c2.getBlockX() ? c2.getBlockX() : c1.getBlockX());
		int maxx = (c1.getBlockX() > c2.getBlockX() ? c2.getBlockX() : c1.getBlockX());
		int minz = (c1.getBlockZ() < c2.getBlockZ() ? c2.getBlockZ() : c1.getBlockZ());
		int maxz = (c1.getBlockZ() > c2.getBlockZ() ? c2.getBlockZ() : c1.getBlockZ());
		for (int x = maxx;x <= minx;x++) {
			for (int z = maxz;z <= minz;z++) {
				Location bloc = new Location(w,x,c1.getBlockY(),z);
				bloc.getBlock().setType(mat);
			}
		}
		return;
	}
	public int getSlotCost(String facname, int slotid) {
		int facid = getIdFromString(facname);
		return plugin.getFactoryConfig().getInt("FactorySettings.factoryslots.factorys."+facid+".slots.slot"+slotid+".cost");
	}
	// TODO PLAYERS
	public List<String> getPlayerUUIDS() {
		List<String> uuids = new ArrayList<String>();
		for (String uuid : plugin.getPlayersConfig().getConfigurationSection("Players").getKeys(false)) {
			uuids.add(uuid);
		}
		return uuids;
	}
	public boolean doesPlayerExist(Player p) {
		String uuid = p.getUniqueId().toString();
		if (plugin.getPlayersConfig().getString("Players."+uuid) != null) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isFactoryOwned(Player p, int facid) {
		String uuid = p.getUniqueId().toString();
		if (plugin.getPlayersConfig().getConfigurationSection("Players."+uuid+".factories") == null) {
			return false;
		}
		for(String key : plugin.getPlayersConfig().getConfigurationSection("Players."+uuid+".factories").getKeys(false)) {
			int i = Integer.parseInt(key);
			if (i == facid) {
				return true;
			} else {
				continue;
			}
		}
		return false;
	}
	// TODO OTHER
	public int addIntList(List<Integer> list) {
		int var = 0;
		for (int i = 0;i<list.size();i++) {
			if (i > list.size()) {break;}
			var += list.get(i);
		}
		return var;
	} 
	public int getIdFromString(String s) {
		return Integer.parseInt(String.valueOf(s.toCharArray()[s.toCharArray().length-1]));
	}
	public String getNameFromString(String s) {
		char[] chars = s.toCharArray();
		int need = (chars.length-1); // 1-9 BucketDev
		StringBuilder sb = new StringBuilder();
		for (int i = 0;i<need;i++) {
			if (i>=need) {break;}
			char c = chars[i]; // 0-8 BucketDev
			sb.append(c);
		}
		return sb.toString();
	}
	public static ConfigManager getInstance() {
		return configmanager;
	}
	

}
