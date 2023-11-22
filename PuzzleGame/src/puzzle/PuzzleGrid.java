package puzzle;

import java.awt.Graphics;
import java.util.Random;

public class PuzzleGrid {
	private PuzzlePiece[][] PuzzleMaze = new PuzzlePiece[5][5]; //declare matrix 5 by 5 of type Puzzle Piece
	private PuzzlePiece TempPuzzlePiece;						//declare temp puzzle piece
	
	public PuzzleGrid() {							//empty puzzle grid constructor 
		
	}
	
	public void setCells(int row, int col, PuzzlePiece value) {	//set puzzle piece at (row,col) position to a value(for example null)
		PuzzleMaze[row][col] = value ; 
	}
	
	public PuzzlePiece getPuzzlePiece(int row, int col) {	//get puzzle piece at (row,col) position
		return PuzzleMaze[row][col];
	}
	
	//draw method iterates through matrix and draws the images of height and width
	//by calling the draw method of Puzzle Piece class
	//draw piece only when the piece is not null/not empty
	public void drawPuzzlePiece(Graphics g){
		
		for(int i=0; i<5; i++) {
			for(int j = 0; j<5; j++) {
				TempPuzzlePiece = PuzzleMaze[i][j];
				if(TempPuzzlePiece!=null)  {
					TempPuzzlePiece.drawPiece(g, TempPuzzlePiece);
				}
			}
		}
	}
	
	//randomize function randomises the puzzle pieces to solve for by iterating from back to front of the matrix
	//generate random position and swap positions of current puzzle piece at (i,j) position with the puzzle piece 
	//at random generated position
	public void Randomize(PuzzleGrid puzzlegrid) {
		
	    Random random = new Random();
	    
	    for (int i = puzzlegrid.PuzzleMaze.length - 1; i > 0; i--) {
	        for (int j = puzzlegrid.PuzzleMaze[i].length - 1; j > 0; j--) {
	            int m = random.nextInt(i + 1);
	            int n = random.nextInt(j + 1);

	            PuzzlePiece temp =	puzzlegrid.PuzzleMaze[i][j];
	            puzzlegrid.PuzzleMaze[i][j] = puzzlegrid.PuzzleMaze[m][n];
	            puzzlegrid.PuzzleMaze[m][n] = temp;
	          
	        }
	    }
	    
	    //this section iterates through the puzzle grid and change the x and y positions of the pieces that the user 
	    //has to solve for, to be 400 y positions under the previous position
	    
	    for(int m=0; m<puzzlegrid.PuzzleMaze.length; m++) {
			for(int n = 0; n<puzzlegrid.PuzzleMaze[m].length; n++) {
				if(puzzlegrid.PuzzleMaze[m][n]!=null) {
	            	puzzlegrid.PuzzleMaze[m][n].setX(m*puzzlegrid.PuzzleMaze[m][n].getWidth());
	            	puzzlegrid.PuzzleMaze[m][n].setY(n*puzzlegrid.PuzzleMaze[m][n].getHeight()+400);
	            }
			}
	    }
	}
	
	//add puzzlepiece to matrix at the assigned/pre-specified row and column positions
	public void AddPuzzlePiece(PuzzlePiece puzzlepiece) {
		PuzzleMaze[puzzlepiece.getRow()][puzzlepiece.getCol()] = puzzlepiece;
	}
	
	//add puzzlepiece to matrix at a specified row and column positions
	public void setPuzzlePiece(int i, int j, PuzzlePiece puzzlepiece) {
		PuzzleMaze[i][j] = puzzlepiece;
	}
}
