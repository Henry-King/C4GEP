package domain.core.algmodel.configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import domain.core.algmodel.genecomponent.GenePiece;
import domain.core.algmodel.individualcomponent.HomeoticGene;
import domain.core.algmodel.individualcomponent.NormalGene;


/**
 * ���������������������������е�ʱ��Ӧ�ÿ����Զ����ɣ����û����Ӧ��get�������ڲ��Ե�ʱ�����ʹ��reflect���ƽ��и�ֵ�����ʵ��
 * ����Ļ�������ȥ��private���в���
 */
public class Individual implements Comparable<Individual>,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7942680950588488680L;
	/**
	 * 
	 */
	private List<GenePiece> containedGenePieces;
	private List<NormalGene> normalGeneList;
	private List<HomeoticGene> homeoticGeneList;
	private Float fitness;
	private int selectedHomeoticGeneNumber;
	public Individual(){

	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer result=new StringBuffer();
		result.append("��������:");
		for(NormalGene normalGene:getNormalGeneList()){
			for(GenePiece piece:normalGene.getHeader().getContainedGenePieces())
				result.append(piece+" ");
			for(GenePiece piece:normalGene.getTail().getContainedGenePieces())
				result.append(piece+" ");
		}
		result.append("\nͬԴ����:");
		for(HomeoticGene homeoticGene:getHomeoticGeneList()){
			for(GenePiece piece:homeoticGene.getHeader().getContainedGenePieces())
				result.append(piece+" ");
			for(GenePiece piece:homeoticGene.getTail().getContainedGenePieces())
				result.append(piece+" ");
		}
		return result.toString();
	}
	@Override
	public int compareTo(Individual o) {
		// TODO Auto-generated method stub
		return Float.compare(fitness, o.getFitness());
	}
	
	public int getSelectedHomeoticGeneNumber() {
		return selectedHomeoticGeneNumber;
	}
	public void setSelectedHomeoticGeneNumber(int selectedHomeoticGeneNumber) {
		this.selectedHomeoticGeneNumber = selectedHomeoticGeneNumber;
	}
	public Float getFitness(){
		return fitness;
	}
	public void setFitness(Float fitness) {
		this.fitness = fitness;
	}

	public List<NormalGene> getNormalGeneList() {
		return normalGeneList;
	}
	public List<HomeoticGene> getHomeoticGeneList() {
		return homeoticGeneList;
	}
	/**
	 * ���ظ�����������л���λ
	 * @return ������������л���λ
	 */
	public List<GenePiece> getContainedGenePieces(){
		return containedGenePieces;
	}
	/**
	 * �����ڳ�ʼ����ʱ���벻Ҫ���ô˷���
	 */
	public void setContainedGenePieces(List<GenePiece> genePieces,int normalGeneNumber,int normalGeneHeaderLength,int normalGeneLength,int homeoticGeneNumber,int homeoticGeneHeaderLength,int homeoticGeneLength){
		this.containedGenePieces=genePieces;
		NormalGene normalGene;
		HomeoticGene homeoticGene;
		normalGeneList=new ArrayList<NormalGene>(normalGeneNumber);
		homeoticGeneList=new ArrayList<HomeoticGene>(homeoticGeneNumber);
		for(int i=0;i<normalGeneNumber;i++){
			normalGene=new NormalGene(normalGeneHeaderLength,normalGeneLength-normalGeneHeaderLength,
					containedGenePieces,i*normalGeneLength, (i+1)*normalGeneLength);
			normalGeneList.add(normalGene);
		}
		int normalGeneTotalLength=normalGeneLength*normalGeneNumber;
		for(int i=0;i<homeoticGeneNumber;i++){
			homeoticGene=new HomeoticGene(homeoticGeneHeaderLength,homeoticGeneLength-homeoticGeneHeaderLength,
					containedGenePieces,normalGeneTotalLength+i*homeoticGeneLength, normalGeneTotalLength+(i+1)*homeoticGeneLength);
			homeoticGeneList.add(homeoticGene);
		}
	}
}
