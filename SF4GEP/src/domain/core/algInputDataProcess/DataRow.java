package domain.core.algInputDataProcess;

import java.io.Serializable;
import java.util.List;

public class DataRow implements Serializable{
	private static final long serialVersionUID = 1380132131272882724L;
	private Integer id;
	private List<DataColumn> dataColumns;
	private DataColumn resultColumn;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
