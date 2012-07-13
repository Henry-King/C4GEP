package domain.core.algOutput;

import java.util.ArrayList;
import java.util.List;

import domain.core.algInputDataProcess.DataColumn;
import domain.core.algInputDataProcess.DataSet;
import domain.core.algconfiguration.Function;
import domain.core.algconfiguration.GepAlgConfiguration;

public class GepAlgRun {
	private GepAlgConfiguration gepAlgConfiguration;
	private DataSet dataSet;
	private Integer id;
	private long currentGenerationNum;
	private List<Float> maxFitness;
	private List<Float> minFitness;
	private List<Population> populations=new ArrayList<Population>(2);
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public GepAlgConfiguration getGepAlgConfiguration() {
		return gepAlgConfiguration;
	}
	public void setGepAlgConfiguration(GepAlgConfiguration gepAlgConfiguration) {
		this.gepAlgConfiguration = gepAlgConfiguration;
	}
	public DataSet getDataSet() {
		return dataSet;
	}
	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}
	public long getCurrentGenerationNum() {
		return currentGenerationNum;
	}
	public void setCurrentGenerationNum(long currentGenerationNum) {
		this.currentGenerationNum = currentGenerationNum;
	}
	public Population getCurrentPopulation(){
		removeRedundancyPopulation();
		return populations.get(populations.size());
	}
	public void setCurrentPopulation(Population population){
		removeRedundancyPopulation();
		populations.set(populations.size(), population);
	}
	public List<Population> getPopulations() {
		removeRedundancyPopulation();
		return populations;
	}
	public void setPopulations(List<Population> population) {
		this.populations = population;
		removeRedundancyPopulation();
	}
	public List<Function> getUsedFunctions(){
		return gepAlgConfiguration.getIndividualConfiguration().getGeneConfiguration().getFunctionUsed();
	}
	public List<DataColumn> getUsedVariables(){
		return dataSet.getDataRow().get(0).getDataColumns();
	}
	public List<Float> getMaxFitness() {
		return maxFitness;
	}
	public void setMaxFitness(List<Float> maxFitness) {
		this.maxFitness = maxFitness;
	}
	public List<Float> getMinFitness() {
		return minFitness;
	}
	public void setMinFitness(List<Float> minFitness) {
		this.minFitness = minFitness;
	}
	private void removeRedundancyPopulation(){
		while(populations.size()>2)
			populations.remove(0);
	}
}
