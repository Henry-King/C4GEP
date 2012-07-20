package domain.core.algconfiguration;

import java.io.Serializable;

public class GepAlgConfiguration implements Serializable{
	private static final long serialVersionUID = 1450046907494125922L;
	private Integer id;
	private String name;
	private Long maxGeneration;
	private Float selectionRange;
	private Float accuracy;
	private Float maxFitness;
	private IndividualConfiguration individualConfiguration;
	private OperatorConfiguration operatorConfiguration;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	@Override
	public int hashCode(){
		int result = 17;
		result = 37 * result + name.hashCode();
		result = 37 * result + Float.floatToIntBits(selectionRange);
		result = 37 * result + Float.floatToIntBits(accuracy);
		result = 37 * result + Float.floatToIntBits(maxFitness);
		result = 37 * result + individualConfiguration.hashCode();
		result = 37 * result + operatorConfiguration.hashCode();
		return result;
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof GepAlgConfiguration){
			GepAlgConfiguration gac = (GepAlgConfiguration)o;
			return gac.getName() .equals(name)
				&&	gac.getSelectionRange().equals(selectionRange)
				&&	gac.getAccuracy() .equals(accuracy)
				&&	gac.getMaxFitness() .equals(maxFitness)
				&&	gac.getIndividualConfiguration() .equals(individualConfiguration)
				&&	gac.getOperatorConfiguration() .equals(operatorConfiguration);			
		}
		else {
			return false;
		}
	}
}
