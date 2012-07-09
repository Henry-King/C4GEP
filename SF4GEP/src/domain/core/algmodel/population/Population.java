package domain.core.algmodel.population;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import common.ICopy;

/**
 * 种群实体类
 * @author 申远
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
	 * 设置种群所包含的个体
	 * @param individuals 种群中个体的List
	 */
	public void setIndividuals(List<Individual> individuals) {
		this.individuals = individuals;
	}
	/**
	 * 创建一个种群，用户需要提供种群大小
	 * @param size 种群大小
	 */
	public Population(int size){
		this.size=size;
		individuals=new ArrayList<Individual>(size);
	}
	/**
	 * 创建种群，用户提供种群中所有个体组成的List
	 * @param popuList 包含有种群中所有个体的List
	 */
	public Population(List<Individual> popuList){
		this.individuals=popuList;
	}
	/**
	 * 返回种群中所有的个体
	 * @return 种群中所有个体组成的List
	 */
	public List<Individual> getIndividuals(){
		return individuals;
	}
	/**
	 * 返回种群中最优个体
	 * @return 种群中最优个体
	 */
	public Individual getBestIndividual(){
		Individual bestIndividual=individuals.get(0);
		for(Individual element:individuals)
			if(element.getFitness()>=bestIndividual.getFitness())
				bestIndividual=element;
		return bestIndividual;
	}
	/**
	 * 返回种群中最差个体
	 * @return 种群中最差个体
	 */
	public Individual getWorstIndividual(){
		Individual bestIndividual=individuals.get(0);
		for(Individual element:individuals)
			if(element.getFitness()<=bestIndividual.getFitness())
				bestIndividual=element;
		return bestIndividual;
	}
	/**
	 * 返回当前种群的代数，即当前种群是第几代种群
	 * @return 当前种群的代数
	 */
	public long getGeneration(){
		return generation;
	}
	/**
	 * 设置当前种群的代数，即设置当前种群是第几代种群
	 * @param generation 当前种群的代数
	 */
	public void setGeneration(long generation) {
		this.generation = generation;
	}
	/**
	 * 向种群中添加个体
	 * @param individual 待添加的个体
	 */
	public void addIndividual(Individual individual){
		individuals.add(individual);
	}
	/**
	 * 对当前种群进行复制，产生一个新的种群，对于string 这样的不可改变对象和int这样的基本数据类型，这是一个浅复制，对于其他的对象而言，这是一个深度复制
	 * @return 一个新的种群，二者不共享内存空间，只是具有相同的初始化的值
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
