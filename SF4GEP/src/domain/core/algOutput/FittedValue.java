package domain.core.algOutput;

import java.io.Serializable;

import domain.core.algInputDataProcess.DataRow;
import domain.core.algconfiguration.Function;

public class FittedValue implements Serializable,Cloneable {
	private static final long serialVersionUID = 7974157432692414973L;
	private Integer id;
	private Float fittedValue;
	private DataRow dataRow;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Float getFittedValue() {
		return fittedValue;
	}
	public void setFittedValue(Float fittedValue) {
		this.fittedValue = fittedValue;
	}
	public DataRow getDataRow() {
		return dataRow;
	}
	public void setDataRow(DataRow dataRow) {
		this.dataRow = dataRow;
	}
	/**
	 * 对DataRow的复制为shallow copy，其余属性为deep copy
	 */
	@Override
	public FittedValue clone(){
		// TODO Auto-generated method stub
		FittedValue fittedValue=null;
		try {
			fittedValue = (FittedValue) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fittedValue;
	}
	
	

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof FittedValue){
			FittedValue r=(FittedValue) obj;
			return r.getClass().equals(getClass());			
		}
		else {
			return false;
		}
	}
	@Override
	public int hashCode(){
		int result = 17;
		result = 37 * result + (int)id;
		result = 37 * result + Float.floatToIntBits(fittedValue);
		result = 37 * result + dataRow.hashCode();
		return result;
	}
	
	
	
	
	
	
	
	
}
