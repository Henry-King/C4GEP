package domain.core.inputmodel;

import java.util.ArrayList;
import java.util.List;

public class FieldRow extends Row{
	private List<FieldItem> fieldItemList;
	public FieldRow(int columns) {
		super(columns);
		// TODO Auto-generated constructor stub
		fieldItemList=new ArrayList<FieldItem>(columns);
	}
	public List<FieldItem> getFieldItemList() {
		return fieldItemList;
	}
	public void setFieldItemList(List<FieldItem> fieldItemList) {
		this.fieldItemList = fieldItemList;
	}
	public void addItem(FieldItem item){
		fieldItemList.add(item);
	}
	public List<FieldItem> getVariableField(){
		return fieldItemList.subList(0, fieldItemList.size()-2);
	}
}
