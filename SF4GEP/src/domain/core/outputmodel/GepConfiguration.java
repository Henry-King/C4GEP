package domain.core.outputmodel;

import java.io.Serializable;

public class GepConfiguration implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3397525135819808968L;
	/**
	 * 
	 */

	private Long id;
	private String name;
	private String inputFile;
	private String populationSize;
	private String normalGeneNumber;
	private String homeoticGeneNumber;
	private String normalHeaderLength;
	private String homeoticHeaderLength;
	private String randomConstantStart;
	private String randomConstantEnd;
	private String constantListSize;
	private String creator;
	private String calculator;
	private String selectionRange;
	private String accuray;
	private String maxGeneration;
	private String functionList;
	private String selector;
	private String modify;
	private String mutateRate;
	private String isTransportRate;
	private String isElement;
	private String risTransportRate;
	private String risElement;
	private String geneTransportRate;
	private String onePointRecombineRate;
	private String twoPointRecombineRate;
	private String geneRecombineRate;
	public GepConfiguration(){
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInputFile() {
		return inputFile;
	}
	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}
	public String getPopulationSize() {
		return populationSize;
	}
	public void setPopulationSize(String populationSize) {
		this.populationSize = populationSize;
	}
	public String getNormalGeneNumber() {
		return normalGeneNumber;
	}
	public void setNormalGeneNumber(String normalGeneNumber) {
		this.normalGeneNumber = normalGeneNumber;
	}
	public String getHomeoticGeneNumber() {
		return homeoticGeneNumber;
	}
	public void setHomeoticGeneNumber(String homeoticGeneNumber) {
		this.homeoticGeneNumber = homeoticGeneNumber;
	}
	public String getNormalHeaderLength() {
		return normalHeaderLength;
	}
	public void setNormalHeaderLength(String normalHeaderLength) {
		this.normalHeaderLength = normalHeaderLength;
	}
	public String getHomeoticHeaderLength() {
		return homeoticHeaderLength;
	}
	public void setHomeoticHeaderLength(String homeoticHeaderLength) {
		this.homeoticHeaderLength = homeoticHeaderLength;
	}
	public String getRandomConstantStart() {
		return randomConstantStart;
	}
	public void setRandomConstantStart(String randomConstantStart) {
		this.randomConstantStart = randomConstantStart;
	}
	public String getRandomConstantEnd() {
		return randomConstantEnd;
	}
	public void setRandomConstantEnd(String randomConstantEnd) {
		this.randomConstantEnd = randomConstantEnd;
	}
	public String getConstantListSize() {
		return constantListSize;
	}
	public void setConstantListSize(String constantListSize) {
		this.constantListSize = constantListSize;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCalculator() {
		return calculator;
	}
	public void setCalculator(String calculator) {
		this.calculator = calculator;
	}
	public String getSelectionRange() {
		return selectionRange;
	}
	public void setSelectionRange(String selectionRange) {
		this.selectionRange = selectionRange;
	}
	public String getAccuray() {
		return accuray;
	}
	public void setAccuray(String accuray) {
		this.accuray = accuray;
	}
	public String getMaxGeneration() {
		return maxGeneration;
	}
	public void setMaxGeneration(String maxGeneration) {
		this.maxGeneration = maxGeneration;
	}
	public String getFunctionList() {
		return functionList;
	}
	public void setFunctionList(String functionList) {
		this.functionList = functionList;
	}
	public String getSelector() {
		return selector;
	}
	public void setSelector(String selector) {
		this.selector = selector;
	}
	public String getModify() {
		return modify;
	}
	public void setModify(String modify) {
		this.modify = modify;
	}
	public String getMutateRate() {
		return mutateRate;
	}
	public void setMutateRate(String mutateRate) {
		this.mutateRate = mutateRate;
	}
	public String getIsTransportRate() {
		return isTransportRate;
	}
	public void setIsTransportRate(String isTransportRate) {
		this.isTransportRate = isTransportRate;
	}
	public String getIsElement() {
		return isElement;
	}
	public void setIsElement(String isElement) {
		this.isElement = isElement;
	}
	public String getRisTransportRate() {
		return risTransportRate;
	}
	public void setRisTransportRate(String risTransportRate) {
		this.risTransportRate = risTransportRate;
	}
	public String getRisElement() {
		return risElement;
	}
	public void setRisElement(String risElement) {
		this.risElement = risElement;
	}
	public String getGeneTransportRate() {
		return geneTransportRate;
	}
	public void setGeneTransportRate(String geneTransportRate) {
		this.geneTransportRate = geneTransportRate;
	}
	public String getOnePointRecombineRate() {
		return onePointRecombineRate;
	}
	public void setOnePointRecombineRate(String onePointRecombineRate) {
		this.onePointRecombineRate = onePointRecombineRate;
	}
	public String getTwoPointRecombineRate() {
		return twoPointRecombineRate;
	}
	public void setTwoPointRecombineRate(String twoPointRecombineRate) {
		this.twoPointRecombineRate = twoPointRecombineRate;
	}
	public String getGeneRecombineRate() {
		return geneRecombineRate;
	}
	public void setGeneRecombineRate(String geneRecombineRate) {
		this.geneRecombineRate = geneRecombineRate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 1;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		GepConfiguration compared=(GepConfiguration)obj;
		if(getAccuray().equals(compared.getAccuray()))
			if(getCalculator().equals(compared.getCalculator()))
				if(getConstantListSize().equals(compared.getConstantListSize()))
					if(getCreator().equals(compared.getCreator()))
						if(getFunctionList().equals(compared.getFunctionList()))
							if(getGeneRecombineRate().equals(compared.getGeneRecombineRate()))
								if(getGeneTransportRate().equals(compared.getGeneTransportRate()))
									if(getHomeoticGeneNumber().equals(compared.getHomeoticGeneNumber()))
										if(getHomeoticHeaderLength().equals(compared.getHomeoticHeaderLength()))
											if(getIsElement().equals(compared.getIsElement()))
												if(getIsTransportRate().equals(compared.getIsTransportRate()))
													if(getMaxGeneration().equals(compared.getMaxGeneration()))
														if(getModify().equals(compared.getModify()))
															if(getMutateRate().equals(compared.getMutateRate()))
																if(getNormalGeneNumber().equals(compared.getNormalGeneNumber()))
																	if(getNormalHeaderLength().endsWith(compared.getNormalHeaderLength()))
																		if(getOnePointRecombineRate().equals(compared.getOnePointRecombineRate()))
																			if(getPopulationSize().equals(compared.getPopulationSize()))
																				if(getRandomConstantEnd().equals(compared.getRandomConstantEnd()))
																					if(getRandomConstantStart().equals(compared.getRandomConstantStart()))
																						if(getRisElement().equals(compared.getRisElement()))
																							if(getRisTransportRate().equals(compared.getRisTransportRate()))
																								if(getSelectionRange().equals(compared.getSelectionRange()))
																									if(getSelector().equals(compared.getSelector()))
																										if(getTwoPointRecombineRate().equals(compared.getTwoPointRecombineRate()))
																											if(getName().equals(compared.getName()))
																												return true;
		return false;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
}
