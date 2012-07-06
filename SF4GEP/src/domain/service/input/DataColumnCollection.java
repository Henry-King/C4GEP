package domain.service.input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class DataColumnCollection {
  List<DataColumn> columns;
  HashMap<String,DataColumn> nameMap;
  
  public DataColumnCollection(){
	  columns=new ArrayList<DataColumn>();
	  nameMap=new HashMap<String, DataColumn>();
  }
  public DataColumnCollection(List<DataColumn> columns,HashMap<String,DataColumn> nameMap){
	  this.columns=columns;
	  this.nameMap=nameMap;
  }
  
  public void add(int index,DataColumn column){
	 if(index<columns.size())
	  columns.add(index, column);
	 else
	  columns.add(columns.size(),column);
  }
  public void add(DataColumn column){
	  columns.add(column);
  }
  public void add(Object obj){
	  columns.add((DataColumn) obj);
  }
public DataColumn addColumn(String columnName, int dataType) {
	DataColumn column=new DataColumn(columnName, dataType);
	nameMap.put(columnName, column);
	columns.add(column);
	return column;
}
public void addColumn(String columnName) {
	DataColumn column=new DataColumn(columnName);
	nameMap.put(columnName, column);
	columns.add(column);
	
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

  
}
