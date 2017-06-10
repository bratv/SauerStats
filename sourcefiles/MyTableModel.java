package pkg3;

import java.util.Vector;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4647644389069485044L;
	private TableModelEvent event;
	private Vector<Vector<Object>> data;
	private Vector<String> columnNames;
	
	
	public MyTableModel() {
		
	}
	
	public MyTableModel(Vector<Vector<Object>> data, Vector<String> col) {
		this.data = data;
		this.columnNames = col;
	}
	@Override
	public int getColumnCount() {
		return 14;
	}
	
	public String getColumnName(int col) {
		return columnNames.get(col);
	}
	
	public void setColumns(Vector<String> columns) {
		columnNames = columns;
	}

	@Override
	public int getRowCount() {
		
		return data.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		
		return data.get(row).get(col);
	}
	
	public void setData(Vector<Vector<Object>> d) {
		data = d;
		fireTableDataChanged();
	}
	
//	public void setValueAt(Object value, int row, int col) {
//		
//	}
	
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
	
	
	


}
