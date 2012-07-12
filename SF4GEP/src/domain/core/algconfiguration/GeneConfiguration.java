package domain.core.algconfiguration;

import java.util.List;


public class GeneConfiguration {
	private int normalGeneNumber;
	private int normalGeneHeaderLength;
	private int normalGeneTailLength;
	private int normalGeneLength;
	private int homeoticGeneNumber;
	private int homeoticGeneHeaderLength;
	private int homeoticGeneTailLength;
	private int homeoticGeneLength;
	private List<Function> functionUsed;
	public int getNormalGeneNumber() {
		return normalGeneNumber;
	}
	public void setNormalGeneNumber(int normalGeneNumber) {
		this.normalGeneNumber = normalGeneNumber;
	}
	public int getNormalGeneHeaderLength() {
		return normalGeneHeaderLength;
	}
	public void setNormalGeneHeaderLength(int normalGeneHeaderLength) {
		this.normalGeneHeaderLength = normalGeneHeaderLength;
	}
	public int getNormalGeneTailLength() {
		return normalGeneTailLength;
	}
	public void setNormalGeneTailLength(int normalGeneTailLength) {
		this.normalGeneTailLength = normalGeneTailLength;
	}
	public int getNormalGeneLength() {
		return normalGeneLength;
	}
	public void setNormalGeneLength(int normalGeneLength) {
		this.normalGeneLength = normalGeneLength;
	}
	public int getHomeoticGeneNumber() {
		return homeoticGeneNumber;
	}
	public void setHomeoticGeneNumber(int homeoticGeneNumber) {
		this.homeoticGeneNumber = homeoticGeneNumber;
	}
	public int getHomeoticGeneHeaderLength() {
		return homeoticGeneHeaderLength;
	}
	public void setHomeoticGeneHeaderLength(int homeoticGeneHeaderLength) {
		this.homeoticGeneHeaderLength = homeoticGeneHeaderLength;
	}
	public int getHomeoticGeneTailLength() {
		return homeoticGeneTailLength;
	}
	public void setHomeoticGeneTailLength(int homeoticGeneTailLength) {
		this.homeoticGeneTailLength = homeoticGeneTailLength;
	}
	public int getHomeoticGeneLength() {
		return homeoticGeneLength;
	}
	public void setHomeoticGeneLength(int homeoticGeneLength) {
		this.homeoticGeneLength = homeoticGeneLength;
	}
	public List<Function> getFunctionUsed() {
		return functionUsed;
	}
	public void setFunctionUsed(List<Function> functionUsed) {
		this.functionUsed = functionUsed;
	}
	
}
