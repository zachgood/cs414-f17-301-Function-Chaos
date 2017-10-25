package edu.csu.cs414.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {
	Board board;

	@Before
	public void setUp() throws Exception {
		board = new Board();
	}
	
	@Test
	public void testInitialBoardState(){
		Square curr;
		int i;
		//sweep through 1 row at a time, make sure all squares are correct
		
		//first row should all be empty regular squares
		for(i = 0; i < 12; i++){
			curr = board.getSquare(0, i);
			assertNull(curr.getPiece());
			assertFalse(curr.isWall());
			assertFalse(curr.isBlackCastle());
			assertFalse(curr.isWhiteCastle());
		}
		
		//second row contains top wall of black castle
		for(i = 0; i < 7; i++){
			curr = board.getSquare(1, i);
			assertNull(curr.getPiece());
			assertFalse(curr.isWall());
			assertFalse(curr.isBlackCastle());
			assertFalse(curr.isWhiteCastle());
		}
		for(; i < 10; i++){
			curr = board.getSquare(1, i);
			assertNull(curr.getPiece());
			assertTrue(curr.isWall());
			assertTrue(curr.isBlackCastle());
			assertFalse(curr.isWhiteCastle());
		}
		for(; i < 12; i++){
			curr = board.getSquare(1, i);
			assertNull(curr.getPiece());
			assertFalse(curr.isWall());
			assertFalse(curr.isBlackCastle());
			assertFalse(curr.isWhiteCastle());
		}
		
		//third row
		for(i = 0; i < 6; i++){
			curr = board.getSquare(2, i);
			assertNull(curr.getPiece());
			assertFalse(curr.isWall());
			assertFalse(curr.isBlackCastle());
			assertFalse(curr.isWhiteCastle());
		}
		curr = board.getSquare(2, i);
		assertNull(curr.getPiece());
		assertTrue(curr.isWall());
		assertTrue(curr.isBlackCastle());
		assertFalse(curr.isWhiteCastle());
		for(i++; i < 10; i++){
			curr = board.getSquare(2, i);
			assertTrue(curr.getPiece().getType() == PieceType.ROOK);
			assertFalse(curr.getPiece().isWhite());
			assertFalse(curr.isWall());
			assertTrue(curr.isBlackCastle());
			assertFalse(curr.isWhiteCastle());
		}
		curr = board.getSquare(2, i);
		assertNull(curr.getPiece());
		assertTrue(curr.isWall());
		assertTrue(curr.isBlackCastle());
		assertFalse(curr.isWhiteCastle());
		i++;
		curr = board.getSquare(2, i);
		assertNull(curr.getPiece());
		assertFalse(curr.isWall());
		assertFalse(curr.isBlackCastle());
		assertFalse(curr.isWhiteCastle());
		
		//fourth row
		for(i = 0; i < 6; i++){
			curr = board.getSquare(3, i);
			assertNull(curr.getPiece());
			assertFalse(curr.isWall());
			assertFalse(curr.isBlackCastle());
			assertFalse(curr.isWhiteCastle());
		}
		for(; i < 12; i += 4){
			curr = board.getSquare(3, i);
			assertNull(curr.getPiece());
			assertTrue(curr.isWall());
			assertTrue(curr.isBlackCastle());
			assertFalse(curr.isWhiteCastle());
		}
		for(i = 7; i <= 9; i+=2){
			curr = board.getSquare(3, i);
			assertTrue(curr.getPiece().getType() == PieceType.ROOK);
			assertFalse(curr.getPiece().isWhite());
			assertFalse(curr.isWall());
			assertTrue(curr.isBlackCastle());
			assertFalse(curr.isWhiteCastle());
		}
		curr = board.getSquare(3, 8);
		assertTrue(curr.getPiece().getType() == PieceType.KING);
		assertFalse(curr.getPiece().isWhite());
		assertFalse(curr.isWall());
		assertTrue(curr.isBlackCastle());
		assertFalse(curr.isWhiteCastle());
		curr = board.getSquare(3, 11);
		assertNull(curr.getPiece());
		assertFalse(curr.isWall());
		assertFalse(curr.isBlackCastle());
		assertFalse(curr.isWhiteCastle());
		
		//fifth row same as third row
		for(i = 0; i < 6; i++){
			curr = board.getSquare(4, i);
			assertNull(curr.getPiece());
			assertFalse(curr.isWall());
			assertFalse(curr.isBlackCastle());
			assertFalse(curr.isWhiteCastle());
		}
		curr = board.getSquare(4, i);
		assertNull(curr.getPiece());
		assertTrue(curr.isWall());
		assertTrue(curr.isBlackCastle());
		assertFalse(curr.isWhiteCastle());
		for(i++; i < 10; i++){
			curr = board.getSquare(4, i);
			assertTrue(curr.getPiece().getType() == PieceType.ROOK);
			assertFalse(curr.getPiece().isWhite());
			assertFalse(curr.isWall());
			assertTrue(curr.isBlackCastle());
			assertFalse(curr.isWhiteCastle());
		}
		curr = board.getSquare(4, i);
		assertNull(curr.getPiece());
		assertTrue(curr.isWall());
		assertTrue(curr.isBlackCastle());
		assertFalse(curr.isWhiteCastle());
		i++;
		curr = board.getSquare(4, i);
		assertNull(curr.getPiece());
		assertFalse(curr.isWall());
		assertFalse(curr.isBlackCastle());
		assertFalse(curr.isWhiteCastle());
		
		//sixth row same as second row
		for(i = 0; i < 7; i++){
			curr = board.getSquare(5, i);
			assertNull(curr.getPiece());
			assertFalse(curr.isWall());
			assertFalse(curr.isBlackCastle());
			assertFalse(curr.isWhiteCastle());
		}
		for(; i < 10; i++){
			curr = board.getSquare(5, i);
			assertNull(curr.getPiece());
			assertTrue(curr.isWall());
			assertTrue(curr.isBlackCastle());
			assertFalse(curr.isWhiteCastle());
		}
		for(; i < 12; i++){
			curr = board.getSquare(5, i);
			assertNull(curr.getPiece());
			assertFalse(curr.isWall());
			assertFalse(curr.isBlackCastle());
			assertFalse(curr.isWhiteCastle());
		}
		
		//seventh row, top of white castle
		for(i = 0; i < 2; i++){
			curr = board.getSquare(6, i);
			assertNull(curr.getPiece());
			assertFalse(curr.isWall());
			assertFalse(curr.isBlackCastle());
			assertFalse(curr.isWhiteCastle());
		}
		for(; i < 5; i++){
			curr = board.getSquare(6, i);
			assertNull(curr.getPiece());
			assertTrue(curr.isWall());
			assertFalse(curr.isBlackCastle());
			assertTrue(curr.isWhiteCastle());
		}
		for(; i < 12; i++){
			curr = board.getSquare(6, i);
			assertNull(curr.getPiece());
			assertFalse(curr.isWall());
			assertFalse(curr.isBlackCastle());
			assertFalse(curr.isWhiteCastle());
		}
		
		//eighth row
		curr = board.getSquare(7, 0);
		assertNull(curr.getPiece());
		assertFalse(curr.isWall());
		assertFalse(curr.isBlackCastle());
		assertFalse(curr.isWhiteCastle());
		for(i = 1; i < 6; i += 4){
			curr = board.getSquare(7, i);
			assertTrue(curr.isWall());
			assertFalse(curr.isBlackCastle());
			assertTrue(curr.isWhiteCastle());
		}
		for(i = 2; i < 5; i++){
			curr = board.getSquare(7, i);
			assertEquals(PieceType.ROOK, curr.getPiece().getType());
			assertTrue(curr.getPiece().isWhite());
			assertFalse(curr.isWall());
			assertFalse(curr.isBlackCastle());
			assertTrue(curr.isWhiteCastle());
		}
		for(i = 6; i < 12; i++){
			curr = board.getSquare(7, i);
			assertNull(curr.getPiece());
			assertFalse(curr.isWall());
			assertFalse(curr.isBlackCastle());
			assertFalse(curr.isWhiteCastle());
		}
		
		//ninth row
		curr = board.getSquare(8, 0);
		assertNull(curr.getPiece());
		assertFalse(curr.isWall());
		assertFalse(curr.isBlackCastle());
		assertFalse(curr.isWhiteCastle());
		for(i = 1; i < 6; i += 4){
			curr = board.getSquare(8, i);
			assertNull(curr.getPiece());
			assertTrue(curr.isWall());
			assertFalse(curr.isBlackCastle());
			assertTrue(curr.isWhiteCastle());
		}
		for(i = 2; i < 5; i+=2){
			curr = board.getSquare(8, i);
			assertEquals(PieceType.ROOK, curr.getPiece().getType());
			assertTrue(curr.getPiece().isWhite());
			assertFalse(curr.isWall());
			assertFalse(curr.isBlackCastle());
			assertTrue(curr.isWhiteCastle());
		}
		curr = board.getSquare(8, 3);
		assertEquals(PieceType.KING, curr.getPiece().getType());
		assertTrue(curr.getPiece().isWhite());
		assertFalse(curr.isWall());
		assertFalse(curr.isBlackCastle());
		assertTrue(curr.isWhiteCastle());
		for(i = 6; i < 12; i++){
			curr = board.getSquare(8, i);
			assertNull(curr.getPiece());
			assertFalse(curr.isWall());
			assertFalse(curr.isBlackCastle());
			assertFalse(curr.isWhiteCastle());
		}
		
		//tenth row same as eighth row
		curr = board.getSquare(9, 0);
		assertNull(curr.getPiece());
		assertFalse(curr.isWall());
		assertFalse(curr.isBlackCastle());
		assertFalse(curr.isWhiteCastle());
		for(i = 1; i < 6; i += 4){
			curr = board.getSquare(9, i);
			assertTrue(curr.isWall());
			assertFalse(curr.isBlackCastle());
			assertTrue(curr.isWhiteCastle());
		}
		for(i = 2; i < 5; i++){
			curr = board.getSquare(9, i);
			assertEquals(PieceType.ROOK, curr.getPiece().getType());
			assertTrue(curr.getPiece().isWhite());
			assertFalse(curr.isWall());
			assertFalse(curr.isBlackCastle());
			assertTrue(curr.isWhiteCastle());
		}
		for(i = 6; i < 12; i++){
			curr = board.getSquare(9, i);
			assertNull(curr.getPiece());
			assertFalse(curr.isWall());
			assertFalse(curr.isBlackCastle());
			assertFalse(curr.isWhiteCastle());
		}
		
		//eleventh row same as seventh row
		for(i = 0; i < 2; i++){
			curr = board.getSquare(10, i);
			assertNull(curr.getPiece());
			assertFalse(curr.isWall());
			assertFalse(curr.isBlackCastle());
			assertFalse(curr.isWhiteCastle());
		}
		for(; i < 5; i++){
			curr = board.getSquare(10, i);
			assertNull(curr.getPiece());
			assertTrue(curr.isWall());
			assertFalse(curr.isBlackCastle());
			assertTrue(curr.isWhiteCastle());
		}
		for(; i < 12; i++){
			curr = board.getSquare(10, i);
			assertNull(curr.getPiece());
			assertFalse(curr.isWall());
			assertFalse(curr.isBlackCastle());
			assertFalse(curr.isWhiteCastle());
		}
		
		//twelfth row same as first row
		for(i = 0; i < 12; i++){
			curr = board.getSquare(11, i);
			assertNull(curr.getPiece());
			assertFalse(curr.isWall());
			assertFalse(curr.isBlackCastle());
			assertFalse(curr.isWhiteCastle());
		}
	}

	@Test
	public void testRookMove() {
		assertTrue(board.move(board.getSquare(2, 7), board.getSquare(2, 4)));
		assertNull(board.getSquare(2, 7).getPiece());
		assertEquals(PieceType.ROOK, board.getSquare(2, 4).getPiece().getType());
		
		assertTrue(board.move(board.getSquare(4, 7), board.getSquare(11, 7)));
		assertNull(board.getSquare(4, 7).getPiece());
		assertEquals(PieceType.ROOK, board.getSquare(11, 7).getPiece().getType());
		
		assertFalse(board.move(board.getSquare(11, 7),board.getSquare(0, 7)));
		assertNull(board.getSquare(0, 7).getPiece());
		assertEquals(PieceType.ROOK, board.getSquare(11, 7).getPiece().getType());
	}
	
	@Test
	public void testCaptureAndPromote(){
		assertTrue(board.move(4,7,7,7));
		assertFalse(board.move(7,7,7,4));
		assertTrue(board.move(7,7,7,5));
		assertTrue(board.move(7,5,7,4));
		assertFalse(board.getSquare(7, 4).getPiece().isWhite());
		assertEquals(PieceType.QUEEN,board.getSquare(7, 4).getPiece().getType());
		
		assertTrue(board.move(7,4,8,5));
		assertTrue(board.move(8,4,8,5));
		assertNull(board.getSquare(8, 4).getPiece());
		assertTrue(board.getSquare(8, 5).getPiece().isWhite());
		assertEquals(PieceType.ROOK,board.getSquare(8, 5).getPiece().getType());
	}
	
	@Test
	public void testKingMove(){
		assertTrue(board.move(2,7,0,7));
		assertTrue(board.move(3,8,2,7));
		Square s1 = board.getSquare(2, 7);
		assertEquals(PieceType.KING, s1.getPiece().getType());
		assertEquals(s1, board.getBlackKing());
		
		//test knight-type move
	}

}