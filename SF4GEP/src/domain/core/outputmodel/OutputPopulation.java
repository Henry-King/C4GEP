package domain.core.outputmodel;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

public class OutputPopulation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6666071241631918798L;
	private long id;
	private long generation;
	private Set<OutputIndividual> indvididualSet;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Set<OutputIndividual> getIndvididualSet() {
		return indvididualSet;
	}
	public void setIndvididualSet(Set<OutputIndividual> indvididualSet) {
		this.indvididualSet = indvididualSet;
	}
	public long getGeneration() {
		return generation;
	}
	public void setGeneration(long generation) {
		this.generation = generation;
	}
	public OutputIndividual getBestOutputIndividual(){
		Iterator<OutputIndividual> individualIterator=indvididualSet.iterator();
		OutputIndividual bestIndividual=null;
		if(individualIterator.hasNext())
			bestIndividual=individualIterator.next();
		OutputIndividual temp;
		while(individualIterator.hasNext()){
			temp=individualIterator.next();
			if(temp.getFitness()>bestIndividual.getFitness())
				bestIndividual=temp;
		}
		return bestIndividual; 
	}
	public OutputIndividual getWorstOutputIndividual(){
		Iterator<OutputIndividual> individualIterator=indvididualSet.iterator();
		OutputIndividual worstIndividual=null;
		if(individualIterator.hasNext())
			worstIndividual=individualIterator.next();
		OutputIndividual temp;
		while(individualIterator.hasNext()){
			temp=individualIterator.next();
			if(temp.getFitness()<worstIndividual.getFitness())
				worstIndividual=temp;
		}
		return worstIndividual; 
	}
}
