package domain.core.algconfiguration;

import java.io.Serializable;

public class GepAlgConfiguration implements Serializable{
	private static final long serialVersionUID = 1450046907494125922L;
	private String name;
	private Long maxGeneration;
	private Float selectionRange;
	private Float accuracy;
	private Float maxFitness;
	private IndividualConfiguration individualConfiguration;
	private OperatorConfiguration operatorConfiguration;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getMaxGeneration() {
		return maxGeneration;
	}
	public void setMaxGeneration(Long maxGeneration) {
		this.maxGeneration = maxGeneration;
	}
	public Float getSelectionRange() {
		return selectionRange;
	}
	public void setSelectionRange(Float selectionRange) {
		this.selectionRange = selectionRange;
	}
	public Float getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(Float accuracy) {
		this.accuracy = accuracy;
	}
	public Float getMaxFitness() {
		return maxFitness;
	}
	public void setMaxFitness(Float maxFitness) {
		this.maxFitness = maxFitness;
	}
	public IndividualConfiguration getIndividualConfiguration() {
		return individualConfiguration;
	}
	public void setIndividualConfiguration(
			IndividualConfiguration individualConfiguration) {
		this.individualConfiguration = individualConfiguration;
	}
	public OperatorConfiguration getOperatorConfiguration() {
		return operatorConfiguration;
	}
	public void setOperatorConfiguration(OperatorConfiguration operatorConfiguration) {
		this.operatorConfiguration = operatorConfiguration;
	}
	
}
