package org.reeedev.invmanager.Listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.reeedev.invmanager.InvManager;
import org.reeedev.invmanager.Classes.IEHelper;
import org.reeedev.invmanager.Utils.ReverseArrayList;

import java.io.IOException;
import java.util.ArrayList;

public class OnInventoryClick implements Listener {
    @EventHandler
    public void Event(InventoryClickEvent e) throws IOException {
        Player p = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equals("§7InvSee Options") && e.getCurrentItem() != null) {
            ArrayList<String> temp = InvManager.receiveTemp(p);
            e.setCancelled(true);

            switch (e.getCurrentItem().getType()) {
                case COMPASS:
                    p.sendMessage("§7Analytics coming soon. It tracks if many players got items in a short time.");
                    break;
                case PAPER:
                    p.sendMessage("§7Logs coming soon. It tracks when and from who, they got these items.");
                    break;
                case BARRIER:
                    if (temp.get(1) != null) {
                        InvManager.deleteTemp(p);
                        Player target = Bukkit.getPlayer(temp.get(0));

                        if (target == null) {
                            InvManager.saveEmptyInventory(temp.get(0), p.getWorld().getName());
                        } else {
                            target.getInventory().clear();
                        }
                    }
                    break;
                case CHEST:
                    if (temp.get(1) != null) {
                        Player target = Bukkit.getPlayer(temp.get(0));

                        if (target == null) {
                            InvManager.deleteTemp(p);

                            Inventory customInv = Bukkit.createInventory(null, InventoryType.PLAYER);

                            ArrayList<IEHelper> inv = InvManager.receiveInventory(temp.get(0), p.getWorld());

                            if (inv != null) {
                                ReverseArrayList revObj = new ReverseArrayList();

                                for (IEHelper IEHelper : revObj.reverseArrayList(inv)) {
                                    customInv.setItem(IEHelper.index, IEHelper.is);
                                }
                            }

                            p.closeInventory();
                            InvManager.createTemp(p, temp.get(0), "INVENTORY");
                            p.openInventory(customInv);
                        } else {
                            p.closeInventory();
                            p.openInventory(target.getInventory());
                        }
                    }
                    break;
            }
        }
        if (e.getView().getTitle().equals("§7EnderSee Options") && e.getCurrentItem() != null) {
            ArrayList<String> temp = InvManager.receiveTemp(p);
            e.setCancelled(true);

            switch (e.getCurrentItem().getType()) {
                case COMPASS:
                    p.sendMessage("§7Analytics coming soon. It tracks if many players got items in a short time.");
                    break;
                case PAPER:
                    p.sendMessage("§7Logs coming soon. It tracks when and from who, they got these items.");
                    break;
                case BARRIER:
                    if (temp.get(1) != null) {
                        InvManager.deleteTemp(p);
                        Player target = Bukkit.getPlayer(temp.get(0));

                        if (target == null) {
                            InvManager.saveEmptyEnderChest(temp.get(0), p.getWorld().getName());
                        } else {
                            target.getEnderChest().clear();
                        }
                    }
                    break;
                case ENDER_CHEST:
                    if (temp.get(1) != null) {
                        Player target = Bukkit.getPlayer(temp.get(0));

                        if (target == null) {
                            InvManager.deleteTemp(p);

                            Inventory customInv = Bukkit.createInventory(null, InventoryType.ENDER_CHEST);

                            ArrayList<IEHelper> inv = InvManager.receiveEnderChest(temp.get(0), p.getWorld());

                            if (inv != null) {
                                ReverseArrayList revObj = new ReverseArrayList();

                                for (IEHelper IEHelper : revObj.reverseArrayList(inv)) {
                                    customInv.setItem(IEHelper.index, IEHelper.is);
                                }
                            }

                            p.closeInventory();
                            InvManager.createTemp(p, temp.get(0), "ENDER_CHEST");
                            p.openInventory(customInv);
                        } else {
                            p.closeInventory();
                            p.openInventory(target.getEnderChest());
                        }
                    }
                    break;
            }
        }
    }
}