package hw2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import hw2.FantasyPlayer.AverageComparator;


public class DraftBoard {

    // List that stores all FantasyPlayers on a team's DraftBoard
    private ArrayList<FantasyPlayer> teamBoard;
    public DraftBoard() {
        teamBoard = new ArrayList<>();
    }

    // Question 1
    public FantasyPlayer getBestAvailablePlayer() {
        FantasyPlayer bestPlayer = null;
        for (FantasyPlayer player : teamBoard) {
            if (bestPlayer == null || player.compareTo(bestPlayer) > 0) {
                bestPlayer = player;
            }
        }
        return bestPlayer;
    }
    // Question 2
    public FantasyPlayer getBestAvailablePlayer(PlayerFilter filter) {
        FantasyPlayer bestPlayer = null;
        for (FantasyPlayer player : teamBoard) {
            if (filter.filter(player)) {
                if (bestPlayer == null || player.compareTo(bestPlayer) > 0) {
                    bestPlayer = player;
                }
            }
        }
        return bestPlayer;
    }
    // Question 3
    public FantasyPlayer getBestAvailablePlayer(Comparator c, PlayerFilter filter) {
        List<FantasyPlayer> filtered = new ArrayList<>();
        for (FantasyPlayer player : teamBoard) {
            if (filter.filter(player)) {
                filtered.add(player);
            }
        }
        filtered.sort(c);
        if (!filtered.isEmpty()) {
            return filtered.get(filtered.size() - 1);
        }
        return null;
    }
    // Question 4
    public FantasyPlayer getBestAvailablePlayer(List<PlayerFilter> filters) {
        List<FantasyPlayer> filtered = new ArrayList<>(teamBoard);
        for (PlayerFilter filter : filters) {
            filtered.removeIf(player -> !filter.filter(player));
        }
        FantasyPlayer bestPlayer = null;
        for (FantasyPlayer player : filtered) {
            if (bestPlayer == null || player.compareTo(bestPlayer) > 0) {
                bestPlayer = player;
            }
        }
        return bestPlayer;
    }
    // Do not modify this in any way
    public static FantasyPlayer createPlayer(String[] playerInfo) {
        return new FantasyPlayer(playerInfo[0],
                playerInfo[1],
                playerInfo[2],
                Integer.parseInt(playerInfo[3]),
                Integer.parseInt(playerInfo[4]),
                Integer.parseInt(playerInfo[5]),
                Integer.parseInt(playerInfo[6]),
                Integer.parseInt(playerInfo[7]),
                Integer.parseInt(playerInfo[8]),
                Integer.parseInt(playerInfo[9]),
                Integer.parseInt(playerInfo[10]),
                Integer.parseInt(playerInfo[11])
        );
    }

    /*
        Do not delete or modify this method in any way.
     */
    public static void createDraftBoard(DraftBoard d, String playerFile){
        boolean isHeader = true;
        try {
            BufferedReader br = new BufferedReader(new FileReader(playerFile));
            String line;
            while(true){
                line = br.readLine();
                if(isHeader) {
                    isHeader = false;
                    continue;
                }
                if(line == null){
                    break;
                }
                String[] values = line.split(",");
                FantasyPlayer player = createPlayer(values);
                if(player.gamesPlayed > 100) {
                    d.teamBoard.add(createPlayer(values));
                }
            }
        } catch(FileNotFoundException f){
            System.out.println("File not found: " + f.getMessage());
        } catch(IOException e){
            System.out.println("IO Error: " + e.getMessage());
        }
    }
}
