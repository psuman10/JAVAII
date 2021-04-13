import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.swing.*;
public class SoldBooks {
    public static void main(String[] args) {
    	SoldBooks vm =new SoldBooks();
        
    }
    SoldBooks(){
        
        
        JFrame f=new JFrame("Bookstore Inventory Management System");
        JButton btnBack ;
        JLabel lNo,lAb;
        String column[]= {"Book Number","Book Name","Author Name","Date Published","Price", "Quantity Sold"};
         
        lAb = new JLabel("Sold Books");
        f.add(lAb);
        lAb.setBounds(400, -20, 300, 100);
        
        String query="Select * from soldb";
        DbConnection db= new DbConnection();
        ArrayList<Books> book= new ArrayList<Books>();
        
        
        
        try {
            ResultSet result=  db.connection().executeQuery(query);
            
            while(result.next()) {
                String BookName=result.getString("b_name");
                String AuthorName=result.getString("a_name");
                String date=result.getString("p_date");
                int price=Integer.parseInt(result.getString("price"));
                int bookNumber=Integer.parseInt(result.getString("bcode"));
                int Quantity=Integer.parseInt(result.getString("quantity"));
                Books stff =new Books(BookName, AuthorName, date, price,bookNumber,Quantity);
                book.add(stff);
                
                
            }
            
            
            
            
        }catch(SQLException e) {
            e.printStackTrace();
        }
        lNo = new JLabel("No of books Sold:"+ book.size());
        f.add(lNo);
        lNo.setBounds(50, 410, 300, 100);
        
        Object data[][]= new Object[book.size()] [column.length];
        
        for(int i=0; i<book.size(); i++) {
            data[i][0]=book.get(i).bookNumber;
            data[i][1]=book.get(i).BookName;
            data[i][2]=book.get(i).AuthorName;
            data[i][3]=book.get(i).date;
            data[i][4]=book.get(i).price;
            data[i][5]=book.get(i).Quantity;
        }
 
        
        JTable jt =new JTable(data,column);
        JScrollPane sp=new JScrollPane(jt);
        f.add(sp);
        sp.setBounds(50,50,800,400);
        
        
        //button
        
        btnBack=new JButton("Back");
        f.add(btnBack);
        btnBack.setBounds(30, 10, 70, 30);
        
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Dash();
                f.dispose();
            }
        });
      
        f.setLayout(null);  
        f.setSize(1000,600);
        f.setVisible(true);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        
    }
}

