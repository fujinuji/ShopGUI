package me.fujinuji.ShopPlus.GUI.Navigation;

import org.bukkit.entity.Player;

import me.fujinuji.ShopPlus.ShopPlus;
import me.fujinuji.ShopPlus.Utils.AdminVariables;
import me.fujinuji.ShopPlus.Utils.Enums.ViewMode;

public class Navigator extends ShopNavigation{
	
	public Navigator() {
	}
	
	public void openAdminShopNavigaor(Player player) {
		AdminVariables.shopIndex = 0;
		AdminVariables.shopPage = 1;
		player.openInventory(getShopInventory(ShopPlus.getItems(player), ViewMode.ADMIN_VIEW, player));
	}
	
	public void openPlayerShopNavigation(Player player) {
		player.openInventory(getShopInventory(ShopPlus.getItems(player), ViewMode.PLAYER_VIEW, player));
	}
}
