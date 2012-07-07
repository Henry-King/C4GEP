package domain.core.inputmodel;
import java.util.Iterator;
import java.util.Map.Entry;

public final class DataTable {
	
	/**
	 * �����еļ���
	 * ��������DataRowCollection�ļ���
	 */
	private DataRowCollection rows; 
	
	/**
	 * �����еļ���
	 * ���ڱ���DataColumn�Ķ���
	 */
	private DataColumnCollection columns; 
	
	/**
	 * ����Table������
	 */
	private String tableName; //����
	
	/**
	 * �����Ƿ�ֻ��
	 * Ĭ��false
	 */
    private boolean readOnly = false;
    
    /**
     * ��һ�е�����
     * ��ʼֵΪ0
     */
	private int nextRowIndex = 0;
	
	/**
	 * ��Ƕ���
	 */
	private Object tag;
	
	/**
	 * Ĭ�ϵĹ��캯��
	 * ��ʼ�� �еļ��ϡ��еļ���
	 */
	public DataTable() {
		  this.columns = new DataColumnCollection();
		  this.rows = new DataRowCollection();
		  this.rows.setColumns(columns);
    }
	
	/**
	 * ���ñ������
	 * @param dataTableName
	 */
	public DataTable(String dataTableName) {
          this();
		  this.tableName = dataTableName;
    }
	
	
	/**
	 * ��ȡ�е�����
	 * @return
	 */
	public int getTotalCount() {
          return rows.size();
	} 
	
	
	/**
	 * �ж��Ƿ�ֻ��
	 * @return
	 */
    public boolean isReadOnly() {
		  return this.readOnly;
    }   
    
    /**
     * �����Ƿ�ֻ��
     * @param readOnly
     */
	public void setReadOnly(boolean readOnly) {
		  this.readOnly = readOnly;
	}
	
	/**
	 * ��ȡ����
	 * @return
	 */
	public String getTableName() {
         return this.tableName;
	}
	
	/**
	 * ���ñ���
	 * @param tableName
	 */
    public void setTableName(String tableName) {
        this.tableName = tableName;
	}
    
    
    /**
     * ����еļ���
     * @return
     */
    public DataRowCollection getRows() {
            return this.rows;
     }
    
    /**
     * ����еļ���
     * @return
     */
    public DataColumnCollection getColumns() {
    	    return this.columns;
    }
    
    
    
    /**
     * ��ȡ�����кŵĸ���������ֵ
     * @param row	�к�
     * @param colName	����
     * @return
     */
    public Object getValue(int row,String colName) {
    		return this.rows.get(row).getValue(colName);
    }
    
    
    
    /**
     * ��ȡ�����кź��кŵ�ֵ
     * @param row	�к�
     * @param col	�к�
     * @return
     */
    public Object getValue(int row,int col) {
    	     return this.rows.get(row).getValue(col);
    }
    
    
    /**
     * ����µ�һ��
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
     * �����кź��к��趨ĳһ��Ԫ���ֵ
     * @param row
     * @param col	�к�
     * @param value
     */
    public void setValue(int row,int col,Object value) {
             this.rows.get(row).setValue(col, value);
    }
       
    /**
     * �����кź��������õ�Ԫ���ֵ
     * @param row
     * @param colName	����
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
	 * ����µ�һ�У�Ϊ�µ�һ��ָ������
	 * ���ظ��е�ʵ��
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
	 * ����µ�һ��
	 * @param row
	 * @throws Exception
	 */
	public void addRow(DataRow row) throws Exception {
		if (row.getRowIndex() > this.rows.size())
		    row.setRowIndex(this.rows.size());
			 this.rows.add(row);
	}
	
	
	/**
	 * ��ȡĳһ��
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
	 return "û���ҵ�";		
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
    * ����µ�һ��
    * @param columnName
    */
public void addColumn(String  columnName) {
	this.columns.addColumn(columnName);
	
}



}
