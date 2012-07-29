package domain.core.algInputDataProcess;

import java.io.Serializable;

public class DataColumn implements Cloneable,Serializable{
	private static final long serialVersionUID = 1816337452133558929L;
	private Integer id;
	private String columnName;
	private Float value;
	public DataColumn(){
		
	}
	public DataColumn(String columnName,Float value){
		this.columnName=columnName;
		this.value=value;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof DataColumn){
			DataColumn o=(DataColumn) obj;
			return o.getColumnName().equals(columnName)
					&&o.getValue().equals(value);
		}
		else {
			return false;
		}
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int result=17;
		result=37*result+value.hashCode();
		result=37*result+columnName.hashCode();
		return result;
	}
}
