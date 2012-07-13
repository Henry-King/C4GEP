package domain.core.algInputDataProcess;

public class DataColumn implements Cloneable{
	private String columnName;
	private Float value;
	public DataColumn(){
		
	}
	public DataColumn(String columnName,Float value){
		this.columnName=columnName;
		this.value=value;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public Float getValue() {
		return value;
	}
	public void setValue(Float value) {
		this.value = value;
	}
	@Override
	public DataColumn clone(){
		// TODO Auto-generated method stub
		DataColumn o=null;
		try {
			o=(DataColumn) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}
}
