package me.hype.factory.managers;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.hype.factory.Core;

public class InventoryManager {
	
	ConfigManager cm = new ConfigManager();
	Core plugin = Core.getInstance();
	ItemManager im = new ItemManager();
	
	
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
	public Inventory buyEquipmentInventory() {
		if (plugin == null) {plugin	= Core.getInstance();}
		Inventory inv = Bukkit.createInventory(null,9,format("&6Factory Equipment"));
		//
		ItemStack buyer = new ItemStack(Material.DISPENSER,1);
		ItemStack seller = new ItemStack(Material.DROPPER,1);
		ItemStack conveyor = new ItemStack(Material.RAILS,1);
		//
		ArrayList<String> blore = new ArrayList<String>();
		ArrayList<String> slore = new ArrayList<String>();
		ArrayList<String> clore = new ArrayList<String>();
		//
		blore.add(format("&7Spawns in items to use it for crafting or selling."));
		blore.add(format("&aCost: $&e "+plugin.getFactoryConfig().getInt("FactorySettings.factory-equipment.buyer.cost")));
		slore.add(format("&7Sells items that is given to it."));
		slore.add(format("&aCost: $&e "+plugin.getFactoryConfig().getInt("FactorySettings.factory-equipment.seller.cost")));
		clore.add(format("&7Allows movement of your items around your factory."));
		clore.add(format("&aCost: $&e "+plugin.getFactoryConfig().getInt("FactorySettings.factory-equipment.conveyorbelt.cost")));
		//
		setIm(buyer,format("&6Buyer"),blore);
		setIm(seller,format("&6Seller"),slore);
		setIm(conveyor,format("&6Conveyor Belt"),clore);
		//
		inv.setItem(0, buyer);
		inv.setItem(1, seller);
		inv.setItem(2, conveyor);
		//
		return inv;
	}
	// TODO PLAYER INVENTORYS
	public void factoryInventory(Player p) {
		if (plugin == null) {plugin = Core.getInstance();}
		if (cm == null) {cm = new ConfigManager();}
		Inventory pinv = p.getInventory();
		//
		ItemStack equipment = new ItemStack(Material.EMERALD,1);
		ItemStack estock = new ItemStack(Material.CHEST,1);
		//
		ArrayList<String> elore = new ArrayList<String>();
		ArrayList<String> eslore = new ArrayList<String>();
		//
		elore.add(format("&7Right click me to buy your equipment!"));
		eslore.add(format("&7Right click me to see what you have in stock!"));
		//
		setIm(equipment,format("&aBuy Factory Equipment"),elore);
		setIm(estock,format("&aEquipment Storage"),eslore);
		//
		pinv.clear();
		pinv.setItem(0, equipment);
		pinv.setItem(8, estock);
		//
		return;
	}
	public void storageInventory(Player p) {
		if (plugin == null) {plugin = Core.getInstance();}
		if (cm == null) {cm = new ConfigManager();}
		Inventory pinv = p.getInventory();
		//
		ItemStack buyer = im.getBuyerItem();
		ItemStack seller = im.getSellerItem();
		ItemStack conveyor = im.getConveyorBeltItem();
		ItemStack exit = new ItemStack(Material.BARRIER,1);
		//
		ArrayList<String> elore = new ArrayList<String>();
		//
		elore.add(format("&7Right click to exit out of the storage menu."));
		//
		setIm(exit,format("&cExit"),elore);
		//
		pinv.setItem(8, exit);
		if (hasInStock(p,"buyer") > 0) {buyer.setAmount(hasInStock(p,"buyer"));pinv.addItem(buyer);}
		if (hasInStock(p,"seller") > 0) {seller.setAmount(hasInStock(p,"buyer"));pinv.addItem(seller);}
		if (hasInStock(p,"conveyorbelt") > 0) {conveyor.setAmount(hasInStock(p,"buyer"));pinv.addItem(conveyor);}
		//
		return;
	}
	// TODO OTHER METHODS
	public ItemStack setIm(ItemStack is, String displayname, ArrayList<String> lore) {
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(displayname);
		im.setLore(lore);
		is.setItemMeta(im);
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
	
	public int hasInStock(Player p, String item) {
		String uuid = p.getUniqueId().toString();
		if (plugin.getPlayersConfig().getInt("Players."+uuid+".equipmentstock."+item) > 0) {
			return plugin.getPlayersConfig().getInt("Players."+uuid+".equipmentstock."+item);
		} else {
			return 0;
		}
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
