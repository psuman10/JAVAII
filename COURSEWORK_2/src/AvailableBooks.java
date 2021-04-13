import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.swing.*;
import javax.swing.table.TableModel;
public class AvailableBooks {
	public static void main(String[] args) {
		AvailableBooks vm = new AvailableBooks();

	}

	AvailableBooks() {

		JFrame f = new JFrame("Bookstore Inventory Management System");
		JButton btnBack, btnDelete, btnUpdate, btnSell, btnSearch;
		JLabel lNo, lAbl;
		String column[] = { "Book Number", "Book Name", "Author Name", "Date Published", "Price",
				"Quantity Available" };

		lAbl = new JLabel("Available Books");
		f.add(lAbl);
		lAbl.setBounds(400, 30, 300, 100);

		String query = "Select * from books";
		DbConnection db = new DbConnection();
		ArrayList<Books> book = new ArrayList<Books>();

		try {
			ResultSet result = db.connection().executeQuery(query);

			while (result.next()) {
				String BookName = result.getString("b_name");
				String AuthorName = result.getString("a_name");
				String date = result.getString("p_date");
				int price = Integer.parseInt(result.getString("price"));
				int bookNumber = Integer.parseInt(result.getString("bcode"));
				int Quantity = Integer.parseInt(result.getString("quantity"));
				Books stff = new Books(BookName, AuthorName, date, price, bookNumber, Quantity);
				book.add(stff);



			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		lNo = new JLabel("Number of books available:" + book.size());
		f.add(lNo);
		lNo.setBounds(50, 360, 300, 100);

		Object data[][] = new Object[book.size()][column.length];

		for (int i = 0; i < book.size(); i++) {
			data[i][0] = book.get(i).bookNumber;
			data[i][1] = book.get(i).BookName;
			data[i][2] = book.get(i).AuthorName;
			data[i][3] = book.get(i).date;
			data[i][4] = book.get(i).price;
			data[i][5] = book.get(i).Quantity;
		}

		JTable jt = new JTable(data, column);
		JScrollPane sp = new JScrollPane(jt);
		f.add(sp);
		sp.setBounds(50, 100, 800, 300);

	

		btnBack = new JButton("Back");
		f.add(btnBack);
		btnBack.setBounds(10, 10, 70, 30);

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Dash();
				f.dispose();
			}
		});

		btnDelete = new JButton("Delete");
		f.add(btnDelete);
		btnDelete.setBounds(500, 550, 150, 30);

		btnDelete.addActionListener(e -> {
			int row = jt.getSelectedRow();
			if (row >= 0) {

				TableModel model = jt.getModel();

				String code = Integer.toString((int) model.getValueAt(row, 0));

				String dquery = "Delete from books WHERE `bcode` = '" + code + "'";
				try {
					int dresult = db.connection().executeUpdate(dquery);
					if (dresult >= 1) {
						JOptionPane.showMessageDialog(sp, "Book Deleted");
						new AvailableBooks();
						f.dispose();
					}

				} catch (SQLException e1) {

					e1.printStackTrace();
				}

			} else {
				JOptionPane.showMessageDialog(sp, "please select row");
			}

		});

		btnUpdate = new JButton("Update");
		f.add(btnUpdate);
		btnUpdate.setBounds(300, 550, 150, 30);

