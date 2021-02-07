package fr.redwoub.modplugin.managers;


import fr.redwoub.modplugin.Main;
import fr.redwoub.modplugin.commands.CheckCMD;
import fr.redwoub.modplugin.commands.DiscordCMD;
import fr.redwoub.modplugin.commands.VanishCMD;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class RegisterManager {
    private Main main = Main.getInstance();

    public void onRegister(){
        PluginManager pm = Bukkit.getPluginManager();
        main.getCommand("check").setExecutor(new CheckCMD());
        main.getCommand("discord").setExecutor(new DiscordCMD());
        main.getCommand("vanish").setExecutor(new VanishCMD());

        pm.registerEvents(new CheckCMD(), main);
        pm.registerEvents(new PlayerManager(), main);
        pm.registerEvents(new PlayerManagerVanish(), main);
    }



}
