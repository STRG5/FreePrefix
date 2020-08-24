package de.strg5.freeprefix.config;

import de.strg5.freeprefix.FreePrefix;
import de.strg5.freeprefix.rank.Rank;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;


public class RankConfig {

    private final File file;
    private final FileConfiguration fileConfiguration;
    @Getter
    private boolean manageTabList;
    @Getter
    private boolean manageChat;

    public RankConfig(String pathName, String fileName) {
        this.file = new File(pathName, fileName);
        this.fileConfiguration = YamlConfiguration.loadConfiguration(file);
        this.load();
    }

    private void load() {
        if (!file.exists()) {
            fileConfiguration.set("general.settings.manageTab", true);
            fileConfiguration.set("general.settings.manageChat", true);
            fileConfiguration.set("ranks.admin.name", "01Admin");
            fileConfiguration.set("ranks.admin.permission", "rank.admin");
            fileConfiguration.set("ranks.admin.prefix", "&4Admin &8| &4");
            fileConfiguration.set("ranks.admin.chatPrefix", "&4Administrator &8| &4");
            fileConfiguration.set("ranks.admin.chatSuffix", "&c");
            fileConfiguration.set("ranks.admin.default", false);

            fileConfiguration.set("ranks.default.name", "02Default");
            fileConfiguration.set("ranks.default.permission", "rank.default");
            fileConfiguration.set("ranks.default.prefix", "&7");
            fileConfiguration.set("ranks.default.chatPrefix", "&7");
            fileConfiguration.set("ranks.default.chatSuffix", "&7");
            fileConfiguration.set("ranks.default.default", true);
            try {
                fileConfiguration.save(file);
                this.load();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        } else {
            this.manageTabList = this.fileConfiguration.getBoolean("general.settings.manageTab");
            this.manageChat = this.fileConfiguration.getBoolean("general.settings.manageChat");
            Collection<String> keys = this.fileConfiguration.getConfigurationSection("ranks").getKeys(true);
            Iterator iterator = keys.iterator();
            while (iterator.hasNext()) {
                iterator.next();
                Rank rank = new Rank(
                        this.fileConfiguration.getString("ranks." + iterator.next()), //name
                        this.fileConfiguration.getString("ranks." + iterator.next()), //permission
                        ChatColor.translateAlternateColorCodes(
                                '&',
                                this.fileConfiguration.getString("ranks." + iterator.next())), //prefix
                        ChatColor.translateAlternateColorCodes(
                                '&',
                                this.fileConfiguration.getString("ranks." + iterator.next())), //chatPrefix
                        ChatColor.translateAlternateColorCodes(
                                '&',
                                this.fileConfiguration.getString("ranks." + iterator.next())), //chatSuffix
                        this.fileConfiguration.getBoolean("ranks." + iterator.next())); //default
                FreePrefix.getInstance().getRankList().add(rank);
            }
        }
    }

}
