package domain.core.inputmodel;

import java.util.ArrayList;
import java.util.List;

public class DataRowCollection {
	DataColumnCollection columns;
	List<DataRow> dataRows;

	public DataRowCollection() {
		columns = new DataColumnCollection();
		dataRows = new ArrayList<DataRow>();
	}

	public DataRowCollection(List<DataRow> rows, DataColumnCollection columns) {
		this.columns = columns;
		this.dataRows = rows;
	}

	public void add(int index, DataRow row) {
		if (index < dataRows.size())
			dataRows.add(index, row);
		else
			dataRows.add(dataRows.size(), row);
	}

	public void add(DataRow row) {
		dataRows.add(row);
	}

	public void add(Object obj) {
		dataRows.add((DataRow) obj);
	}

	public void add(int index, Object obj) {
		dataRows.add(index, (DataRow) obj);
	}

	public int size() {

		return dataRows.size();
	}

	public DataRow get(int index) {

		return dataRows.get(index);
	}

	public void setColumns(DataColumnCollection columns2) {
		this.columns = columns2;

	}

}
