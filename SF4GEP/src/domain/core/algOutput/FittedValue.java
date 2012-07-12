package domain.core.algOutput;

import java.io.Serializable;

import domain.core.algInputDataProcess.DataRow;

public class FittedValue implements Serializable,Cloneable {
	private static final long serialVersionUID = 7974157432692414973L;
	private Float fittedValue;
	private DataRow dataRow;
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
	 * ��DataRow�ĸ���Ϊshallow copy����������Ϊdeep copy
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
}
