package domain.core.algOutput;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ������,��Ⱥ�б����Ÿ����List
 * @author ������
 *
 */
public class Individual implements Comparable<Individual>,Serializable,Cloneable{
	private static final long serialVersionUID = -7942680950588488680L;
	private Integer id;
	private List<Gene> genes;
	private List<FittedValue> fittedValues;
	private Float fitness;
	private Integer selectedHomeoticGeneNumber=-1;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * ������Object��toString�����������������ַ�������ʽ�����������������ȫ��GenePiece��Ϣ��
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer result=new StringBuffer();
		for(Gene gene:genes){
			if(gene.getGeneType()==GeneType.HomeoticGene)
				result.append("ͬԴ����");
			else
				result.append("��������:");
			for(GenePiece genePiece:gene.getGenePieces())
				result.append(genePiece.getSymbol()+" ");
			result.append("\n");
		}
		return result.toString();
	}
	/**
	 * �Ƚϵ�ǰ�������Ӧֵ���û�ָ����ĳ���������Ӧֵ�������˽������
	 * @param���û��ṩ�Ĵ��Ƚϵĸ���
	 * @return �����ǰ������ӦֵС�ڲ�����Ӧֵ�����ظ����������ǰ������Ӧֵ���ڲ���������Ӧֵ������0�������ǰ������Ӧֵ���ڲ���������Ӧֵ����������
	 */
	@Override
	public int compareTo(Individual o) {
		// TODO Auto-generated method stub
		return Float.compare(fitness, o.getFitness());
	}
	public List<Gene> getGenes() {
		return genes;
	}
	public void setGenes(List<Gene> genes) {
		this.genes = genes;
	}
	public List<FittedValue> getFittedValues() {
		return fittedValues;
	}
	public void setFittedValues(List<FittedValue> fittedValues) {
		this.fittedValues = fittedValues;
	}
	/**
	 * ���ص�ǰ�������Ӧֵ
	 * @return ��ǰ�������Ӧֵ
	 */
	public Float getFitness(){
		return fitness;
	}
	/**
	 * ���õ�ǰ�������Ӧֵ
	 * @param fitness ��ǰ�������Ӧֵ
	 */
	public void setFitness(Float fitness) {
		this.fitness = fitness;
	}
	/**
	 * һ��������ܾ��ж��ͬԴ����һ�������ڵĶ��ͬԴ������ܲ��������ͬ�ı��ʽ����������������ڶ�ĳ��ȷ�������뼯����ʱ��ʹ���ĸ�ͬԴ���������
	 * @return ��ѡ���ͬԴ����ı�ţ���0��ʼ�����ֵΪ��ͨ����������1
	 */
	public Integer getSelectedHomeoticGeneNumber() {
		return selectedHomeoticGeneNumber;
	}
	/**
	 * ������������뼯��ʱ��ʹ���ĸ�ͬԴ�����ϴ����塣
	 * @param selectedHomeoticGeneNumber ͬԴ������
	 */
	public void setSelectedHomeoticGeneNumber(Integer selectedHomeoticGeneNumber) {
		this.selectedHomeoticGeneNumber = selectedHomeoticGeneNumber;
	}

	@Override
	public Individual clone(){
		// TODO Auto-generated method stub
		Individual individual=null;
		try {
			individual=(Individual) super.clone();
			if (genes!=null) {
				List<Gene> copiedGenes=new ArrayList<Gene>(genes.size());
				if(genes.size()!=0)
					for(int i=0;i<genes.size();i++)
						copiedGenes.add(genes.get(i).clone());
				individual.setGenes(copiedGenes);
			}
			if(fittedValues!=null){
				List<FittedValue> copiedFittedValues=new ArrayList<FittedValue>(fittedValues.size());
				if(fittedValues.size()!=0)
					for(int i=0;i<fittedValues.size();i++)
						copiedFittedValues.add(fittedValues.get(i).clone());
				individual.setFittedValues(copiedFittedValues);
			}
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return individual;
	}
	
	@Override
	public int hashCode(){
		int result = 37;
		result = 37 * result + genes.hashCode();
		result = 37 * result + Float.floatToIntBits(fitness);
		result = 37 * result + fittedValues.hashCode();
		result = 37 * result + (int)selectedHomeoticGeneNumber;
		return result;
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Individual){
			Individual gac = (Individual)o;
			return gac.getGenes().equals(genes)
				&&	gac.getFitness().equals(fitness)
				&&	gac.getFittedValues().equals(fittedValues)
				&&	gac.getSelectedHomeoticGeneNumber().equals(selectedHomeoticGeneNumber);
		}
		else {
			return false;
		}
	}
}
