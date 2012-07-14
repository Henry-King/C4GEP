package domain.core.algconfiguration;

import java.io.Serializable;
import java.util.List;


public class GeneConfiguration implements Serializable{
	private static final long serialVersionUID = -4801304275798018482L;
	private Integer id;
	private Integer normalGeneNumber;
	private Integer normalGeneHeaderLength;
	private Integer normalGeneTailLength;
	private Integer normalGeneLength;
	private Integer homeoticGeneNumber;
	private Integer homeoticGeneHeaderLength;
	private Integer homeoticGeneTailLength;
	private Integer homeoticGeneLength;
	private List<Function> functionUsed;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getNormalGeneTailLength() {
		return normalGeneTailLength;
	}
	public void setNormalGeneTailLength(Integer normalGeneTailLength) {
		this.normalGeneTailLength = normalGeneTailLength;
	}
	public Integer getNormalGeneLength() {
		return normalGeneLength;
	}
	public void setNormalGeneLength(Integer normalGeneLength) {
		this.normalGeneLength = normalGeneLength;
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
	public Integer getHomeoticGeneTailLength() {
		return homeoticGeneTailLength;
	}
	public void setHomeoticGeneTailLength(Integer homeoticGeneTailLength) {
		this.homeoticGeneTailLength = homeoticGeneTailLength;
	}
	public Integer getHomeoticGeneLength() {
		return homeoticGeneLength;
	}
	public void setHomeoticGeneLength(Integer homeoticGeneLength) {
		this.homeoticGeneLength = homeoticGeneLength;
	}
	public List<Function> getFunctionUsed() {
		return functionUsed;
	}
	public void setFunctionUsed(List<Function> functionUsed) {
		this.functionUsed = functionUsed;
	}
	
}
