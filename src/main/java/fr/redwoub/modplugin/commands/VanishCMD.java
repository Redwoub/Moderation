package fr.redwoub.modplugin.commands;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class VanishCMD implements CommandExecutor {

    public static List<Player> playerVanish = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage("Only a player can execute this command");
            return false;
        }

        if(args.length != 1){
            sender.sendMessage("§cInvalid syntax");
            sender.sendMessage("§e/vanish on | off");
            return false;
        }

        Player player = (Player) sender;

        if(args[0].equals("on")){
            playerVanish.add(player);
            for(Player players : Bukkit.getOnlinePlayers()){
                if(!players.hasPermission("mod.use")){
                    players.hidePlayer(player);
                }
            }
            player.sendMessage("§aVanish on !");
        }

        if(args[0].equals("off")){
            for(Player players : Bukkit.getOnlinePlayers()){
                players.showPlayer(player);
            }
            playerVanish.remove(player);
            player.sendMessage("§cVanish off !");
        }

        return false;
    }

    public static Player getPlayer(List<Player> player){

        for(Player players : player){
            return players;
        }
        return null;
    }
}
