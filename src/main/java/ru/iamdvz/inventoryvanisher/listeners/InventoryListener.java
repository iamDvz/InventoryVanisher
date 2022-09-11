package ru.iamdvz.inventoryvanisher.listeners;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import ru.iamdvz.inventoryvanisher.InventoryVanisher;

import java.util.HashMap;
import java.util.Map;

public class InventoryListener implements Listener {
    private Map<HumanEntity, ItemStack[]> playerInventories = new HashMap<>();

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        if (((!event.getInventory().getType().defaultTitle().equals("Crafting"))
                && (!InventoryVanisher.getDefaultMenuNames().contains(event.getView().getTitle()))
                && InventoryVanisher.getVanishOnAllNames())
                || InventoryVanisher.getInventoryNames().contains(event.getView().getTitle())) {
            HumanEntity ePlayer = event.getPlayer();
            ePlayer.sendMessage(event.getView().getTitle());
            PlayerInventory ePlayerInv = ePlayer.getInventory();
            playerInventories.put(ePlayer, ePlayerInv.getContents());
            ePlayerInv.clear();
        }
    }
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        HumanEntity ePlayer = event.getPlayer();
        if (((!event.getInventory().getType().defaultTitle().equals("Crafting"))
                && (!InventoryVanisher.getDefaultMenuNames().contains(event.getView().getTitle()))
                && (InventoryVanisher.getVanishOnAllNames())
                || (InventoryVanisher.getInventoryNames().contains(event.getView().getTitle())
                && playerInventories.containsKey(ePlayer)
                && playerInventories.get(ePlayer) != null))) {
            ePlayer.getInventory().setContents(playerInventories.get(ePlayer));
        }

    }

}
