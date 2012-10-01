package ui.conf.model;

import java.util.Hashtable;

import domain.core.algconfiguration.Function;

public class GeneModel extends Model{
	
	private Hashtable<String, Boolean> isDataFitedHashtable = new Hashtable<String, Boolean>();
	
	private Integer individualNumber;
	private Integer normalGeneNumber;
	private Integer normalGeneHeaderLength;
	
	private boolean useHomeoticGene;
	
	private Function connectionFunction;
	
	
	private Integer homeoticGeneNumber;
	private Integer homeoticGeneHeaderLength;
	
	
	
	public GeneModel() {
		super();
		init();
	}
	
	
	private void init(){
		useHomeoticGene = false;
		isDataFitedHashtable.put("individualNumber", false);
		isDataFitedHashtable.put("normalGeneNumber", false);
		isDataFitedHashtable.put("normalGeneHeaderLength", false);
		//isDataFitedHashtable.put("homeoticGeneNumber", false);
		//isDataFitedHashtable.put("homeoticGeneHeaderLength", false);
	}
	


	public Hashtable<String, Boolean> getIsDataFitedHashtable() {
		return isDataFitedHashtable;
	}


	public void setIsDataFitedHashtable(
			Hashtable<String, Boolean> isDataFitedHashtable) {
		this.isDataFitedHashtable = isDataFitedHashtable;
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
	
	
	
	
	

}
