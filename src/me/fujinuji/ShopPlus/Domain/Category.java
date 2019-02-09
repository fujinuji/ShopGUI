package me.fujinuji.ShopPlus.Domain;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;

public class Category {
	private String category_name;
	private ArrayList<Item> category_items;
	private Item category_head;
	
	public Category() {
		this.category_head = new Item();
		this.category_name = "";
		this.category_items = new ArrayList<Item>();
	}
	
	public Category(Item headItem, String categoryName, ArrayList<Item> categoryItems) {
		this.category_head = headItem;
		this.category_name = categoryName;
		this.category_items = categoryItems;
	}
	
	public ItemStack getHead() {
		return this.category_head.getItemStack();
	}

}
