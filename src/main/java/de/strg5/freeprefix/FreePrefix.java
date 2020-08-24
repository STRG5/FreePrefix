package de.strg5.freeprefix;

import de.strg5.freeprefix.command.FreePrefixCommand;
import de.strg5.freeprefix.config.RankConfig;
import de.strg5.freeprefix.listener.AsyncPlayerChatListener;
import de.strg5.freeprefix.listener.PlayerJoinListener;
import de.strg5.freeprefix.rank.Rank;
import de.strg5.freeprefix.user.RankUser;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;

@Getter
public class FreePrefix extends JavaPlugin {

    @Getter
    private static FreePrefix instance;
    private Scoreboard scoreboard;
    private RankConfig rankConfig;
    private List<Rank> rankList;

    @Override
    public void onEnable() {
        instance = this;
        this.setup();
        this.loadListener();
        getCommand("freeprefix").setExecutor(new FreePrefixCommand());
        System.out.println("--------------------------------------------");
        System.out.println("Author: STRG5");
        System.out.println("Version: " + this.getDescription().getVersion());
        System.out.println("--------------------------------------------");
        System.out.println("Registered " + this.rankList.size() + " ranks.");
    }

    public void setup() {
        this.rankList = new ArrayList<>();
        this.rankConfig = new RankConfig("plugins/FreePrefix", "config.yml");
        this.loadScoreboard();
    }

    private void loadListener() {
        if (this.rankConfig.isManageTabList()) {
            Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        }
        if (this.rankConfig.isManageChat()) {
            Bukkit.getPluginManager().registerEvents(new AsyncPlayerChatListener(), this);
        }
    }

    private void loadScoreboard() {
        if (this.scoreboard == null) {
            this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        }
        this.rankList.forEach(rank -> {
            if (this.scoreboard.getTeam(rank.getName()) == null) {
                this.scoreboard.registerNewTeam(rank.getName()).setPrefix(rank.getPrefix());
            }
        });
    }

    public void setScoreboard(Player player) {
        for (Rank rank : this.rankList) {
            if (player.hasPermission(rank.getPermission())) {
                this.scoreboard.getTeam(rank.getName()).addPlayer(player);
                player.setDisplayName(rank.getPrefix() + player.getName());
                player.setScoreboard(this.scoreboard);
                new RankUser(player.getUniqueId(), rank);
                break;
            }
            if (rank.getDefaultRank()) {
                this.scoreboard.getTeam(rank.getName()).addPlayer(player);
                player.setDisplayName(rank.getPrefix() + player.getName());
                player.setScoreboard(this.scoreboard);
                new RankUser(player.getUniqueId(), rank);
                break;
            }
        }
    }
}
