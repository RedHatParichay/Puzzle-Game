package puzzle;

public class PuzzleMove {			//create puzzle move class to work with the movement of pieces in terms of rows and columns
	
	int oldRow;					//old row, the row where the piece belongs to 
	int oldCol; 				//old column, the column where the piece belongs to
	int newRow;					//new row, the row where the user has pressed the mouse at
	int newCol;					//new column, the column where the user has pressed the mouse at
	
	PuzzlePiece puzzlepiece;	//create a puzzle piece variable
	
	public PuzzleMove(PuzzlePanel puzzlepanel, PuzzlePiece puzzlepiece, int newRow, int newCol) {
		//constructor that used whenever a user tries to move a puzzle piece
		//the old row and column are accessed and new row and column are mutated
		
		this.oldRow = puzzlepiece.getRow();
		this.oldCol = puzzlepiece.getCol();
		this.newRow = newRow;
		this.newCol = newCol;
		this.puzzlepiece = puzzlepiece;
	}
}
