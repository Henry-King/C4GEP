package domain.core.algmodel.population;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import common.ICopy;

import domain.core.algmodel.gene.HomeoticGene;
import domain.core.algmodel.gene.NormalGene;
import domain.core.algmodel.genepiece.GenePiece;

/**
 * ������,��Ⱥ�б����Ÿ����List
 * @author ������
 *
 */
public class Individual implements Comparable<Individual>,Serializable,ICopy<Individual>{
	private static final long serialVersionUID = -7942680950588488680L;
	private int normalGeneNumber;
	private int normalGeneHeaderLength;
	private int normalGeneLength;
	private int homeoticGeneNumber;
	private int homeoticGeneHeaderLength;
	private int homeoticGeneLength;
	
	
	private List<GenePiece> containedGenePieces;
	private List<GenePiece> normalGenePieces;
	private List<GenePiece> homeoticGenePieces;
	
	private List<NormalGene> normalGeneList;
	private List<HomeoticGene> homeoticGeneList;
	private Float fitness;
	private int selectedHomeoticGeneNumber;
	public Individual(){

	}
	/**
	 * ������Object��toString�����������������ַ�������ʽ�����������������ȫ��Genepiece��Ϣ��
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer result=new StringBuffer();
		result.append("��������:");
		for(NormalGene normalGene:getNormalGeneList()){
			for(GenePiece piece:normalGene.getHeader().getContainedGenePieces())
				result.append(piece.getSymbol()+" ");
			for(GenePiece piece:normalGene.getTail().getContainedGenePieces())
				result.append(piece.getSymbol()+" ");
		}
		result.append("\nͬԴ����:");
		for(HomeoticGene homeoticGene:getHomeoticGeneList()){
			for(GenePiece piece:homeoticGene.getHeader().getContainedGenePieces())
				result.append(piece.getSymbol()+" ");
			for(GenePiece piece:homeoticGene.getTail().getContainedGenePieces())
				result.append(piece.getSymbol()+" ");
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
	 * ���ظ���������������ͨ�����List
	 * @return �����е�������ͨ����
	 */
	public List<NormalGene> getNormalGeneList() {
		return normalGeneList;
	}
	/**
	 * ���ظ�������������ͬԴ�����List
	 * @return �����е�����ͬԴ����
	 */
	public List<HomeoticGene> getHomeoticGeneList() {
		return homeoticGeneList;
	}
	/**
	 * ���ظ����а��������л���Ƭ�Σ�����������˳��������е���ͨ��������е�ͬԴ�����벻Ҫ�����List�Ĵ�С�����޸ģ�������ܻ���ִ���
	 * ��Ϊ�����List�޸Ľ�ֱ��Ӱ���Ӧ�Ļ��򣬷�֮Ҳ��һ����
	 * @return �������������л���Ƭ�ε�list
	 */
	public List<GenePiece> getContainedGenePieces(){
		return containedGenePieces;
	}
	/**
	 * ������ͨ�����а��������л���Ƭ�Σ�����������˳��������е���ͨ�����벻Ҫ�����List�Ĵ�С�����޸ģ�������׳��쳣��
	 * ��Ϊ�����List�޸Ľ�ֱ��Ӱ���Ӧ�Ļ��򣬷�֮Ҳ��һ����
	 * @return ������ͨ���������л���Ƭ�ε�list
	 */
	public List<GenePiece> getNormalGenePieces(){
		return normalGenePieces;
	}
	/**
	 * ����ͬԴ�����а��������л���Ƭ�Σ�����������˳��������е�ͬԴ�����벻Ҫ�����List�Ĵ�С�����޸ģ�������׳��쳣��
	 * ��Ϊ�����List�޸Ľ�ֱ��Ӱ���Ӧ�Ļ��򣬷�֮Ҳ��һ����
	 * @return ����ͬԴ���������л���Ƭ�ε�list
	 */
	public List<GenePiece> getHomeoticGenePieces(){
		return homeoticGenePieces;
	}
	/**
	 * �˷���ֻ��Ҫ�ڳ�ʼ��ʱ����ã���������������µ��ã�����������Զ�������������������ͨ�����ͬԴ���򣬲��Զ����úû����ͷ����β������Ϣ��
	 * ����Щ������޸Ľ�ֱ��Ӱ������еĻ���Ƭ�ε�List����֮��Ȼ��
	 * @param genePieces ����������ͬԴ��������з�ͬԴ�Ļ���Ƭ��
	 * @param normalGeneNumber ��ͨ���������
	 * @param normalGeneHeaderLength ��ͨ�����ͷ������
	 * @param normalGeneLength һ����ͨ����ĳ��ȣ���ͨ����ĳ��ȵ�����ͨ�����ͷ��+��ͨ�����β��
	 * @param homeoticGeneNumber ͬԴ���������
	 * @param homeoticGeneHeaderLength ͬԴ�����ͷ������
	 * @param homeoticGeneLength һ��ͬԴ����ĳ��ȣ�ͬԴ����ĳ��ȵ���ͬԴ�����ͷ��+ͬԴ�����β��
	 */
	public void setContainedGenePieces(List<GenePiece> genePieces,int normalGeneNumber,int normalGeneHeaderLength,int normalGeneLength,int homeoticGeneNumber,int homeoticGeneHeaderLength,int homeoticGeneLength){
		this.containedGenePieces=genePieces;
		this.normalGeneHeaderLength=normalGeneHeaderLength;
		this.normalGeneLength=normalGeneLength;
		this.normalGeneNumber=normalGeneNumber;
		this.homeoticGeneHeaderLength=homeoticGeneHeaderLength;
		this.homeoticGeneLength=homeoticGeneLength;
		this.homeoticGeneNumber=homeoticGeneNumber;
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
		int homeoticGeneTotalLenth=homeoticGeneLength*homeoticGeneNumber;
		normalGenePieces=genePieces.subList(0, normalGeneTotalLength);
		homeoticGenePieces=genePieces.subList(normalGeneTotalLength, normalGeneTotalLength+homeoticGeneTotalLenth);
	}
	@SuppressWarnings("unchecked")
	private void readObject(ObjectInputStream in){
		try {
			normalGeneHeaderLength=in.readInt();
			normalGeneLength=in.readInt();
			normalGeneNumber=in.readInt();
			homeoticGeneHeaderLength=in.readInt();
			homeoticGeneLength=in.readInt();
			homeoticGeneNumber=in.readInt();
			containedGenePieces=(List<GenePiece>) in.readObject();
			normalGeneList=(List<NormalGene>) in.readObject();
			homeoticGeneList=(List<HomeoticGene>) in.readObject();
			fitness=in.readFloat();
			selectedHomeoticGeneNumber=in.readInt();
			int normalGeneTotalLength=normalGeneLength*normalGeneNumber;
			int homeoticGeneTotalLenth=homeoticGeneLength*homeoticGeneNumber;
			normalGenePieces=containedGenePieces.subList(0, normalGeneTotalLength);
			homeoticGenePieces=containedGenePieces.subList(normalGeneTotalLength, normalGeneTotalLength+homeoticGeneTotalLenth);
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void writeObject(ObjectOutputStream out){
		try {
			out.writeInt(normalGeneHeaderLength);
			out.writeInt(normalGeneLength);
			out.writeInt(normalGeneNumber);
			out.writeInt(homeoticGeneHeaderLength);
			out.writeInt(homeoticGeneLength);
			out.writeInt(homeoticGeneNumber);
			out.writeObject(containedGenePieces);
			out.writeObject(normalGeneList);
			out.writeObject(homeoticGeneList);
			out.writeFloat(fitness);
			out.writeInt(selectedHomeoticGeneNumber);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public Individual copy() {
		// TODO Auto-generated method stub
		Individual individual=new Individual();
		List<GenePiece> copiedAllGenePieces=new ArrayList<GenePiece>(containedGenePieces.size());
		for(GenePiece genePiece:containedGenePieces)
			copiedAllGenePieces.add(genePiece.clone());
		individual.setContainedGenePieces(copiedAllGenePieces, normalGeneNumber, normalGeneHeaderLength, normalGeneLength, homeoticGeneNumber, homeoticGeneHeaderLength, homeoticGeneLength);
		individual.setFitness(fitness);
		individual.setSelectedHomeoticGeneNumber(selectedHomeoticGeneNumber);
		return individual;
	}
}
