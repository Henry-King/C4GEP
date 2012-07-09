package domain.service.alg.baseclass;

import java.util.List;

import domain.core.algmodel.configuration.GepAlgorithm;
import domain.core.algmodel.genecomponent.Function;
import domain.core.algmodel.genecomponent.Variable;

/**
 * 请确保子类有默认构造函数，否则会出现错误
 */
public abstract class Creator {
	private final String name;
	private List<Function> selectedFunctions;
	private List<Variable> variables;
	private float start;
	private float end;
	private int constanListSize;
	public Creator(String name){
		this.name=name;
	}
	@Override
	public final String toString() {
		// TODO Auto-generated method stub
		return name;
	}
	public void setSelectedFunctions(List<Function> functions){
		selectedFunctions=functions;
	}
	
	
	public void setVariables(List<Variable> variables){
		this.variables=variables; 
	}
	public List<Variable> getVariables() {
		return variables;
	}
	
	
	public List<Function> getSelectedFunctions() {
		return selectedFunctions;
	}
	
	public void setRandomConstantRange(float start,float end){
		this.start=start;
		this.end=end;
	}
	public float getStart() {
		return start;
	}
	public float getEnd() {
		return end;
	}
	public int getConstanListSize() {
		return constanListSize;
	}
	public void setConstanListSize(int constanListSize) {
		this.constanListSize = constanListSize;
	}
	public abstract void create(GepAlgorithm algorithm);
}
