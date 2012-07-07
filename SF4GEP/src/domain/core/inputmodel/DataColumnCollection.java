package domain.core.inputmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import domain.core.algmodel.genecomponent.Variable;

public class DataColumnCollection {
	List<DataColumn> columns;
	HashMap<String, DataColumn> nameMap;
	
	List<Variable> variableList = new ArrayList<Variable>();

	public DataColumnCollection() {
		columns = new ArrayList<DataColumn>();
		nameMap = new HashMap<String, DataColumn>();
	}

	public DataColumnCollection(List<DataColumn> columns,
			HashMap<String, DataColumn> nameMap) {
		this.columns = columns;
		this.nameMap = nameMap;
	}

	public void add(int index, DataColumn column) {
		if (index < columns.size())
			columns.add(index, column);
		else
			columns.add(columns.size(), column);
		
		
		
		variableList.add(new Variable(column.getColumnName()));
		
		
	}

	public void add(DataColumn column) {
		
		columns.add(column);
		variableList.add(new Variable(column.getColumnName()));
	}

	public void add(Object obj) {
		
		DataColumn column = (DataColumn) obj;
		columns.add(column);
		variableList.add(new Variable(column.getColumnName()));
	}

	public DataColumn addColumn(String columnName, int dataType) {
		DataColumn column = new DataColumn(columnName, dataType);
		nameMap.put(columnName, column);
		columns.add(column);
		variableList.add(new Variable(column.getColumnName()));
		return column;
	}

	public void addColumn(String columnName) {
		DataColumn column = new DataColumn(columnName);
		nameMap.put(columnName, column);
		columns.add(column);
		variableList.add(new Variable(column.getColumnName()));
	}
	
	

	public DataColumn get(int index) {
		return columns.get(index);
	}

	public DataColumn get(String columnName) {

		return nameMap.get(columnName);

	}

	public int size() {
		// TODO Auto-generated method stub
		return columns.size();
	}
	
	
	public List<Variable> getVariableList(){
		return variableList;
	}

}
