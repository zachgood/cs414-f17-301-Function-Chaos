package edu.csu.cs414.controler;

import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;  
import java.sql.Statement;

import javax.swing.*;

import edu.csu.cs414.model.Register;
import edu.csu.cs414.model.StartGame;  

public class Login_db extends ConnectionMySQL implements ActionListener {

	JTextField name;
	JTextField password;
	JButton Submit, Register;
	Register re;
	ResultSet rs;
	StartGame re1;

	
	public void setname(JTextField nametext){
		name = nametext;
	}
	
	public void setpwd(JTextField passtext){
		password = passtext;
	}
	
	public void setButton(JButton b1,JButton b2){  
		Submit = b1;  
        Register = b2;  
    }

	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == Submit){  
            if(name.getText().equals(""))           //judge if the UserName is null  
                JOptionPane.showMessageDialog(null, "please input your UserName");  
            else if(password.getText().equals(""))  
                JOptionPane.showMessageDialog(null, "please input your password");  
            else{  
                String name1 = name.getText();  
                String password1 = password.getText();  
                try {  
                	ConnectionMySQL();       //connect to db
                    boolean com = compareWithSql(name1,password1);  
                    if(com)  
                        JOptionPane.showMessageDialog(null, "Login in successful"); 
                    	
                    else{  
                        JOptionPane.showMessageDialog(null, "incorrect username or password");  
                        name.setText("");  
                        password.setText("");  
                    }  
                }   
                catch (Exception e1) {  
                    e1.printStackTrace();  
                }  
            }  
        }  
        else if(e.getSource() == Register){  
        	new JFrame().dispose();  
            re = new Register();  
        }  
	}
		
		boolean compareWithSql(String name1,String password1) throws Exception{
		// TODO Auto-generated method stub
		String sql;       
		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/chadgame","root","123456");
	    Statement stmt = connect.createStatement();
        sql = "select Password from Player where UserName=\""+name1+"\"";  
//      System.out.println(sql);  
        rs = stmt.executeQuery(sql);  
        	while(rs.next()){               
//          String name1 = rs.getString(1);  
            String password2 = rs.getString(1);  
            	if(password2.equals(password1)){  
            		
            		new JFrame().dispose();  
            		re1 = new StartGame(); 
            		return true;  
            		  
            	}          
            }
			return false;  
              
			    
		}

}
