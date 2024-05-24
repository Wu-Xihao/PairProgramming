// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class GameOfLifeSample {
    private static final int SIZE = 10; // 定义网格大小
    private static final int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},         {0, 1},
            {1, -1}, {1, 0}, {1, 1}
    };

    public static void main(String[] args) {
        int[][] board = new int[SIZE][SIZE];

        // 初始化游戏板，设置一些初始状态
        board[1][2] = 1;
        board[2][3] = 1;
        board[3][1] = 1;
        board[3][2] = 1;
        board[3][3] = 1;

        System.out.println("初始状态:");
        printBoard(board);

        for (int i = 0; i < 10; i++) { // 演化10代
            board = nextGeneration(board);
            System.out.println("第 " + (i + 1) + " 代:");
            printBoard(board);
        }
    }

    private static int[][] nextGeneration(int[][] board) {
        int[][] newBoard = new int[SIZE][SIZE];

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                int liveNeighbors = countLiveNeighbors(board, row, col);

                if (board[row][col] == 1) {
                    if (liveNeighbors < 2 || liveNeighbors > 3) {
                        newBoard[row][col] = 0; // 死亡
                    } else {
                        newBoard[row][col] = 1; // 继续存活
                    }
                } else {
                    if (liveNeighbors == 3) {
                        newBoard[row][col] = 1; // 复活
                    }
                }
            }
        }

        return newBoard;
    }

    private static int countLiveNeighbors(int[][] board, int row, int col) {
        int liveNeighbors = 0;

        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];

            if (newRow >= 0 && newRow < SIZE && newCol >= 0 && newCol < SIZE) {
                liveNeighbors += board[newRow][newCol];
            }
        }

        return liveNeighbors;
    }

    private static void printBoard(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                System.out.print(board[row][col] == 1 ? "O" : ".");
            }
            System.out.println();
        }
        System.out.println();
    }
}