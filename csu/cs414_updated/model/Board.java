package edu.csu.cs414.model;

public class Board {
	// 0,0 is the top left of the board, 11,0 is bottom left, 0,11 is top right, 11,11 is bottom right
	private Square[][] squares;
	private Square blackKing;
	private Square whiteKing;
	private boolean whiteCheck;
	private boolean blackCheck;
	private String winner;
	
	public Board(){
		squares = new Square[12][12];
		whiteCheck = false;
		blackCheck = false;
		winner = null;
		initialize();
	}
	
	public Square getWhiteKing(){
		return whiteKing;
	}
	
	public Square getBlackKing(){
		return blackKing;
	}
	
	public String getWinner(){
		return winner;
	}
	
	private void initialize(){
		for(int y = 0; y < 12; y++){
			for(int x = 0; x < 12; x++){
				squares[y][x] = new Square(y,x);
			}
		}
		setWalls();
		initPieces();
	}
	
	private void initPieces(){
		//black pieces
		for(int i = 0; i < 3; i ++){
			squares[2][7+i].setPiece(new Piece(PieceType.ROOK,false));
			squares[2][7+i].setBlackCastle();
		}
		squares[3][7].setPiece(new Piece(PieceType.ROOK,false));
		squares[3][7].setBlackCastle();
		squares[3][8].setPiece(new Piece(PieceType.KING,false));
		squares[3][8].setBlackCastle();
		blackKing = squares[3][8];
		squares[3][9].setPiece(new Piece(PieceType.ROOK,false));
		squares[3][9].setBlackCastle();
		for(int i = 0; i < 3; i ++){
			squares[4][7+i].setPiece(new Piece(PieceType.ROOK,false));
			squares[4][7+i].setBlackCastle();
		}
		
		//white pieces
		for(int i = 0; i < 3; i ++){
			squares[7][2+i].setPiece(new Piece(PieceType.ROOK,true));
			squares[7][2+i].setWhiteCastle();
		}
		squares[8][2].setPiece(new Piece(PieceType.ROOK,true));
		squares[8][2].setWhiteCastle();
		squares[8][3].setPiece(new Piece(PieceType.KING,true));
		squares[8][3].setWhiteCastle();
		whiteKing = squares[8][3];
		squares[8][4].setPiece(new Piece(PieceType.ROOK,true));
		squares[8][4].setWhiteCastle();
		for(int i = 0; i < 3; i ++){
			squares[9][2+i].setPiece(new Piece(PieceType.ROOK,true));
			squares[9][2+i].setWhiteCastle();
		}
	}
	
	public Square getSquare(int y, int x){
		return squares[y][x];
	}
	
	private void setWalls(){
		//black castle
		//top wall
		for(int i = 0; i < 3; i++){
			squares[1][7+i].makeWall();
			squares[1][7+i].setBlackCastle();
		}
		
		//left wall
		for(int i = 0; i < 3; i++){
			squares[2+i][6].makeWall();
			squares[2+i][6].setBlackCastle();
		}
		
		//right wall
		for(int i = 0; i < 3; i++){
			squares[2+i][10].makeWall();
			squares[2+i][10].setBlackCastle();
		}
		
		//bottom wall
		for(int i = 0; i < 3; i++){
			squares[5][7+i].makeWall();
			squares[5][7+i].setBlackCastle();
		}
		
		
		//white castle
		//top wall
		for(int i = 0; i < 3; i++){
			squares[6][2+i].makeWall();
			squares[6][2+i].setWhiteCastle();
		}
		
		//left wall
		for(int i = 0; i < 3; i++){
			squares[7+i][1].makeWall();
			squares[7+i][1].setWhiteCastle();
		}
		
		//right wall
		for(int i = 0; i < 3; i++){
			squares[7+i][5].makeWall();
			squares[7+i][5].setWhiteCastle();
		}
		
		//bottom wall
		for(int i = 0; i < 3; i++){
			squares[10][2+i].makeWall();
			squares[10][2+i].setWhiteCastle();
		}
	}
	
	//returns true if the move is valid and the piece is successfully moved
	//returns false if the move is not valid. No pieces will be moved
	public boolean move(Square src, Square dest){
		Piece piece = src.getPiece();
		boolean capKing = false;
		if(dest.getPiece() != null && dest.getPiece().getType() == PieceType.KING)
			capKing = true;
		boolean result = false;
		
		if(piece == null)
			return false;
		
		switch(piece.getType()){
		case ROOK:
			result = moveRook(src, dest);
			break;
			
		case QUEEN:
			result = moveQueen(src, dest);
			break;
			
		case KING:
			result = moveKing(src, dest);
		}
		
//		if(result){
//			checkMate(dest.getPiece().isWhite());
//		}
		
		if(result && capKing){
			declareWinner(dest.getPiece().isWhite());
		}
		return result;
	}
	
