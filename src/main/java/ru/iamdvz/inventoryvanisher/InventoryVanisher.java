package ru.iamdvz.inventoryvanisher;

import org.bukkit.plugin.java.JavaPlugin;
import ru.iamdvz.inventoryvanisher.commands.InventoryCommands;
import ru.iamdvz.inventoryvanisher.listeners.InventoryListener;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class InventoryVanisher extends JavaPlugin {
    private static InventoryVanisher instance;
    private static List<String> inventoryNames;
    private static boolean vanishOnAllNames;
    private static List<String> blacklistMenuNames;
    private static int vanishDelay;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
        Objects.requireNonNull(this.getCommand("ivanisher")).setExecutor(new InventoryCommands());
    }
    @Override
    public void onLoad() {
        instance = this;
        loadFromConfig();
    }

    @Override
    public void onDisable() {
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
    public static List<String> getBlacklistMenuNames() {
        return blacklistMenuNames;
    }
    public static int getVanishDelay() {
        return vanishDelay;
    }
    public static void loadFromConfig() {
        inventoryNames = instance.getConfig().getStringList("menu-names");
        vanishOnAllNames = instance.getConfig().getBoolean("vanish-on-all-menu-names");
        blacklistMenuNames = instance.getConfig().getStringList("blacklist-menu-names");
        vanishDelay = instance.getConfig().getInt("vanish-delay");

        if (inventoryNames == null) {
            inventoryNames = Collections.singletonList(" ");
        }

        if (blacklistMenuNames == null) {
            blacklistMenuNames = Collections.singletonList(" ");
        }
        if (vanishDelay <= 0) {
            vanishDelay = 1;
        }
    }
}
