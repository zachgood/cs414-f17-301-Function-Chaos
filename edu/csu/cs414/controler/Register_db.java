package edu.csu.cs414.controler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import java.sql.ResultSet;  
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import edu.csu.cs414.model.Register;

public class Register_db extends ConnectionMySQL implements ActionListener{
	
	JTextField Usertext, passwordtext, emailtext, gendertext, addresstext;
	JButton Sumbit,Reset;
	Statement stmt;
	Register re;
	
	
	public void setname(JTextField n){  
		Usertext = n;  
    }  
    public void setpassword(JTextField p){  
    	passwordtext = p;  
    }  
    public void setemail(JTextField s){  
    	emailtext = s;  
    } 
    public void setgender(JTextField g){  
    	gendertext = g;  
    } 
    public void setaddress(JTextField a){  
    	addresstext = a;  
    } 
    
    public void setSumbitButton(JButton b1){  
    	Sumbit = b1;  
    }  
    public void setResetButton(JButton b2){  
    	Reset = b2;  
    }  
    
     public String getcurrentuser(){
    	return Usertext.getText();
    }
	
    public void actionPerformed(ActionEvent e){  
        if(e.getSource() == Sumbit){  
            if(Usertext.getText().equals(""))            //if the username is null   
                JOptionPane.showMessageDialog(null, "Please input your Username","Warning",JOptionPane.WARNING_MESSAGE);  
            else if(passwordtext.getText().equals(""))  
                JOptionPane.showMessageDialog(null,"Please input your password","Warning",JOptionPane.WARNING_MESSAGE);  
            else{  
            	int UserID =001;
                String name = Usertext.getText();  
                String password = passwordtext.getText(); 
                String email = emailtext.getText();  
                String gender = gendertext.getText(); 
                String address = addresstext.getText();  
                
                try {  
                	ConnectionMySQL();  
                    writeInSql(UserID, name, password, email, gender, address);  
                } catch (Exception e1) {  
                    System.out.println("Registeration error");  
                    e1.printStackTrace();  
                }  
            }  
        }  
        else if(e.getSource() == Reset){  
        	Usertext.setText("");  
        	passwordtext.setText("");  
        	addresstext.setText("");  
        	emailtext.setText("");  
        	gendertext.setText("");  
        }  
    }
    
   
    
	private void writeInSql(int UserID, String name, String password, String email, String gender, String address) throws Exception{
		// TODO Auto-generated method stub
		 String sql;  
         
		 	Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/chadgame","root","123456");
		    Statement stmt = connect.createStatement();  
	        
	        
	        //insert data
	        sql = "insert into profile(UserID,UserName,Password,Email,Gender,Address) values('"+UserID+"','"+name+"','"+password+"','"+email+"','"+gender+"','"+address+"')";  
	        int rw = stmt.executeUpdate(sql);  
//	        System.out.println(sql);  
	        if(rw <= 0){             //judgment  
	            JOptionPane.showMessageDialog(null,"Register error");  
	        }  
	        else{  
	            JOptionPane.showMessageDialog(null, "Register successful");  
	            new JFrame().dispose();  
	            re = new Register(); 
	            
	            
	            
	        }  
	 }  
} 
	

