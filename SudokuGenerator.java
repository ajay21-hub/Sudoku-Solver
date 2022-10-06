package Sudoku;

public class SudokuGenerator {
	public SudokuPuzzle generateBlankSudoku() {
		SudokuPuzzleType puzzleType = Sudoku.SudokuPuzzleType.PARAMETERS;
		SudokuPuzzle puzzle = new SudokuPuzzle(puzzleType.getRows(), puzzleType.getColumns(), puzzleType.getBoxWidth(), puzzleType.getBoxHeight(), puzzleType.getValidValues());
		return puzzle;
	}
}
