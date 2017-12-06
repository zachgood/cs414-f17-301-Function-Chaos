package edu.csu.cs414.view;
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

import edu.csu.cs414.controler.ChadSub;
import edu.csu.cs414.controler.Login_db;
import edu.csu.cs414.controler.Register_db;
import edu.csu.cs414.controler.StartGame_db;
import edu.csu.cs414.model.Profile;
import edu.csu.cs414.model.StartPageMusic;  
 
public class StartGame extends JFrame implements ActionListener{  
    JButton creategame;  
    JButton join;
    MyPanel mp;  
    Register_db current;
    private JTextField txtMartin;
    private JButton profile;
    private JButton history;
    private JButton btnUnregister;
    Profile re; 
    History re1;
    ChadGameApp re2;
    Login re3;
    public static String user_name=null;
    StartGame_db log1;
    
//	private String currentuser;
    
    public StartGame(){
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	log1 = new StartGame_db();
//    	currentuser ="Martin";
//    	currentuser =current.getcurrentuser();
    	
//    	String n= currentuser.getcurrentuser();
    	
    	current= new Register_db();
        creategame = new JButton("Create");
        creategame.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new JFrame().dispose();  
        		re2 = new ChadGameApp(); 
        		
        		
        	}
        });
        	
        
        mp = new MyPanel();  
          
        new Thread(new StartPageMusic()).start();  
        
        getContentPane().setLayout(null);  
        getContentPane().add(creategame);  
        getContentPane().add(mp);  
        creategame.setBounds(199, 470, 101, 52);
        
        txtMartin = new JTextField();
        txtMartin.setEditable(false);
        txtMartin.setVerifyInputWhenFocusTarget(false);
        txtMartin.setSelectionColor(UIManager.getColor("InternalFrame.activeTitleGradient"));
        txtMartin.setBounds(346, 54, 86, 20);
        txtMartin.setText(user_name);
        
        getContentPane().add(txtMartin);
        txtMartin.setColumns(10);
        
        
        
        profile = new JButton("Profile");
        profile.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new JFrame().dispose();
        		Profile.user_name1 = user_name;
        		
    			re = new Profile();
        	}
        });
        profile.setBounds(343, 85, 89, 23);
        getContentPane().add(profile);
        
        history = new JButton("History");
        history.setBounds(343, 119, 89, 23);
        getContentPane().add(history);
        
        JButton button = new JButton("Login out");
        button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setVisible(false);  
        		
        		re3 = new Login();
        	}
        });
        button.setBounds(343, 153, 89, 23);
        getContentPane().add(button);
        
        JButton btnUnregister = new JButton("Unregister");
        
    	btnUnregister.addActionListener(log1);
    	log1.setButton(btnUnregister);
    	log1.setuser(txtMartin);
        btnUnregister.setBounds(10, 119, 114, 23);
        getContentPane().add(btnUnregister);
        history.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new JFrame().dispose();
        		History.user_name2 = user_name;
    			re1 = new History();
        	}
        });
        
        
        
        
        mp.setBounds(0, 0, 500, 600);
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
    	

