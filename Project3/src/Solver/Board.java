package Solver;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
public class Board {
    private int[][] tiles;
    private int size;

    public Board(int[][] initialTiles) {
        this.tiles = initialTiles;
        this.size = initialTiles.length;
    }

    public boolean isGoal() {
        int goal = 1;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (row == size - 1 && col == size - 1) {
                    return tiles[row][col] == 0;
                }
                if (tiles[row][col] != goal) {
                    return false;
                }
                goal++;
            }
        }
        return true;
    }

    public int computeManhattanDistance() {
        int totalDistance = 0;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                int value = tiles[row][col];
                if (value != 0) {
                    int newRow = (value - 1)/size;
                    int newCol = (value - 1)%size;
                    int distance = Math.abs(row- newRow) + Math.abs(col- newCol);
                    totalDistance += distance;
                }
            }
        }
        return totalDistance;
    }

    public List<Board> getNeighbors() {
        List<Board> getneighbors = new ArrayList<>();
        int blankRow = -1, blankCol = -1;
        boolean bool = false;
        for (int row = 0; row < size && !bool; row++) {
            for (int col = 0; col < size && !bool; col++) {
                if (tiles[row][col] == 0) {
                    blankRow = row;
                    blankCol = col;
                    bool = true;
                }
            }
        }
        int[][] directions = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };//these are all the directions it can go
        for (int[] dir : directions) {
            int newRow = blankRow + dir[0];
            int newCol = blankCol + dir[1];
            if (correct(newRow, newCol)) {
                int[][] newTiles = copy();
                newTiles[blankRow][blankCol] = newTiles[newRow][newCol];
                newTiles[newRow][newCol] = 0;
                getneighbors.add(new Board(newTiles));
            }
        }
        return getneighbors;
    }

    private boolean correct(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }

    private int[][] copy() {
        int[][] newTiles = new int[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                newTiles[row][col] = tiles[row][col];
            }
        }
        return newTiles;
    }

    public int hashCode() { //finds hash code for row/col array
        return Arrays.deepHashCode(tiles);
    }

    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null || getClass() != obj.getClass()){
            return false;
        }
        Board board = (Board)obj;
        return Arrays.deepEquals(tiles, board.tiles);
    }
    /*
           Define and implement the required + helper methods here
        */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                sb.append(tiles[row][col]);
                if (col < size - 1) sb.append(" ");
            }
            if (row < size - 1) sb.append("\n");
        }
        return sb.toString();
    }
}

