package ui.algconfiguration.model;


public class ModelForJPanelGEne {
	
	String normalGeneNumber;
    String normalHeaderLength;
    String homeoticGeneNums;
    String homeoticHeaderLength;
    String geneRecombineRate;
    String risTransportRate;
    String risElement;
    String isTransportRate;
    String isElement;
    String geneTransportRate;
    String mutateRate;
    String twoPointRecombineRate;
    String geneOnePointRecombineRate;
    int availableModifyingsIndex;
    public ModelForJPanelGEne(String normalGeneNumber,String normalHeaderLength,String homeoticGeneNums,String homeoticHeaderLength,String geneRecombineRate,
    		String risTransportRate,String risElement,String isTransportRate, String isElement,String geneTransportRate,String mutateRate,String twoPointRecombineRate,String geneOnePointRecombineRate,int availableModifyings){
    	this.normalGeneNumber= normalGeneNumber;
        this.normalHeaderLength=normalHeaderLength;
        this.homeoticGeneNums=homeoticGeneNums;
        this.homeoticHeaderLength= homeoticHeaderLength;
        this.geneRecombineRate=geneRecombineRate;
        this.risTransportRate=risTransportRate;
        this.risElement=risElement;
        this.isTransportRate=isTransportRate;
        this.isElement=isElement;
        this.geneTransportRate=geneTransportRate;
        this.mutateRate=mutateRate;
        this.twoPointRecombineRate=twoPointRecombineRate;
        this.geneOnePointRecombineRate=geneOnePointRecombineRate;
        this.availableModifyingsIndex=availableModifyings;
    	
    }
    public String getNormalGeneNumber() {
		return normalGeneNumber;
	}
	public void setNormalGeneNumber(String normalGeneNumber) {
		this.normalGeneNumber = normalGeneNumber;
	}
	public String getNormalHeaderLength() {
		return normalHeaderLength;
	}
	public void setNormalHeaderLength(String normalHeaderLength) {
		this.normalHeaderLength = normalHeaderLength;
	}
	public String getHomeoticGeneNums() {
		return homeoticGeneNums;
	}
	public void setHomeoticGeneNums(String homeoticGeneNums) {
		this.homeoticGeneNums = homeoticGeneNums;
	}
	public String getHomeoticHeaderLength() {
		return homeoticHeaderLength;
	}
	public void setHomeoticHeaderLength(String homeoticHeaderLength) {
		this.homeoticHeaderLength = homeoticHeaderLength;
	}
	public String getGeneRecombineRate() {
		return geneRecombineRate;
	}
	public void setGeneRecombineRate(String geneRecombineRate) {
		this.geneRecombineRate = geneRecombineRate;
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
	public String getGeneTransportRate() {
		return geneTransportRate;
	}
	public void setGeneTransportRate(String geneTransportRate) {
		this.geneTransportRate = geneTransportRate;
	}
	public String getMutateRate() {
		return mutateRate;
	}
	public void setMutateRate(String mutateRate) {
		this.mutateRate = mutateRate;
	}
	public String getTwoPointRecombineRate() {
		return twoPointRecombineRate;
	}
	public void setTwoPointRecombineRate(String twoPointRecombineRate) {
		this.twoPointRecombineRate = twoPointRecombineRate;
	}
	public String getGeneOnePointRecombineRate() {
		return geneOnePointRecombineRate;
	}
	public void setGeneOnePointRecombineRate(String geneOnePointRecombineRate) {
		this.geneOnePointRecombineRate = geneOnePointRecombineRate;
	}
	public int getAvailableModifyingsIndex() {
		return availableModifyingsIndex;
	}
	public void setAvailableModifyings(int availableModifyings) {
		this.availableModifyingsIndex = availableModifyings;
	}
}
