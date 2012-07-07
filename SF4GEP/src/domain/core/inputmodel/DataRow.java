package domain.core.inputmodel;

import java.util.LinkedHashMap;
import java.util.Map;

public class DataRow {
	private int rowIndex = -1;// 定义该行记录在table所处的行数
	private DataColumnCollection columns;

	public void setTable(DataTable table) {
		this.table = table;
		this.columns = table.getColumns();
	}

	public void setItemMap(Map<String, Object> itemMap) {
		this.itemMap = itemMap;
	}

	private DataTable table; // table的一个引用
	private Map<String, Object> itemMap = new LinkedHashMap<String, Object>();

	// 用于存储数据的Map对象，这里保存的对象不包括顺序信息，数据获取的索引通过行信息标识
	public DataRow() {
	}

	public DataRow(DataTable table) {
		this.table = table;
		this.columns = table.getColumns();
	}

	// 获取当前行所属的数据表对象
	public DataTable getTable() {
		return this.table;
	}

	public DataColumnCollection getColumns() {
		return columns;
	}

	public void setColumns(DataColumnCollection columns) {
		this.columns = columns;
	}

	public void setValue(String columnName, Object value) {
		setValue(this.columns.get(columnName), value);
	}

	public void setValue(DataColumn column, Object value) {
		if (column != null) {
			String lowerColumnName = column.getColumnName().toLowerCase();
			if (getItemMap().containsKey(lowerColumnName))
				getItemMap().remove(lowerColumnName);
			getItemMap().put(lowerColumnName, column.convertTo(value));

		}
	}

	public Object getValue(int index) {
		String colName = this.columns.get(index).getColumnName().toLowerCase();
		return this.getItemMap().get(colName);
	}

	public Object getValue(String columnName) {
		return this.getItemMap().get(columnName.toLowerCase());
	}

	public Map<String, Object> getItemMap() {
		return itemMap;
	}

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	public void setValue(int col, Object value) {
		String lowerColumnName = this.columns.get(col).getColumnName()
				.toLowerCase();
		if (getItemMap().containsKey(lowerColumnName))
			getItemMap().remove(lowerColumnName);
		getItemMap().put(lowerColumnName, columns.get(col).convertTo(value));
	}

	public int getRowIndex() {

		return rowIndex;
	}

	/*
	 * public void copyFrom(DataRow row) { this.itemMap.clear();//首先请客当前记录 for
	 * (Object c : this.columns) { this.itemMap.put(c.toString().toLowerCase(),
	 * row.getValue(c.toString())); } }
	 */

}
