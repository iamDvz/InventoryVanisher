package ru.iamdvz.inventoryvanisher.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import ru.iamdvz.inventoryvanisher.InventoryVanisher;

import java.util.HashMap;
import java.util.Map;

public class InventoryListener implements Listener {
    private final Map<HumanEntity, ItemStack[]> playerInventories = new HashMap<>();

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        String eventTitle = event.getView().getTitle();
        if (((!event.getInventory().getType().defaultTitle().equals("Crafting"))
                && InventoryVanisher.getVanishOnAllNames())
                && (!InventoryVanisher.getBlacklistMenuNames().contains(eventTitle))
                || (InventoryVanisher.getInventoryNames().contains(eventTitle)
                && (!InventoryVanisher.getBlacklistMenuNames().contains(eventTitle)))) {

            Bukkit.getScheduler().runTaskLater(InventoryVanisher.getInstance(), new Runnable() {
                @Override
                public void run() {
                    HumanEntity ePlayer = event.getPlayer();
                    playerInventories.put(ePlayer, ePlayer.getInventory().getContents());
                    ePlayer.getInventory().clear();
                }
            }, InventoryVanisher.getVanishDelay());
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        HumanEntity ePlayer = event.getPlayer();
        String eventTitle = event.getView().getTitle();
        if (((!event.getInventory().getType().defaultTitle().equals("Crafting"))
                && (InventoryVanisher.getVanishOnAllNames())
                && (!InventoryVanisher.getBlacklistMenuNames().contains(eventTitle))
                || (InventoryVanisher.getInventoryNames().contains(eventTitle)
                && (!InventoryVanisher.getBlacklistMenuNames().contains(eventTitle))
                && playerInventories.containsKey(ePlayer)
                && playerInventories.get(ePlayer) != null))) {
            Bukkit.getScheduler().runTaskLater(InventoryVanisher.getInstance(), new Runnable() {
                @Override
                public void run() {
                    ePlayer.getInventory().setContents(playerInventories.get(ePlayer));
                    playerInventories.remove(ePlayer);
                }
            }, InventoryVanisher.getVanishDelay());

        }

    }
}
