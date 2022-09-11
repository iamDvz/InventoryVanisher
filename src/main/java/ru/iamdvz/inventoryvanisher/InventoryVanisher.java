package ru.iamdvz.inventoryvanisher;

import org.bukkit.plugin.java.JavaPlugin;
import ru.iamdvz.inventoryvanisher.listeners.InventoryListener;

import java.util.List;

public final class InventoryVanisher extends JavaPlugin {
    private static InventoryVanisher instance;
    private List<String> inventoryNames;
    private boolean vanishOnAllNames;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        saveDefaultConfig();
        inventoryNames = this.getConfig().getStringList("menu-names");
        vanishOnAllNames = this.getConfig().getBoolean("vanish-on-all-menu-names");
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static InventoryVanisher getInstance() {
        return instance;
    }

    public static List<String> getInventoryNames() {
        return instance.inventoryNames;
    }
    public static boolean getVanishOnAllNames() {
        return instance.vanishOnAllNames;
    }
}
