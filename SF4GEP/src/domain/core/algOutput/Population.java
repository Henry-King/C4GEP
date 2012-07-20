package domain.core.algOutput;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * ��Ⱥʵ����
 * @author ��Զ
 *
 */
public class Population implements Serializable,Cloneable{
	private static final long serialVersionUID = -7846826225395737521L;
	private Integer id;
	private GepAlgRun gepAlgRun;
	private List<Individual> individuals;
	private Long generationNum;
	
	/**
	 * ����һ����Ⱥ��Ĭ�ϵ���Ⱥ��СΪ20
	 */
	public Population() {
		// TODO Auto-generated constructor stub
		individuals=new ArrayList<Individual>(20);
	}
	
	/**
	 * ����һ����Ⱥ���û���Ҫ�ṩ��Ⱥ��С
	 * @param size ��Ⱥ��С
	 */
	public Population(int size){
		individuals=new ArrayList<Individual>(size);
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GepAlgRun getGepAlgRun() {
		return gepAlgRun;
	}
	public void setGepAlgRun(GepAlgRun gepAlgRun) {
		this.gepAlgRun = gepAlgRun;
	}
	/**
	 * ������Ⱥ�����еĸ���
	 * @return ��Ⱥ�����и�����ɵ�List
	 */
	public List<Individual> getIndividuals(){
		return individuals;
	}
	/**
	 * ������Ⱥ�������ĸ���
	 * @param individuals ��Ⱥ�и����List
	 */
	public void setIndividuals(List<Individual> individuals) {
		this.individuals = individuals;
	}
	/**
	 * ����Ⱥ����Ӹ���
	 * @param individual ����ӵĸ���
	 */
	public void addIndividual(Individual individual){
		individuals.add(individual);
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
	public Long getGenerationNum(){
		return generationNum;
	}
	/**
	 * ���õ�ǰ��Ⱥ�Ĵ����������õ�ǰ��Ⱥ�ǵڼ�����Ⱥ
	 * @param generation ��ǰ��Ⱥ�Ĵ���
	 */
	public void setGenerationNum(Long generationNum) {
		this.generationNum = generationNum;
	}
	/**
	 * �Ե�ǰ��Ⱥ���и��ƣ�����һ���µ���Ⱥ������string �����Ĳ��ɸı�����int�����Ļ����������ͺ�AlgRun������һ��ǳ���ƣ����������Ķ�����ԣ�����һ����ȸ���
	 * @return һ���µ���Ⱥ�����߲������ڴ�ռ䣬ֻ�Ǿ�����ͬ�ĳ�ʼ����ֵ��
	 */
	@Override
	public Population clone(){
		// TODO Auto-generated method stub
		Population o=null;
		try {
			o = (Population) super.clone();
			if(individuals!=null){
				List<Individual> copiedIndividual=new ArrayList<Individual>();
				if(individuals.size()!=0)
					for(int i=0;i<individuals.size();i++)
						copiedIndividual.add(individuals.get(i).clone());
				o.setIndividuals(copiedIndividual);
			}
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}
}
