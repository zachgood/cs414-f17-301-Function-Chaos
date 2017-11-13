package edu.csu.cs414.model;

public class Piece {
	private boolean white;
	private PieceType type;
	
	public Piece(PieceType type, boolean white){
		this.type = type;
		this.white = white;
	}
	
	public Piece(Piece p){
		white = p.white;
		type = p.type;
	}
	
	public boolean isWhite(){
		return white;
	}
	
	public PieceType getType(){
		return type;
	}
	
	public void promote(){
		if (type == PieceType.ROOK)
			type = PieceType.QUEEN;
	}
}
