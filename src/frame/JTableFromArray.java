package com.sql.jdbc.frame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.ScrollPane;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JTableFromArray {

	public static void main(String[] args) {
		
		Object [] columnName = {"Фамилия", "Имя", "Отчество"}; 
		
		Object [][] tableData = {
				{"Кудлай", "Владимир", "Олегович"},
				{"Кудлай", "Юрий", "Олегович"},
				{"Пивторак", "Андрей", "Юрьевич"},
				{"Честных", "Алексей", "Алексанрович"},
				{"Ярошенко", "Сергей", "Александрович"}
		};
	
		
		JFrame frame = new JFrame("JTable example");
		frame.getContentPane().setLayout(new FlowLayout());
		frame.setSize(420, 220);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTable jtableFIO = new JTable(tableData, columnName);
		JScrollPane scroll = new JScrollPane (jtableFIO);
		jtableFIO.setPreferredScrollableViewportSize(new Dimension(400, 200));
		frame.getContentPane().add(scroll);
		frame.setVisible(true);
	}

}
