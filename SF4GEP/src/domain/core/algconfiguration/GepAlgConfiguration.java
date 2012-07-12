package domain.core.algconfiguration;

public class GepAlgConfiguration {
	private String name;
	private long maxGeneration;
	private float selectionRange;
	private float accuracy;
	private float maxFitness;
	private IndividualConfiguration individualConfiguration;
	private OperatorConfiguration operatorConfiguration;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getMaxGeneration() {
		return maxGeneration;
	}
	public void setMaxGeneration(long maxGeneration) {
		this.maxGeneration = maxGeneration;
	}
	public float getSelectionRange() {
		return selectionRange;
	}
	public void setSelectionRange(float selectionRange) {
		this.selectionRange = selectionRange;
	}
	public float getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(float accuracy) {
		this.accuracy = accuracy;
	}
	public float getMaxFitness() {
		return maxFitness;
	}
	public void setMaxFitness(float maxFitness) {
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
