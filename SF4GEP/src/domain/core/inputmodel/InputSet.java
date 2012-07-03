package domain.core.inputmodel;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class InputSet {
	private VariableRow variableRow;
	private List<FieldRow> fieldRowList;
	public InputSet(){
		fieldRowList=new LinkedList<FieldRow>();
	}
	public InputSet(int fieldRows){
		fieldRowList=new ArrayList<FieldRow>(fieldRows);
	}
	public VariableRow getVariableRow() {
		return variableRow;
	}
	public void setVariableRow(VariableRow variableRow) {
		this.variableRow = variableRow;
	}
	public List<FieldRow> getFieldRowList() {
		return fieldRowList;
	}
	public void setFieldRowList(List<FieldRow> fieldRowList) {
		this.fieldRowList = fieldRowList;
	}

}
