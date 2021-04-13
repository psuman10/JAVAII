import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class Loogin {
    public static void main(String[] args) {
        Login log = new Login();
        
    }
}
class Login {
	DbConnection db= new DbConnection();
    Login() {
        JFrame f = new JFrame("Library");
        JLabel lUser, lpsw,lhead,lneed;
        JTextField tfUser;
        JPasswordField tfpsw;
        JButton btnLogin,btnSignUp;
        
        //user label
        lUser = new JLabel("Username");
        f.add(lUser);
        lUser.setBounds(125, 115, 100, 100);
        
        
        lpsw = new JLabel("Password");
        f.add(lpsw);
        lpsw.setBounds(125, 165, 100, 100);
        
        lhead = new JLabel("Welcome to book store");
        f.add(lhead);
        lhead.setBounds(220, 25, 150, 150);
        
        lneed = new JLabel("Don't Have An Account?");
        f.add(lneed);
        lneed.setBounds(130, 400, 150, 150);
        
        
        tfUser = new JTextField(30);
        f.add(tfUser);
        tfUser.setBounds(200, 150,200,30);
        
        
        tfpsw = new JPasswordField(30);
        f.add(tfpsw);
        tfpsw.setBounds(200, 200,200,30);
        
        
        btnLogin = new JButton("Login");
        f.add(btnLogin);
        btnLogin.setBounds(250,310,100,40);
        
        btnSignUp = new JButton("SignUp");
        f.add(btnSignUp);
        btnSignUp.setBounds(290,460,100,40);
        
        
        btnLogin.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {

                String user = tfUser.getText();
                String psw = tfpsw.getText();
                int value=userLogin(user,psw);
                
            	
               
					if (value==1) {
						new Dash();
						f.dispose();
					}else if (value==2){
						JOptionPane.showMessageDialog(f, "Password Not Matched ");
						
					}else {
						JOptionPane.showMessageDialog(f, "Username/Password Not Valid ");
					}
           
                
            
            }
            
        });
        
        btnSignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Reg();
                f.dispose();
                
            }
        });
        
      
        f.setLayout(null);
        f.setSize(600, 600);
        f.setVisible(true);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
    }
    
    public int userLogin(String username, String psw) {
    	String query="Select * from accountsign where UserName='"+username+"'";
    	
        try {
			ResultSet result=  db.connection().executeQuery(query);
	 		
			
			if (result.next()) {
				if (result.getString(3).equals(psw)) {
					return 1;
				}else if (!result.getString(3).equals(psw)){
					return 2;
					
				}

			}
	
			
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		return 0;
    	
    }
    
    
}
