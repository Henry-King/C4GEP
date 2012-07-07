package ui.input.model;

public class ModelForJPanelPopulation {
	
	String populationSize;
	String selectionRange;
    int populationCreatorIndex;
    int selectionStrategyIndex;
    int availableCalculatorIndex;
    public ModelForJPanelPopulation(String populationSize,String selectionRange,int populationCreator,int selectionStrategy,int availableCalculator){
    	this.availableCalculatorIndex=availableCalculator;
    	this.populationCreatorIndex=populationCreator;
    	this.selectionStrategyIndex=selectionStrategy;
    	this.selectionRange=selectionRange;
    	this.populationSize=populationSize;
    	
    }
    public String getPopulationSize() {
		return populationSize;
	}
	public void setPopulationSize(String populationSize) {
		this.populationSize = populationSize;
	}
	public String getSelectionRange() {
		return selectionRange;
	}
	public void setSelectionRange(String selectionRange) {
		this.selectionRange = selectionRange;
	}
	public int getPopulationCreatorIndex() {
		return populationCreatorIndex;
	}
	public void setPopulationCreatorIndex(int populationCreatorIndex) {
		this.populationCreatorIndex = populationCreatorIndex;
	}
	public int getSelectionStrategyIndex() {
		return selectionStrategyIndex;
	}
	public void setSelectionStrategyIndex(int selectionStrategyIndex) {
		this.selectionStrategyIndex = selectionStrategyIndex;
	}
	public int getAvailableCalculatorIndex() {
		return availableCalculatorIndex;
	}
	public void setAvailableCalculatorIndex(int availableCalculatorIndex) {
		this.availableCalculatorIndex = availableCalculatorIndex;
	}
}
