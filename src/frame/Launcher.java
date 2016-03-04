package frame;

import java.awt.Dimension;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Launcher {

	public static void main(String[] args) {
		
		frame.SQLiteConnection sqlCon = new frame.SQLiteConnection("org.sqlite.JDBC", "jdbc:sqlite:resources\\CARShop.db");
		
		try{
			Connection conn = frame.SQLiteConnection.getConnection();
			TableModel mod = new frame.MyTableModel(conn, "SoldCars");
			JTable jTable = new JTable(mod);
			
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(mod);
			sorter.setComparator(2, new frame.MyComparator());
			jTable.setRowSorter(sorter);
			jTable.setDefaultRenderer(Object.class, new frame.MyTableRenderer());
			JScrollPane scroll = new JScrollPane(jTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			
			JFrame frame = new JFrame("DownLoad data in JTable");
			frame.setMinimumSize(new Dimension(600, 200));
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().add(scroll);
			frame.pack();
			frame.setVisible(true);
			
			
			if (conn == null){
				conn.close();
			}
		}catch (Exception ex){
			ex.printStackTrace();
		}

	}

}
