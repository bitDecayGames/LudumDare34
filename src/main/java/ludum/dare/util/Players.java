package ludum.dare.util;

import ludum.dare.actors.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Players {
    static Boolean initialized = false;

    static List<Player> players;

    public static void intialize(List<Player> startingPlayers) {
        if (initialized) {
            throw new Error("Players have already been initialized");
        }

        players = startingPlayers;
        initialized = true;
    }

    public static List<Player> list() {
        return shallowCopy();
    }

    private static List<Player> shallowCopy() {
        List<Player> shallowCopy = new ArrayList<>();
        for (Player player : players) {
            shallowCopy.add(player);
        }
        return shallowCopy;
    }
}
