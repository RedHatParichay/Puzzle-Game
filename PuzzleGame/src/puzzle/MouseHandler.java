package puzzle;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {	//create class to detect mouse movement

	PuzzlePanel puzzlepanel;				//create puzzle panel variable			
	int PressedRow;							//to store which row the user has selected
	int PressedCol;							//to store which column the user has selected
	
	public MouseHandler(PuzzlePanel puzzlepanel) {		//constructor to set the puzzle panel to work with 
		this.puzzlepanel = puzzlepanel;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {			//work with mouse pressing
		
		//if the user is pressing a puzzle piece not in the displayed pieces, then
		//get an integer value for the row and column where the mouse was pressed according the tile width and height
		//use a temp puzzle piece variable that contains the relevant piece from the puzzle piece matrix
		//if the piece is not null, set it as the selected puzzle piece to validate for
		
		if(e.getY()>400) {
			PressedRow = e.getX()/puzzlepanel.puzzlepiece.getWidth();
			PressedCol = (e.getY()-400)/puzzlepanel.puzzlepiece.getHeight();
			
			PuzzlePiece pieceXY = puzzlepanel.puzzlepiecesgrid.getPuzzlePiece(PressedRow, PressedCol);
			if (pieceXY!=null) {
				puzzlepanel.SelectedPuzzlePiece = pieceXY;
			}
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {			//work with mouse dragging
		
		//if the chosen piece is not null, then set the x and y positions according to user movement and centre of puzzle piece dimensions
		
		if (puzzlepanel.SelectedPuzzlePiece!=null)  {		
			puzzlepanel.SelectedPuzzlePiece.setX(e.getX() - puzzlepanel.puzzlepiece.getWidth()/2);
			puzzlepanel.SelectedPuzzlePiece.setY(e.getY() - puzzlepanel.puzzlepiece.getHeight()/2);
		
		}
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {				//work with mouse releasing
		
		//get an integer value for the row and column where the mouse was released according the tile width and height
		int ReleasedRow = e.getX()/puzzlepanel.puzzlepiece.getWidth();
		int ReleasedCol = e.getY()/puzzlepanel.puzzlepiece.getHeight();
		
		//if the piece that was selected is not null, then
		//initialize a puzzle move
		//check if the move is valid/correct
		//if it is, then make the move and set the puzzle piece as immovable
		//or else set x and y positions of the puzzle piece according to tile width and height
		
		if (puzzlepanel.SelectedPuzzlePiece!=null)  {
			
			PuzzleMove puzzlemove = new PuzzleMove(puzzlepanel, puzzlepanel.SelectedPuzzlePiece, ReleasedRow, ReleasedCol);
			
			if (puzzlepanel.IsValidMove(puzzlemove)) {
				puzzlepanel.makePuzzleMove(puzzlemove);
				puzzlepanel.puzzlepiecesgrid.setPuzzlePiece(PressedRow, PressedCol, null);
			}
			else {
				puzzlepanel.SelectedPuzzlePiece.setX(PressedRow * puzzlepanel.puzzlepiece.getWidth());
				puzzlepanel.SelectedPuzzlePiece.setY((PressedCol * puzzlepanel.puzzlepiece.getHeight())+400);
			}
		}
		
		//at the end, set the selected piece to default value of null and repaint the screen
		
		puzzlepanel.SelectedPuzzlePiece = null;
		
		puzzlepanel.repaint();
		
	}
}
