package me.fujinuji.ShopPlus.Updater;

import me.fujinuji.ShopPlus.Domain.ShopItem;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DataController {

    private String path;
    private ArrayList<ItemStack> oldItemsList;

    public DataController() {
        this.path = System.getProperty("user.dir") + "\\plugins\\ShopPlus\\";
    }

    public ArrayList<ShopItem> loadShopItems() {
        File shopItemsFile = new File(this.path, "ShopItems.yml");
        ArrayList<ShopItem> shopItems = new ArrayList<>();

        if (shopItemsFile.exists()) {
            YamlConfiguration shopYaml = new YamlConfiguration();

            try {
                shopYaml.load(shopItemsFile);
            } catch (IOException e) {
            } catch (InvalidConfigurationException e) {
            }

            for(String itemCode : shopYaml.getConfigurationSection("Items").getKeys(false)) {
                shopItems.add(getShopItem(itemCode, shopYaml));
            }

            return shopItems;
        }

        return null;
    }

    private ShopItem getShopItem(String itemCode, YamlConfiguration yamlConfiguration) {
        final String itemPath = "Items." + itemCode;

        ItemStack itemStack = getStackFromCode(itemCode);
        String itemName = yamlConfiguration.getString(itemPath + "name");
        int buyPrice = Integer.parseInt(yamlConfiguration.getString(itemPath + ".buy"));
        int sellPrice = Integer.parseInt(yamlConfiguration.getString(itemPath + ".sell"));
        int buyAmount = Integer.parseInt(yamlConfiguration.getString(itemPath + ".buy_amount"));
        int sellAmount = Integer.parseInt(yamlConfiguration.getString(itemPath + ".sellAmount"));

        return new ShopItem(itemName, itemStack, sellPrice, buyPrice, buyAmount, sellAmount);
    }

    private ItemStack getStackFromCode(String itemCode) {
        itemCode = itemCode.substring(5);
        System.out.println(itemCode);
        String[] itemData =  itemCode.split("/");
        System.out.println(itemData.length + " " + itemData[0] + "   " + itemData[1]);

        return null;
    }





}
