package edu.csu.cs414.view;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import edu.csu.cs414.controler.Login_db;

import java.awt.SystemColor;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;

public class Login extends JFrame{
	Login_db log;	
	private JPanel pan = new JPanel();
	private JLabel namelab = new JLabel("UserName");
	private JLabel passlab = new JLabel("Password");
	private JTextField nametext = new JTextField();
	private JPasswordField passtext = new JPasswordField();
	public JButton Submit = new JButton("Sumbit");
	public JButton Register = new JButton("Register");

public Login(){
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	log =new Login_db();
	setVisible(true);
	setMinimumSize(new Dimension(450, 300));
	setLocation(new Point(100, 100));
	pan.setBounds(new Rectangle(100, 100, 450, 350));
	setBounds(new Rectangle(100, 100, 450, 300));
    
    super.setTitle("Welcome to ChadGame");
    
    super.add(pan);
    pan.setLayout(new GridLayout(0, 1, 0, 0));
    
    JPanel panel = new JPanel();
    panel.setBackground(SystemColor.activeCaption);
    pan.add(panel);
    
    JLabel lblWelcomeToChadgame = new JLabel("Welcome to ChadGame");
    lblWelcomeToChadgame.setForeground(SystemColor.text);
    lblWelcomeToChadgame.setFont(new Font("Tahoma", Font.PLAIN, 30));
    panel.add(lblWelcomeToChadgame);
    
    JPanel panel_1 = new JPanel();
    pan.add(panel_1);
    panel_1.add(namelab);
    nametext.setColumns(10);
    panel_1.add(nametext);
    panel_1.add(passlab);
    passtext.setColumns(10);
    panel_1.add(passtext);
    
    JPanel panel_2 = new JPanel();
    pan.add(panel_2);
    
    Submit.setBackground(UIManager.getColor("textHighlight"));
    panel_2.add(Submit);
    Register.setBackground(UIManager.getColor("textHighlight"));
    panel_2.add(Register);
    
    
	Submit.addActionListener(log);
    Register.addActionListener(log);
    
    log.setname(nametext);  
    log.setpwd(passtext);  
    log.setButton(Submit,Register);
    
    
    super.add(pan);
    super.setSize(300,250);
    super.setVisible(true);
	}
 
	public static void main(String []args){
     
    new Login();
	}
	
//	 public void actionPerformed(ActionEvent e) {
//		    if(e.getSource()==Submit){
//		    	Submit();
//		    }else if (e.getSource()==Register){
//		    	Register();
//		    }
//	 }
//
//	private void Register() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	private void Submit() {
//		// TODO Auto-generated method stub
//		pan.setVisible(false);
//	}
}

