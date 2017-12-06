package edu.csu.cs414.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import edu.csu.cs414.view.Login;
import edu.csu.cs414.view.Register;

public class StartGame_db extends ConnectionMySQL implements ActionListener{
	Login re5;
	JButton btnUnregister;
	JTextField currentuser;
	
	public void setButton(JButton b1){  
		btnUnregister = b1; 
		}
	public void setuser(JTextField user){
		currentuser = user;
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnUnregister){  
			try {  
            	ConnectionMySQL();  
                String name1=currentuser.getText();
                deleteSql(name1); 
                System.out.println("Delete successful");  
            } catch (Exception e1) {  
                System.out.println("Delete error!");  
                e1.printStackTrace();  
            }  
		}
		
	}


	private void deleteSql(String name1) throws SQLException {
		String sql;  
        
	 	Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/chadgame","root","123456");
	    Statement stmt = connect.createStatement();  
        
        
        //insert data
        sql = "delete from player where(UserName='"+name1+"')";  
        int rw = stmt.executeUpdate(sql);  
//        System.out.println(sql);  
        if(rw <= 0){             //judgment  
            JOptionPane.showMessageDialog(null,"Delete error");  
        }  
        else{  
            JOptionPane.showMessageDialog(null, "Delete successful");  
            System.exit(0);  
            re5 = new Login(); 
        } 
	
	}
}

