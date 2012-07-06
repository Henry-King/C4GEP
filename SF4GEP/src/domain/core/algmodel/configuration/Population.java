package domain.core.algmodel.configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import common.ICopy;


public class Population implements Serializable,ICopy<Population>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7369251203166977601L;
	private int size;
	private List<Individual> individuals;
	private long generation;
	public void setIndividuals(List<Individual> individuals) {
		this.individuals = individuals;
	}
	public Population(int size){
		this.size=size;
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
	@Override
	public Population copy() {
		// TODO Auto-generated method stub
		Population population=new Population(size);
		population.setGeneration(generation);
		for(int i=0;i<individuals.size();i++)
			population.addIndividual(individuals.get(i).copy());
		return population;
	}
}
