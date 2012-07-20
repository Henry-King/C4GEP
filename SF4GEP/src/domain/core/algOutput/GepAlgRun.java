package domain.core.algOutput;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.core.algInputDataProcess.DataColumn;
import domain.core.algInputDataProcess.DataSet;
import domain.core.algconfiguration.Function;
import domain.core.algconfiguration.GepAlgConfiguration;

public class GepAlgRun implements Serializable{
	private static final long serialVersionUID = -6809763278870156382L;
	private Integer id;
	private GepAlgConfiguration gepAlgConfiguration;
	private DataSet dataSet;
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
	public Population getCurrentPopulation(){
		removeRedundancyPopulation();
		if(populations.size()==0)
			return null;
		else
			return populations.get(populations.size()-1);
	}
	public void setCurrentPopulation(Population population){
		removeRedundancyPopulation();
		if(populations.size()==0)
			populations.add(population);
		else
			populations.set(populations.size()-1, population);
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
		return dataSet.getDataRows().get(0).getDataColumns();
	}
	public Individual getBestIndividual(){
		return Collections.max(getCurrentPopulation().getIndividuals());
	}
	private void removeRedundancyPopulation(){
		while(populations.size()>2)
			populations.remove(0);
	}
	
	
	@Override
	public int hashCode(){
		int result = 17;
		result = 37 * result + (int)id;
		result = 37 * result + gepAlgConfiguration.hashCode();
		result = 37 * result + dataSet.hashCode();
		result = 37 * result + populations.hashCode();
		return result;
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof GepAlgRun){
			GepAlgRun gac = (GepAlgRun)o;
			return gac.getId().equals(id)
				&&	gac.getGepAlgConfiguration().equals(gepAlgConfiguration)
				&&	gac.getDataSet().equals(dataSet)
				&&	gac.getPopulations().equals(populations);
		}
		else {
			return false;
		}
	}
	
	
	
	
	
	
	
	
	
}
