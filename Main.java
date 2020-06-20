import java.util.Random;
import java.util.Scanner;

public class Main {
    //Create input stream
    protected static final Scanner scanner = new Scanner(System.in);

    //Random generator
    protected static Random random = new Random();
    private static int range = 3;

    private static int[][] setBoardSize(int size) {
        range = size;
        return new int[size][size];
    }

    //checks if the current square is empty
    private static boolean isEmpty(int board[][], int row, int column) {
        if (board[row][column] == 0)
            return true;
        return false;
    }

    private static boolean isFull(int board[][]) {
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board.length; j++)
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
            int row = random.nextInt(range);
            int column = random.nextInt(range);

            if (isEmpty(board, row, column)) {
                board[row][column] = 2;
                break;
            }
        }

        return board;
    }

    private static void printBoard(int board[][]) {
        System.out.println();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 1)
                    System.out.print("|X");
                else if (board[i][j] == 2)
                    System.out.print("|O");
                else
                    System.out.print("| ");
            }
            System.out.println("|");
            System.out.println();
        }
    }

    private static boolean checkEndCondition(int board[][], int signature, String name) {
       int sigCount = 0;

       //check rows
       for (int row = 0; row < board.length; ++row) {
            for (int column = 0; column < board.length; ++column) {
                if (board[row][column] == signature) {
                    ++sigCount;
                }

                if (sigCount == board.length) {
                    return true;
                }
            }

            sigCount = 0;            
       }

       //check columns
        for (int row = 0; row < board.length; ++row) {
            for (int column = 0; column < board.length; ++column) {
                if (board[column][row] == signature) {
                    ++sigCount;
                }

                if (sigCount == board.length) {
                    return true;
                }
            }

            sigCount = 0;
       }       

       //check diagonal left
       for (int pos = 0; pos < board.length; ++pos) {
            if (board[pos][pos] == signature) {
                ++sigCount;
            }
       }


       if (sigCount == board.length) {
            return true;
       }

       sigCount = 0;

       //check diagonal right
       for (int row = 0, column = board.length - 1; row < board.length; ++row, --column) {
            if (board[row][column] == signature) {
                ++sigCount;
            }
       }

        if (sigCount == board.length) {
            return true;
       }

       sigCount = 0;       

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
        System.out.print("Choose your board size: ");
        int size = scanner.nextInt();
        gameLoop(playerInfo(), botInfo(), setBoardSize(size));
        System.out.println("Thanks for playing!");
        scanner.close();
    }
}
