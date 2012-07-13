package ui.algoutput.model;

import domain.core.algInputDataProcess.DataSet;
import domain.core.algOutput.Individual;
import domain.core.algconfiguration.GepAlgConfiguration;
import domain.iservice.algOutput.IAlgRunStep;
import domain.service.alg.baseclass.Calculator;




public class OutputModel {
	private Individual individual;
	private GepAlgConfiguration gepAlgConfiguration;
	private Calculator calculator;
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
	public Calculator getCalculator() {
		return calculator;
	}
	public void setCalculator(Calculator calculator) {
		this.calculator = calculator;
	}
	public IAlgRunStep getAlgRunStep() {
		return algRunStep;
	}
	public void setAlgRunStep(IAlgRunStep algRunStep) {
		this.algRunStep = algRunStep;
	}
}
