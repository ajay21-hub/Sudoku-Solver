package Sudoku;

import java.util.ArrayList;
import java.util.Arrays;

public class Solver {
    private static final int GRID_SIZE = 9;
    private static final int BOX_SIZE = 3;
    private static final ArrayList<Integer> ALLOWED_PARAMETERS = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

    public static void main(String[] args) {

        int[][] dummyBoard = {
                {7, 0, 2, 0, 5, 0, 6, 0, 0},
                {0, 0, 0, 0, 0, 3, 0, 0, 0},
                {1, 0, 0, 0, 0, 9, 5, 0, 0},
                {8, 0, 0, 0, 0, 0, 0, 9, 0},
                {0, 4, 3, 0, 0, 0, 7, 5, 0},
                {0, 9, 0, 0, 0, 0, 0, 0, 8},
                {0, 0, 9, 7, 0, 0, 0, 0, 5},
                {0, 0, 0, 2, 0, 0, 0, 0, 0},
                {0, 0, 7, 0, 4, 0, 2, 0, 3}
        };

        solve(convertIntToString(dummyBoard));

    }
    public static String[][] solve(String[][] board){
        int[][] intBoard = convertStringToInt(board);
        String[][] finalBoard = null;
        printBoard(intBoard);
        if (solveBoard(intBoard)) {
            System.out.println("\nSolved successfully!");
            finalBoard = convertIntToString(intBoard);
        } else {
            System.err.println("Unsolvable board :(");
        }
        printBoard(intBoard);
        return finalBoard;
    }

    public static void printBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("---------------------");
            }
            for (int column = 0; column < GRID_SIZE; column++) {
                int value = board[row][column];
                if (column % 3 == 0 && column != 0)
                    System.out.print("| ");
                if (value == 0)
                    System.out.print("- ");
                else
                    System.out.print(value + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    private static boolean isNumberInRow(int[][] board, int number, int row) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInColumn(int[][] board, int number, int column) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][column] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInBox(int[][] board, int number, int row, int column) {
        int localBoxRow = row - row % BOX_SIZE;
        int localBoxColumn = column - column % BOX_SIZE;

        for (int i = localBoxRow; i < localBoxRow + BOX_SIZE; i++) {
            for (int j = localBoxColumn; j < localBoxColumn + BOX_SIZE; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isValidPlacement(int[][] board, int number, int row, int column) {
        return  !isNumberInRow(board, number, row) &&
                !isNumberInColumn(board, number, column) &&
                !isNumberInBox(board, number, row, column);
    }

    private static int[][] convertStringToInt(String board[][]) {
        int[][] newBoard = new int[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                int value = 0;
                try {
                    value = Integer.parseInt(board[i][j]);
                } catch (NumberFormatException ignored){}
                if (!ALLOWED_PARAMETERS.contains(value)) {
                    newBoard[i][j] = 0;
                } else {
                    newBoard[i][j] = value;
                }
            }
        }
        return newBoard;
    }
    private static String[][] convertIntToString(int[][] board) {
        String[][] newBoard = new String[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (ALLOWED_PARAMETERS.contains(board[i][j])) {
                    newBoard[i][j] = String.valueOf(board[i][j]);
                } else {
                    newBoard[i][j] = "";
                }
            }
        }
        return newBoard;
    }
    public static boolean solveBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++) {
                if (board[row][column] == 0) {
                    for (int numberToTry : ALLOWED_PARAMETERS) {
                        if (isValidPlacement(board, numberToTry, row, column)) {
                            board[row][column] = numberToTry;
                            if (solveBoard(board)) {
                                return true;
                            } else {
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isBoardSolved(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++) {
                int value = board[row][column];
                if (!isValidPlacement(board, value, row, column) && value!=0) {
                    return false;
                }
            }
        }
        return true;
    }

}


