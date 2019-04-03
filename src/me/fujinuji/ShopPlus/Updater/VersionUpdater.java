package me.fujinuji.ShopPlus.Updater;

import me.fujinuji.ShopPlus.Domain.MinecraftVersion;
import me.fujinuji.ShopPlus.Utils.ConfigConnection;

import java.io.File;

public class VersionUpdater {

    private MinecraftVersion version;
    private ConfigConnection configConnection;

    public VersionUpdater(MinecraftVersion version) {
        this.version = version;
    }

    public boolean updateAvailable() {
        return ! configConnection.get("version").equals(this.version.getVersion());
    }

    public void update() {
        if(version.isGreater(new MinecraftVersion("1.12"))) {
            if(dataAlreadyExists()) {
                DataController dataController = new DataController();


            } else {

            }
            ///UPDATE TO 1.13+ ITEMS
        } else {
            ///STAY ON 1.12- ITEMS
        }
    }

    private static boolean dataAlreadyExists() {
        String pluginFolder = System.getProperty("user.dir");
        File oldConfig = new File(pluginFolder + "\\plugins\\ShopPlus\\ShopItems.yml");

        return oldConfig.exists();
    }
}
