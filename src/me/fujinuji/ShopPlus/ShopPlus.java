package me.fujinuji.ShopPlus;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import me.fujinuji.ShopPlus.Database.DatabaseConnection;
import me.fujinuji.ShopPlus.Listeners.SignGUI;
import me.fujinuji.ShopPlus.Utils.Enums.XMaterial;
import me.fujinuji.ShopPlus.Domain.Category;
import me.fujinuji.ShopPlus.Domain.Item;
import me.fujinuji.ShopPlus.Listeners.MainMenuListener;
import me.fujinuji.ShopPlus.Listeners.ShopMenuListener;
import me.fujinuji.ShopPlus.Utils.PlayerVariables;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import org.jetbrains.annotations.NotNull;

public class ShopPlus extends JavaPlugin{

    private SignGUI signGui;
    private Connection connection;
    private Statement connectionStatement;
    private DatabaseConnection databaseConnection;

    @Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new MainMenuListener(), this);
		getServer().getPluginManager().registerEvents(new ShopMenuListener(), this);
		
		PlayerVariables.initializeVariables();
        getItems(null);
        signGui = new SignGUI(this);
        databaseConnection = new DatabaseConnection("fujinuji", "parola");

        try {
            databaseConnection.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		//if ((cmd.getName().equalsIgnoreCase("shop")) && ((sender instanceof Player))) {
			/*Player commandPlayer = (Player) (sender);
			
			if (args.length != 0) {
				if (args[0].equals("admin")) {
					MainMenuAdmin.showAdminMainMenu(commandPlayer);
				}
			}*/
            /*
            signGui.open((Player) sender, new String[] { "test0", "test1", "test2", "test3" }, new SignGUI.SignGUIListener() {
                @Override
                public void onSignDone(Player player, String[] lines) {
                    // do something with the input
                    System.out.println(lines[0]);
                }
            });*/
            try {
                this.appendToDatabase("fujinuji", args[0]);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

		/*} else {
			sender.sendMessage("Only players can access this command");
		}
		*/
		return false;
	}
	
	public static ArrayList<Category> getItems(Player player) {
		Material[] materialsList =  Material.values();
		ArrayList<Category> items = new ArrayList<Category>();
		Inventory inventory = org.bukkit.Bukkit.createInventory(null, 9);
		
		for (Material material : materialsList) {
		    ArrayList<Integer> dataList = (ArrayList<Integer>) XMaterial.getDataByMaterial(material);

		    if (dataList.size() > 0) {
                for(int data : dataList) {
                    inventory.setItem(1, new ItemStack(material, 1, (byte) data));
                    if (inventory.getItem(1) != null && inventory.getItem(1).getType() != Material.AIR) {
                        items.add(new Category(new Item(new ItemStack(material, 1, (byte) data)), null, null));
                    }
                }
            } else {
                inventory.setItem(1, new ItemStack(material, 1));
                if (inventory.getItem(1) != null && inventory.getItem(1).getType() != Material.AIR) {
                    items.add(new Category(new Item(material), null, null));
                }
            }
		}

		Bukkit.getServer().getConsoleSender().sendMessage("" + items.size());
		Bukkit.getServer().getConsoleSender().sendMessage(Bukkit.getBukkitVersion());
		
		return items;
	}

	public static void send(String string) {
		Bukkit.getServer().getConsoleSender().sendMessage(string);
	}

    private void loadExternalDriver(File file) throws Exception {
        Method method = URLClassLoader.class.getDeclaredMethod("addURL", new Class[]{URL.class});
        method.setAccessible(true);
        method.invoke(ClassLoader.getSystemClassLoader(), new Object[]{file.toURI().toURL()});
    }

	public void createConnection() throws Exception{
        final String dir = System.getProperty("user.dir");
        getServer().getConsoleSender().sendMessage(dir);
        File driver = new File(dir + "\\plugins\\ShopPlus\\h2.jar");
        getServer().getConsoleSender().sendMessage(driver.toString());
        this.loadExternalDriver(driver);

        Class.forName("org.h2.Driver").newInstance();
        this.connection = DriverManager.getConnection("jdbc:h2:" + ".\\plugins\\ShopPlus\\Database\\messages", "root", "password");
        this.connectionStatement = connection.createStatement();

        String query = "CREATE TABLE IF NOT EXISTS MESSAGES" +
                "( id INTEGER auto_increment, " +
                "userName VARCHAR(255), " +
                "message VARCHAR(255), " +
                "PRIMARY KEY( id ))";

        this.connectionStatement.executeUpdate(query);
    }

    private void appendToDatabase(@NotNull String player, String message) throws Exception{
        String query = "INSERT INTO MESSAGES(userName, message) " +
                "VALUES('" + player + "', '" + message + "')";
        this.connectionStatement.executeUpdate(query);
        this.printDatabase();
    }

    private void printDatabase() {
        try {
            ResultSet resultSet = this.connectionStatement.executeQuery("SELECT  * FROM MESSAGES");

            while(resultSet.next()) {
                System.out.println(resultSet.getString("userName") + " " + resultSet.getString("message"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
