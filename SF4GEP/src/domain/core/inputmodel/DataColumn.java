package domain.core.inputmodel;

public class DataColumn {
   
	
	private boolean readOnly;//只读
    private DataTable table;//dataTable的引用
    private String columnName;//列名
    private String captionName;//显示名称
    private int columnIndex;//列索引
    private int dataType;//数据类型名称
    public DataColumn(){
    	this("default1");
    }
    public DataColumn(int dataType){
    	this("default1",dataType);
    }
    public DataColumn(String columnName){
    	this(columnName,0);
    	
    }
    public DataColumn(String columnName,int dataType){
    	this.setDataType(dataType);
    	this.columnName=columnName;
    }
    public boolean isReadOnly() {
		return readOnly;
	}
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}
	public DataTable getTable() {
		return table;
	}
	public void setTable(DataTable table) {
		this.table = table;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getCaptionName() {
		return captionName;
	}
	public void setCaptionName(String captionName) {
		this.captionName = captionName;
	}
	public int getColumnIndex() {
		return columnIndex;
	}
	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}
	public int getDataType() {
		return dataType;
	}
	public void setDataType(int dataType) {
		this.dataType = dataType;
	}
	
	public Object convertTo(Object value){
		return value;
		
	}
	public String toString(){
		return this.columnName;
	}
}
