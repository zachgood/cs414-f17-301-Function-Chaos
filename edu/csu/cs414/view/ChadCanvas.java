package edu.csu.cs414.view;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import edu.csu.cs414.model.*;

// This canvas displays a 244-by-244 pix chad board pattern with
// a 2-pixel black border.  It is assumed that the size of the
// canvas is set to exactly 244-by-244 pixels.  This class does
// the work of letting the users play chad, and it displays
// the chad board.

public class ChadCanvas extends Canvas implements ActionListener, MouseListener {
	/* Buttons for applet */
	// Current player can resign by clicking this button.
	public Button resignButton;
	// This button starts a new game. It is enabled only when the current game has ended.
	public Button newGameButton; 
	// Button to figure out how to mess with buttons
	public Button otherButton; 
	
	/* Lables for applet */
	// A label for displaying messages to the user.
	public Label message; 

	/* Actual chad board */
	// The data for the chad board is kept here.
	// This board is also responsible for generating lists of legal moves.
//	ChadData board;
	Board board;

	/* Booleans regarding game information */
	// Is a game currently in progress?
	boolean gameInProgress;

	/* Variables that are only valid when the game is in progress. */
	// Whose turn is it now? The possible values ChadBoard.WHITE and ChadBoard.BLACK
	int currentPlayer; // 2 = white, 1 = black
	// If the current player has selected a piece to move, these give the row and column
	// containing that piece. If no piece is yet selected, then selectedRow is -1.
	int selectedRow, selectedCol, pieceSelectedRow, pieceSelectedCol;
	// An array containing the legal moves for the current player.
	ChadMove[] legalMoves;
	
	// Matrix for paining square color. 0 = light, 1 = dark
	int[][] sqColor =  {{0,0,0,0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,1,1,1,0,0},
						{0,0,0,0,0,0,1,0,0,0,1,0},
						{0,0,0,0,0,0,1,0,0,0,1,0},
						{0,0,0,0,0,0,1,0,0,0,1,0},
						{0,0,0,0,0,0,0,1,1,1,0,0},
						{0,0,1,1,1,0,0,0,0,0,0,0},
						{0,1,0,0,0,1,0,0,0,0,0,0},
						{0,1,0,0,0,1,0,0,0,0,0,0},
						{0,1,0,0,0,1,0,0,0,0,0,0},
						{0,0,1,1,1,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0,0,0,0}};

	public ChadCanvas() {
		// Constructor. Create the buttons and labels. 
		// Listen for mouse clicks and for clicks on the buttons. 
		// Create the board and start the first game.
		setBackground(Color.black); // Background color
		addMouseListener(this); // Required to get UI from the mouse
		setFont(new Font("Serif", Font.BOLD, 14)); // Not sure where this font is applied
		
		// Add resign button
		resignButton = new Button("Resign");
		resignButton.addActionListener(this);
		// Add new game button
		newGameButton = new Button("New Game");
		newGameButton.addActionListener(this);
		// Add other button
		otherButton = new Button("test Button");
		otherButton.addActionListener(this);
		
		// Add message
		message = new Label("", Label.CENTER);
		// Initialize board to keep track of the game
//		board = new ChadData();
		
		// Begin a new game
		doNewGame();
	}

	public void actionPerformed(ActionEvent evt) {
		// Respond to user's click on one of the two buttons.
		Object src = evt.getSource();
		if (src == newGameButton)
			doNewGame();
		else if (src == resignButton)
			doResign();
		else if (src == otherButton)
			doOther();
	}
   

	void doNewGame() {
		// Begin a new game.
		if (gameInProgress == true) {
			// This should not be possible, but it doens't hurt to check.
			message.setText("Finish the current game first!");
			return;
		}
		
//		board.setUpGame(); // Set up the pieces.
		board = new Board();
		currentPlayer = 2; //ChadData.WHITE; // WHITE moves first.
//		legalMoves = board.getLegalMoves(ChadData.WHITE); // Get WHITE's legal moves.
		selectedRow = -1;
		pieceSelectedRow = -1; // WHITE has not yet selected a piece to move.
		message.setText("White:  Make your move.");
		gameInProgress = true;
		newGameButton.setEnabled(false); // This means the newGameButton is not able to be clicked
		resignButton.setEnabled(true); // resignButton is able to be clicked now
		otherButton.setEnabled(true); // otherButton is able to be clicked now
		repaint();
	}
   

