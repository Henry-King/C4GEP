package domain.core.inputmodel;

import java.util.ArrayList;
import java.util.List;

import domain.core.algmodel.genecomponent.Variable;

public class VariableRow extends Row{
	private List<Variable> variableList;
	public VariableRow(int columns) {
		super(columns);
		// TODO Auto-generated constructor stub
		variableList=new ArrayList<Variable>(columns);
	}
	public List<Variable> getVariableList() {
		return variableList;
	}
	public void setVariableList(List<Variable> variableList) {
		this.variableList = variableList;
	}
	public void add(Variable variable){
		variableList.add(variable);
	}
}
