package fr.redwoub.modplugin;


import fr.redwoub.modplugin.commands.CheckCMD;
import fr.redwoub.modplugin.managers.PlayerManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("check").setExecutor(new CheckCMD());
        getServer().getPluginManager().registerEvents(new CheckCMD(), this);
        getServer().getPluginManager().registerEvents(new PlayerManager(), this);
    }

    @Override
    public void onDisable() {

    }
}
