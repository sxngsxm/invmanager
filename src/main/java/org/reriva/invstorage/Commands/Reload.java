package org.reriva.invstorage.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.reriva.invstorage.InvStorage;

public class Reload implements CommandExecutor {
    InvStorage plugin;

    public Reload(InvStorage plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player p = (Player) sender;
        if (p.hasPermission("invstorage.reload")) {
            p.sendMessage("§cReloading config...");
            plugin.reloadConfig();
            p.sendMessage("§cReload complete.");
        } else {
            p.sendMessage(InvStorage.getConfigValue("missing_permissions").toString().replace("{command}", "/invstoragereload"));
        }
        return false;
    }
}