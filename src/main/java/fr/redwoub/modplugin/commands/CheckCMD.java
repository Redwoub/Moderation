package fr.redwoub.modplugin.commands;

import fr.redwoub.modplugin.utils.Freeze;
import fr.redwoub.modplugin.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CheckCMD implements CommandExecutor, Listener {

    String target;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if(!(sender instanceof Player)){
            sender.sendMessage("Only a player can execute this command");
            return false;
        }

        Player player = (Player) sender;

        if(args.length < 1){
            player.sendMessage("§cInvalid syntax");
            player.sendMessage("§e/check <player>");
            return false;
        }

        if(Bukkit.getPlayer(args[0]) == null){
            player.sendMessage("§cInvalid Player");
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);
        Inventory inv = Bukkit.createInventory(null, 9*6, "§aCheck " + target.getName());
        player.openInventory(inv);
        inv.setItem(19, new ItemBuilder(Material.REDSTONE).setName("§6Alert this player").toItemStack());
        inv.setItem(21, new ItemBuilder(Material.PACKED_ICE).setName("§6Freeze this player").toItemStack());
        inv.setItem(23, new ItemBuilder(Material.ICE).setName("§6Unfreeze this player").toItemStack());
        inv.setItem(25, new ItemBuilder(Material.BLAZE_ROD).setName("§6Kill this player").toItemStack());

        return false;
    }

    @EventHandler
    public void onInterract(InventoryClickEvent event){
        Inventory inv = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        ItemStack itemStack = event.getCurrentItem();

        if(inv.getName().contains("§aCheck")){
            switch (itemStack.getType()){
                case REDSTONE:
                    if(!itemStack.hasItemMeta()) return;
                    if(itemStack.getItemMeta().getDisplayName().equals("§6Alert this player")){
                        target = inv.getName().substring(8);
                        if(Bukkit.getPlayer(target) == null) return;
                        Player targetName = Bukkit.getPlayer(target);
                        targetName.sendMessage("§c§lStop cheat !\n" + "§cPlease go discord : /discord");
                        event.setCancelled(true);
                        return;
                    }
                    break;

                case PACKED_ICE:
                    if(!itemStack.hasItemMeta()) return;
                    if(itemStack.getItemMeta().getDisplayName().equals("§6Freeze this player")){
                        target = inv.getName().substring(8);
                        if(Bukkit.getPlayer(target) == null) return;
                        Player targe = Bukkit.getPlayer(target);
                        Freeze.setFreeze(targe, true);
                        player.sendMessage("§aYou have freeze this player");
                        event.setCancelled(true);
                        return;
                    }
                    break;

                case ICE:
                    if(!itemStack.hasItemMeta()) return;
                    if(itemStack.getItemMeta().getDisplayName().equals("§6Unfreeze this player")){
                        target = inv.getName().substring(8);
                        if (Bukkit.getPlayer(target) == null) return;
                        Player tar = Bukkit.getPlayer(target);
                        Freeze.setFreeze(tar, false);
                        player.sendMessage("§aYou have unfreeze this player");
                        event.setCancelled(true);
                        return;
                    }
                    break;

                case BLAZE_ROD:
                    if(!itemStack.hasItemMeta()) return;
                    if(itemStack.getItemMeta().getDisplayName().equals("§6Kill this player")){
                        target = inv.getName().substring(8);
                        if(Bukkit.getPlayer(target) == null) return;
                        Player tart = Bukkit.getPlayer(target);
                        tart.setHealth(0);
                        player.sendMessage("§aYou killed this player well");
                        event.setCancelled(true);
                        return;
                    }
                    break;
                default: break;
            }
        }
    }
}
