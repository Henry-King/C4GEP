package domain.service.alg.userdefined;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

import common.ObjectCopy;

import domain.core.algmodel.configuration.Individual;
import domain.core.algmodel.configuration.Population;
import domain.service.alg.baseclass.Selector;

public class DefaultSelector extends Selector{
	private long maxGeneration;
	public DefaultSelector() {
		// TODO Auto-generated constructor stub
		super("ÂÖÅÌ¶Ä+¾«Ó¢Ñ¡Ôñ²ßÂÔ");
	}
	public long getMaxGeneration() {
		return maxGeneration;
	}
	public void setMaxGeneration(long maxGeneration) {
		this.maxGeneration = maxGeneration;
	}
	@Override
	public Population select(Deque<Population> population) {
		// TODO Auto-generated method stub
		Population lastPopulation=population.getLast();
		float sumFitness=addFitness(lastPopulation);

		List<Float> probability=calculateProbability(lastPopulation, sumFitness);
		calculateCumulative(probability);
		Population newPopulation=createNewPopulation(lastPopulation, probability);
		return newPopulation;
	}
	private float addFitness(Population population){
		float sum=0;
		for(Individual individual:population.getIndividuals())
			sum+=individual.getFitness();
		return sum;
	}
	private List<Float> calculateProbability(Population population,float sum){
		float divide;
		List<Float> floatList=new ArrayList<Float>(population.getIndividuals().size());
		for(Individual individual:population.getIndividuals()){
			divide=individual.getFitness()/sum;
			floatList.add(divide);
		}
		return floatList;
	}
	private List<Float> calculateCumulative(List<Float> probability){
		float sum=0;
		for(int i=0;i<probability.size();i++){
			sum+=probability.get(i);
			probability.set(i, sum);
		}
		return probability;
	}
	private Population createNewPopulation(Population original,List<Float> cumulativeProbability){
		Population resultPopulation=new Population(original.getIndividuals().size());
		Individual bestIndividual=ObjectCopy.newInstance(original.getBestIndividual());
		resultPopulation.addIndividual(bestIndividual);
		Random random=new Random();
		int position;
		Individual insertedIndividual;
		for(int i=1;i<cumulativeProbability.size();i++){
			position=search(cumulativeProbability, random.nextFloat());
			insertedIndividual=ObjectCopy.newInstance(original.getIndividuals().get(position));
			resultPopulation.addIndividual(insertedIndividual);
		}
		return resultPopulation;
	}
	private int search(List<Float> floats,float random){
		for(int i=0;i<floats.size();i++){
			if(random<=floats.get(i))
				return i;
		}
		return floats.size()-1;
	}
}
