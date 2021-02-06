package fr.redwoub.modplugin.commands;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCMD implements CommandExecutor {

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
            player.hidePlayer(player);
            player.sendMessage("§aVanish on !");
        }

        if(args[0].equals("off")){
            player.showPlayer(player);
            player.sendMessage("§cVanish off !");
        }

        return false;
    }
}
