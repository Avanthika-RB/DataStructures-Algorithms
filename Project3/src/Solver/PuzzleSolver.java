package Solver;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class PuzzleSolver {

    Board startingBoard;

    public PuzzleSolver(Board b) {
        this.startingBoard = b;
    }
    /*
            Define and implement the required + helper methods here.
         */
    public int getNumMoves() {
        PriorityQueue<Node> priority = new PriorityQueue<>();
        Set<Board> hashboard = new HashSet<>();
        priority.add(new Node(startingBoard, 0, null));
        while (!priority.isEmpty()) {
            Node current = priority.poll();
            if (current.board.isGoal()) {
                return current.times;
            }
            hashboard.add(current.board);
            for (Board neighbor : current.board.getNeighbors()) {
                if (!hashboard.contains(neighbor)) {
                    priority.add(new Node(neighbor, current.times + 1, current));
                }
            }
        }
        return -1;
    }

    public List<Board> getSolutionSteps() {
        PriorityQueue<Node> priority = new PriorityQueue<>();
        Set<Board> hashboard = new HashSet<>();
        priority.add(new Node(startingBoard, 0, null));
        while (!priority.isEmpty()) {
            Node current = priority.poll();
            if (current.board.isGoal()) {
                List<Board> solution = new ArrayList<>();
                while (current != null) {
                    solution.add(current.board);
                    current = current.prev;
                }
                Collections.reverse(solution);
                return solution;
            }
            hashboard.add(current.board);
            for (Board neighbor : current.board.getNeighbors()) {
                if (!hashboard.contains(neighbor)) {
                    priority.add(new Node(neighbor, current.times + 1, current));
                }
            }
        }
        return Collections.emptyList();
    }

    private static class Node implements Comparable<Node> {
        Board board;
        int times;
        Node prev;

        public Node(Board board, int moves, Node previous) {
            this.board = board;
            this.times = moves;
            this.prev = previous;
        }

        public int compareTo(Node other) {
            return (this.times + this.board.computeManhattanDistance()) -
                    (other.times + other.board.computeManhattanDistance());
        }
    }
    /*
       Method to initialize the puzzle. Do not delete this method.
    */
    public static Board initializeBoard(String filename) {
        Scanner br;
        try {
            br = new Scanner(new FileReader(filename));
            int boardDimension = br.nextInt();
            int[][] blocks = new int[boardDimension][boardDimension];
            for (int row = 0; row < boardDimension; row++) {
                for (int col = 0; col < boardDimension; col++) {
                    blocks[row][col] = br.nextInt();
                }
            }
            return new Board(blocks);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}