	public boolean move(int srcY, int srcX, int destY, int destX){
		return move(squares[srcY][srcX], squares[destY][destX]);
	}
	
	
	
	//true parameter means to test the checkmate status of black. False means test white
	private void checkMate(boolean testBlack){
		if((testBlack && blackCheck) || (!testBlack && whiteCheck)){		
			Square king;
			if(testBlack)
				king = blackKing;
			else
				king = whiteKing;
			
			boolean hCheck = inHorizontalCheck(king);
			boolean vCheck = inVerticalCheck(king);
			boolean dCheck = inDiagonalCheck(king);
			
			if(canMoveKingOutOfCheck(king))
				return;
			int checkNum = 0;
			if(hCheck) checkNum++;
			if(vCheck) checkNum++;
			if(dCheck) checkNum++;
			
			//can only block one piece with a single move. So if you can't move the king
			//and you're in check multiple ways, that's a guarenteed checkmate
			if(checkNum > 1){
				declareWinner(testBlack);
				return;
			}
			
			if(hCheck){
				
			}
		}
	}
	
	private boolean canBlockHorizontal(Square king){
		boolean white = king.getPiece().isWhite();
		return false;
	}
	
	private boolean canMoveKingOutOfCheck(Square king){
		boolean white = king.getPiece().isWhite();
		if(white){
			for(int y = 7; y < 10; y++){
				for(int x = 2; x < 5; x++){
					if(validKingMove(king, squares[y][x]))
						return true;
				}
			}
			return false;
		}else{
			for(int y = 2; y < 5; y++){
				for(int x = 7; x < 10; x++){
					if(validKingMove(king,squares[y][x]))
						return true;
				}
			}
			return false;
		}
	}
	
	private void declareWinner(boolean whiteWins){
		if(whiteWins)
			winner = "White";
		else
			winner = "Black";
	}
	
	private boolean isValidMove(Square src, Square dest){
		Piece piece = src.getPiece();
		if(piece == null)
			return false;
		
		switch(piece.getType()){
		case ROOK:
			return validRookMove(src, dest);
			
		case QUEEN:
			return validQueenMove(src, dest);
			
		case KING:
			return validKingMove(src, dest);
			
		default:
			return false;
		}
	}
	
	private boolean moveRook(Square src, Square dest){
		if(validRookMove(src,dest)){
			dest.setPiece(src.getPiece());
			src.setPiece(null);
			promote(dest);
			//testIfMoveCausedCheck(dest);				
			return true;
		}else
			return false;
	}
	
	private void testIfMoveCausedCheck(Square dest){
		if(dest.getPiece().isWhite()){
			if(isValidMove(dest,blackKing)){
				blackCheck = true;
			}
		}else{
			if(isValidMove(dest,whiteKing))
				whiteCheck = true;
		}
	}
	
	private void promote(Square square){
		Piece piece = square.getPiece();
		if(piece.isWhite() && square.isBlackCastle() && !square.isWall())
			piece.promote();
		else if(!piece.isWhite() && square.isWhiteCastle() && !square.isWall())
			piece.promote();
	}
	
	private boolean validRookMove(Square src, Square dest){
		if(src.getX() == dest.getX() && src.getY() == dest.getY())
			return false;
		
		//check that dest is a valid square to move to
		int xDiff = Math.abs(src.getX() - dest.getX());
		int yDiff = Math.abs(src.getY() - dest.getY());
		if((xDiff == 0 && yDiff > 0) || (xDiff > 0 && yDiff == 0)){
			if(unobstructed(src,dest)){
				return checkRookCapture(src,dest);
					//return checkCheck(src,dest);
			}
		}
		
		return false;
	}
	
	//assumes unobstructed() has already been checked and returned true!
	private boolean checkRookCapture(Square src, Square dest){
		if(dest.getPiece() == null)
			return true;
		if(src.getPiece().isWhite() == dest.getPiece().isWhite())
			return false;
		
		if (dest.getPiece().getType() != PieceType.KING) {
			//check if rook is on enemy castle wall and enemy piece is inside its own castle
			if (src.isWall()) {
				if (src.getPiece().isWhite() && src.isBlackCastle()) {
					if (dest.isBlackCastle() && !dest.isWall())
						return true;
				} else if (!src.getPiece().isWhite() && src.isWhiteCastle()) {
					if (dest.isWhiteCastle() && !dest.isWall())
						return true;
				}
			}
			//check if rook is in its own castle and enemy is on castle wall
			if (src.isBlackCastle() && !src.getPiece().isWhite()) {
				if (dest.isBlackCastle() && dest.isWall())
					return true;
			} else if (src.isWhiteCastle() && src.getPiece().isWhite()) {
				if (dest.isWhiteCastle() && dest.isWall())
					return true;
			} 
		}else{//dest is enemy king. This move will never actually happen, just used for check and checkmate
			return true;
		}
		
		return false;
	}
	
