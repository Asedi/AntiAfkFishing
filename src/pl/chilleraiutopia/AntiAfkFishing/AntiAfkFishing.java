package pl.chilleraiutopia.AntiAfkFishing;

import com.mysql.fabric.xmlrpc.base.Data;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.*;

public class AntiAfkFishing extends JavaPlugin {
    @Override
    public void onEnable() {
        DatabaseHandler.createNewDatabase();
        DatabaseHandler.createNewTable();
        getLogger().info("AntiAfkFishing by Asedi has enabled.");
        getServer().getPluginManager().registerEvents(new FishEventListener(), this);
    }
}
