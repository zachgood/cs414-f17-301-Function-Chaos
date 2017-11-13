package edu.csu.cs414.controler;
import edu.csu.cs414.model.*;
import edu.csu.cs414.view.*;

import java.awt.*;
import java.awt.event.*;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.applet.*;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;


public class ChadGameApp extends JFrame {

	private boolean isBlack = true; 
    private ChadCanvas b = new ChadCanvas();  
    private boolean isWin = false;  
    private boolean isStart = false;    
    private boolean isYourTurn = false; 
 
    private DataInputStream din = null;
    private DataOutputStream dout = null;
	
	
  
	public ChadGameApp() {
		getContentPane().setBackground(SystemColor.textHighlight);
		init();
	}
	public void init() {
		setMinimumSize(new Dimension(600, 400));
		// Set void to do layout myself.
		getContentPane().setLayout(null);
		// Set a dark green background.
		setBackground(new Color(0, 150, 0)); 

		
		ChadCanvas board = new ChadCanvas();
		getContentPane().add(board);

		
		board.newGameButton.setBackground(Color.lightGray);
		getContentPane().add(board.newGameButton);
		// Resign button
		board.resignButton.setBackground(Color.lightGray);
		getContentPane().add(board.resignButton);
		// Other button
		board.otherButton.setBackground(Color.green);
		board.otherButton.setForeground(Color.magenta); // Color of text?
		getContentPane().add(board.otherButton);
		// Add message that says whos turn it is
		board.message.setForeground(Color.green);
		board.message.setFont(new Font("Serif", Font.BOLD, 14));
		getContentPane().add(board.message);

		/*
		 * Set the position and size of each component by calling its
		 * setBounds() method.
		 */
		board.setBounds(20, 20, 244, 244); // Note: size MUST be 164-by-164 !
		board.newGameButton.setBounds(310, 60, 100, 30);
		board.resignButton.setBounds(310, 120, 100, 30);
		board.otherButton.setBounds(420, 80, 150, 50);
		board.message.setBounds(0, 300, 330, 30);
		
		new Thread(new Runnable() {
			 
            public void run() {
                while (true) {  
                	while (isStart && !isYourTurn) {
                        try {
                            
                            int fromRow = din.readInt();
							int fromCol = din.readInt();
							int toRow = din.readInt();
							int toCol = din.readInt();
							b.doMakeMove(fromRow, fromCol, toRow, toCol);  
                            repaint();
                            
                           
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }).start();
		
		
		JButton button = new JButton("Create");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					 
	                @Override
	                public void run() {
	                    try {
	                        ServerSocket server = new ServerSocket(5566);
	                        while(true){
	                        	Socket client = server.accept();
	                        
	                        din = new DataInputStream(client.getInputStream());
	                        dout = new DataOutputStream(client.getOutputStream());
	                        isStart = true;
	                        isYourTurn = true;
	                        
	                        }
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                    }
	                }
	            }).start();
				
				
			}
		});
		button.setBounds(341, 287, 101, 52);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("join");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ip = JOptionPane.showInputDialog(this, "please input your ID: ");
	            if(ip != null && !ip.equals("")) {
	                try {
	                    Socket client = new Socket("192.0.0.1", 5566);
	                    din = new DataInputStream(client.getInputStream());
	                    dout = new DataOutputStream(client.getOutputStream());
	                    isStart = true;
	                    isYourTurn = false;
	                    
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
	                repaint();
	                button.setEnabled(false);
	                
	            }
				
			}
		});
		button_1.setBounds(473, 287, 101, 52);
		getContentPane().add(button_1);
		this.show();
	}
} 