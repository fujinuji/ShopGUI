package me.fujinuji.ShopPlus.GUI.Navigation;

import java.util.ArrayList;

import me.fujinuji.ShopPlus.Domain.Category;
import me.fujinuji.ShopPlus.Domain.Item;
import me.fujinuji.ShopPlus.Utils.AdminVariables;
import me.fujinuji.ShopPlus.Utils.PlayerVariables;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.ItemMeta;

import me.fujinuji.ShopPlus.Utils.Enums.ViewMode;

public class ShopNavigation {
	private int shopPage;
	private int shopIndex;
	
	protected Inventory getShopInventory(ArrayList<Category> categoriesList, ViewMode viewMode, Player player) {
		Inventory inventory = Bukkit.createInventory(null, 54, "Categories");
		
		if (viewMode.equals(ViewMode.ADMIN_VIEW)) {
			this.shopPage = AdminVariables.shopPage;
			this.shopIndex = AdminVariables.shopIndex;
			player.sendMessage("admin " + this.shopPage + " " + this.shopIndex);
		} else if (viewMode.equals(ViewMode.PLAYER_VIEW)) {
			player.sendMessage(PlayerVariables.shopIndex.toString() + " " + PlayerVariables.shopPage.toString());
			this.shopPage = PlayerVariables.shopPage.get(player);
			this.shopIndex = PlayerVariables.shopIndex.get(player);
		}
		
		if (this.shopPage > 1) {
			Item backItem = new Item(Material.ARROW);
			ItemMeta backItemMeta = backItem.getItemStack().getItemMeta();
			
			ArrayList<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.GRAY + "Page " +(this.shopPage - 1));
			
			backItemMeta.setDisplayName(ChatColor.GREEN + "Back");
			backItemMeta.setLore(itemLore);
			backItem.setItemMeta(backItemMeta);
			
			inventory.setItem(48, backItem.getItemStack());
		}
		
		if (this.shopIndex < categoriesList.size()) {
			Item nextItem = new Item(Material.ARROW);
			ItemMeta nextItemMeta = nextItem.getItemStack().getItemMeta();
			
			ArrayList<String> itemLore = new ArrayList<String>();
			itemLore.add(ChatColor.GRAY + "Page " +(this.shopPage + 1));
			
			nextItemMeta.setDisplayName(ChatColor.GREEN + "Next");
			nextItemMeta.setLore(itemLore);
			nextItem.setItemMeta(nextItemMeta);
			
			inventory.setItem(50, nextItem.getItemStack());
		}
		
		int current = (viewMode.equals(ViewMode.ADMIN_VIEW) ? AdminVariables.shopIndex : PlayerVariables.shopIndex.get(player));
		
		for(int i = 2; (i < 5) && (current < categoriesList.size()); i++) {
			for (int j = 8; (j > 1) && (current < categoriesList.size()); j--) {
				inventory.setItem(i * 9 - j, categoriesList.get(current).getHead());
				current++;
			}
		}
		
		return inventory;
		
	}
}
