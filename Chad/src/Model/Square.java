package Model;

public class Square {
	private boolean wall;
	private boolean whiteCastle;
	private boolean blackCastle;
	private int x;
	private int y;
	private Piece piece;
	
	public Square(int y, int x){
		this.x = x;
		this.y = y;
		wall = false;
		whiteCastle = false;
		blackCastle = false;
		piece = null;
	}
	
	public Square(Square s){
		x = s.x;
		y = s.y;
		wall = s.wall;
		whiteCastle = s.whiteCastle;
		blackCastle = s.blackCastle;
		if(s.piece != null)
			piece = s.piece;
		else
			piece = null;
	}
	
	//turns this square into a copy of the parameter square
	//essentially the same as copy constructor without new allocation
	public void copy(Square s){
		x = s.x;
		y = s.y;
		wall = s.wall;
		whiteCastle = s.whiteCastle;
		blackCastle = s.blackCastle;
		piece = s.piece;
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