package frame;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class MyTableRenderer extends DefaultTableCellRenderer {

	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		
		setText(value.toString());

		setHorizontalAlignment(SwingConstants.LEFT);
		
		if (isSelected){
			setBackground(Color.BLUE);
			setForeground(Color.WHITE);
		}else {
			setBackground(Color.WHITE);
			setForeground(Color.BLACK);
		}
		return this;
		
	}
}
