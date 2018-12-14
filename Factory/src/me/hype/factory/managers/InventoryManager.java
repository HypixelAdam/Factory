package me.hype.factory.managers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import me.hype.factory.Core;

public class InventoryManager {
	
	ConfigManager cm = new ConfigManager();
	Core plugin = Core.getInstance();
	
	public Inventory buyFactory(Player p) {
		if (plugin == null) {plugin = Core.getInstance();}
		if (cm == null) {cm = new ConfigManager();}
		Inventory inv = Bukkit.createInventory(null,9,format("&6Select a Factory"));
		//
		ItemStack factorynb = new ItemStack(Material.COAL_BLOCK,1);
		ItemStack factoryb = new ItemStack(Material.IRON_BLOCK,1);
		//
		ArrayList<String> f1lore = new ArrayList<String>();
		ArrayList<String> f2lore = new ArrayList<String>();
		//
		f1lore.add(format("&7Your first factory! It's on us!"));
		f1lore.add(format("&6Price: &6"+cm.getFactoryCost(1)));
		f1lore.add(format("&6Slot Size: &613x13"));
		f1lore.add(format("&6Purchased?: "+cm.alreadyBoughtS(p, 1)));
		f2lore.add(format("&7Another factory is worth the taking. High price for it."));
		f2lore.add(format("&6Price: &6"+cm.getFactoryCost(2)));
		f2lore.add(format("&6Slot Size: &613x13"));
		f2lore.add(format("&6Purchased?: "+cm.alreadyBoughtS(p, 2)));
		//
		//
		inv.setItem(0, setIm(checkIfFactoryBought(p,1,factoryb,factorynb),format("&f&lFactory 1"),f1lore));
		inv.setItem(1, setIm(checkIfFactoryBought(p,2,factoryb,factorynb),format("&f&lFactory 2"),f2lore));
		//
		return inv;
	}
	
	public Inventory buySlot(Player p, int facid) {
		if (plugin == null) {plugin = Core.getInstance();}
		if (cm == null) {cm = new ConfigManager();}
		Inventory inv = Bukkit.createInventory(null,27,format("&6Select a slot"));
		//
		ItemStack slotnb = new ItemStack(Material.COAL_BLOCK,1);
		ItemStack slotb = new ItemStack(Material.IRON_BLOCK,1);
		//
		setItemsSlotLoop(inv,p,facid,slotb,slotnb);
		//
		return inv;
	}
	
	public Inventory confirmFactoryPurchase(Player p, int facid) {
		if (plugin == null) {plugin = Core.getInstance();}
		if (cm == null) {cm = new ConfigManager();}
		int cost = cm.getFactoryCost(facid);
		//
		Inventory inv = Bukkit.createInventory(null,9,format("&aConfirm your Factory purchase..."));
		//
		ItemStack yes = new ItemStack(Material.WOOL,1,(byte)5);
		ItemStack info = new ItemStack(Material.PAPER,1);
		ItemStack no = new ItemStack(Material.WOOL,1,(byte)14);
		//
		ArrayList<String> ylore = new ArrayList<String>();
		ArrayList<String> inlore = new ArrayList<String>();
		ArrayList<String> nlore = new ArrayList<String>();
		//
		ylore.add(format("&7After clicking this button, you are accepting to buy '&f&lFactory "+facid+"&7'."));
		inlore.add(format("&7Information on the factory you are buying:"));
		inlore.add(format("&7Slot size: &613x13"));
		inlore.add(format("&7Cost: &6"+cost));
		nlore.add(format("&7After clicking this button, you are not accepting to buy the factory."));
		//
		setIm(yes,format("&a&lYES"),ylore);
		setIm(info,format("&7Information on '&f&lFactory "+facid+"&7'"),inlore);
		setIm(no,format("&c&lNO"),nlore);
		// 0,1,2,3,4,5,6,7,8
		inv.setItem(2, yes);
		inv.setItem(4, info);
		inv.setItem(6, no);
		//
		return inv;
	}
	
