package me.fujinuji.ShopPlus.Domain;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Item {
	private String item_name;
	private Material item_material;
	private int amount;
	private ItemStack item_stack;
	
	public Item() {
		this.item_name = "";
		this.item_material = Material.AIR;
		this.amount = 1;
		
		this.item_stack = new ItemStack(this.item_material, this.amount);
	}
	
	public Item(Material material) {
		this.item_name = "";
		this.item_material = material;
		this.amount = 1;
	
		this.item_stack = new ItemStack(this.item_material, this.amount);
	}
	
	public Item(String name, Material material) {
		this.item_name = name;
		this.item_material = material;
		this.amount = 1;
	
		this.item_stack = new ItemStack(this.item_material, this.amount);
	}
	
	public Item(String name, Material material, int amount) {
		this.item_name = name;
		this.item_material = material;
		this.amount = amount;
	
		this.item_stack = new ItemStack(this.item_material, this.amount);
	}
	
	public Item(ItemStack itemStack) {
		this.item_stack = itemStack;
		
		this.item_name = this.item_stack.getType().name();
		this.item_material = this.item_stack.getType();
		this.amount = this.item_stack.getAmount();
	}
	
	public ItemStack getItemStack() {
		return this.item_stack;
	}
	
	public void setItemMeta(ItemMeta itemMeta) {
		this.item_stack.setItemMeta(itemMeta);
	}
}