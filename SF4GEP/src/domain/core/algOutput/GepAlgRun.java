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
	public List<Population> getPopulations() {
		return populations;
	}
	public void setPopulations(List<Population> population) {
		this.populations = population;
		if(populations.size()>2)
			populations.remove(0);
	}
	public List<Function> getUsedFunctions(){
		return gepAlgConfiguration.getIndividualConfiguration().getGeneConfiguration().getFunctionUsed();
	}
	public List<DataColumn> getUsedVariables(){
		return dataSet.getDataRow().get(0).getDataColumns();
	}
}
