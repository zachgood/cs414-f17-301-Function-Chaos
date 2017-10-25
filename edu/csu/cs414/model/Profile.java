package edu.csu.cs414.model;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.SystemColor;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

import edu.csu.cs414.controler.Register_db;  

public class Profile extends JFrame{  
	
		
	private JPanel pan = new JPanel();
	private JLabel namelab = new JLabel("UserName");
	private JLabel passlab = new JLabel("Password");
	private JLabel emaillab = new JLabel("email");
	private JLabel genderlab = new JLabel("gender");
	private JLabel addresslab = new JLabel("address");
	
	private JTextField usrnametext = new JTextField();
	private JPasswordField passtext = new JPasswordField();
	private JTextField addresstext = new JTextField();
	public JButton Submit = new JButton("Sumbit");
	private final JTextField gendertext = new JTextField();
	private final JTextField emailtext = new JTextField();
	
   	Register_db regist;
   
   	
   	
	public Profile() {
		
		regist =new Register_db();
		setVisible(true);
		setMinimumSize(new Dimension(450, 600));
		setLocation(new Point(100, 100));
		pan.setBounds(new Rectangle(100, 100, 450, 600));
		setBounds(new Rectangle(100, 100, 450, 600));
		    
		super.setTitle("Welcome to ChadGame");
		    
		super.add(pan);
		pan.setLayout(new GridLayout(0, 1, 0, 0));
		    
		JPanel panel = new JPanel();
		panel.setBounds(new Rectangle(100, 100, 450, 100));
		panel.setBackground(SystemColor.activeCaption);
		pan.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		    
		JLabel lblWelcomeToChadgame = new JLabel("ChadGame Profile");
		lblWelcomeToChadgame.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToChadgame.setForeground(SystemColor.text);
		lblWelcomeToChadgame.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel.add(lblWelcomeToChadgame);
		    
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 85, 10));
		namelab.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_1.add(namelab);
		usrnametext.setEditable(false);
		panel_1.add(usrnametext);
		usrnametext.setColumns(15);
		passlab.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_1.add(passlab);
		passtext.setEditable(false);
		passtext.setColumns(15);
		panel_1.add(passtext);
		emaillab.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(emaillab);
		emailtext.setEditable(false);
		emailtext.setColumns(15);
		    
		panel_1.add(emailtext);
		panel_1.add(genderlab);
		gendertext.setEditable(false);
		gendertext.setColumns(15);
		    
		panel_1.add(gendertext);
		panel_1.add(addresslab);
		addresstext.setEditable(false);
		addresstext.setColumns(15);
		panel_1.add(addresstext);
		usrnametext.setColumns(15);
		pan.add(panel_1);
		    
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pan.add(panel_3);
		    
		JPanel panel_2 = new JPanel();
		panel_3.add(panel_2);
		    
		Submit.setBackground(UIManager.getColor("textHighlight"));
		panel_2.add(Submit);
		    
		    
		Submit.addActionListener(regist);
		
		regist.setname(usrnametext);  
		regist.setpassword(passtext); 
		regist.setemail(emailtext); 
		regist.setgender(gendertext); 
		regist.setaddress(addresstext); 
		regist.setSumbitButton(Submit);
		    
	      
//		    super.add(pan);
//		    super.setSize(300,250);
//		    super.setVisible(true);
			}
	 	public static void main(String []args){
	     
		    new Profile();
			}

		}