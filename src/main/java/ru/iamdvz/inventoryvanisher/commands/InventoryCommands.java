package ru.iamdvz.inventoryvanisher.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import ru.iamdvz.inventoryvanisher.InventoryVanisher;

public class InventoryCommands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        try {
            if (args[0].equals("")) {};
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "no arguments");
            return true;
        }
        if (args[0].equalsIgnoreCase("reload")){
            InventoryVanisher.getInstance().reloadConfig();
            InventoryVanisher.loadFromConfig();
            sender.sendMessage(ChatColor.GREEN + "configs was reloaded");
            return true;
        }
        return false;
    }
}
