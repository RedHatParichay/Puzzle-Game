package puzzle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PuzzlePanel extends JPanel implements ActionListener {
		
	private static final long serialVersionUID = 1L;
	BufferedImage image;				//load main image
	BufferedImage cutimage;				//temp variable to store part of the image
	PuzzlePiece puzzlepiece;			//temp puzzle piece variable for initializing
	PuzzleGrid displayedpiecesgrid;		//grid to store the displayed pieces in a grid
	PuzzleGrid puzzlepiecesgrid;		//grid to store the pieces the user has to solve for
	Graphics g;							//load graphics
	OptionsPanel optionspanel;			//declare options panel
	MouseHandler mousehandler;			//declare mouse handler
	Timer timer;						//declare timer
	PuzzlePiece SelectedPuzzlePiece;	//declare temp puzzle piece for storing chosen puzzle piece

	boolean GameStarted;				//boolean to see if the game started
	boolean SomePieces;					//boolean to see if the user wants to see some/no pieces
	
	public PuzzlePanel(OptionsPanel optionspanel) {			//constructor requires the options panel
		
		//load puzzle image with exception
		try {
			image = ImageIO.read(new File("images/Wallpaper.png"));
		}
		catch (IOException e) {
			System.out.println("Puzzle image not found");
		}
		
		this.setBackground(Color.black);				//set background
		this.optionspanel = optionspanel;				//declare the options panel in this file as the same one as the function parameters
		optionspanel.puzzlepanel = this;				//set the puzzle panel in the options panel to this panel
		
		mousehandler = new MouseHandler(this);			//set the mouse handler to this panel
		this.addMouseListener(mousehandler);			//add mouse listener
		this.addMouseMotionListener(mousehandler);		//add mouse motion listener
		
		// set puzzlepiece with the puzzlepiece.getWidth(() and puzzlepiece.getHeight() of each part	
		puzzlepiece = new PuzzlePiece(image.getWidth() / 5, image.getHeight() / 5); 		

    	displayedpiecesgrid = new PuzzleGrid();			//initialize displayed pieces grid
		puzzlepiecesgrid = new PuzzleGrid();			//initialize puzzle pieces grid
	
		this.setLayout(null);									//set layout to none
		this.setPreferredSize(new Dimension(660, 830));			//set size of puzzle panel
		this.setFocusable(true);								//set focusable
		this.setVisible(true);									//make the panel appear
		
		startGame(); 											//start timer
	}
	
	//timer to set the screen refresh rate/repaint rate
	public void startGame() {
		timer = new Timer(100, this);
    	timer.start();
	}
	
	public void initGrid() {
		//initialize grid method calls the grid methods to set them all to null
		for(int i=0; i<5; i++) { 										//for 5 rows
			for(int j=0; j<5; j++) {									//for 5 columns
				puzzlepiecesgrid.setCells(i, j, null);
				displayedpiecesgrid.setCells(i, j, null);
			}
		}
		
		if (optionspanel.GameInitialized==true) {
			//it checks if the game has been initialized i.e. the user has chosen an option
			//then splits the image into 25 parts
			for(int i=0; i<5; i++) { 										//for 5 rows
				for(int j=0; j<5; j++) {									//for 5 columns
					
					//if no pieces are to be displayed, add all puzzle pieces to puzzle grid
					//after cutting and setting the pieces
					if (optionspanel.SomePieces==false) {
						puzzlepiece = new PuzzlePiece(i*puzzlepiece.getWidth(), j*puzzlepiece.getHeight()+400, puzzlepiece.getWidth(), puzzlepiece.getHeight(), i, j);
						cutimage = image.getSubimage(i*puzzlepiece.getWidth(), j*puzzlepiece.getHeight(), puzzlepiece.getWidth(), puzzlepiece.getHeight());
						puzzlepiece.setImage(cutimage);
						puzzlepiecesgrid.AddPuzzlePiece(puzzlepiece);
					}
					
					else {		//else if some pieces are to be displayed, 
						
						//add pieces to puzzle grid when i+j is even after cutting and setting the pieces
						if (((i+j)%2==0)) {										
							puzzlepiece = new PuzzlePiece(i*puzzlepiece.getWidth(), j*puzzlepiece.getHeight()+400, puzzlepiece.getWidth(), puzzlepiece.getHeight(), i, j);
							cutimage = image.getSubimage(i*puzzlepiece.getWidth(), j*puzzlepiece.getHeight(), puzzlepiece.getWidth(), puzzlepiece.getHeight());
							puzzlepiece.setImage(cutimage);
							puzzlepiecesgrid.AddPuzzlePiece(puzzlepiece);
						}
						
						else {
							
							//else add other pieces to displayed grid after cutting and setting the pieces
							puzzlepiece = new PuzzlePiece(i*puzzlepiece.getWidth(), j*puzzlepiece.getHeight(), puzzlepiece.getWidth(), puzzlepiece.getHeight(), i, j);
							cutimage = image.getSubimage(i*puzzlepiece.getWidth(), j*puzzlepiece.getHeight(), puzzlepiece.getWidth(), puzzlepiece.getHeight());
							puzzlepiece.setImage(cutimage);
							displayedpiecesgrid.AddPuzzlePiece(puzzlepiece);
						}
					}
				}
			}
			puzzlepiecesgrid.Randomize(puzzlepiecesgrid); 					//randomize puzzle pieces
			optionspanel.GameStarted = true;								//set the game has started to true
		}
	}
	
	//method checks if the move is valid, by checking the id row and id column with the row to be moved to
	//and the column to be moved to
	public boolean IsValidMove(PuzzleMove puzzlemove) {
		
		if(puzzlemove.oldRow == puzzlemove.newRow && puzzlemove.newCol == puzzlemove.oldCol) {
			return true;
		}
		return false;
	}
	
	//method moves the puzzle piece to new row and column and stores the new row and column where it was moved to
	public void makePuzzleMove(PuzzleMove puzzlemove) {
		
		displayedpiecesgrid.setPuzzlePiece(puzzlemove.newRow, puzzlemove.newCol, puzzlemove.puzzlepiece);
		
		puzzlemove.puzzlepiece.setX(puzzlemove.newRow * puzzlemove.puzzlepiece.getWidth());
		puzzlemove.puzzlepiece.setY(puzzlemove.newCol * puzzlemove.puzzlepiece.getHeight());
		
	}
	
	//paint method paints the screen after the game has started
	public void paint(Graphics g) {
		
		if (optionspanel.GameStarted==true) {
			
			displayedpiecesgrid.drawPuzzlePiece(g);		//draws all pieces from the displayed pieces grid
			puzzlepiecesgrid.drawPuzzlePiece(g);		//draws all pieces from the puzzle pieces grid
			
			//draw swhite grid
			for(int i=1; i<6; i++) {
				g.setColor(Color.white);
				g.drawLine(i*puzzlepiece.getWidth(), 0, i*puzzlepiece.getWidth(), 5*puzzlepiece.getHeight());
			}
			
			for(int j=1; j<6; j++) {
				g.setColor(Color.white);
				g.drawLine(0, j*puzzlepiece.getHeight(), 5*puzzlepiece.getWidth(), j*puzzlepiece.getHeight());
			}
			
			
			int PiecesInPlace = 0;			//count to check how many pieces are in place
			
			//iterate the puzzle pieces grid and if a piece at a certain position is null(immovable), then increment
			for(int i = 0; i<5; i++) {
				for(int j=0; j<5; j++) {
					
					if (puzzlepiecesgrid.getPuzzlePiece(i, j) == null) {
						PiecesInPlace++;
					}
				}
			}
			
			//end game after all 25 pieces have been set
			if(PiecesInPlace==25) {
				GameOver(g);
			}	
			
		}
		
	}
	
	//game over method is called when the game is over, to set background and display win text
	public void GameOver(Graphics g) {
			super.paint(g);
		
	    	g.setColor(Color.red);
	    	g.setFont(new Font("Times New Roman", Font.BOLD, 40));
	    	
	    	
	    	g.drawString("YOU WIN!",this.getWidth()/3, this.getHeight()/3);
	    	
	    	
	    	g.setColor(Color.red);
	    	g.setFont(new Font("Times New Roman", Font.BOLD, 40));
	    	g.drawString("GAME OVER! ", 200, this.getHeight()/2);
	    }
	
	//if anything happens on the screen, repaint the screen
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
}

