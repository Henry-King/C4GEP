package ui.conf.model;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import domain.core.algconfiguration.Function;

public class GeneModel extends Model{
	
	private Map<String, Boolean> isDataFitedMap = new HashMap<String, Boolean>();
	
	private Integer individualNumber;
	private Integer normalGeneNumber;
	private Integer normalGeneHeaderLength;
	
	private boolean useHomeoticGene;
	
	private Function connectionFunction;
	
	
	private Integer homeoticGeneNumber;
	private Integer homeoticGeneHeaderLength;
	private List<Function> functionList;
	
	
	public GeneModel() {
		super();
		init();
	}
	
	
	private void init(){
		useHomeoticGene = false;
		isDataFitedMap.put("individualNumber", false);
		isDataFitedMap.put("normalGeneNumber", false);
		isDataFitedMap.put("normalGeneHeaderLength", false);
		//isDataFitedMap.put("homeoticGeneNumber", false);
		//isDataFitedMap.put("homeoticGeneHeaderLength", false);
	}
	


	public Map<String, Boolean> getIsDataFitedMap() {
		return isDataFitedMap;
	}


	public void setIsDataFitedMap(
			Map<String, Boolean> isDataFitedMap) {
		this.isDataFitedMap = isDataFitedMap;
	}


	public Integer getIndividualNumber() {
		return individualNumber;
	}


	public void setIndividualNumber(Integer individualNumber) {
		this.individualNumber = individualNumber;
	}


	public Integer getNormalGeneNumber() {
		return normalGeneNumber;
	}


	public void setNormalGeneNumber(Integer normalGeneNumber) {
		this.normalGeneNumber = normalGeneNumber;
	}


	public Integer getNormalGeneHeaderLength() {
		return normalGeneHeaderLength;
	}


	public void setNormalGeneHeaderLength(Integer normalGeneHeaderLength) {
		this.normalGeneHeaderLength = normalGeneHeaderLength;
	}


	public Function getConnectionFunction() {
		return connectionFunction;
	}


	public void setConnectionFunction(Function connectionFunction) {
		this.connectionFunction = connectionFunction;
	}


	public Integer getHomeoticGeneNumber() {
		return homeoticGeneNumber;
	}


	public void setHomeoticGeneNumber(Integer homeoticGeneNumber) {
		this.homeoticGeneNumber = homeoticGeneNumber;
	}


	public Integer getHomeoticGeneHeaderLength() {
		return homeoticGeneHeaderLength;
	}


	public void setHomeoticGeneHeaderLength(Integer homeoticGeneHeaderLength) {
		this.homeoticGeneHeaderLength = homeoticGeneHeaderLength;
	}


	public boolean isUseHomeoticGene() {
		return useHomeoticGene;
	}


	public void setUseHomeoticGene(boolean useHomeoticGene) {
		this.useHomeoticGene = useHomeoticGene;
	}


	public List<Function> getFunctionList() {
		return functionList;
	}


	public void setFunctionList(List<Function> functionList) {
		this.functionList = functionList;
	}
	
	
	
	
	

}
