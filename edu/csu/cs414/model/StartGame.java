package edu.csu.cs414.model;
import java.awt.Graphics;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.ImageIcon;  
import javax.swing.JButton;  
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import edu.csu.cs414.controler.ChadGameApp;
import edu.csu.cs414.controler.ChadSub;
import edu.csu.cs414.controler.Register_db;  
 
public class StartGame extends JFrame implements ActionListener{  
    JButton creategame;  
    JButton join;
    MyPanel mp;  
    Register_db current;
    private JTextField txtMartin;
    private JButton profile;
    private JButton history;
    Profile re; 
    History re1;
    ChadGameApp re2;
    
    public StartGame(){  
    	
    	current= new Register_db();
        creategame = new JButton("Create");
        creategame.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new JFrame().dispose();
    			re2 = new ChadGameApp();
        	
        	}
        });
        creategame.setDoubleBuffered(true);
        mp = new MyPanel();  
          
        new Thread(new StartPageMusic()).start();  
        
        getContentPane().setLayout(null);  
        getContentPane().add(creategame);  
        getContentPane().add(mp);  
        creategame.setBounds(108, 470, 101, 52);
        
        JButton join = new JButton("join");
        join.setBounds(277, 470, 101, 52);
        getContentPane().add(join);
        
        txtMartin = new JTextField();
        txtMartin.setEnabled(false);
        txtMartin.setEditable(false);
        txtMartin.setVerifyInputWhenFocusTarget(false);
        txtMartin.setSelectionColor(UIManager.getColor("InternalFrame.activeTitleGradient"));
        txtMartin.setBounds(346, 54, 86, 20);
        txtMartin.setText("Martin");
        
        getContentPane().add(txtMartin);
        txtMartin.setColumns(10);
        
        
        
        profile = new JButton("Profile");
        profile.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new JFrame().dispose();
    			re = new Profile();
        	}
        });
        profile.setBounds(343, 85, 89, 23);
        getContentPane().add(profile);
        
        history = new JButton("History");
        history.setBounds(343, 119, 89, 23);
        getContentPane().add(history);
        
        JButton btnServer = new JButton("server");
        btnServer.setBounds(0, 470, 89, 23);
        getContentPane().add(btnServer);
        history.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new JFrame().dispose();
    			re1 = new History();
        	}
        });
        
        
        
        
        mp.setBounds(0, 0, 500, 600);  
        //set bound 
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);  
        this.setSize(500, 600);  
        
        this.setVisible(true);  
    }  
 
	//Start
    class MyPanel extends JPanel{  
	    public void paint(Graphics g){  
	        super.paint(g);  
	        g.drawImage(new ImageIcon("pic/24465.jpg").getImage(),0,0, 500,600, null);  
	    }  
	}
    
    
    
    public static void main(String[] args) {  
        new StartGame();  
    }



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	} 
}
    	

