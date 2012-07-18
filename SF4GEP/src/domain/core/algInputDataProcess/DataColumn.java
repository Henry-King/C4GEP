package domain.core.algInputDataProcess;

import java.io.Serializable;

public class DataColumn implements Cloneable,Serializable{
	private static final long serialVersionUID = -6377488076384527164L;
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
}
