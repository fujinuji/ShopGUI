package me.fujinuji.ShopPlus.Utils;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

public class PlayerVariables {
	public static Map<Player, Integer> shopPage;
	public static Map<Player, Integer> shopIndex;
	
	public static void initializeVariables() {
		shopPage = new HashMap<Player, Integer>();
		shopIndex = new HashMap<Player, Integer>();
	}
}
