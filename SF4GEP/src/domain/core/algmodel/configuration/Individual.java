package domain.core.algmodel.configuration;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import common.ICopy;

import domain.core.algmodel.genecomponent.GenePiece;
import domain.core.algmodel.individualcomponent.HomeoticGene;
import domain.core.algmodel.individualcomponent.NormalGene;


/**
 * 本类的三个属性在完整版程序运行的时候应该可以自动生成，因此没有相应的get方法，在测试的时候最好使用reflect机制进行赋值，如果实在
 * 不会的话，可以去掉private进行测试
 */
public class Individual implements Comparable<Individual>,Serializable,ICopy<Individual>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7942680950588488680L;
	/**
	 * 
	 */	
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
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer result=new StringBuffer();
		result.append("正常基因:");
		for(NormalGene normalGene:getNormalGeneList()){
			for(GenePiece piece:normalGene.getHeader().getContainedGenePieces())
				result.append(piece+" ");
			for(GenePiece piece:normalGene.getTail().getContainedGenePieces())
				result.append(piece+" ");
		}
		result.append("\n同源基因:");
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
	public List<GenePiece> getContainedGenePieces(){
		return containedGenePieces;
	}
	public List<GenePiece> getNormalGenePieces(){
		return normalGenePieces;
	}
	public List<GenePiece> getHomeoticGenePieces(){
		return homeoticGenePieces;
	}
	/**
	 * 除了在初始化的时候，请不要调用此方法
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