	void doResign() {
		// Current player resigns. Game ends. Opponent wins.
		if (gameInProgress == false) {
			message.setText("There is no game in progress!");
			return;
		}
		if (currentPlayer == 2)
			gameOver("WHITE resigns.  BLACK wins.");
		else
			gameOver("BLACK resigns.  WHITE winds.");
	}

	public void doOther() {
		if (currentPlayer == 2) {
			System.out.println("CURRENT PLAYER IS WHITE!");
		} else {
			System.out.println("CURRENT PLAYER IS BLACK!");
		}
	}
   

	void gameOver(String str) {
		// The game ends. The parameter, str, is displayed as a message to the user. 
		// The states of the buttons are adjusted so players can start a new game.
		message.setText(str);
		newGameButton.setEnabled(true);
		resignButton.setEnabled(false);
		otherButton.setEnabled(false);
		gameInProgress = false;
	}
      

	void doClickSquare(int row, int col) {
//		System.out.println("In doClickSquare with row = " + row + " and col = " + col);
//		System.out.println("\t selectedRow = " + selectedRow + " selectedCol = " + selectedCol);
		// This is called by mousePressed() when a player clicks on the
		// square in the specified row and col. It has already been checked
		// that a game is, in fact, in progress and row and col are valid
		
		// Set currently selected row and col
		selectedRow = row;
		selectedCol = col;
//		int squareInfo = board.pieceAt(row, col);
		Piece currP = board.getSquare(row, col).getPiece();
		
		/* If the user clicked on a square that the selected piece can be
		 * legally moved, then make the move and return.
		 
		for (ChadMove move : legalMoves){
			if (move.fromRow == pieceSelectedRow && move.fromCol == pieceSelectedCol
					&& move.toRow == row && move.toCol == col) {
				doMakeMove(move);
				return;
			}
		}
		*/
		if(pieceSelectedRow >= 0){ // valid move
			if(board.move(pieceSelectedRow, pieceSelectedCol, row, col)){
				doMakeMove(pieceSelectedRow, pieceSelectedCol, row, col);
				if(board.getWinner() != null)
					gameOver(board.getWinner() + " wins!");
				return;
			}
		}
		
		if(currP == null){ // Empty space
			if (pieceSelectedRow >= 0)
				message.setText("Not a valid move for selected piece.");
			else
				if (currentPlayer == 2) 
					message.setText("WHITE:  Click the piece you want to move.");
				else 
					message.setText("BLACK:  Click the piece you want to move.");
		} else if((currP.isWhite() && currentPlayer == 2) || (!currP.isWhite() && currentPlayer == 1)) { // your piece
			// Set the piece you selected
			pieceSelectedRow = row;
			pieceSelectedCol = col;
			if (currentPlayer == 2)
				message.setText("WHITE:  Make your move for piece selected.");
			else
				message.setText("BLACK:  Make your move for piece selected.");
			// If you selected one of your pieces that cant be moved anywhere
//			message.setText("No valid moves for that piece :(");
			/* If the player clicked on one of the pieces that the player can move,
			 * mark this row and col as selected and return. (This might change a
			 * previous selection.) Reset the message, in case it was previously
			 * displaying an error message.
			 */
			/*
			for (ChadMove move : legalMoves) {
				if (move.fromRow == row && move.fromCol == col) {
					if (currentPlayer == 2)
						message.setText("WHITE:  Make your move.");
					else
						message.setText("BLACK:  Make your move.");
//					repaint();
//					return;
					break;
				}
			}
			*/
			
		} /*else {
			message.setText("Uh, thats not your piece...");
		}*/
		
		repaint();
		return;
		
		/*
		 * If we get to this point, there is a piece selected, and the square
		 * where the user just clicked is not one where that piece can be
		 * legally moved. Show an error message.
		 */

//		message.setText("Click the square you want to move to.");

	}

