package me.hype.factory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.WorldCreator;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.hype.factory.commands.BuyEquipment;
import me.hype.factory.commands.BuyFactory;
import me.hype.factory.commands.BuySlot;
import me.hype.factory.commands.FactoryHome;
import me.hype.factory.commands.Help;
import me.hype.factory.commands.PlayerStats;
import me.hype.factory.events.InventoryClick;
import me.hype.factory.events.OnBlockPlace;
import me.hype.factory.events.OnEntityClick;
import me.hype.factory.events.OnInteract;
import me.hype.factory.events.OnJoin;
import me.hype.factory.managers.ArmorstandManager;
import me.hype.factory.managers.ConfigManager;
import me.hype.factory.managers.ScoreboardManager;

public class Core extends JavaPlugin {
	
	private static Core plugin;
	public File configFile,playersFile,helpFile,factoryFile,scoreboardsFile;
	public YamlConfiguration playersConfig,helpConfig,factoryConfig,scoreboardsConfig;
	private int facid = 0;
	private int slotid = 0;

	public void onEnable() {
		plugin = this;
		setupConfig();
		registerEvents();
		spawnArmorStands();
		checkFactoryWorlds();
		setEnableScoreboard();
		ConfigManager cm = new ConfigManager();
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				Random r = new Random();
				List<String> tips = plugin.getConfig().getStringList("Messages.tips");
				int tindex = r.nextInt(tips.size());
				String tip = tips.get(tindex);
				for (Player p : Bukkit.getOnlinePlayers()) {
					if (cm.getFactoryWorlds().contains(p.getWorld().getName())) {
						p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
						p.sendMessage(format("&7[&bTIP&7] "+tip));
						return;
					} else {
						return;
					}
				}
			}
		}, 5L, 6000L);
		for (String cwn : cm.getFactoryWorlds()) {
			WorldCreator wc = new WorldCreator(cwn);
			Bukkit.getServer().createWorld(wc);
		}
		
	}

	public void onDisable() {
		despawnArmorStands();
		setDisableScoreboard();
		plugin = null;
	}

	public void registerEvents() {
		setupConfig();
		saveDefaultConfig();
		// ADD COMMANDS HERE
		getCmds("fbuyfactory",new BuyFactory());
		getCmds("fhelp",new Help());
		getCmds("fhome",new FactoryHome());
		getCmds("fbuyslot",new BuySlot());
		getCmds("fstats",new PlayerStats());
		getCmds("fbuyequipment",new BuyEquipment());
		// ADD LISTENERS HERE
		getLisn(new InventoryClick(),this);
		getLisn(new OnJoin(),this);
		getLisn(new OnEntityClick(),this);
		getLisn(new OnInteract(),this);
		getLisn(new OnBlockPlace(),this);
		return;
	}

	public void setupConfig() {
		if (!getDataFolder().exists()) {
			getDataFolder().mkdirs();
		}
		
		configFile = new File(getDataFolder(), "config.yml");
		playersFile = new File(getDataFolder(), "players.yml");
		helpFile = new File(getDataFolder(), "help.yml");
		factoryFile = new File(getDataFolder(), "factory.yml");
		
		
		loadConfig(configFile,"config.yml");
		loadConfig(playersFile, "players.yml");
		loadConfig(helpFile,"help.yml");
		loadConfig(factoryFile,"factory.yml");
		
		playersConfig = YamlConfiguration.loadConfiguration(playersFile); 
		helpConfig = YamlConfiguration.loadConfiguration(helpFile);
		factoryConfig = YamlConfiguration.loadConfiguration(factoryFile);
		return;
	}
	
	public void setEnableScoreboard() {
		ScoreboardManager sbm = new ScoreboardManager();
		ConfigManager cm = new ConfigManager();
		for (Player p : Bukkit.getOnlinePlayers()) {
			String w = p.getWorld().getName();
			List<String> worlds = cm.getFactoryWorlds();
			if (!worlds.contains(w)) {
				p.setScoreboard(sbm.spawnScoreboard(p));
			} else {
				p.setScoreboard(sbm.factoryScoreboard(p,cm.getIdFromString(w)));
			}
		}
		return;
	}
	
	public void setDisableScoreboard() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
		}
		return;
	}
	
	public void spawnArmorStands() {
		ConfigManager cm = new ConfigManager();
		ArmorstandManager asm = new ArmorstandManager();
		for (Entry<String, List<Integer>> s : cm.getPlayerSlotsOwnedUUID().entrySet()) {
			String worldname = s.getKey();
			List<Integer> slotsowned = s.getValue();
			asm.createFactorySlotNumberArmorStandsR(Bukkit.getWorld(worldname), cm.getIdFromString(worldname), slotsowned);
			asm.createFactorySlotCostArmorStandsR(Bukkit.getWorld(worldname), cm.getIdFromString(worldname), slotsowned);
			asm.createFactorySlotInformationStandsR(Bukkit.getWorld(worldname), cm.getIdFromString(worldname), slotsowned);
		}
		return;
	}
	
	public void spawnArmorStands(Player p, int facid) {
		ConfigManager cm = new ConfigManager();
		ArmorstandManager asm = new ArmorstandManager();
		List<String> worlds = cm.getFactoryWorlds();
		String pworld = p.getName()+facid;
		if (Bukkit.getWorld(pworld) == null || !worlds.contains(pworld)) {
			return;
		}
		List<Integer> slotsowned = cm.getPlayerSlotsOwned(p,facid);
		asm.createFactorySlotNumberArmorStandsR(Bukkit.getWorld(pworld), facid, slotsowned);
		asm.createFactorySlotCostArmorStandsR(Bukkit.getWorld(pworld), facid, slotsowned);
		asm.createFactorySlotInformationStandsR(Bukkit.getWorld(pworld), facid, slotsowned);
		return;
	}
	
	public void despawnArmorStands() {
		ConfigManager cm = new ConfigManager();
		List<String> worlds = cm.getFactoryWorlds();
		for (int i = 0;i<worlds.size();i++) {
			if (i > worlds.size()) {
				break;
			}
			String index = worlds.get(i);
			if (Bukkit.getWorld(index) == null) {
				worlds.remove(index);
				continue;
			}
			for (Entity e : Bukkit.getWorld(index).getEntities()) {
				if (e instanceof ArmorStand) {
					e.remove();
				}
			}
		}
		return;
	}
	
	public void despawnArmorStands(String w) {
		ConfigManager cm = new ConfigManager();
		List<String> worlds = cm.getFactoryWorlds();
		if (Bukkit.getWorld(w) == null || !worlds.contains(w)) {
			return;
		}
		for (Entity e : Bukkit.getWorld(w).getEntities()) {
			if (e instanceof ArmorStand) {
				e.remove();
			}
		}
		return;
	}
	
	public void checkFactoryWorlds() {
		ConfigManager cm = new ConfigManager();
		cm.checkIfWorldsAreAdded();
		return;
	}
	
	public void loadConfig(File f, String filename) {
		InputStream in = getResource(filename);
	    if(f.exists()) {
	    	return;
	    }
	    try {
			Files.copy(in, f.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	    return;
	}
	public void deleteFile(File f) {
		if (!f.exists()) {
			return;
		}
		f.delete();
		return;
	}
	
	public void saveConfigToFile(File f, YamlConfiguration c) {
		try {
			c.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}
	
	public void getCmds(String command, CommandExecutor cmdclass) {
		getCommand(command).setExecutor(cmdclass);
		return;
	}

	public void getLisn(Listener l, Plugin p) {
		Bukkit.getServer().getPluginManager().registerEvents(l, p);
		return;
	}

	public static Core getInstance() {
		return plugin;
	}
	
	public YamlConfiguration getScoreboardConfig() {
		return scoreboardsConfig;
	}
	
	public YamlConfiguration getFactoryConfig() {
		return factoryConfig;
	}
	
	public YamlConfiguration getPlayersConfig() {
		return playersConfig;
	}
	
	public YamlConfiguration getHelpConfig() {
		return helpConfig;
	}
	
	public String format(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}

	public int getFactoryId() {
		return facid;
	}

	public void setFactoryId(int facid) {
		this.facid = facid;
	}

	public int getSlotId() {
		return slotid;
	}

	public void setSlotId(int slotid) {
		this.slotid = slotid;
	}
}
