package puzzle;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;

public class PuzzleFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	PuzzlePanel puzzlepanel;	//declare the puzzle pieces panel
	OptionsPanel optionspanel;	//declare the options panel
		
	public PuzzleFrame() {								//constructor to initialize the frame
		optionspanel = new OptionsPanel();				//call constructor to initialize options panel
		puzzlepanel = new PuzzlePanel(optionspanel);	//call constructor to initialize puzzle panel with the options panel
		this.setTitle("Puzzle Game");					//set title of window to Puzzle Game
		this.setResizable(false);						//stop user from resizing the window
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);	//when window closed, exit program
		this.setLayout(new FlowLayout());				//to allow options panel on the right of puzzle panel
		this.add(puzzlepanel);							//add puzzle panel to frame
		this.add(optionspanel);							//add options panel to frame
		this.pack();									//sizes the frame to preferred size	
		this.setSize(new Dimension(900, 830));			//set size of frame
		this.setLocationRelativeTo(null);				
		this.setVisible(true);							//make frame visible
	}
}