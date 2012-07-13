package ui.algoutput.model;

import domain.core.algInputDataProcess.DataSet;
import domain.core.algOutput.Individual;
import domain.core.algconfiguration.GepAlgConfiguration;
import domain.iservice.algOutput.IAlgRunStep;




public class OutputModel {
	private Individual individual;
	private GepAlgConfiguration gepAlgConfiguration;
	private IAlgRunStep algRunStep;
	private DataSet inputSet;
	
	public DataSet getInputSet() {
		return inputSet;
	}
	public void setInputSet(DataSet inputSet) {
		this.inputSet = inputSet;
	}
	
	public Individual getIndividual() {
		return individual;
	}
	public void setIndividual(Individual individual) {
		this.individual = individual;
	}
	public GepAlgConfiguration getGepAlgConfiguration() {
		return gepAlgConfiguration;
	}
	public void setGepAlgConfiguration(GepAlgConfiguration gepAlgConfiguration) {
		this.gepAlgConfiguration = gepAlgConfiguration;
	}
	public IAlgRunStep getAlgRunStep() {
		return algRunStep;
	}
	public void setAlgRunStep(IAlgRunStep algRunStep) {
		this.algRunStep = algRunStep;
	}
}
