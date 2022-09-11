package ru.iamdvz.inventoryvanisher;

import org.bukkit.plugin.java.JavaPlugin;
import ru.iamdvz.inventoryvanisher.commands.InventoryCommands;
import ru.iamdvz.inventoryvanisher.listeners.InventoryListener;

import java.util.List;
import java.util.Objects;

public final class InventoryVanisher extends JavaPlugin {
    private static InventoryVanisher instance;
    private static List<String> inventoryNames;
    private static boolean vanishOnAllNames;
    private static List<String> defaultMenuNames;
    private static List<String> blacklistMenuNames;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
        Objects.requireNonNull(this.getCommand("ivanisher")).setExecutor(new InventoryCommands());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static InventoryVanisher getInstance() {
        return instance;
    }
    public static List<String> getInventoryNames() {
        return inventoryNames;
    }
    public static boolean getVanishOnAllNames() {
        return vanishOnAllNames;
    }
    public static List<String> getDefaultMenuNames() {
        return defaultMenuNames;
    }
    public static List<String> getBlacklistMenuNames() {
        return blacklistMenuNames;
    }
    public static void loadFromConfig() {
        inventoryNames = instance.getConfig().getStringList("menu-names");
        vanishOnAllNames = instance.getConfig().getBoolean("vanish-on-all-menu-names");
        defaultMenuNames = instance.getConfig().getStringList("default-menu-names");
        blacklistMenuNames = instance.getConfig().getStringList("blacklist-menu-names");
    }
}
