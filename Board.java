package temp;

public class Board {
	// 0,0 is the top left of the board
	private Square[][] squares;
	
	public Board(){
		squares = new Square[12][12];
		initialize();
	}
	
	private void initialize(){
		for(int y = 0; y < 12; y++){
			for(int x = 0; x < 12; x++){
				squares[x][y] = new Square(x,y);
			}
		}
		setWalls();
		initPieces();
	}
	
	private void initPieces(){
		
	}
	
	public Square getSquare(int x, int y){
		return squares[y][x];
	}
	
	private void setWalls(){
		//black castle
		//top wall
		for(int i = 0; i < 3; i++)
			squares[1][7+i].makeWall();
		
		//left wall
		for(int i = 0; i < 3; i++)
			squares[2+i][6].makeWall();
		
		//right wall
		for(int i = 0; i < 3; i++)
			squares[2+i][10].makeWall();
		
		//bottom wall
		for(int i = 0; i < 3; i++)
			squares[5][7+i].makeWall();
		
		
		//white castle
		//top wall
		for(int i = 0; i < 3; i++)
			squares[6][2+i].makeWall();
		
		//left wall
		for(int i = 0; i < 3; i++)
			squares[7+i][1].makeWall();
		
		//right wall
		for(int i = 0; i < 3; i++)
			squares[7+i][5].makeWall();
		
		//bottom wall
		for(int i = 0; i < 3; i++)
			squares[10][2+i].makeWall();
	}
}
