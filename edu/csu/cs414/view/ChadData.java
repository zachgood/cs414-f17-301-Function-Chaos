package edu.csu.cs414.view;
import java.util.Arrays;
import java.util.Vector;

import edu.csu.cs414.model.*;

public class ChadData {

	

	public static final int 
	EMPTY = 0, 
	BLACK = 10, BLACK_ROOK = 11, BLACK_QUEEN = 12, BLACK_KING = 13, 
	WHITE = 20, WHITE_ROOK = 21, WHITE_QUEEN = 22, WHITE_KING = 23;
	

	private int[][] board; 

	
	public ChadData() {
		// Constructor. Create the board and set it up for a new game.
		setUpGame();
	}

	public void setUpGame() {
		board = new int[12][12];
		// Set up the board with rooks and kings for white and black
		for (int row = 0; row < 12; row++) {
			for (int col = 0; col < 12; col++) {
				// Black Pieces
				if (Arrays.asList(2, 3, 4).contains(row)) {
					if (Arrays.asList(7, 8, 9).contains(col)) {
						if (row == 3 && col == 8)
							board[row][col] = BLACK_KING;
						else
							board[row][col] = BLACK_ROOK;
					}
				}
				// White Pieces
				if (Arrays.asList(2, 3, 4).contains(col)) {
					if (Arrays.asList(7, 8, 9).contains(row)) {
						if (col == 3 && row == 8)
							board[row][col] = WHITE_KING;
						else
							board[row][col] = WHITE_ROOK;
					}
				}
			}
		}
	}

	public int pieceAt(int row, int col) {
		// Return the contents of the square in the specified row and column.
		return board[row][col];
	}

	public void setPieceAt(int row, int col, int piece) {
		// Set the contents of the square in the specified row and column.
		board[row][col] = piece;
	}

	public void makeMove(ChadMove move) {
		// Make the specified move. It is assumed that move
		// is non-null and that the move it represents is legal.
		makeMove(move.fromRow, move.fromCol, move.toRow, move.toCol);
	}

	public void makeMove(int fromRow, int fromCol, int toRow, int toCol) {
		
		board[toRow][toCol] = board[fromRow][fromCol];
		board[fromRow][fromCol] = EMPTY;
		if (fromRow - toRow == 2 || fromRow - toRow == -2) {
			// The move is a jump. Remove the jumped piece from the board.
			int jumpRow = (fromRow + toRow) / 2; // Row of the jumped piece.
			int jumpCol = (fromCol + toCol) / 2; // Column of the jumped piece.
			board[jumpRow][jumpCol] = EMPTY;
		}
	}

	public ChadMove[] getLegalMoves(int player) {
		
		ChadMove[] moveArray = new ChadMove[16];
		moveArray[0] = new ChadMove(7,2,6,2);
		moveArray[1] = new ChadMove(7,3,6,3);
		moveArray[2] = new ChadMove(7,4,6,4);
		moveArray[3] = new ChadMove(8,2,8,1);
		moveArray[4] = new ChadMove(8,4,8,5);
		moveArray[5] = new ChadMove(9,2,10,2);
		moveArray[6] = new ChadMove(9,3,10,3);
		moveArray[7] = new ChadMove(9,4,10,4);
		
		moveArray[8] = new ChadMove(7,2,5,2);
		moveArray[9] = new ChadMove(7,2,4,2);
		moveArray[10] = new ChadMove(7,2,3,2);
		moveArray[11] = new ChadMove(7,2,2,2);
		moveArray[12] = new ChadMove(7,2,1,2);
		moveArray[13] = new ChadMove(7,2,0,2);
		moveArray[14] = new ChadMove(7,2,7,1);
		moveArray[15] = new ChadMove(7,2,7,0);
		
//		ChadMove[] moveArrayT = new ChadMove[16];
		if(player == ChadData.BLACK){
			int i = 0;
			for(ChadMove move : moveArray){
				moveArray[i] = new ChadMove(move.fromCol, move.fromRow, move.toCol, move.toRow);
				i++;
			}
		}
		
		return moveArray;
	} // end getLegalMoves

	public ChadMove[] getLegalJumpsFrom(int player, int row, int col) {
		
		return null;
	} // end getLegalMovesFrom()

	private boolean canJump(int player, int r1, int c1, int r2, int c2, int r3, int c3) {
		
		return false;
	} // end canJump()

	private boolean canMove(int player, int r1, int c1, int r2, int c2) {
		
		return true;
	} // end canMove()
	 
} // end class CheckersData