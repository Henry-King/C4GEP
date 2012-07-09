package domain.core.algmodel.population;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import common.ICopy;

/**
 * ��Ⱥʵ����
 * @author ��Զ
 *
 */
public class Population implements Serializable,ICopy<Population>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7369251203166977601L;
	private int size;
	private List<Individual> individuals;
	private long generation;
	/**
	 * ������Ⱥ�������ĸ���
	 * @param individuals ��Ⱥ�и����List
	 */
	public void setIndividuals(List<Individual> individuals) {
		this.individuals = individuals;
	}
	/**
	 * ����һ����Ⱥ���û���Ҫ�ṩ��Ⱥ��С
	 * @param size ��Ⱥ��С
	 */
	public Population(int size){
		this.size=size;
		individuals=new ArrayList<Individual>(size);
	}
	/**
	 * ������Ⱥ���û��ṩ��Ⱥ�����и�����ɵ�List
	 * @param popuList ��������Ⱥ�����и����List
	 */
	public Population(List<Individual> popuList){
		this.individuals=popuList;
	}
	/**
	 * ������Ⱥ�����еĸ���
	 * @return ��Ⱥ�����и�����ɵ�List
	 */
	public List<Individual> getIndividuals(){
		return individuals;
	}
	/**
	 * ������Ⱥ�����Ÿ���
	 * @return ��Ⱥ�����Ÿ���
	 */
	public Individual getBestIndividual(){
		Individual bestIndividual=individuals.get(0);
		for(Individual element:individuals)
			if(element.getFitness()>=bestIndividual.getFitness())
				bestIndividual=element;
		return bestIndividual;
	}
	/**
	 * ������Ⱥ��������
	 * @return ��Ⱥ��������
	 */
	public Individual getWorstIndividual(){
		Individual bestIndividual=individuals.get(0);
		for(Individual element:individuals)
			if(element.getFitness()<=bestIndividual.getFitness())
				bestIndividual=element;
		return bestIndividual;
	}
	/**
	 * ���ص�ǰ��Ⱥ�Ĵ���������ǰ��Ⱥ�ǵڼ�����Ⱥ
	 * @return ��ǰ��Ⱥ�Ĵ���
	 */
	public long getGeneration(){
		return generation;
	}
	/**
	 * ���õ�ǰ��Ⱥ�Ĵ����������õ�ǰ��Ⱥ�ǵڼ�����Ⱥ
	 * @param generation ��ǰ��Ⱥ�Ĵ���
	 */
	public void setGeneration(long generation) {
		this.generation = generation;
	}
	/**
	 * ����Ⱥ����Ӹ���
	 * @param individual ����ӵĸ���
	 */
	public void addIndividual(Individual individual){
		individuals.add(individual);
	}
	/**
	 * �Ե�ǰ��Ⱥ���и��ƣ�����һ���µ���Ⱥ������string �����Ĳ��ɸı�����int�����Ļ����������ͣ�����һ��ǳ���ƣ����������Ķ�����ԣ�����һ����ȸ���
	 * @return һ���µ���Ⱥ�����߲������ڴ�ռ䣬ֻ�Ǿ�����ͬ�ĳ�ʼ����ֵ
	 */
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
