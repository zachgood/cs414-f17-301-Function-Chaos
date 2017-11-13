package edu.csu.cs414.view;


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
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import edu.csu.cs414.controler.Register_db;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.ComponentOrientation;  

public class History extends JFrame{  
	
		
	private JPanel pan = new JPanel();
	public JButton Submit = new JButton("Sumbit");
	
   	Register_db regist;
   	private JTable table;
   	private JTextField textField;
   	private JTextField textField_1;
   	private JTextField textField_2;
   	private String name1 = "Martin";
    private String wincount1 = null;
    private String losecount = null;
   	
   	
	public History() {
		
		try {
		      Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/chadgame","root","123456");
		      System.out.println("Success connect Mysql server!");
		      Statement stmt = connect.createStatement();
		      ResultSet rs = stmt.executeQuery("select * from game where UserName=\""+name1+"\"");	
		      while (rs.next()) {
		    	  wincount1 = rs.getString(2);  
		    	  losecount = rs.getString(3);  
		          System.out.println(rs.getString("UserName") + wincount1 + losecount);
		      }
		}
		catch (Exception e) {
		      System.out.print("get data error!");
		      e.printStackTrace();
		}
		
		
		
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
		    
		JLabel lblWelcomeToChadgame = new JLabel("ChadGame History");
		lblWelcomeToChadgame.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToChadgame.setForeground(SystemColor.text);
		lblWelcomeToChadgame.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel.add(lblWelcomeToChadgame);
		    
		JPanel panel_1 = new JPanel();
		panel_1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 85, 10));
		pan.add(panel_1);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		panel_1.add(textField_2);
		textField_2.setColumns(15);
		textField_2.setText(name1);
		
		JLabel lblUsername = new JLabel("Username");
		panel_1.add(lblUsername);
		
		textField = new JTextField();
		textField.setEditable(false);
		panel_1.add(textField);
		textField.setColumns(15);
		textField.setText(wincount1);
		
		JLabel lblWincount = new JLabel("Wincount");
		panel_1.add(lblWincount);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		panel_1.add(textField_1);
		textField_1.setColumns(15);
		textField_1.setText(losecount);
		
		JLabel lblLosecount = new JLabel("Losecount");
		panel_1.add(lblLosecount);
		
		table = new JTable();
		panel_1.add(table);
		table.setToolTipText("显示所有的数据");  
	    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// 设置表格调整尺寸模式  
	    table.setCellSelectionEnabled(false);// 设置单元格选择方式  
	    table.setShowVerticalLines(true);// 设置是否显示单元格间的分割线  
	    table.setShowHorizontalLines(true);  
		    
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
	     
		    new History();
			}

		}