	private boolean unobstructed(Square src, Square dest){
		int xDiff = src.getX() - dest.getX();
		int yDiff = src.getY() - dest.getY();
		if(xDiff != 0 && yDiff == 0){//horizontal move
			int i;
			if(xDiff > 0)
				i = src.getX() - 1;
			else
				i = src.getX() + 1;
			while(i != dest.getX()){
				if(squares[src.getY()][i].getPiece() != null)
					return false;
				
				if(xDiff > 0)
					i--;
				else
					i++;
			}
			return true;
		}else if(xDiff == 0 && yDiff != 0){//vertical move
			int i;
			if(yDiff > 0)
				i = src.getY() -1;
			else
				i = src.getY()+1;
			while(i != dest.getY()){
				if(squares[i][src.getX()].getPiece() != null)
					return false;
				
				if(yDiff > 0)
					i--;
				else
					i++;
			}
			return true;
		}else{//diagonal move			
			return unobstructedDiagonal(src, dest, xDiff, yDiff);
		}
	}
	
	private boolean unobstructedDiagonal(Square src, Square dest, int xDiff, int yDiff){
		//up and right
		if(xDiff < 0 && yDiff > 0){
			int x = src.getX() + 1;
			int y = src.getY() - 1;
			while(x != dest.getX()){
				if(squares[y][x].getPiece() != null)
					return false;
				x++;
				y--;
			}
			return true;
		}
		
		//up and left
		else if(xDiff > 0 && yDiff > 0){
			int x = src.getX() - 1;
			int y = src.getY() - 1;
			while(x != dest.getX()){
				if(squares[y][x].getPiece() != null)
					return false;
				x--;
				y--;
			}
			return true;
		}
		
		//down and left
		else if(xDiff > 0 && yDiff < 0){
			int x = src.getX() - 1;
			int y = src.getY() + 1;
			while(x != dest.getX()){
				if(squares[y][x].getPiece() != null)
					return false;
				x--;
				y++;
			}
			return true;
		}
		
		//down and right
		else if(xDiff < 0 && yDiff < 0){
			int x = src.getX() + 1;
			int y = src.getY() + 1;
			while(x != dest.getX()){
				if(squares[y][x].getPiece() != null)
					return false;
				x++;
				y++;
			}
			return true;
		}else{//shouldn't really ever get here
			return false;
		}
	}
	
	private boolean moveQueen(Square src, Square dest){
		if(validQueenMove(src,dest)){
			dest.setPiece(src.getPiece());
			src.setPiece(null);
			//testIfMoveCausedCheck(dest);
			return true;
		}
		
		return false;
	}
	
	private boolean validQueenMove(Square src, Square dest){
		if(validRookMove(src,dest))
			return true;
		
		int xDiff = Math.abs(src.getX() - dest.getX());
		int yDiff = Math.abs(src.getY() - dest.getY());
		if(xDiff == yDiff){
			if(unobstructed(src, dest))
				return checkRookCapture(src, dest);
					//return checkCheck(src,dest);
		}
		
		return false;
	}
	
	private boolean moveKing(Square src, Square dest){
		if(validKingMove(src,dest)){
			dest.setPiece(src.getPiece());
			src.setPiece(null);
			if(dest.getPiece().isWhite())
				whiteKing = dest;
			else
				blackKing = dest;
			return true;
		}else
			return false;
	}
	
	private boolean validKingMove(Square src, Square dest){
		if(src.getX() == dest.getX() && src.getY() == dest.getY())
			return false;
		
		//check destination square is within castle
		if(src.equals(whiteKing)){
			if(!(dest.isWhiteCastle() && !dest.isWall()))
				return false;
		}else if(src.equals(blackKing)){
			if(!(dest.isBlackCastle() && !dest.isWall()))
				return false;
		}else
			return false;
		
		//check if it's a valid 1 square move
		int xDiff = Math.abs(src.getX() - dest.getX());
		int yDiff = Math.abs(src.getY() - dest.getY());
		if(xDiff <= 1 && yDiff <= 1){
			return checkKingCapture(src, dest);
				//return checkCheck(src,dest);
		}
		
		//check if it's a valid knight-type move
		if((xDiff == 2 && yDiff == 1) || (xDiff == 1 && yDiff == 2)){
			return checkKingCapture(src,dest);
				//return checkCheck(src,dest);
		}
		
		return false;
	}
	
