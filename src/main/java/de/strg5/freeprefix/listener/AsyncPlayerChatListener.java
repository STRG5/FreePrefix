package de.strg5.freeprefix.listener;

import de.strg5.freeprefix.FreePrefix;
import de.strg5.freeprefix.rank.Rank;
import de.strg5.freeprefix.user.RankUser;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Objects;

public class AsyncPlayerChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (FreePrefix.getInstance().getRankConfig().isManageChat()) {
            Player player = event.getPlayer();
            if (RankUser.getUser(player.getUniqueId()) != null) {
                Rank rank = Objects.requireNonNull(RankUser.getUser(player.getUniqueId())).getRank();
                event.setFormat(
                        rank.getChatPrefix() + player.getName() + "§8: " + rank.getChatSuffix() + event.getMessage());
            }
        }
    }

}
