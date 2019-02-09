package me.fujinuji.ShopPlus.Listeners;

import me.fujinuji.ShopPlus.GUI.Navigation.Navigator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.fujinuji.ShopPlus.Utils.PlayerVariables;

public class MainMenuListener implements Listener{
	
	@EventHandler	
	public void invInteract(InventoryClickEvent event) {
		if (!(event.getWhoClicked() instanceof Player)) {
			return ;
		}
		if (event.getCurrentItem() == null) {
			return ;
		}
		if (event.getCurrentItem().getType().equals(Material.AIR)) {
			return ;
		}
		
		if (!(event.getInventory().getName().equals("Main Menu"))) {
			return ;
		}
		event.setCancelled(true);
		Navigator navigator = new Navigator();
		
		if (event.getCurrentItem().getType().equals(Material.COMPASS)) {
			PlayerVariables.shopIndex.put((Player) event.getWhoClicked(), 0);
			PlayerVariables.shopPage.put((Player) event.getWhoClicked(), 1);
			navigator.openPlayerShopNavigation((Player) event.getWhoClicked());
		}
		
		if (event.getCurrentItem().getType().equals(Material.REDSTONE_COMPARATOR)) {
			
		}
		
		if (event.getCurrentItem().getType().equals(Material.BOOKSHELF)) {
			
		}
		
		if (event.getCurrentItem().getType().equals(Material.BREWING_STAND)) {
			
		}
		
		event.setCancelled(false);
	}
}
