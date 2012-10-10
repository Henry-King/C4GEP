package ui.conf.model;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class OperatorModel extends Model{
	private Float mutateRate;
	
	private Float isTransportRate;
	private Integer[] isElement;
	
	private Float risTransportRate;
	private Integer[] risElement;
	
	private Float geneTransportRate;
	
	private Float onePointRecombineRate;
	private Float twoPointRecombineRate;
	
	private Float geneRecombineRate;
	
	private Map<String, Boolean> isDataFitedMap = new HashMap<String, Boolean>();
	
	
	public OperatorModel() {
		super();
		init();
	}
	
	private void init(){
		isDataFitedMap.put("mutateRate", false);
		isDataFitedMap.put("isTransportRate", false);
		isDataFitedMap.put("isElement", false);
		isDataFitedMap.put("risTransportRate", false);
		isDataFitedMap.put("risElement", false);
		isDataFitedMap.put("geneTransportRate", false);
		isDataFitedMap.put("onePointRecombineRate", false);
		isDataFitedMap.put("twoPointRecombineRate", false);
		isDataFitedMap.put("geneRecombineRate", false);
		
	}


	public Float getMutateRate() {
		return mutateRate;
	}

	public void setMutateRate(Float mutateRate) {
		this.mutateRate = mutateRate;
	}

	public Float getIsTransportRate() {
		return isTransportRate;
	}

	public void setIsTransportRate(Float isTransportRate) {
		this.isTransportRate = isTransportRate;
	}

	public Integer[] getIsElement() {
		return isElement;
	}

	public void setIsElement(Integer[] isElement) {
		this.isElement = isElement;
	}

	public Float getRisTransportRate() {
		return risTransportRate;
	}

	public void setRisTransportRate(Float risTransportRate) {
		this.risTransportRate = risTransportRate;
	}

	public Integer[] getRisElement() {
		return risElement;
	}

	public void setRisElement(Integer[] risElement) {
		this.risElement = risElement;
	}

	public Float getGeneTransportRate() {
		return geneTransportRate;
	}

	public void setGeneTransportRate(Float geneTransportRate) {
		this.geneTransportRate = geneTransportRate;
	}

	public Float getOnePointRecombineRate() {
		return onePointRecombineRate;
	}

	public void setOnePointRecombineRate(Float onePointRecombineRate) {
		this.onePointRecombineRate = onePointRecombineRate;
	}

	public Float getTwoPointRecombineRate() {
		return twoPointRecombineRate;
	}

	public void setTwoPointRecombineRate(Float twoPointRecombineRate) {
		this.twoPointRecombineRate = twoPointRecombineRate;
	}

	public Float getGeneRecombineRate() {
		return geneRecombineRate;
	}

	public void setGeneRecombineRate(Float geneRecombineRate) {
		this.geneRecombineRate = geneRecombineRate;
	}

	public Map<String, Boolean> getIsDataFitedMap() {
		return isDataFitedMap;
	}

	public void setisDataFitedMap(
			Hashtable<String, Boolean> isDataFitedMap) {
		this.isDataFitedMap = isDataFitedMap;
	}
	
	
	
	
}
