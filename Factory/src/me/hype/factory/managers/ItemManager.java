package me.hype.factory.managers;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.hype.factory.Core;

public class ItemManager {
	
	Core plugin = Core.getInstance();

	public ItemStack getBuyerItem() {
		ItemStack buyer = new ItemStack(Material.DISPENSER,1);
		//
		ArrayList<String> blore = new ArrayList<String>();
		//
		blore.add(format("&7Spawns in items to use it for crafting or selling."));
		//
		setIm(buyer,format("&6Buyer"),blore);
		//
		return buyer;
	}
	
	public ItemStack getSellerItem() {
		ItemStack seller = new ItemStack(Material.DROPPER,1);
		//
		ArrayList<String> slore = new ArrayList<String>();
		//
		slore.add(format("&7Sells items that is given to it."));
		//
		setIm(seller,format("&6Seller"),slore);
		//
		return seller;
	}
	
	public ItemStack getConveyorBeltItem() {
		ItemStack conveyor = new ItemStack(Material.RAILS);
		//
		ArrayList<String> clore = new ArrayList<String>();
		//
		clore.add(format("&7Allows movement of your items around your factory."));
		//
		setIm(conveyor,format("&6Conveyor Belt"),clore);
		//
		return conveyor;
	}
	
	public String format(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	public ItemStack setIm(ItemStack is, String displayname, ArrayList<String> lore) {
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(displayname);
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}
}
