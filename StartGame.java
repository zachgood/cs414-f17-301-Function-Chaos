import java.awt.Graphics;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
  
import javax.swing.ImageIcon;  
import javax.swing.JButton;  
import javax.swing.JFrame;  
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;  
 
public class StartGame extends JFrame{  
    JButton creategame;  
    MyPanel mp;  
    Register_db current;
    private JTextField textField;
    
    
    public static void main(String[] args) {  
        new StartGame();  
    }  
    
    StartGame(){  
    	
    	current= new Register_db();
        creategame = new JButton("Create");  
        mp = new MyPanel();  
          
        new Thread(new StartPageMusic()).start();  
        
        getContentPane().setLayout(null);  
        getContentPane().add(creategame);  
        getContentPane().add(mp);  
        creategame.setBounds(199, 470, 101, 52);
        
        textField = new JTextField();
        textField.setEditable(false);
        textField.setVerifyInputWhenFocusTarget(false);
        textField.setSelectionColor(UIManager.getColor("InternalFrame.activeTitleGradient"));
        textField.setBounds(346, 54, 86, 20);
        textField.setText("1");
        
        getContentPane().add(textField);
        textField.setColumns(10);
        
        
        
        
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
}

