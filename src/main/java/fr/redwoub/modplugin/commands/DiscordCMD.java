package fr.redwoub.modplugin.commands;

import fr.redwoub.modplugin.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class DiscordCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage("Only a player can execute this command");
            return false;
        }

        File file = Main.getInstance().getFileDiscord();
        YamlConfiguration file1 = YamlConfiguration.loadConfiguration(file);
        sender.sendMessage("§bDiscord : " + file1.get("discord"));
        return false;
    }
}
