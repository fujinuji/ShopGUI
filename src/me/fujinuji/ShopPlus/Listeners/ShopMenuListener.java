package me.fujinuji.ShopPlus.Listeners;

import me.fujinuji.ShopPlus.GUI.Navigation.Navigator;
import me.fujinuji.ShopPlus.Utils.PlayerVariables;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ShopMenuListener implements Listener{
	
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
		
		if (!(event.getInventory().getName().equals("Categories"))) {
			return ;
		}

		event.setCancelled(true);
		Navigator navigator = new Navigator();
		
		if (event.getSlot() == 48 && event.getCurrentItem().getType().equals(Material.ARROW)) {
			PlayerVariables.shopIndex.put((Player) event.getWhoClicked(), PlayerVariables.shopIndex.get(event.getWhoClicked()) - 21);
			PlayerVariables.shopPage.put((Player) event.getWhoClicked(), PlayerVariables.shopPage.get(event.getWhoClicked()) - 1);
			navigator.openPlayerShopNavigation((Player) event.getWhoClicked());
		}
		
		if (event.getSlot() == 50 && event.getCurrentItem().getType().equals(Material.ARROW)) {
			PlayerVariables.shopIndex.put((Player) event.getWhoClicked(), PlayerVariables.shopIndex.get(event.getWhoClicked()) + 21);
			PlayerVariables.shopPage.put((Player) event.getWhoClicked(), PlayerVariables.shopPage.get(event.getWhoClicked()) + 1);
			navigator.openPlayerShopNavigation((Player) event.getWhoClicked());
		}
		
		event.setCancelled(false);
	}
		
}
