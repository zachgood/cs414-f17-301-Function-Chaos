package edu.csu.cs414.controler;
import java.awt.Button;
import java.awt.event.MouseAdapter;
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
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import edu.csu.cs414.model.*;


public class ChadCanvas extends Canvas implements ActionListener, MouseListener {
	
	public Button resignButton;
	public Button newGameButton; 
	public Button otherButton; 
	public Label message; 
	Board board = new Board(); 
	boolean gameInProgress;
	int currentPlayer; 
	int selectedRow, selectedCol, pieceSelectedRow, pieceSelectedCol;
	ChadMove[] legalMoves;
    private boolean isStart = false;   
    private boolean isYourTurn = false;
    private DataInputStream din = null;
    private DataOutputStream dout = null;
    private FileOutputStream fos = null;
    InputStream is = null;
    public JButton btnStart, Button1;
    boolean isBlack = false;
    
    
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
	
	public void setButton(JButton b1,JButton b2){  
		btnStart = b1; 
		Button1 = b2;
	}
	
	@Override
	public void mousePressed(MouseEvent evt) {
		if (gameInProgress == false)
			message.setText("Click \"New Game\" to start a new game.");
		else {
			int col = (evt.getX() - 2) / 20;
			int row = (evt.getY() - 2) / 20;
//			System.out.println("In mousePressed with row = " + row + " col = " + col);
			if (col >= 0 && col < 12 && row >= 0 && row < 12){
//				System.out.println("Mouse clicked: row = " + row + " col = " + col);
				try {
					doClickSquare(row, col);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
			}
		}
		
		
		
	}
	
	public void doClickSquare(int row, int col) throws IOException{
		
		Piece currP = board.getSquare(row, col).getPiece();
		
//		
		isStart = true;
		isYourTurn = true;	
		

		
		selectedRow = row;
		selectedCol = col;
		
		
		if(pieceSelectedRow >= 0){ // valid move
			if(board.move(pieceSelectedRow, pieceSelectedCol, row, col)){
				doMakeMove(pieceSelectedRow, pieceSelectedCol, row, col);
//				System.out.println(pieceSelectedRow);
//				System.out.println(pieceSelectedCol);
//				System.out.println(row);
//				System.out.println(col); 
//				
//				
				
				if (isStart && isYourTurn){
				fos = new FileOutputStream("e:/test.txt");
				dout = new DataOutputStream(fos);
				 try {  
					dout.writeInt(pieceSelectedRow);
					dout.writeInt(pieceSelectedCol);
					dout.writeInt(row);
		            dout.writeInt(col);
		            dout.flush();
		            
//		            System.out.println(isYourTurn);
//					System.out.println(isStart); 
					System.out.println(pieceSelectedRow);
					System.out.println(pieceSelectedCol);
					System.out.println(row);
					System.out.println(col);
					
		            isYourTurn = !isYourTurn;
		            System.out.println(isYourTurn);
		            System.out.println(isStart);
		            
					} catch (IOException ex) {
				    ex.printStackTrace();
					}
				}
				
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
			
		} 
		
		
		
		repaint();
		return;
		

	}
	
public void doMakeMove(int srcY, int srcX, int destY, int destX /*ChadMove move*/) {
		
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
//		selectedRow = -1;
//		pieceSelectedRow = -1;
		
//
//		
		update1();
		// Make sure the board is redrawn in its new state.
		repaint();
		
//		System.out.println(srcY);
//		System.out.println(srcX);
//		System.out.println(destY);
//		System.out.println(destX);
		
	}
	


	public ChadCanvas() {
		
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
		
//		update1();
	
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
		else if (src == btnStart)
			doStart();
		else if (src == Button1)
			dojoin();
		
		
	}
   

	void doStart() {
		new Thread(new Runnable() {
			 
            @Override
            public void run() {
                try {
                    ServerSocket server = new ServerSocket(9990);
                    while(true){
                    	Socket client = server.accept();
                    
                    din = new DataInputStream(client.getInputStream());
                    dout = new DataOutputStream(client.getOutputStream());
                    isStart = true;
                    isYourTurn = true;
                    System.out.print(isStart);
                    System.out.print(isYourTurn);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
		JOptionPane.showMessageDialog(null, "Game created");
		
		repaint();
		Button1.setEnabled(false);
	}
	
	

	void dojoin() {
		try {
        	Socket client = new Socket("127.0.0.1", 9990);
            din = new DataInputStream(client.getInputStream());
            dout = new DataOutputStream(client.getOutputStream());
            isStart = true;
            isYourTurn = false;
            
            JOptionPane.showMessageDialog(null, "connected to player1");
            System.out.print(isStart);
            System.out.print(isYourTurn);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		update1();
        repaint();
        btnStart.setEnabled(false);
        
    }
		
		
	
	
	void doNewGame() {
		// Begin a new game.
		if (gameInProgress == true) {
			// This should not be possible, but it doens't hurt to check.
			message.setText("Finish the current game first!");
			return;
		}
		
		board = new Board();
		currentPlayer = 2; 
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
   

	public void gameOver(String str) {
		message.setText(str);
		newGameButton.setEnabled(true);
		resignButton.setEnabled(false);
		otherButton.setEnabled(false);
		gameInProgress = false;
	}
      
	public void update1(){
		new Thread(new Runnable() {
			 
	        public void run() {
	            while (true) {  

//	            	isStart = true;
//	            	isYourTurn = false;
	            	while (isStart && !isYourTurn) {
	                    try {
	                    	is = new FileInputStream("e:/test.txt");
	                    	din = new DataInputStream(is);
							int pieceSelectedRow = din.readInt();
							int pieceSelectedCol = din.readInt();
							int row = din.readInt();
							int col = din.readInt();
							
							System.out.println(pieceSelectedRow);
							System.out.println(pieceSelectedCol);
							System.out.println(row);
							System.out.println(col);
							board.move(7, 4, 7, 8);  
	                        repaint();
	                        isYourTurn = !isYourTurn;

	                       
	                    } catch (IOException ex) {
	                        ex.printStackTrace();
	                    }
	                }
	            }
	        }
	    }).start();
		
	}

	

	

	public void update(Graphics g) {
		// The paint method completely redraws the canvas, so don't erase before calling paint().
		paint(g);
		
		
		
	}

	public void paint(Graphics g) {
//		
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
				
			}
		}


		
		if (gameInProgress) {
			
			if (selectedRow >= 0) {
				g.setColor(Color.white);
				g.drawRect(2 + selectedCol * 20, 2 + selectedRow * 20, 20, 20);
			}
			
			
			if (pieceSelectedRow >= 0) {
			
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


	public void mouseReleased(MouseEvent evt) {
	}

	public void mouseClicked(MouseEvent evt) {
	}

	public void mouseEntered(MouseEvent evt) {
	}

	public void mouseExited(MouseEvent evt) {
	}


} 