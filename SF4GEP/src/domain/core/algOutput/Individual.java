package domain.core.algOutput;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import domain.core.algconfiguration.Function;
import domain.core.algconfiguration.GeneConfiguration;
import domain.core.algconfiguration.function.Addition;
import domain.core.algconfiguration.function.Cos;
import domain.core.algconfiguration.function.Divide;
import domain.core.algconfiguration.function.Minus;
import domain.core.algconfiguration.function.Multiply;
import domain.core.algconfiguration.function.Sin;

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
	public String toExprString(GeneConfiguration geneConfiguration){
		if(selectedHomeoticGeneNumber!=-1){
			int normalGeneNum=geneConfiguration.getNormalGeneNumber();
			return genes.get(normalGeneNum+selectedHomeoticGeneNumber).toExprString(genes.subList(0, normalGeneNum));			
		}
		else {
			return null;
		}
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
	public char[] getGeneBitType(int start,int end,int geneLength) {
		int geneNum=end-start;
		int length=geneNum*geneLength;
		char result[]=new char[length];
		for(int i=0;i<geneNum;i++){
			for(int j=0;j<geneLength;j++){
				result[j+i*geneLength]=getGeneBitType(i+start, j);
			}
		}
		return result;
	}
	public char[] getGeneBitIndex(int start,int end,int geneLength){
		int geneNum=end-start;
		int length=geneNum*geneLength;
		char result[]=new char[length];
		for(int i=0;i<geneNum;i++){
			for(int j=0;j<geneLength;j++){
				result[j+i*geneLength]=getGeneBitIndex(i+start, j);
			}
		}
		return result;
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
	private char getGeneBitType(int gene,int index){
		GenePiece genePiece=genes.get(gene).getGenePieces().get(index);
		switch (genePiece.getGenePieceType()) {
		case Variable:
			return 0;
		case Function:
			return 1;
		case Constant:
			return 2;
		default:
			return Character.MAX_VALUE;
		}
	}
	private char getGeneBitIndex(int gene,int index){
		GenePiece genePiece=genes.get(gene).getGenePieces().get(index);
		switch (genePiece.getGenePieceType()) {
		case Variable:
			return (char) genePiece.getVariableIndex().intValue();
		case Function:
			return getFuncIndex(genePiece.getFunc());
		default:
			return Character.MAX_VALUE;
		}
	}
	private char getFuncIndex(Function function){
		Class<? extends Function> funcClass=function.getClass();
		if(funcClass==Addition.class)
			return 0;
		else if(funcClass==Minus.class)
			return 1;
		else if(funcClass==Multiply.class)
			return 2;
		else if(funcClass==Divide.class)
			return 3;
		else if(funcClass==Sin.class)
			return 4;
		else if(funcClass==Cos.class)
			return 5;
		else 
			return Character.MAX_VALUE;
	}
}
