import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Container;  
import java.awt.FlowLayout;  
import java.awt.GridLayout;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.util.Arrays;

import javax.swing.*;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font; 

public class Login {

	
	String s1=null;  
    char[] s2=null;  
    JFrame frame=new JFrame(); 
    
    Container c=frame.getContentPane();
    private JPasswordField passwordField;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		frame.setBounds(100, 100, 450, 300);
		c.setLayout(new GridLayout(3,1,10,10));//the Container uses the GridLayout for 3 JPanels   
        JPanel panel1=new JPanel(new FlowLayout(FlowLayout.CENTER));//each JPanel uses the FlowLayout  
        panel1.setBackground(SystemColor.activeCaption);
        JPanel panel2=new JPanel(new FlowLayout(FlowLayout.CENTER));  
        JPanel panel3=new JPanel(new FlowLayout());
        JLabel label2=new JLabel("Password��");  
        final JPasswordField jp=new JPasswordField(10);  
        jp.setEchoChar((char) 0);//set the display words as visible.  
        final JButton jb1 = new JButton("Submit");  
        final JButton jb2 = new JButton("forget");
        
        JLabel lblUsername = new JLabel("Email\uFF1A");
        panel2.add(lblUsername);
        
        passwordField = new JPasswordField(10);
        passwordField.setEchoChar(' ');
        panel2.add(passwordField);
        panel2.add(label2);  
        panel2.add(jp);  
        panel3.add(jb1);  
        panel3.add(jb2);  
        c.add(panel1);  
        
        JLabel lblWelcome = new JLabel("Welcome ChadGame");
        lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblWelcome.setForeground(new Color(255, 255, 255));
        panel1.add(lblWelcome);
        c.add(panel2);  
        c.add(panel3);        
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
//	private void initialize() {
//		frame = new JFrame();
//		frame.setBounds(100, 100, 450, 300);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	}

}
