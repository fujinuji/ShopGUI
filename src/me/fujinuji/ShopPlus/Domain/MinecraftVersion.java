package me.fujinuji.ShopPlus.Domain;

public class MinecraftVersion {

    private String version;

    public MinecraftVersion(String version) {
        this.version =  version;
    }

    public String getVersion() {
        return this.version;
    }

    public boolean isGreater(MinecraftVersion minecraftVersion) {
        String[] currentVersion = this.version.split(".");
        String[] compareVersion = minecraftVersion.getVersion().split(".");

        if (currentVersion[0].equals(compareVersion[0]))
            return Integer.parseInt(currentVersion[1]) >
                    Integer.parseInt(compareVersion[1]);

        return Integer.parseInt(currentVersion[0]) >
                Integer.parseInt(compareVersion[0]);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MinecraftVersion))
            return false;

        MinecraftVersion minecraftVersion = (MinecraftVersion) object;
        String[] currentVersion = this.version.split(".");
        String[] compareVersion = minecraftVersion.getVersion().split(".");

        return currentVersion[0].equals(compareVersion[0]) &&
                currentVersion[1].equals(compareVersion[1]);
    }
}
