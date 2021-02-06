package fr.redwoub.modplugin.utils;

import org.bukkit.entity.Player;

import java.util.WeakHashMap;

public class Freeze {

    private final static WeakHashMap<Player, Boolean> freezeMap = new WeakHashMap<Player, Boolean>();

    public static void setFreeze(Player player, boolean freeze) {
        if (player == null)
            return;
        if (freeze) {
            Freeze.freezeMap.put(player, true);
            return;
        }
        Freeze.freezeMap.remove(player);
    }

    public static boolean isFreeze(Player player) {
        return player != null && Freeze.freezeMap.containsKey(player);
    }
}
