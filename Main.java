import java.util.Random;
import java.util.Scanner;

public class Main {
    //Create input stream
    protected static final Scanner scanner = new Scanner(System.in);

    //Random generator
    protected static Random random = new Random();

    private static int[][] setBoardSize(int rows, int columns) {
        return new int[rows][columns];
    }

    //checks if the current square is empty
    private static boolean isEmpty(int board[][], int row, int column) {
        if (board[row][column] == 0)
            return true;
        return false;
    }

    private static boolean isFull(int board[][]) {
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++)
                if (board[i][j] == 0)
                    return false;
        return true;
    }

    private static String playerInfo() {
        System.out.print("Please tell me your name: ");
        return scanner.next();
    }

    private static String botInfo() {
        String names[] = {"Alabama", "A2", "djangospel", "MVP"};
        return names[random.nextInt(4)];
    }

    private static int[][] playerMove(int board[][]) {
        System.out.print("Please select a square: ");

        while (true) {
            int row = scanner.nextInt();
            int column = scanner.nextInt();

            if (isEmpty(board, row, column)) {
                board[row][column] = 1;
                break;
            }

            System.out.print("That square is full, choose another square: ");
        }

        return board;
    }

    private static int[][] botMove(int board[][]) {
        while (true) {
            int row = random.nextInt(3);
            int column = random.nextInt(3);

            if (isEmpty(board, row, column)) {
                board[row][column] = 2;
                break;
            }
        }

        return board;
    }

    private static void printBoard(int board[][]) {
        System.out.println("-------");

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 1)
                    System.out.print("|X");
                else if (board[i][j] == 2)
                    System.out.print("|O");
                else
                    System.out.print("| ");
            }
            System.out.println("|");
            System.out.println("-------");
        }
    }

    private static boolean checkEndCondition(int board[][], int signature, String name) {
        //I stg this is painful

        //checks rows
        if (board[0][0] == signature && board[0][1] == signature && board[0][2] == signature)
            return true;
        if (board[1][0] == signature && board[1][1] == signature && board[1][2] == signature)
            return true;
        if (board[2][0] == signature && board[2][1] == signature && board[2][2] == signature)
            return true;

        //checks columns
        if (board[0][0] == signature && board[1][0] == signature && board[2][0] == signature)
            return true;
        if (board[0][1] == signature && board[1][1] == signature && board[2][1] == signature)
            return true;
        if (board[0][2] == signature && board[1][2] == signature && board[2][2] == signature)
            return true;

        //checks diagonals
        if (board[0][0] == signature && board[1][1] == signature && board[2][2] == signature)
            return true;
        if (board[0][2] == signature && board[1][1] == signature && board[2][0] == signature)
            return true;

        return false;
    }

    private static void announce(String name) {
        System.out.println(name + " wins tic tac toe!");
    }

    private static void gameLoop(String pName, String bName, int board[][]) {
        System.out.println("Player " + bName + " would like to challenge you to a game of Tic tac toe.");

        while (true) {
            printBoard(board);

            System.out.println("Your turn, " + pName + ".");
            board = playerMove(board);

            if (checkEndCondition(board, 1, pName)) {
                announce(pName);
                break;
            }

            if (isFull(board)) {
                System.out.println("The game ends in a draw!");
                break;
            }

            System.out.println("It's " + bName + "'s turn.");
            board = botMove(board);

            if (checkEndCondition(board, 2, bName)) {
                announce(bName);
                break;
            }
        }
        
        printBoard(board);
    }

    public static void main(String[] args) {
        //X - 1
        //O - 2
        System.out.println("Welcome to Tic tac toe!");
        gameLoop(playerInfo(), botInfo(), setBoardSize(3,3));
        System.out.println("Thanks for playing!");
        scanner.close();
    }
}
