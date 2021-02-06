package fr.redwoub.modplugin.managers;

import fr.redwoub.modplugin.commands.VanishCMD;
import fr.redwoub.modplugin.utils.Freeze;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerManager implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = event.getPlayer();

        if (Freeze.isFreeze(player) && event.getFrom().distance(event.getTo()) > 0) {
            player.teleport(player.getLocation());
            player.sendMessage("§cYou were freeze !\n §c If you log out you will be banned for life !");
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if(!player.hasPermission("mod.use")){
            for(Player players : Bukkit.getOnlinePlayers()){
                players.hidePlayer(VanishCMD.getPlayer(VanishCMD.playerVanish));
            }
        }
    }
}
