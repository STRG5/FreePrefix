package de.strg5.freeprefix.user;

import de.strg5.freeprefix.rank.Rank;
import lombok.Getter;

import java.util.HashMap;
import java.util.UUID;

@Getter
public class RankUser {

    private final UUID uuid;
    private final Rank rank;
    public static HashMap<UUID, RankUser> userList = new HashMap<>();

    public RankUser(UUID uuid, Rank rank) {
        this.uuid = uuid;
        this.rank = rank;
        userList.put(uuid, this);
    }

    public static RankUser getUser(UUID uuid) {
        if (userList.containsKey(uuid)) {
            return userList.get(uuid);
        }
        return null;
    }
}
