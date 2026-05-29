package misc;

public class MT {
    /*
        Question 1
     */
    public static boolean canReach(String s, int p) {
        int count = 0;
        int size = 1;
        char last = ' ';
        for (char character : s.toCharArray()) { //iterates through array
            if (character == last) {
                size *= 2;
            } else {
                size = 1;
            }
            if (character == 'F') { //assume F and R are the only char in sequence
                count += size;
            } else if (character == 'R') {
                count -= size;
            }
            last = character;
        }
        return count == p;
    }

    /*
        Question 2:
     */
    public static void tilt(int[][] gameBoard) {
        int rows = gameBoard.length; //initialize row and theCol
        int theCol = gameBoard[0].length;
        for (int col = 0; col < theCol; col++) { //iterates every column
            int emptyRow = 0;
            for (int row = 0; row < rows; row++) { //iterates every row
                if (gameBoard[row][col] != -1) {
                    if (emptyRow != row) { //if not empty, change the values to row col
                        gameBoard[emptyRow][col] = gameBoard[row][col];
                        gameBoard[row][col] = -1; //set rowcol to empty
                    }
                    emptyRow++;
                }
            }

            for (int row = 0; row < emptyRow - 1; row++) {
                if (gameBoard[row][col] == gameBoard[row + 1][col]) {
                    gameBoard[row][col] *= 2; //double the initial number
                    for (int theRow = row + 1; theRow < rows - 1; theRow++) {
                        gameBoard[theRow][col] = gameBoard[theRow + 1][col];
                    }
                    gameBoard[rows - 1][col] = -1;
                    emptyRow--;
                }
            }
        }
    }

    /*
        Question 4
     */
    public static boolean correctSum(int[] A, int[] B, int[] C) {
        int lengthA = A.length; //initialize all lengths and values
        int lengthB = B.length;
        int lengthC = C.length;
        int ans = 0;
        int i = lengthA - 1;
        int j = lengthB - 1;
        int m = lengthC - 1;
        while (m >= 0) {
            int sum = ans;
            if (i >= 0){
                sum += A[i--];
            }
            if (j >= 0){
                sum += B[j--];
            }
            if (C[m--] != sum % 10) {
                return false;
            }
            ans = sum / 10;
        }
        return ans == 0;
    }
}