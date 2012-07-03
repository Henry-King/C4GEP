package domain.core.algmodel.configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Population implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7369251203166977601L;
	private List<Individual> individuals;
	private long generation;
	public void setIndividuals(List<Individual> individuals) {
		this.individuals = individuals;
	}
	public Population(int size){
		individuals=new ArrayList<Individual>(size);
	}
	public Population(List<Individual> popuList){
		this.individuals=popuList;
	}
	public List<Individual> getIndividuals(){
		return individuals;
	}
	public Individual getBestIndividual(){
		Individual bestIndividual=individuals.get(0);
		for(Individual element:individuals)
			if(element.getFitness()>=bestIndividual.getFitness())
				bestIndividual=element;
		return bestIndividual;
	}
	public Individual getWorstIndividual(){
		Individual bestIndividual=individuals.get(0);
		for(Individual element:individuals)
			if(element.getFitness()<=bestIndividual.getFitness())
				bestIndividual=element;
		return bestIndividual;
	}
	public long getGeneration(){
		return generation;
	}
	public void setGeneration(long generation) {
		this.generation = generation;
	}
	public void addIndividual(Individual individual){
		individuals.add(individual);
	}
}
