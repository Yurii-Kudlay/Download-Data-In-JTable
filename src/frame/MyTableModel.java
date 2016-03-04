package frame;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {

	private Object[][] contents;
	private String[] columnNames;
	private Class[] colunmClasses;

	public MyTableModel(Connection conn, String tableName) throws SQLException {
		super();
		getTableContents(conn, tableName);
	}

	private void getTableContents(Connection conn, String tableName)
			throws SQLException {
		DatabaseMetaData meta = conn.getMetaData();
		ResultSet result = meta.getColumns(null, null, tableName, null);

		ArrayList colNamesList = new ArrayList();
		ArrayList colTypesList = new ArrayList();

		while (result.next()) {
			colNamesList.add(result.getString("COLUMN_NAME"));
			int dbType = result.getInt("DATA_TYPE");

			if (dbType == Types.INTEGER) {
				colTypesList.add(Integer.class);			
			} else if (dbType == Types.DOUBLE || dbType == Types.REAL) {
				colTypesList.add(Double.class);
			} else if (dbType == Types.DATE || dbType == Types.TIME
					|| dbType == Types.TIMESTAMP) {
				colTypesList.add(java.sql.Date.class);
			} else {
				colTypesList.add(String.class);
			}
		}

		columnNames = new String[colNamesList.size()];
		colNamesList.toArray(columnNames);

		colunmClasses = new Class[colTypesList.size()];
		colTypesList.toArray(colunmClasses);

		Statement state = conn.createStatement();
		result = state.executeQuery("SELECT * FROM " + tableName);

		ArrayList rowList = new ArrayList();

		while (result.next()) {
			ArrayList cellList = new ArrayList();

			for (int i = 0; i < colunmClasses.length; i++) {
				Object cellValue = null;

				if (colunmClasses[i] == String.class) {
					cellValue = result.getString(columnNames[i]);
				} else if (colunmClasses[i] == Integer.class) {
					cellValue = result.getInt(columnNames[i]);
				} else if (colunmClasses[i] == Float.class) {
					cellValue = result.getFloat(columnNames[i]);
				} else if (colunmClasses[i] == Double.class) {
					cellValue = result.getDouble(columnNames[i]);
				} else if (colunmClasses[i] == java.sql.Date.class) {
					cellValue = result.getDate(columnNames[i]);
				} else {
					System.out.println("Field type is not specified "
							+ columnNames[i]);
				}
				cellList.add(cellValue);
			}
			Object[] cells = cellList.toArray();
			rowList.add(cells);
		}

		contents = new Object[rowList.size()][];
		for (int i = 0; i < contents.length; i++) {
			contents[i] = (Object[]) rowList.get(i);
		}

		if (result == null || state == null) {
			result.close();
			state.close();
		}
	}

	@Override
	public int getRowCount() {
		return contents.length;
	}

	@Override
	public int getColumnCount() {
		if (contents.length == 0) {
			return 0;
		} else {
			return contents[0].length;
		}
	}

	@Override
	public Object getValueAt(int row, int column) {
		return contents[row][column];
	}

	@Override
	public Class getColumnClass(int col) {
		return colunmClasses[col];
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

}
