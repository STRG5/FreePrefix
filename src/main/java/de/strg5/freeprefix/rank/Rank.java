package de.strg5.freeprefix.rank;

import lombok.Getter;

@Getter
public class Rank {

    private final String name;
    private final String permission;
    private final String prefix;
    private final String chatPrefix;
    private final String chatSuffix;
    private final Boolean defaultRank;

    public Rank(String name, String permission, String prefix, String chatPrefix, String chatSuffix, Boolean defaultRank) {
        this.name = name;
        this.prefix = prefix;
        this.permission = permission;
        this.chatPrefix = chatPrefix;
        this.chatSuffix = chatSuffix;
        this.defaultRank = defaultRank;
    }
}
