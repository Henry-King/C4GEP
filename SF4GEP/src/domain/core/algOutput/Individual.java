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
	private List<Gene> genes;
	private List<FittedValue> fittedValues;
	private Float fitness;
	private int selectedHomeoticGeneNumber=-1;
	/**
	 * ������Object��toString�����������������ַ�������ʽ�����������������ȫ��Genepiece��Ϣ��
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		boolean isNormalGene=true;
		StringBuffer result=new StringBuffer();
		result.append("��������:");
		for(Gene gene:genes){
			if(gene.getGeneType()==GeneType.HomeoticGene&&isNormalGene){
				isNormalGene=false;
				result.append("\nͬԴ����");
			}
			for(GenePiece genePiece:gene.getGenePieces())
				result.append(genePiece.getSymbol()+" ");
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
	public int getSelectedHomeoticGeneNumber() {
		return selectedHomeoticGeneNumber;
	}
	/**
	 * ������������뼯��ʱ��ʹ���ĸ�ͬԴ�����ϴ����塣
	 * @param selectedHomeoticGeneNumber ͬԴ������
	 */
	public void setSelectedHomeoticGeneNumber(int selectedHomeoticGeneNumber) {
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
						fittedValues.add(copiedFittedValues.get(i).clone());
				individual.setFittedValues(copiedFittedValues);
			}
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return individual;
	}
}
