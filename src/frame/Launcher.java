package com.sql.jdbc.frame;

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
		
		SQLiteConnection sqlCon = new SQLiteConnection("org.sqlite.JDBC", "jdbc:sqlite:f:\\eclipse-jee-kepler\\Programing\\Java Class\\DownloadDataInJTable\\CARShop.db");
		
		try{
			Connection conn = SQLiteConnection.getConnection();
			TableModel mod = new MyTableModel(conn, "SoldCars");
			JTable jTable = new JTable(mod);
			
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(mod);
			sorter.setComparator(2, new MyComparator ());
			jTable.setRowSorter(sorter);
			jTable.setDefaultRenderer(Object.class, new MyTableRenderer());
			JScrollPane scroll = new JScrollPane(jTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			
			JFrame frame = new JFrame("DownLoad data in JTable");
			frame.setMinimumSize(new Dimension(600, 400));
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
