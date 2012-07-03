package domain.core.outputmodel;

import java.io.Serializable;
import java.util.Set;

public class AlgInstance implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1368598667691715099L;
	private Long id;
	private Long runTimes;
	private Long totalGeneration;
	private GepConfiguration gepConfiguration;
	private Set<OutputPopulation> populationSet;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRunTimes() {
		return runTimes;
	}
	public void setRunTimes(Long runTimes) {
		this.runTimes = runTimes;
	}
	public Long getTotalGeneration() {
		return totalGeneration;
	}
	public void setTotalGeneration(Long totalGeneration) {
		this.totalGeneration = totalGeneration;
	}
	public GepConfiguration getGepConfiguration() {
		return gepConfiguration;
	}
	public void setGepConfiguration(GepConfiguration gepConfiguration) {
		this.gepConfiguration = gepConfiguration;
	}
	public Set<OutputPopulation> getPopulationSet() {
		return populationSet;
	}
	public void setPopulationSet(Set<OutputPopulation> populationSet) {
		this.populationSet = populationSet;
	}
	public void addPopulation(OutputPopulation population){
		populationSet.add(population);
	}
}
