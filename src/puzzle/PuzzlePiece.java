package puzzle;

import java.awt.Graphics;
import java.awt.Image;

public class PuzzlePiece {				//create puzzle piece class
	
	private int x;						//x position of puzzle piece on grid
	private int y;						//y position of puzzle piece on grid
	private int width;					//width of puzzle piece on grid
	private int height;					//height of puzzle piece on grid
	private Image image;				//image of puzzle piece on grid
	private int row;					//correct row (in terms of puzzle grid) of puzzle piece on grid
	private int col;					//correct column (in terms of puzzle grid) of puzzle piece on grid
	
	
	public PuzzlePiece(int width, int height) {		//constructor for temporary puzzle pieces to set width and height
		this.width = width;							
		this.height = height;
	}
	
	public PuzzlePiece(int x, int y, int width, int height, int row, int col) {
		
		//constructor to initialize every puzzle piece with all the attributes, where row and col act as the id to check later for
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.row = row;
		this.col = col;
	}
	
	
	//getters/accessors for width, height, x, y, row, column and image
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getCol() {
		return this.col;
	}
	
	public Image getImage() {
		return this.image;
	}
	
	//setters/mutators for width, height, x, y, row, column and image
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public void setCol(int col) {
		this.col = col;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
	
	
	//draw method to draw specific puzzle piece in the right position with the right width and height
	public void drawPiece(Graphics g, PuzzlePiece puzzlepiece) {
		g.drawImage(puzzlepiece.getImage(), puzzlepiece.getX(), puzzlepiece.getY(), puzzlepiece.getWidth(), puzzlepiece.getHeight(), null);
	}
}
