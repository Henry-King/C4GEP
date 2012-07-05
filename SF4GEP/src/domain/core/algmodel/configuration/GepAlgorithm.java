package domain.core.algmodel.configuration;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import domain.core.algmodel.genecomponent.Function;
import domain.core.algmodel.genecomponent.Variable;

public class GepAlgorithm implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9145263051145401068L;
	private int populationSize;
	private int normalGeneNumber;
	private int normalHeaderLength;
	private int normalTailLength;
	private int normalGeneLength;
	private int homeoticGeneNumber;
	private int homeoticHeaderLength;
	private int homeoticTailLength;
	private int homeoticGeneLength;
	private int individualLength;
	private Deque<Population> populationQueue=new ArrayDeque<Population>(2);
	private long maxGeneration;
	private float maxFitness;
	private List<Variable> variableList;
	private List<Function> functionList;
	
	public GepAlgorithm(){
		
	}
	public int getPopulationSize() {
		return populationSize;
	}
	public void setPopulationSize(int populationNumber) {
		this.populationSize = populationNumber;
	}
	public int getNormalGeneNumber() {
		return normalGeneNumber;
	}
	public void setNormalGeneNumber(int geneNumber) {
		this.normalGeneNumber = geneNumber;
	}
	public int getNormalHeaderLength() {
		return normalHeaderLength;
	}
	public void setNormalHeaderLength(int headerLength) {
		this.normalHeaderLength = headerLength;
	}
	public int getHomeoticGeneNumber() {
		return homeoticGeneNumber;
	}
	public void setHomeoticGeneNumber(int homeoticGeneNumber) {
		this.homeoticGeneNumber = homeoticGeneNumber;
	}
	public int getHomeoticHeaderLength() {
		return homeoticHeaderLength;
	}
	public void setHomeoticHeaderLength(int homeoticHeaderLength) {
		this.homeoticHeaderLength = homeoticHeaderLength;
	}
	public int getIndividualLength() {
		return individualLength;
	}
	public void setIndividualLength(int individualLength) {
		this.individualLength = individualLength;
	}
	public void addPopulation(Population myPopulation){
		populationQueue.addLast(myPopulation);
		if(populationQueue.size()>2)
			populationQueue.removeFirst();
	}
	public Deque<Population> getPopulationQueue(){
		return populationQueue;
	}
	public int getNormalTailLength() {
		return normalTailLength;
	}
	public void setNormalTailLength(int normalTailLength) {
		this.normalTailLength = normalTailLength;
		normalGeneLength=normalHeaderLength+this.normalTailLength;
	}
	public int getHomeoticTailLength() {
		return homeoticTailLength;
	}
	public void setHomeoticTailLength(int homeoticTailLength) {
		this.homeoticTailLength = homeoticTailLength;
		homeoticGeneLength=homeoticHeaderLength+this.homeoticTailLength;
	}
	public int getNormalGeneLength() {
		return normalGeneLength;
	}
	public int getHomeoticGeneLength() {
		return homeoticGeneLength;
	}
	public long getMaxGeneration() {
		return maxGeneration;
	}
	public void setMaxGeneration(long maxGeneration) {
		this.maxGeneration = maxGeneration;
	}
	public float getMaxFitness() {
		return maxFitness;
	}
	public void setMaxFitness(float maxFitness) {
		this.maxFitness = maxFitness;
	}
	public List<Variable> getVariableList() {
		return variableList;
	}
	public void setVariableList(List<Variable> variableList) {
		this.variableList = variableList;
	}
	public List<Function> getFunctionList() {
		return functionList;
	}
	public void setFunctionList(List<Function> functionList) {
		this.functionList = functionList;
	}
}
