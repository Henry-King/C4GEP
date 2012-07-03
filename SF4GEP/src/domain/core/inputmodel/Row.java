package domain.core.inputmodel;

public abstract class Row {
	private int columns;
	public Row(int columns){
		this.columns=columns;
	}
	public int getColumns() {
		return columns;
	}
	public void setColumns(int columns) {
		this.columns = columns;
	}
}
