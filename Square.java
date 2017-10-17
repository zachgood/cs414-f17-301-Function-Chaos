package temp;

public class Square {
	private boolean wall;
	private boolean whiteCastle;
	private boolean blackCastle;
	private int x;
	private int y;
	private Piece piece;
	
	public Square(int x, int y){
		this.x = x;
		this.y = y;
		wall = false;
		whiteCastle = false;
		blackCastle = false;
		piece = null;
	}
	
	public void setWhiteCastle(){
		whiteCastle = true;
	}
	
	public void setBlackCastle(){
		blackCastle = true;
	}
	
	public boolean isWhiteCastle(){
		return whiteCastle;
	}
	
	public boolean isBlackCastle(){
		return blackCastle;
	}
	
	public Piece getPiece(){
		return piece;
	}
	
	public void setPiece(Piece piece){
		this.piece = piece;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void makeWall(){
		wall = true;
	}
	
	public boolean isWall(){
		return wall;
	}
	
}
