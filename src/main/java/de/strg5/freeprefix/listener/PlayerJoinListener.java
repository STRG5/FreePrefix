package de.strg5.freeprefix.listener;

import de.strg5.freeprefix.FreePrefix;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (FreePrefix.getInstance().getRankConfig().isManageTabList()) {
            FreePrefix.getInstance().setScoreboard(event.getPlayer());
        }
    }
}
