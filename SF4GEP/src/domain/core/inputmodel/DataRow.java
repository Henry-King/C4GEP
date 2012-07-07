package domain.core.inputmodel;

import java.util.LinkedHashMap;
import java.util.Map;

public class DataRow {
	private int rowIndex = -1;// ������м�¼��table����������
	private DataColumnCollection columns;

	public void setTable(DataTable table) {
		this.table = table;
		this.columns = table.getColumns();
	}

	public void setItemMap(Map<String, Object> itemMap) {
		this.itemMap = itemMap;
	}

	private DataTable table; // table��һ������
	private Map<String, Object> itemMap = new LinkedHashMap<String, Object>();

	// ���ڴ洢���ݵ�Map�������ﱣ��Ķ��󲻰���˳����Ϣ�����ݻ�ȡ������ͨ������Ϣ��ʶ
	public DataRow() {
	}

	public DataRow(DataTable table) {
		this.table = table;
		this.columns = table.getColumns();
	}

	// ��ȡ��ǰ�����������ݱ����
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
	 * public void copyFrom(DataRow row) { this.itemMap.clear();//������͵�ǰ��¼ for
	 * (Object c : this.columns) { this.itemMap.put(c.toString().toLowerCase(),
	 * row.getValue(c.toString())); } }
	 */

}
