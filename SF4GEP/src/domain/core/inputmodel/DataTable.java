package domain.core.inputmodel;
import java.util.Iterator;
import java.util.Map.Entry;

public final class DataTable {
	
	/**
	 * 保存行的集合
	 * 用来保存DataRowCollection的集合
	 */
	private DataRowCollection rows; 
	
	/**
	 * 保存列的集合
	 * 用于保存DataColumn的对象
	 */
	private DataColumnCollection columns; 
	
	/**
	 * 保存Table的名字
	 */
	private String tableName; //表名
	
	/**
	 * 设置是否只读
	 * 默认false
	 */
    private boolean readOnly = false;
    
    /**
     * 下一行的引用
     * 初始值为0
     */
	private int nextRowIndex = 0;
	
	/**
	 * 标记对象
	 */
	private Object tag;
	
	/**
	 * 默认的构造函数
	 * 初始化 行的集合、列的集合
	 */
	public DataTable() {
		  this.columns = new DataColumnCollection();
		  this.rows = new DataRowCollection();
		  this.rows.setColumns(columns);
    }
	
	/**
	 * 设置表的名称
	 * @param dataTableName
	 */
	public DataTable(String dataTableName) {
          this();
		  this.tableName = dataTableName;
    }
	
	
	/**
	 * 获取中的行数
	 * @return
	 */
	public int getTotalCount() {
          return rows.size();
	} 
	
	
	/**
	 * 判断是否只读
	 * @return
	 */
    public boolean isReadOnly() {
		  return this.readOnly;
    }   
    
    /**
     * 设置是否只读
     * @param readOnly
     */
	public void setReadOnly(boolean readOnly) {
		  this.readOnly = readOnly;
	}
	
	/**
	 * 获取表名
	 * @return
	 */
	public String getTableName() {
         return this.tableName;
	}
	
	/**
	 * 设置表名
	 * @param tableName
	 */
    public void setTableName(String tableName) {
        this.tableName = tableName;
	}
    
    
    /**
     * 获得行的集合
     * @return
     */
    public DataRowCollection getRows() {
            return this.rows;
     }
    
    /**
     * 获得列的集合
     * @return
     */
    public DataColumnCollection getColumns() {
    	    return this.columns;
    }
    
    
    
    /**
     * 获取给定行号的给定列名的值
     * @param row	行号
     * @param colName	列名
     * @return
     */
    public Object getValue(int row,String colName) {
    		return this.rows.get(row).getValue(colName);
    }
    
    
    
    /**
     * 获取给定行号和列号的值
     * @param row	行号
     * @param col	列号
     * @return
     */
    public Object getValue(int row,int col) {
    	     return this.rows.get(row).getValue(col);
    }
    
    
    /**
     * 添加新的一行
     * @return
     * @throws Exception
     */
    public DataRow newRow() throws Exception {
             DataRow tempRow = new DataRow(this);
             nextRowIndex = nextRowIndex < this.rows.size() ? this.rows.size(): nextRowIndex;
    	     tempRow.setColumns(this.columns);
    	     tempRow.setRowIndex(nextRowIndex++);
    	     return tempRow;      
    }
    
    
    /**
     * 根据行号和列号设定某一单元格的值
     * @param row
     * @param col	行号
     * @param value
     */
    public void setValue(int row,int col,Object value) {
             this.rows.get(row).setValue(col, value);
    }
       
    /**
     * 根据行号和列名设置单元格的值
     * @param row
     * @param colName	行名
     * @param value
     */
    public void setValue(int row,String colName,Object value) {
    		 this.rows.get(row).setValue(colName, value);
    }
    
    
    
    public Object getTag() {
		return tag;
	}
	public void setTag(Object tag) {
		this.tag = tag;
	}
	
	
	/**
	 * 添加新的一列，为新的一列指定列名
	 * 返回该行的实例
	 * @param columnName
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	public DataColumn addColumn(String columnName,
        int dataType) throws Exception {
		return this.columns.addColumn(columnName, dataType);
	}
	
	
	/**
	 * 添加新的一行
	 * @param row
	 * @throws Exception
	 */
	public void addRow(DataRow row) throws Exception {
		if (row.getRowIndex() > this.rows.size())
		    row.setRowIndex(this.rows.size());
			 this.rows.add(row);
	}
	
	
	/**
	 * 获取某一列
	 * @param index
	 * @return
	 */
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
   
   
   /**
    * 添加新的一行
    * @param columnName
    */
public void addColumn(String  columnName) {
	this.columns.addColumn(columnName);
	
}



}
