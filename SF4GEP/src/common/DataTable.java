package common;

import java.util.Iterator;
import java.util.Map.Entry;



/**
 * Datable
 */
public final class DataTable {
	private DataRowCollection rows; //用于保存DataRow的集合对象
	private DataColumnCollection columns; //用于保存DataColumn的对象
	private String tableName; //表名
    private boolean readOnly = false;
	private int nextRowIndex = 0;
	private Object tag;
	
	public DataTable() {
		  this.columns = new DataColumnCollection();
		  this.rows = new DataRowCollection();
		  this.rows.setColumns(columns);
    }
	public DataTable(String dataTableName) {
          this();
		  this.tableName = dataTableName;
    }
	public int getTotalCount() {
          return rows.size();
	} 
    public boolean isReadOnly() {
		  return this.readOnly;
    }   
	public void setReadOnly(boolean readOnly) {
		  this.readOnly = readOnly;
	}
	public String getTableName() {
         return this.tableName;
	}
	
    public void setTableName(String tableName) {
        this.tableName = tableName;
	}
    public DataRowCollection getRows() {
            return this.rows;
     }
    public DataColumnCollection getColumns() {
    	    return this.columns;
    }
    public Object getValue(int row,String colName) {
    		return this.rows.get(row).getValue(colName);
    }
    public Object getValue(int row,int col) {
    	     return this.rows.get(row).getValue(col);
    }
    public DataRow newRow() throws Exception {
             DataRow tempRow = new DataRow(this);
             nextRowIndex = nextRowIndex < this.rows.size() ? this.rows.size(): nextRowIndex;
    	     tempRow.setColumns(this.columns);
    	     tempRow.setRowIndex(nextRowIndex++);
    	     return tempRow;      
    }
    public void setValue(int row,int col,Object value) {
             this.rows.get(row).setValue(col, value);
    }
       
    public void setValue(int row,String colName,Object value) {
    		 this.rows.get(row).setValue(colName, value);
    }
    public Object getTag() {
		return tag;
	}
	public void setTag(Object tag) {
		this.tag = tag;
	}
	public DataColumn addColumn(String columnName,
        int dataType) throws Exception {
		return this.columns.addColumn(columnName, dataType);
	}
	public void addRow(DataRow row) throws Exception {
		if (row.getRowIndex() > this.rows.size())
		    row.setRowIndex(this.rows.size());
			 this.rows.add(row);
	}
    public String getColumnName(int index){
    	
		return columns.get(index).getColumnName();
    	
    }
   public String getColumnName(Object obj){
	 for(int i=0;i<this.rows.size();i++){
	     DataRow row=this.rows.get(i);
	     //System.out.println("ww"+row.getValue(i));
	     for(int j=0;j<this.columns.size();j++){
	    	 Object obj2=this.getValue(i, j);
	    	 //System.out.println("obj2"+obj2.toString()+"  "+obj.toString());
	    	 if(obj2.toString().equals(obj.toString())){
		    	  return row.getColumns().get(j).getColumnName().toLowerCase();
		     }
	     }
	   }	
	 return "没有找到";		
   }
   /* Iterator it=columns.nameMap.entrySet().iterator();
    * while(it.hasNext()){
		Entry entry=(Entry) it.next();
		System.out.println("ww"+entry.getKey());
		if(entry.getValue().equals(obj)){
			return (String) entry.getKey();
		}
		
	}*/
public void addColumn(String  columnName) {
	this.columns.addColumn(columnName);
	
}



}
