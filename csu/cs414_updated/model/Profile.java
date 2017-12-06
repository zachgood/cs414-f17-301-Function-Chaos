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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException ;
import java.sql.Statement;
import java.sql.PreparedStatement ;  
import java.text.SimpleDateFormat ;  

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import edu.csu.cs414.controler.Register_db;  

public class Profile extends JFrame{  
	
		
	private JPanel pan = new JPanel();
	private JLabel namelab = new JLabel("UserName");
	private JLabel passlab = new JLabel("Password");
	private JLabel emaillab = new JLabel("email");
	public JButton Submit = new JButton("Sumbit");
	public static String user_name1="Martin";
	
   	Register_db regist;
   	private final JTextField username = new JTextField();
   	private final JTextField password = new JTextField();
   	private final JTextField email = new JTextField();
   	
   	
   	static Connection ct = null;  
    static PreparedStatement ps = null;  
    static ResultSet rs = null;  
   	String name1 = user_name1;
    String password1 = null;
    String email1 =null;
    
    
	public Profile() {
		
		try {
		      Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/chadgame","root","123456");
		      System.out.println("Success connect Mysql server!");
		      Statement stmt = connect.createStatement();
		      ResultSet rs = stmt.executeQuery("select * from player where UserName=\""+name1+"\"");	
		      while (rs.next()) {
		    	  password1 = rs.getString(4);  
		          email1 = rs.getString(3);  
		          System.out.println(rs.getString("UserName") + password1);
		      }
		}
		catch (Exception e) {
		      System.out.print("get data error!");
		      e.printStackTrace();
		}
		
         		 
		
		username.setText(user_name1);
		email.setEditable(false);
		email.setText(email1);
		email.setColumns(15);
		password.setText(password1);
		password.setEditable(false);
		password.setColumns(15);
		username.setEditable(false);
		username.setColumns(15);
		
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
		
		panel_1.add(username);
		passlab.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_1.add(passlab);
		
		panel_1.add(password);
		emaillab.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(emaillab);
		pan.add(panel_1);
		
		panel_1.add(email);
		    
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pan.add(panel_3);
		    
		JPanel panel_2 = new JPanel();
		panel_3.add(panel_2);
		    
		Submit.setBackground(UIManager.getColor("textHighlight"));
		panel_2.add(Submit);
		    
		    
		Submit.addActionListener(regist);
		regist.setSumbitButton(Submit);
		    
	      
//		    super.add(pan);
//		    super.setSize(300,250);
//		    super.setVisible(true);
			}
	 	public static void main(String []args){
	     
		    new Profile();
			}

		}