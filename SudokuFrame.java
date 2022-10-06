package Sudoku;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class SudokuFrame extends JFrame {
	private JPanel buttonSelectionPanel;
	private SudokuPanel sPanel;
	
	public SudokuFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Sudoku");
		this.setMinimumSize(new Dimension(800,600));
		this.setVisible(true);

		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("Game");
		JMenu newGame = new JMenu("New Game");
		JMenuItem sudokuGame = new JMenuItem("9 By 9 Game");
		sudokuGame.addActionListener(new NewGameListener());

		newGame.add(sudokuGame);
		file.add(newGame);
		menuBar.add(file);
		this.setJMenuBar(menuBar);
		
		JPanel windowPanel = new JPanel();
		windowPanel.setLayout(new FlowLayout());
		windowPanel.setPreferredSize(new Dimension(800,600));
		
		buttonSelectionPanel = new JPanel();
		buttonSelectionPanel.setPreferredSize(new Dimension(90,500));

		sPanel = new SudokuPanel();

		// Add Keyboard Inputs
		this.addKeyListener(sPanel.new SudokuPanelKeyActions());

		// Add The Final Panel to Frame
		windowPanel.add(sPanel);
		windowPanel.add(buttonSelectionPanel);
		this.add(windowPanel);

		rebuildInterface();
	}
	
	public void rebuildInterface() {
		SudokuPuzzle generatedPuzzle = new SudokuGenerator().generateBlankSudoku();
		sPanel.newSudokuPuzzle(generatedPuzzle);
		sPanel.setFontSize(26);
		buttonSelectionPanel.removeAll();
		for(String value : generatedPuzzle.getValidValues()) {
			JButton b = new JButton(value);
			b.setPreferredSize(new Dimension(40,40));
			b.addActionListener(sPanel.new NumActionListener());
			buttonSelectionPanel.add(b);
		}
		buttonSelectionPanel.add(getSolveButton());
		buttonSelectionPanel.add(getClearButton());
		sPanel.repaint();
		buttonSelectionPanel.revalidate();
		buttonSelectionPanel.repaint();
	}
	
	private class NewGameListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			rebuildInterface();
		}
	}

	public JButton getSolveButton() {
		JButton button = new JButton("Solve");
		button.addActionListener(sPanel.new SolveButton());
		return button;
	}
	public JButton getClearButton() {
		JButton button = new JButton("Clear");
		button.addActionListener(sPanel.new ClearAction());
		return button;
	}
}
