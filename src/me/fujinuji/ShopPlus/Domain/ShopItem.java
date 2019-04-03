package me.fujinuji.ShopPlus.Domain;

import org.bukkit.inventory.ItemStack;

public class ShopItem {

    private String itemName;
    private ItemStack itemStack;
    private int sellPrice;
    private int sellAmount;
    private int buyPrice;
    private int buyAmount;


    public ShopItem(String itemName, ItemStack itemStack, int sellPrice, int buyPrice, int buyAmount, int sellAmount) {
        this.itemName = itemName;
        this.itemStack = itemStack;
        this.sellPrice = sellPrice;
        this.buyPrice = buyPrice;
        this.buyAmount = buyAmount;
        this.sellAmount = sellAmount;
    }

    public String getItemName() {
        return itemName;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public int getSellAmount() {
        return sellAmount;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public int getBuyAmount() {
        return buyAmount;
    }
}
