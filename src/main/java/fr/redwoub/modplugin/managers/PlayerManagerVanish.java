package fr.redwoub.modplugin.managers;

import fr.redwoub.modplugin.commands.VanishCMD;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;


public class PlayerManagerVanish implements Listener {
    private VanishCMD vanishCMD = new VanishCMD();

    @EventHandler
    public void onInterract(PlayerPickupItemEvent event){
        Player player = event.getPlayer();
        if(vanishCMD.playerVanish.contains(player)){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event){
        Player player = event.getPlayer();
        if(vanishCMD.playerVanish.contains(player)){
            event.setCancelled(true);

        }
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event){
        if(!(event.getDamager() instanceof Player)) return;
        Player player = (Player) event.getDamager();
        if(vanishCMD.playerVanish.contains(player)){
            event.setCancelled(true);
        }
    }
}
