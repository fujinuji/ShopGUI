package me.fujinuji.ShopPlus.Database;

import org.bukkit.Bukkit;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;

public class DatabaseConnection {

    private String username;
    private String password;

    protected Connection connection;

    public DatabaseConnection(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void connect() throws Exception {
        final String databasePath = ".\\plugins\\ShopPlus\\Database\\messages";
        setUpDriver();
        Class.forName("org.h2.Driver").newInstance();
        this.connection = DriverManager.getConnection("jdbc:h2:" + databasePath, this.username, this.password);

        Bukkit.getLogger().log(Level.INFO, "Connected to database");
    }

    private static void setUpDriver() {
        String homeDirectory = System.getProperty("user.dir");
        File driverFile = new File(homeDirectory + "\\plugins\\ShopPlus\\Database\\h2.jar");

        if (!driverFile.exists()) {
            Bukkit.getServer().getLogger().log(Level.WARNING, "[ShopPlus] No H2 driver found. Checking internet status...");

            if (netIsAvailable()) {
                Bukkit.getServer().getLogger().log(Level.INFO, "[ShopPlus] Internet connection found. Downloading H2 driver...");
                downloadH2();
                Bukkit.getServer().getLogger().log(Level.INFO,"[ShopPlus] Download completed.");
            } else {
                Bukkit.getServer().getLogger().log(Level.WARNING, "[ShopPlus] Failed to connect to the internet. Switch to local SQL database...");
            }
        }

        try {
            loadExternalDriver(driverFile);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    private static void loadExternalDriver(File file) throws Exception {
        Method method = URLClassLoader.class.getDeclaredMethod("addURL", new Class[]{URL.class});
        method.setAccessible(true);
        method.invoke(ClassLoader.getSystemClassLoader(), new Object[]{file.toURI().toURL()});
    }

    private static void downloadH2() {

        final String link = "http://repo2.maven.org/maven2/com/h2database/h2/1.4.197/h2-1.4.197.jar";
        final String databasePath = "./plugins/ShopPlus/Database/h2.jar";

        try (BufferedInputStream inputStream = new BufferedInputStream(new URL(link).openStream());
             FileOutputStream fileOS = new FileOutputStream(databasePath)) {
            byte data[] = new byte[1024];
            int byteContent;
            while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
                fileOS.write(data, 0, byteContent);
            }
        } catch (IOException e) {
            Bukkit.getLogger().log(Level.WARNING, "[ShopPlus] Failed to download to download H2 driver");
        }
    }

    private static boolean netIsAvailable() {
        try {
            final URL url = new URL("http://www.google.com");
            final URLConnection conn = url.openConnection();
            conn.connect();
            conn.getInputStream().close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
