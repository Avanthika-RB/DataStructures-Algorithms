package hw2;

import java.util.Comparator;

public class FantasyPlayer implements Comparable<FantasyPlayer> {
    public String name; // player name
    public String teamName; // player team name
    public String position; // Position the player plays
    public int age; // Player's age
    public int gamesPlayed; // How many games the player played

    public int atBats; // Number of at-bats for the player

    public int runsScored; // How many runs scored by the player

    public int singles; // How many singles hit by the player
    public int doubles; // How many doubles hit by the player
    public int triples; // How many triples hit by the player
    public int homeruns; // How many homeruns hit by the player

    public int rbis; // How many times a player's teammate scored as a result of a player's at-bat

    public FantasyPlayer(String name, String teamName, String position, int age, int gamesPlayed,
                         int atBats, int runsScored, int singles, int doubles, int triples,
                         int homeruns, int rbis) {
        this.name = name;
        this.teamName = teamName;
        this.position = position;
        this.age = age;
        this.gamesPlayed = gamesPlayed;
        this.atBats = atBats;
        this.runsScored = runsScored;
        this.singles = singles;
        this.doubles = doubles;
        this.triples = triples;
        this.homeruns = homeruns;
        this.rbis = rbis;
    }

    // Question 3
    public static class AverageComparator implements Comparator<FantasyPlayer> {
        public int compare(FantasyPlayer p1, FantasyPlayer p2) {
            double avg1 = p1.getBattingAverage();
            double avg2 = p2.getBattingAverage();
            if (Math.abs(avg1 - avg2) <= 0.01) {
                return 0;
            } else if (avg1 > avg2) {
                return 1;
            } else {
                return -1;
            }
        }
    }
    public double getSluggingPct() {
        int totalBases = singles + 2 * doubles + 3 * triples + 4 * homeruns;
        return (double) totalBases / atBats;
    }
    public double getBattingAverage() {
        int totalHits = singles + doubles + triples + homeruns;
        return (double) totalHits / atBats;
    }
    // Question 1
    public int compareTo(FantasyPlayer other) {
        double thisSluggingPct = this.getSluggingPct();
        double otherSluggingPct = other.getSluggingPct();
        if (Math.abs(thisSluggingPct - otherSluggingPct) <= 0.01) {
            return 0;
        } else if (thisSluggingPct > otherSluggingPct) {
            return 1;
        } else {
            return -1;
        }
    }
    public String toString() {
        return name;
    }
}
