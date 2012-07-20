package domain.core.algInputDataProcess;

import java.io.Serializable;
import java.util.List;

public class DataSet implements Serializable{
	private static final long serialVersionUID = -7595934698740818506L;
	private Integer id;
	private String name;
	private Integer rowNum;
	private Integer columnNum;
	private List<DataRow> dataRows;
	public DataSet(){
		
	}
	public DataSet(int rows,int columns,String name){
		rowNum=rows;
		columnNum=columns;
		this.name=name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getRowNum() {
		return rowNum;
	}
	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}
	public Integer getColumnNum() {
		return columnNum;
	}
	public void setColumnNum(Integer columnNum) {
		this.columnNum = columnNum;
	}
	public List<DataRow> getDataRows() {
		return dataRows;
	}
	public void setDataRows(List<DataRow> dataRows) {
		this.dataRows = dataRows;
	}
	public List<DataColumn> getVariableUsed(){
		return dataRows.get(0).getDataColumns();
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof DataSet){
			DataSet o=(DataSet) obj;
			return o.getColumnNum().equals(columnNum)
					&&o.getDataRows().equals(dataRows)
					&&o.getName().equals(name)
					&&o.getRowNum().equals(rowNum);
		}
		else {
			return false;
		}
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int result=37;
		result=37*result+name.hashCode();
		result=37*result+rowNum.hashCode();
		result=37*result+columnNum.hashCode();
		result=37*result+dataRows.hashCode();
		return result;
	}
}
