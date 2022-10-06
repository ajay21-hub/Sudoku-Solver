package Sudoku;

public class SolverAlgorithm {
    private static final int BOX_SIZE = 3;

    public static void main(String[] args) {

        String[][] dummyBoard = {
                {"7", "0", "2", "0", "5", "0", "6", "0", "0"},
                {"0", "0", "0", "0", "0", "3", "0", "0", "0"},
                {"1", "0", "0", "0", "0", "9", "5", "0", "0"},
                {"8", "0", "0", "0", "0", "0", "0", "9", "0"},
                {"0", "4", "3", "0", "0", "0", "7", "5", "0"},
                {"0", "9", "0", "0", "0", "0", "0", "0", "8"},
                {"0", "0", "9", "7", "0", "0", "0", "0", "5"},
                {"0", "0", "0", "2", "0", "0", "0", "0", "0"},
                {"0", "0", "7", "0", "4", "0", "2", "0", "3"}
        };

        solve(dummyBoard);

    }
    public static String[][] solve(String[][] board){
        String[][] finalBoard = null;
        printBoard(board);
        if (solveBoard(board)) {
            System.out.println("\nSolved successfully!");
            finalBoard = board;
        } else {
            System.err.println("Unsolvable board :(");
        }
        printBoard(board);
        return finalBoard;
    }

    public static void printBoard(String[][] board) {
        for (int row = 0; row < SudokuPuzzleType.PARAMETERS.getRows(); row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("---------------------");
            }
            for (int column = 0; column < SudokuPuzzleType.PARAMETERS.getColumns(); column++) {
                String value = board[row][column];
                if (column % 3 == 0 && column != 0)
                    System.out.print("| ");
                if (!SudokuPuzzleType.PARAMETERS.getValidValuesAsArrayList().contains(value))
                    System.out.print("- ");
                else
                    System.out.print(value + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    private static boolean isNumberInRow(String[][] board, String number, int row) {
        for (int i = 0; i < SudokuPuzzleType.PARAMETERS.getRows(); i++) {
            if (board[row][i].equals(number)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInColumn(String[][] board, String number, int column) {
        for (int i = 0; i < SudokuPuzzleType.PARAMETERS.getColumns(); i++) {
            if (board[i][column].equals(number)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInBox(String[][] board, String number, int row, int column) {
        int localBoxRow = row - row % BOX_SIZE;
        int localBoxColumn = column - column % BOX_SIZE;

        for (int i = localBoxRow; i < localBoxRow + BOX_SIZE; i++) {
            for (int j = localBoxColumn; j < localBoxColumn + BOX_SIZE; j++) {
                if (board[i][j].equals(number)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isValidPlacement(String[][] board, String number, int row, int column) {
        return  !isNumberInRow(board, number, row) &&
                !isNumberInColumn(board, number, column) &&
                !isNumberInBox(board, number, row, column);
    }

    public static boolean solveBoard(String[][] board) {
        for (int row = 0; row < SudokuPuzzleType.PARAMETERS.getRows(); row++) {
            for (int column = 0; column < SudokuPuzzleType.PARAMETERS.getColumns(); column++) {
                if (!SudokuPuzzleType.PARAMETERS.getValidValuesAsArrayList().contains(board[row][column])) {
                    for (String numberToTry : SudokuPuzzleType.PARAMETERS.getValidValues()) {
                        if (isValidPlacement(board, numberToTry, row, column)) {
                            board[row][column] = numberToTry;
                            if (solveBoard(board)) {
                                return true;
                            } else {
                                board[row][column] = "0";
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }


}


