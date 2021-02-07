package fr.redwoub.modplugin;




import fr.redwoub.modplugin.managers.RegisterManager;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {
    private static Main instance;
    private static File filediscord;

    @Override
    public void onEnable() {
        instance = this;
        createFileDiscord();
        new RegisterManager().onRegister();
    }

    @Override
    public void onDisable() {

    }


    public void createFileDiscord(){
        if(!getDataFolder().exists()){
            getDataFolder().mkdir();
        }
        filediscord = new File(getDataFolder(), "discord.yml");
        YamlConfiguration file = YamlConfiguration.loadConfiguration(filediscord);
        file.set("discord : ", "url");
        try {
            file.save(filediscord);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            filediscord.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Main getInstance(){
        return instance;
    }

    public File getFileDiscord(){
        return filediscord;
    }
}