	void doMakeMove(int srcY, int srcX, int destY, int destX /*ChadMove move*/) {
		// This is called when the current player has chosen the specified
		// move. Make the move, and then either end or continue the game
		// appropriately.
		
//		board.makeMove(move); 
		//Move has already been made in Board.java

		/*
		 * If the move was a jump, it's possible that the player has another
		 * jump. Check for legal jumps starting from the square that the player
		 * just moved to. If there are any, the player must jump. The same
		 * player continues moving.
		 */
/*
		if (move.isJump()) {
			legalMoves = board.getLegalJumpsFrom(currentPlayer, move.toRow, move.toCol);
			if (legalMoves != null) {
				if (currentPlayer == CheckersData.RED)
					message.setText("RED:  You must continue jumping.");
				else
					message.setText("BLACK:  You must continue jumping.");
				selectedRow = move.toRow; // Since only one piece can be moved,
											// select it.
				selectedCol = move.toCol;
				repaint();
				return;
			}
		}
*/
		/*
		 * The current player's turn is ended, so change to the other player.
		 * Get that player's legal moves. If the player has no legal moves, then
		 * the game ends.
		 */

		if (currentPlayer == 2) {
			currentPlayer = 1;
//			legalMoves = board.getLegalMoves(currentPlayer);
			if (false/*legalMoves == null*/)
				gameOver("BLACK has no moves.  WHITE wins.");
			else
				message.setText("BLACK:  Select the piece you want to move.");
		} else {
			currentPlayer = 2;
//			legalMoves = board.getLegalMoves(currentPlayer);
			if (false/*legalMoves == null*/)
				gameOver("WHITE has no moves.  BLACK wins.");
			else
				message.setText("WHITE:  Select the piece you want to move.");
		}

		// Set selectedRow & pieceSelectedRow = -1 to record that the player has not yet selected anything
		selectedRow = -1;
		pieceSelectedRow = -1;

		// Make sure the board is redrawn in its new state.
		repaint();
	}

	public void update(Graphics g) {
		// The paint method completely redraws the canvas, so don't erase before calling paint().
		paint(g);
	}

