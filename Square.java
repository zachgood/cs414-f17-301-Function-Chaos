package temp;

public class Square {
	private boolean wall;
	private int x;
	private int y;
	private Piece piece;
	
	public Square(int x, int y){
		this.x = x;
		this.y = y;
		wall = false;
		piece = null;
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
	
	public boolean move(Square dest){
		if(piece == null)
			return false;
		
		switch(piece.getType()){
		case ROOK:
			return moveRook(dest);
			
		case QUEEN:
			return moveQueen(dest);
			
		case KING:
			return moveKing(dest);
			
		default:
			return false;
		}		
	}
	
	private boolean moveRook(Square dest){
		return false;
	}
	
	private boolean moveQueen(Square dest){
		return false;
	}
	
	private boolean moveKing(Square dest){
		return false;
	}
}
