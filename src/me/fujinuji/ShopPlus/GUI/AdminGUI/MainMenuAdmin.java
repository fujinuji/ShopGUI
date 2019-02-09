package me.fujinuji.ShopPlus.GUI.AdminGUI;

import java.util.ArrayList;

import me.fujinuji.ShopPlus.Domain.Item;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.ItemMeta;

public class MainMenuAdmin{
	public static void showAdminMainMenu(Player adminPlayer) {
		Inventory inventory = org.bukkit.Bukkit.createInventory(null, 45, "Main Menu");
		ArrayList<String> itemLore = new ArrayList<String>();
		
		/// SHOP ITEM 
		Item shopItem = new Item(Material.COMPASS);
		ItemMeta shopItemMeta = shopItem.getItemStack().getItemMeta();
		
		itemLore.clear();
		itemLore.add(null);
		itemLore.add(ChatColor.GRAY + "View shop like a player");
		
		shopItemMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Shop");
		shopItemMeta.setLore(itemLore);
		shopItem.setItemMeta(shopItemMeta);
		
		/// PRICE MANAGER ITEM
		Item priceManagerItem = new Item(Material.REDSTONE_COMPARATOR);
		ItemMeta priceManagerItemMeta = priceManagerItem.getItemStack().getItemMeta();
		
		itemLore.clear();
		itemLore.add(null);
		itemLore.add(ChatColor.GRAY + "Set up your shop");
		
		priceManagerItemMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Price settings");
		priceManagerItemMeta.setLore(itemLore);
		priceManagerItem.setItemMeta(priceManagerItemMeta);
		
		/// CATEGORY MANAGER ITEM
		Item categoryManagerItem = new Item(Material.BOOKSHELF);
		ItemMeta categoryManagerItemMeta = categoryManagerItem.getItemStack().getItemMeta();
		
		itemLore.clear();
		itemLore.add(null);
		itemLore.add(ChatColor.GRAY + "Manage categories and items");
		
		categoryManagerItemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Categories Manager");
		categoryManagerItemMeta.setLore(itemLore);
		categoryManagerItem.setItemMeta(categoryManagerItemMeta);
		
		///	CUSTOM CATEGORY ITEM
		Item customCategoryItem = new Item(Material.BREWING_STAND);
		ItemMeta customCategoryItemMeta = customCategoryItem.getItemStack().getItemMeta();
		
		itemLore.clear();
		itemLore.add(null);
		itemLore.add(ChatColor.GRAY + "Delete custom items");
		
		customCategoryItemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Manage custom items");
		customCategoryItemMeta.setLore(itemLore);
		customCategoryItem.setItemMeta(customCategoryItemMeta);
		
		inventory.setItem(11, shopItem.getItemStack());
		inventory.setItem(13, priceManagerItem.getItemStack());
		inventory.setItem(15, categoryManagerItem.getItemStack());
		inventory.setItem(31, customCategoryItem.getItemStack());
		
		adminPlayer.openInventory(inventory);
	}
}