	public Inventory confirmSlotPurchase(Player p, int slotid) {
		if (plugin == null) {plugin	= Core.getInstance();}
		if (cm == null) {cm = new ConfigManager();}
		int cost = cm.getSlotCost(p.getWorld().getName(),slotid-1);
		//
		Inventory inv = Bukkit.createInventory(null,9,format("&aConfirm your Slot purchase..."));
		//
		ItemStack yes = new ItemStack(Material.WOOL,1,(byte)5);
		ItemStack info = new ItemStack(Material.PAPER,1);
		ItemStack no = new ItemStack(Material.WOOL,1,(byte)14);
		//
		ArrayList<String> ylore = new ArrayList<String>();
		ArrayList<String> inlore = new ArrayList<String>();
		ArrayList<String> nlore = new ArrayList<String>();
		//
		ylore.add(format("&7After clicking this button, you are accepting to buy '&f&lSlot "+slotid+"&7'."));
		inlore.add(format("&7Information on the Slot you are buying:"));
		inlore.add(format("&7Size: &613x13"));
		inlore.add(format("&7Cost: &6"+cost));
		nlore.add(format("&7After clicking this button, you are not accepting to buy the slot."));
		//
		setIm(yes,format("&a&lYES"),ylore);
		setIm(info,format("&7Information on '&f&lSlot "+slotid+"&7'"),inlore);
		setIm(no,format("&c&lNO"),nlore);
		//
		inv.setItem(2, yes);
		inv.setItem(4, info);
		inv.setItem(6, no);
		//
		return inv;
	}
	// TODO PLAYER INVENTORYS
	public Inventory playerStats(Player p, List<String> targetP) {
		if (plugin == null) {plugin = Core.getInstance();}
		if (cm == null) {cm = new ConfigManager();}
		int tm = Integer.parseInt(targetP.get(0));
		int tms = Integer.parseInt(targetP.get(1));
		int tme = Integer.parseInt(targetP.get(2));
		int tfo = Integer.parseInt(targetP.get(3));
		String tpn = targetP.get(4);
		//
		Inventory inv = Bukkit.createInventory(null, 9, "&e&l"+tpn+"&a's stats");
		//
		ItemStack pskull = new ItemStack(Material.SKULL_ITEM,1,(byte)3);
		ItemStack tarm = new ItemStack(Material.PAPER,1);
		ItemStack tarms = new ItemStack(Material.PAPER,1);
		ItemStack tarme = new ItemStack(Material.PAPER,1);
		ItemStack tarfo = new ItemStack(Material.PAPER,1);
		//
		ArrayList<String> pslore = new ArrayList<String>();
		ArrayList<String> tmlore = new ArrayList<String>();
		ArrayList<String> tmslore = new ArrayList<String>();
		ArrayList<String> tmelore = new ArrayList<String>();
		ArrayList<String> tfolore = new ArrayList<String>();
		//
		pslore.add(format("&aPlayer name: &e"+tpn));
		tmlore.add(format("&aMoney: &e"+tm));
		tmslore.add(format("&aMoney Spent: &e"+tms));
		tmelore.add(format("&aMoney Earned: &e"+tme));
		tfolore.add(format("&aTotal Factories Owned: &e"+tfo));
		//
		setSm(pskull,format("&6Player Name"),pslore,tpn);
		setIm(tarm,format("&6Current Money"),tmlore);
		setIm(tarms,format("&6Total Money Spent"),tmslore);
		setIm(tarme,format("&6Total Money Earned"),tmelore);
		setIm(tarfo,format("&6Total Factories Owned"),tfolore);
		//
		inv.setItem(0, pskull);
		inv.setItem(2, tarm);
		inv.setItem(4, tarms);
		inv.setItem(6, tarme);
		inv.setItem(8, tarfo);
		//
		return inv;
	}
	
	public ItemStack setIm(ItemStack is, String displayname, ArrayList<String> lore) {
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(displayname);
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}
	
	public ItemStack setSm(ItemStack is, String displayname, ArrayList<String> lore, String playername) {
		SkullMeta sm = (SkullMeta) is;
		sm.setOwner(playername);
		sm.setDisplayName(displayname);
		sm.setLore(lore);
		is.setItemMeta(sm);
		return is;
	}
	
	public void setItemsSlotLoop(Inventory inv,Player p,int facid,ItemStack yes,ItemStack no) {
		int slotid = 1;
		for (int i = 0;i<24;i++) {
			if (i > 23) {break;}
			inv.setItem(i, setIm(checkIfSlotBought(p,facid,slotid,yes,no),
					format("&f&lSlot "+(slotid+1)),
					addSlotLore(p, facid, (slotid+1), checkIfSlotBought(p,facid,slotid,yes,no))));
			slotid++;
		}
		
	}
	
	public ItemStack checkIfFactoryBought(Player p, int facid, ItemStack yes, ItemStack no) {
		if (cm == null) {cm = new ConfigManager();}
		if (cm.isFactoryOwned(p, facid)) {
			return yes;
		}
		return no;
	}
	
	public ItemStack checkIfSlotBought(Player p, int facid, int slotid, ItemStack yes, ItemStack no) {
		if (cm == null) {cm = new ConfigManager();}
		if (cm.isSlotOwned(p, facid, slotid)) {
			return yes;
		} 
		return no;
	}
	
	public ArrayList<String> addSlotLore(Player p, int facid, int slotid, ItemStack is) {
		ArrayList<String> list = new ArrayList<String>();
		String cost = format("&7Cost: &6"+String.valueOf(plugin.getFactoryConfig().getInt("FactorySettings.factoryslots.factorys."+facid+".slots.slot"+(slotid-1)+".cost")));
		String ss = format("&7Slot Size: &613x13");
		String id = format("&7Slot #: &6"+String.valueOf(slotid));
		list.add(id); list.add(ss); list.add(cost);
		return list;
	}
	
	public String format(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
}
