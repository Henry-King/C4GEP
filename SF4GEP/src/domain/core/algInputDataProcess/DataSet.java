package domain.core.algInputDataProcess;

import java.util.List;

public class DataSet {
	private String name;
	private Integer rowNum;
	private Integer columnNum;
	private List<DataRow> dataRow;
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
	public List<DataRow> getDataRow() {
		return dataRow;
	}
	public void setDataRow(List<DataRow> dataRow) {
		this.dataRow = dataRow;
	}
}
