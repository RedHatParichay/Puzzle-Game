package puzzle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class OptionsPanel extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	String[] options = {"Pieces", "Some", "None"};	//options to display in the options drop down menu
	JComboBox comboBox;								//declare combo box
	PuzzlePanel puzzlepanel;						//declare the puzzle panel
	JButton StartButton;							//declare the start button
	String SelectedText;							//declare selected text which hold the text from the combo box which the user has chosen
	boolean GameInitialized;						//boolean to check if the game has been initialized 
	boolean GameStarted;							//boolean to check if the game has started 
	boolean SomePieces;								//boolean to check if the user wants some/no pieces on the board
	
	public OptionsPanel() {							//options panel constructor
		
		//initialize boolean variables
		GameInitialized = false;
		GameStarted = false;
		SomePieces = false;
		
		//create and set visual layout of the drop down menu
		comboBox = new JComboBox(options);		
		comboBox.setSelectedIndex(0);				//choose default text to display
		comboBox.addActionListener(this);			//detect user movement on this component
		comboBox.setBounds(this.getWidth()/2+13, this.getHeight()/2+125, 200, 200);
		
		//create and set visual layout of start button
		StartButton = new JButton();
		StartButton.setText("START");
		StartButton.addActionListener(this);		//detect user movement on this component
		StartButton.setBounds(this.getWidth()/2+13, this.getHeight()/2+350, 200, 50);
		
		//set visual layout, add components
		this.setLayout(null);
		this.add(comboBox);
		this.add(StartButton);
		this.setPreferredSize(new Dimension(226, 830));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {			//to check if the user has done something on the options panel
		
		if (e.getSource()==comboBox) {						//if the combo box has been changed/options has been chosen
			SelectedText = comboBox.getSelectedItem().toString();		//get text of chosen option
		}
	
		if (e.getSource()==StartButton) {					//if the user has pressed the start button

			if (SelectedText=="Some") {						//if the user has selected some pieces to appear on the board
				GameInitialized = true;						//the game has been initialized
				SomePieces = true;							//some pieces should be displayed on the board
				puzzlepanel.initGrid();						//call initialize grid method in the puzzle panel
			}
			
			if (SelectedText=="None") {						//if the user has selected no pieces to appear on the board
				GameInitialized = true;						//the game has been initialized
				SomePieces = false;							//no pieces should be displayed on the board
				puzzlepanel.initGrid();						//call initialize grid method in the puzzle panel
			}	
		}	
	}	
}
