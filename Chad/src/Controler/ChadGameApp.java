package Controler;
import Model.*;
import View.*;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.Vector;

/*
   This applet lets two users play chad against each other.
   White always starts the game. When a player can make no more
   moves, the game ends.
   
   This file defines four classes: the main applet class, GamePlay;
   ChadCanvas, ChadMove, and ChadData.
*/


//public class ChadApp extends Applet {
public class ChadGameApp extends Applet {

   /* The main applet class only lays out the applet.  The work of
      the game is all done in the ChadCanvas object.   Note that
      the Buttons and Label used in the applet are defined as 
      instance variables in the ChadCanvas class.  The applet
      class gives them their visual appearance and sets their
      size and positions.*/

	public void init() {
		// Set void to do layout myself.
		setLayout(null);
		// Set a dark green background.
		setBackground(new Color(0, 150, 0)); 

		/* Create the components and add them to the applet. */
		// Create a checkers canvas to visualize the game and the buttons,
		// messages, and other stuff needed
		// Note: The constructor creates the buttons board.resignButton and
		// board.newGameButton and the Label board.message.
		ChadCanvas board = new ChadCanvas();
		add(board);

		// Add buttons, messages, and stuff to the board so it knows what to
		// include in the window
		// New game button
		board.newGameButton.setBackground(Color.lightGray);
		add(board.newGameButton);
		// Resign button
		board.resignButton.setBackground(Color.lightGray);
		add(board.resignButton);
		// Other button
		board.otherButton.setBackground(Color.green);
		board.otherButton.setForeground(Color.magenta); // Color of text?
		add(board.otherButton);
		// Add message that says whos turn it is
		board.message.setForeground(Color.green);
		board.message.setFont(new Font("Serif", Font.BOLD, 14));
		add(board.message);

		/*
		 * Set the position and size of each component by calling its
		 * setBounds() method.
		 */
		board.setBounds(20, 20, 244, 244); // Note: size MUST be 164-by-164 !
		board.newGameButton.setBounds(310, 60, 100, 30);
		board.resignButton.setBounds(310, 120, 100, 30);
		board.otherButton.setBounds(420, 80, 150, 50);
		board.message.setBounds(0, 300, 330, 30);
	}
   
} 
