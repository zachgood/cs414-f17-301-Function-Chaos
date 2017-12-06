package edu.csu.cs414.view;
import edu.csu.cs414.controler.ChadCanvas;import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JButton;


public class ChadGameApp extends JFrame {

   
	ChadCanvas log2;
	
  
	public ChadGameApp() {
		log2 = new ChadCanvas();
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
		
		
		
		
		JButton btnStart = new JButton("Start");
		JButton button_1 = new JButton("join");
		
		getContentPane().add(btnStart);
		btnStart.setBounds(341, 287, 101, 52);
		button_1.setBounds(473, 287, 101, 52);
		getContentPane().add(button_1);
		
		btnStart.addActionListener(log2);
		button_1.addActionListener(log2);
		
		
		log2.setButton(btnStart, button_1);
		
//		button_1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
////				String ip = JOptionPane.showInputDialog(this, "please input your ID: ");
////	            if(ip != null && !ip.equals("")) {
//	                try {
//	                    Socket client = new Socket("127.0.0.1", 5566);
//	                    din = new DataInputStream(client.getInputStream());
//	                    dout = new DataOutputStream(client.getOutputStream());
//	                    isStart = true;
//	                    isYourTurn = false;
//	                    JOptionPane.showMessageDialog(null, "connected to player1");
//	                    System.out.print(isStart);
//	                } catch (Exception ex) {
//	                    ex.printStackTrace();
//	                }
//	                repaint();
//	                button_1.setEnabled(false);
//	                
////	            }
//				
//			}
//		});
		
		this.show();
	}
} 