	//if you are in check, the only valid moves are those that result in leaving check
	//also can't put yourself in check
	private boolean checkCheck(Square src, Square dest){
	boolean white = src.getPiece().isWhite();
		//save current state, try the move and see if the king's in check
		Square oSrc = new Square(src);
		Square oDest = new Square(dest);
		Square oKing = null;
		if(src.getPiece().getType() == PieceType.KING){
			if(white){
				oKing = new Square(whiteKing);
				whiteKing = dest;				
			}
			else{
				oKing = new Square(blackKing);
				blackKing = dest;
			}
		}
		dest.setPiece(src.getPiece());
		src.setPiece(null);
		promote(dest);		
		boolean result;
		if((white && inCheck(whiteKing)) || (!white && inCheck(blackKing))){
			result = false;
		}else result = true;
		
		//restore previous state and return result
		src.copy(oSrc);
		dest.copy(oDest);
		if(oKing != null){
			if(white)
				whiteKing.copy(oKing);
			else
				blackKing.copy(oKing);
		}
		return result;
	}
	
	private boolean checkKingCapture(Square src, Square dest){
		if(dest.getPiece() == null)
			return true;
		
		if(dest.getPiece().isWhite() == src.getPiece().isWhite())
			return false;
		else
			return true;
	}
	
	private boolean inCheck(Square king){
		if(king.getPiece().getType() != PieceType.KING)
			return false;
		
		if(inHorizontalCheck(king))
			return true;
		
		if(inVerticalCheck(king))
			return true;
		
		return inDiagonalCheck(king);
	}
	
	private boolean inDiagonalCheck(Square king){
		Piece piece;
		int x = king.getX()+1;
		int y = king.getY()-1;
		//check up and right
		while(x < 12 && y >= 0){
			piece = squares[y][x].getPiece();
			if(piece != null){
				if(piece.isWhite() == king.getPiece().isWhite())
					break;
				else
					return true;
			}
			x++;
			y--;
		}
		
		//check up and left
		x = king.getX()-1;
		y = king.getY()-1;
		while(x >= 0 && y >= 0){
			piece = squares[y][x].getPiece();
			if(piece != null){
				if(piece.isWhite() == king.getPiece().isWhite())
					break;
				else
					return true;
			}
			x--; y--;
		}
		
		//check down and left
		x = king.getX()-1;
		y = king.getY()+1;
		while(x >= 0 && y < 12){
			piece = squares[y][x].getPiece();
			if(piece != null){
				if(piece.isWhite() == king.getPiece().isWhite())
					break;
				else
					return true;
			}
			x--; y++;
		}
		
		//check down and right
		x = king.getX()+1;
		y = king.getY()+1;
		while(x < 12 && y < 12){
			piece = squares[y][x].getPiece();
			if(piece != null){
				if(piece.isWhite() == king.getPiece().isWhite())
					break;
				else
					return true;
			}
			x++; y++;
		}
		
		return false;
	}
	
	private boolean inVerticalCheck(Square king){
		int x = king.getX();
		Piece piece;
		//check up
		for(int i = king.getY()-1; i >= 0; i--){
			piece = squares[i][x].getPiece();
			if(piece != null){
				if(piece.isWhite() == king.getPiece().isWhite())
					break;
				else
					return true;
			}
		}
		
		//check down
		for(int i = king.getY()+1; i < 12; i++){
			piece = squares[i][x].getPiece();
			if(piece != null){
				if(piece.isWhite() == king.getPiece().isWhite())
					break;
				else
					return true;
			}
		}
		
		return false;
	}
	
	private boolean inHorizontalCheck(Square king){
		Square[] row = squares[king.getY()];
		Piece piece;
		//check to the left
		for(int i = king.getX()-1; i >= 0; i--){
			piece = row[i].getPiece();
			if(piece != null){
				if(piece.isWhite() == king.getPiece().isWhite())
					break;
				else
					return true;
			}
		}
		
		//check to the right
		for(int i = king.getX()+1; i < 12; i++){
			piece = row[i].getPiece();
			if(piece != null){
				if(piece.isWhite() == king.getPiece().isWhite())
					break;
				else
					return true;
			}
		}
		
		return false;
	}
}