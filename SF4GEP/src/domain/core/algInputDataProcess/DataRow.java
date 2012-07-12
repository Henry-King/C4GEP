package domain.core.algInputDataProcess;

import java.util.List;

public class DataRow{
	private List<DataColumn> dataColumns;
	private DataColumn resultColumn;
	public List<DataColumn> getDataColumns() {
		return dataColumns;
	}
	public void setDataColumns(List<DataColumn> dataColumns) {
		this.dataColumns = dataColumns;
	}
	public DataColumn getResultColumn() {
		return resultColumn;
	}
	public void setResultColumn(DataColumn resultColumn) {
		this.resultColumn = resultColumn;
	}
}
