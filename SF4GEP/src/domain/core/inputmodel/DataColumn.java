package domain.core.inputmodel;

public class DataColumn {
   
	
	private boolean readOnly;//ֻ��
    private DataTable table;//dataTable������
    private String columnName;//����
    private String captionName;//��ʾ����
    private int columnIndex;//������
    private int dataType;//������������
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
