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
    private Inventory sanction;

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

        //Create and complete Check inventory

        Inventory inv = Bukkit.createInventory(null, 9*6, "§aCheck " + target.getName());
        player.openInventory(inv);
        inv.setItem(19, new ItemBuilder(Material.REDSTONE).setName("§6Alert this player").toItemStack());
        inv.setItem(21, new ItemBuilder(Material.PACKED_ICE).setName("§6Freeze this player").toItemStack());
        inv.setItem(23, new ItemBuilder(Material.ICE).setName("§6Unfreeze this player").toItemStack());
        inv.setItem(25, new ItemBuilder(Material.BLAZE_ROD).setName("§6Kill this player").toItemStack());
        inv.setItem(38, new ItemBuilder(Material.EYE_OF_ENDER).setName("§6Open inventory to this player").toItemStack());
        inv.setItem(40, new ItemBuilder(Material.ENDER_PEARL).setName("§6Teleport to this player").toItemStack());
        inv.setItem(42, new ItemBuilder(Material.ARROW).setName("§6Kick this player").toItemStack());
        inv.setItem(53, new ItemBuilder(Material.BARRIER).setName("§6Close this menu").toItemStack());
        inv.setItem(4, new ItemBuilder(Material.STRING).setName("§cSanction").toItemStack());

        //Create and complete Sanction inventory
        sanction = Bukkit.createInventory(null, 9*6, "§4Sanctionfor : " + target.getName());
        sanction.setItem(19, new ItemBuilder(Material.IRON_SWORD).setName("§6Ban this player for 1 month").toItemStack());
        sanction.setItem(21, new ItemBuilder(Material.DIAMOND_SWORD).setName("§6Ban this player").toItemStack());
        //mute 1day
        //mute perm

        return false;
    }

    @EventHandler
    public void onInterractCheckInventory(InventoryClickEvent event){
        Inventory inv = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        ItemStack itemStack = event.getCurrentItem();

        if(inv.getName().contains("§aCheck")){
            switch (itemStack.getType()){
                case REDSTONE:
                    if(!itemStack.hasItemMeta()) return;
                    if(itemStack.getItemMeta().getDisplayName().equals("§6Alert this player")){
                        if(Bukkit.getPlayer(inv.getName().substring(8)) == null) return;
                        Player targetName = Bukkit.getPlayer(inv.getName().substring(8));
                        targetName.sendMessage("§c§lStop cheat !\n" + "§cPlease go discord : /discord");
                        event.setCancelled(true);
                        return;
                    }
                    break;

                case PACKED_ICE:
                    if(!itemStack.hasItemMeta()) return;
                    if(itemStack.getItemMeta().getDisplayName().equals("§6Freeze this player")){
                        if(Bukkit.getPlayer(inv.getName().substring(8)) == null) return;
                        Player targe = Bukkit.getPlayer(inv.getName().substring(8));
                        Freeze.setFreeze(targe, true);
                        player.sendMessage("§aYou have freeze this player");
                        event.setCancelled(true);
                        return;
                    }
                    break;

                case ICE:
                    if(!itemStack.hasItemMeta()) return;
                    if(itemStack.getItemMeta().getDisplayName().equals("§6Unfreeze this player")){
                        if (Bukkit.getPlayer(inv.getName().substring(8)) == null) return;
                        Player tar = Bukkit.getPlayer(inv.getName().substring(8));
                        Freeze.setFreeze(tar, false);
                        player.sendMessage("§aYou have unfreeze this player");
                        event.setCancelled(true);
                        return;
                    }
                    break;

                case BLAZE_ROD:
                    if(!itemStack.hasItemMeta()) return;
                    if(itemStack.getItemMeta().getDisplayName().equals("§6Kill this player")){
                        if(Bukkit.getPlayer(inv.getName().substring(8)) == null) return;
                        Player tart = Bukkit.getPlayer(inv.getName().substring(8));
                        tart.setHealth(0);
                        player.sendMessage("§aYou killed this player well");
                        event.setCancelled(true);
                        return;
                    }
                    break;

                case EYE_OF_ENDER:
                    if(!itemStack.hasItemMeta()) return;
                    if(itemStack.getItemMeta().getDisplayName().equals("§6Open inventory to this player")){
                        if(Bukkit.getPlayer(inv.getName().substring(8)) == null) return;
                        Player target = Bukkit.getPlayer(inv.getName().substring(8));
                        player.openInventory(target.getInventory());
                        event.setCancelled(true);
                        return;

                    }
                    break;

                case ENDER_PEARL:
                    if(!itemStack.hasItemMeta()) return;
                    if(itemStack.getItemMeta().getDisplayName().equals("§6Teleport to this player")){
                        if(Bukkit.getPlayer(inv.getName().substring(8)) == null) return;
                            Player targ = Bukkit.getPlayer(inv.getName().substring(8));
                            player.teleport(targ.getLocation());
                            player.sendMessage("§aTeleportation to " + targ.getName());
                            event.setCancelled(true);
                            return;
                        }
                    break;

                case ARROW:
                    if(!itemStack.hasItemMeta()) return;
                    if(itemStack.getItemMeta().getDisplayName().equals("§6Kick this player")){
                        if(Bukkit.getPlayer(inv.getName().substring(8)) == null) return;
                        Player target = Bukkit.getPlayer(inv.getName().substring(8));
                        target.kickPlayer("§cKick with moderator !\n" + "§cplease go to discord !");
                        event.setCancelled(true);
                        return;
                    }
                    break;

                case BARRIER:
                    if(!itemStack.hasItemMeta()) return;
                    if(itemStack.getItemMeta().getDisplayName().equals("§6Close this menu")){
                        player.closeInventory();
                        event.setCancelled(true);
                        return;
                    }
                    break;

                case STRING:
                    if(!itemStack.hasItemMeta()) return;
                    if(itemStack.getItemMeta().getDisplayName().equals("§cSanction")){
                        player.closeInventory();
                        event.setCancelled(true);
                        player.openInventory(sanction);
                        return;
                    }
                    break;
                default: break;
            }
        }
    }

    public void onInterractSanctionInventory(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getInventory();
        ItemStack itemStack = event.getCurrentItem();

        if(inv.getName().contains("§4Sanctionfor")){
            switch(itemStack.getType()){
                case IRON_SWORD:
                    if(!itemStack.hasItemMeta()) return;
                    if(itemStack.getItemMeta().getDisplayName().equals("§6Ban this player for 1 month")){
                        //todo
                        player.sendMessage("§aThis player was banned for 1 month !");
                        event.setCancelled(true);
                        return;
                    }
                    break;

                case DIAMOND:
                    if(!itemStack.hasItemMeta()) return;
                    if(itemStack.getItemMeta().getDisplayName().equals("§6Ban this player")){
                        if(Bukkit.getPlayer(inv.getName().substring(16)) == null) return;
                        Player target = Bukkit.getPlayer(inv.getName().substring(16));
                        target.setBanned(true);
                        player.sendMessage("This player was banned");
                        event.setCancelled(true);
                        return;
                    }
                    break;
                default: break;
            }
        }
    }
}