	public void paint(Graphics g) {
//		System.out.println("In paint with selectedRow = " + selectedRow + " selectedCol = " + selectedCol);
//		System.out.println("\t pieceSelectedRow = " + pieceSelectedRow + " pieceSelectedCol = " + pieceSelectedCol);
		// Draw chad board pattern in gray and lightGray. 
		// If a game is in progress, hilite the legal moves.

		/* Draw a two-pixel black border around the edges of the canvas. */
		g.setColor(Color.black);
		g.drawRect(0, 0, getSize().width - 1, getSize().height - 1);
		g.drawRect(1, 1, getSize().width - 3, getSize().height - 3);

		/* Draw the squares of the chad board and the pieces. */
		for (int row = 0; row < 12; row++) {
			for (int col = 0; col < 12; col++) {
				// Color of square
				if (sqColor[row][col] == 1)
					g.setColor(Color.gray);
				else
					g.setColor(Color.lightGray);
				// Fill rectangle with the color
				g.fillRect(2 + col * 20, 2 + row * 20, 20, 20);
				// Draw a black boarder around the square
				g.setColor(Color.BLACK);
				g.drawRect(2 + col * 20, 2 + row * 20, 20, 20);
				
				Piece curr = board.getSquare(row, col).getPiece();
				if(curr == null)
					continue;
				if(curr.isWhite()){
					switch(curr.getType()){
					case ROOK:
						g.setColor(Color.white);
						g.fillOval(4 + col * 20, 4 + row * 20, 16, 16);
						break;
					case KING:
						g.setColor(Color.white);
						g.fillOval(4 + col * 20, 4 + row * 20, 16, 16);
						g.setColor(Color.black);
						g.drawString("K", 7 + col * 20, 16 + row * 20);
						break;
					case QUEEN:
						g.setColor(Color.white);
						g.fillOval(4 + col * 20, 4 + row * 20, 16, 16);
						g.setColor(Color.black);
						g.drawString("Q", 7 + col * 20, 16 + row * 20);
						break;
					}
				} else {
					switch(curr.getType()){
					case ROOK:
						g.setColor(Color.black);
						g.fillOval(4 + col * 20, 4 + row * 20, 16, 16);
						break;
					case KING:
						g.setColor(Color.black);
						g.fillOval(4 + col * 20, 4 + row * 20, 16, 16);
						g.setColor(Color.white);
						g.drawString("K", 7 + col * 20, 16 + row * 20);
						break;
					case QUEEN:
						g.setColor(Color.black);
						g.fillOval(4 + col * 20, 4 + row * 20, 16, 16);
						g.setColor(Color.white);
						g.drawString("Q", 7 + col * 20, 16 + row * 20);
						break;
					}
				}
				
				// Piece in square
/*
				switch (board.pieceAt(row, col)) {
				case ChadData.WHITE_ROOK:
					g.setColor(Color.white);
					g.fillOval(4 + col * 20, 4 + row * 20, 16, 16);
					break;
				case ChadData.BLACK_ROOK:
					g.setColor(Color.black);
					g.fillOval(4 + col * 20, 4 + row * 20, 16, 16);
					break;
				case ChadData.WHITE_KING:
					g.setColor(Color.white);
					g.fillOval(4 + col * 20, 4 + row * 20, 16, 16);
					g.setColor(Color.black);
					g.drawString("K", 7 + col * 20, 16 + row * 20);
					break;
				case ChadData.BLACK_KING:
					g.setColor(Color.black);
					g.fillOval(4 + col * 20, 4 + row * 20, 16, 16);
					g.setColor(Color.white);
					g.drawString("K", 7 + col * 20, 16 + row * 20);
					break;
				case ChadData.WHITE_QUEEN:
					g.setColor(Color.white);
					g.fillOval(4 + col * 20, 4 + row * 20, 16, 16);
					g.setColor(Color.black);
					g.drawString("Q", 7 + col * 20, 16 + row * 20);
					break;
				case ChadData.BLACK_QUEEN:
					g.setColor(Color.black);
					g.fillOval(4 + col * 20, 4 + row * 20, 16, 16);
					g.setColor(Color.white);
					g.drawString("Q", 7 + col * 20, 16 + row * 20);
					break;
				}*/
			}
		}


		/*
		 * If a game is in progress, hilite the legal moves. Note that
		 * legalMoves is never null while a game is in progress.
		 */
		if (gameInProgress) {
			// Draw a cyan border around the pieces that can be moved.
			/*
			g.setColor(Color.cyan);
			for (ChadMove move : legalMoves) 
				g.drawRect(2 + move.fromCol * 20, 2 + move.fromRow * 20, 20, 20);
			*/
			
			// Color the selected square grey.  
			// If the square is a valid piece to move, it will get overwritten with red
			if (selectedRow >= 0) {
				g.setColor(Color.white);
				g.drawRect(2 + selectedCol * 20, 2 + selectedRow * 20, 20, 20);
			}
			
			// If a piece is selected for moving (i.e. if pieceSelectedRow >= 0), then
			// draw a 2-pixel red border around that piece and draw green borders
			// around each square that that piece can be moved to.
			
			if (pieceSelectedRow >= 0) {
				// Greed border for all valid moves of piece selected
//				if(pieceSelectedRow == selectedRow && pieceSelectedCol == selectedCol){
				/*
				// *COMMENTED OUT TO KEEP GREEN FOR CURRENTLY SELECTED PIECE*
					g.setColor(Color.green);
					for (ChadMove move : legalMoves) {
						if (move.fromCol == pieceSelectedCol && move.fromRow == pieceSelectedRow)
							g.drawRect(2 + move.toCol * 20, 2 + move.toRow * 20, 20, 20);
					}
//				}
 */
				// Red border for currently selected piece
				g.setColor(Color.red);
				g.drawRect(2 + pieceSelectedCol * 20, 2 + pieceSelectedRow * 20, 20, 20);
			}
			
		}

	} 

	public Dimension getPreferredSize() {
		// Specify desired size for this component. Note:
		// the size MUST be 164 by 164.
		return new Dimension(244, 244);
	}

	public Dimension getMinimumSize() {
		return new Dimension(244, 244);
	}

	public void mousePressed(MouseEvent evt) {
		// Respond to a user click on the board. If no game is in progress, show an error message. 
		// Otherwise, find the row and column that the user clicked and call doClickSquare() to handle it.
		if (gameInProgress == false)
			message.setText("Click \"New Game\" to start a new game.");
		else {
			int col = (evt.getX() - 2) / 20;
			int row = (evt.getY() - 2) / 20;
//			System.out.println("In mousePressed with row = " + row + " col = " + col);
			if (col >= 0 && col < 12 && row >= 0 && row < 12){
//				System.out.println("Mouse clicked: row = " + row + " col = " + col);
				doClickSquare(row, col);
			}
		}
	}

	public void mouseReleased(MouseEvent evt) {
	}

	public void mouseClicked(MouseEvent evt) {
	}

	public void mouseEntered(MouseEvent evt) {
	}

	public void mouseExited(MouseEvent evt) {
	}


} 