		btnUpdate.addActionListener(e -> {
			int row = jt.getSelectedRow();
			if (row >= 0) {

				JLabel lbName = new JLabel("Book Name");
				f.add(lbName);

				lbName.setBounds(10, 600, 900, 30);

				JTextField tfbName = new JTextField();
				f.add(tfbName);
				tfbName.setBounds(80, 600, 120, 30);

				TableModel model = jt.getModel();
				String BookName = (String) model.getValueAt(row, 1);

				tfbName.setText(BookName);

				JLabel laName = new JLabel("Author Name");
				f.add(laName);

				laName.setBounds(210, 600, 300, 30);

				JTextField tfaName = new JTextField();
				f.add(tfaName);
				tfaName.setBounds(310, 600, 120, 30);

				TableModel model1 = jt.getModel();
				String AuthorName = (String) model1.getValueAt(row, 2);

				tfaName.setText(AuthorName);

				JLabel lpDate = new JLabel("Date");
				f.add(lpDate);

				lpDate.setBounds(470, 600, 300, 30);

				JTextField tfpDate = new JTextField();
				f.add(tfpDate);
				tfpDate.setBounds(530, 600, 150, 30);

				TableModel model2 = jt.getModel();

				String date = (String) model2.getValueAt(row, 3);
				tfpDate.setText(date);
				
				JLabel laddBy = new JLabel("Price");
				f.add(laddBy);

				laddBy.setBounds(690, 600, 300, 30);

				JTextField tfaddBy = new JTextField();
				f.add(tfaddBy);
				tfaddBy.setBounds(740, 600, 150, 30);
				
				
				JLabel laddQy = new JLabel("Quantity");
				f.add(laddQy);

				laddQy.setBounds(900, 600, 300, 30);

				JTextField tfaddQy = new JTextField();
				f.add(tfaddQy);
				tfaddQy.setBounds(980, 600, 150, 30);

				TableModel model3 = jt.getModel();

				String price1 = Integer.toString((int) model3.getValueAt(row, 4));
				tfaddBy.setText(price1);
				
				String quantity1 = Integer.toString((int) model3.getValueAt(row, 5));
				tfaddQy.setText(quantity1);

				JButton btnChange = new JButton("Update");
				f.add(btnChange);
				btnChange.setBounds(430, 650, 150, 50);

				JButton btnCancel = new JButton("Cancel");
				f.add(btnCancel);
				btnCancel.setBounds(430, 720, 150, 50);

				

				btnChange.addActionListener(e3 -> {

					String tbName = tfbName.getText();
					String taName = tfaName.getText();
					String tpDate = tfpDate.getText();
					String price = tfaddBy.getText();
					String Quantity = tfaddQy.getText();
				

					TableModel model4 = jt.getModel();
					String Code = Integer.toString((int) model4.getValueAt(row, 0));
					String BookName1 = (String) model4.getValueAt(row, 1);
					
					String uquery = "update `books` set `b_name` = '" + tbName + "',`a_name` = '" + taName
							+ "', `price` = '" + price + "', `quantity` = '" + Quantity + "',  `p_date` = '" + tpDate + "' WHERE `bcode` = '" + Code
							+ "'";
				
					String squery = "update `soldb` set `b_name` = '" + tbName + "',`a_name` = '" + taName
							+ "', `price` = '" + price + "', `quantity` = '" + Quantity + "',  `p_date` = '" + tpDate + "' WHERE `b_name` = '"
							+ BookName1 + "'";
					
					try {
						int uresult = db.connection().executeUpdate(uquery);
						int sresult = db.connection().executeUpdate(squery);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
						
							JOptionPane.showMessageDialog(sp, "Book Updated");
							new AvailableBooks();
							f.dispose();
						
					

				});

				

				btnCancel.addActionListener(e2 -> {

					new AvailableBooks();
					f.dispose();

				});

			} else {
				JOptionPane.showMessageDialog(sp, "selected Row");
			}

		});

		JTextField tfbSearch = new JTextField();
		f.add(tfbSearch);
		tfbSearch.setBounds(50, 70, 170, 30);

		btnSearch = new JButton("Search");
		f.add(btnSearch);
		btnSearch.setBounds(230, 70, 100, 30);

		btnSearch.addActionListener(e3 -> {
			String Search = tfbSearch.getText();
			String query2 = "select * from books where a_name='" + Search + "' or b_name='" + Search + "' or p_date='"
					+ Search + "'";
			try {
				ResultSet result4 = db.connection().executeQuery(query2);
				if (result4.next()) {
					int bookNumber = Integer.parseInt(result4.getString("bcode"));

					BinarySearch se = new BinarySearch();
					ArrayList<Integer> Quan = new ArrayList<Integer>();
					for (int i = 0; i < book.size(); i++) {
						int quann = book.get(i).bookNumber;
						Quan.add(quann);

					}

					int Index = se.binarySearch(Quan, bookNumber);
					Object data1[][] = new Object[1][column.length];
					JTable jt1 = new JTable(data1, column);
					JScrollPane sp1 = new JScrollPane(jt1);
					f.add(sp1);
					sp1.setBounds(50, 450, 800, 38);

					data1[0][0] = book.get(Index).bookNumber;
					data1[0][1] = book.get(Index).BookName;
					data1[0][2] = book.get(Index).AuthorName;
					data1[0][3] = book.get(Index).date;
					data1[0][4] = book.get(Index).price;
					data1[0][5] = book.get(Index).Quantity;
				}

			} catch (SQLException e1) {

				e1.printStackTrace();
			}

		});

		btnSell = new JButton("Sell");
		f.add(btnSell);
		btnSell.setBounds(870, 200, 100, 30);

		btnSell.addActionListener(e -> {
			int row = jt.getSelectedRow();

			if (row >= 0) {

				TableModel model = jt.getModel();

				String code = Integer.toString((int) model.getValueAt(row, 0));

				String BookName = (String) model.getValueAt(row, 1);
				String AuthorName = (String) model.getValueAt(row, 2);
				String date = (String) model.getValueAt(row, 3);

				String price = Integer.toString((int) model.getValueAt(row, 4));
				int Quan = 1;
				int Quantity = ((int) model.getValueAt(row, 5) - 1);

				String query1 = "Select * from soldb where b_name='" + BookName + "'";
				try {
					ResultSet result3 = db.connection().executeQuery(query1);

					if (result3.next()) {
						int prevQuan = Integer.parseInt(result3.getString("quantity"));

					
						String uquery = "update `books` set `quantity` = '" + Quantity + "' WHERE `bcode` = '" + code
								+ "'";
						String urquery = "update `soldb` set `quantity` = '" + prevQuan++ + "' WHERE `b_name` = '"
								+ BookName + "'";
						int uresult = db.connection().executeUpdate(uquery);
						int uaresult = db.connection().executeUpdate(urquery);
						if (uresult >= 1 && uaresult >= 1) {
							JOptionPane.showMessageDialog(sp, "Book Sold");
							new AvailableBooks();
							f.dispose();
						}
					} else {
						String dquery = "update `books` set `quantity` = '" + Quantity + "' WHERE `bcode` = '" + code
								+ "'";
						String aquery = "insert into soldb(b_name,a_name,p_date,price,quantity) values('" + BookName
								+ "','" + AuthorName + "','" + date + "','" + price + "','" + Quan + "') ";
						try {
							int dresult = db.connection().executeUpdate(dquery);
							int aresult = db.connection().executeUpdate(aquery);
							if (aresult >= 1 && dresult >= 1) {
								JOptionPane.showMessageDialog(sp, "Book Sold");
								new AvailableBooks();
								f.dispose();
							}

						} catch (SQLException e1) {

							e1.printStackTrace();
						}
					}
				} catch (SQLException e2) {

					e2.printStackTrace();
				}

			} else {
				JOptionPane.showMessageDialog(sp, "please select row");
			}

		});

		f.setLayout(null);
		f.setSize(1200, 1000);
		f.setVisible(true);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);

	}
}
