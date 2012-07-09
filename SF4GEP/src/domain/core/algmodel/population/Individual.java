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
 * 个体类,种群中保存着个体的List
 * @author 个体类
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
	 * 覆盖了Object的toString方法，本方法将以字符串的形式输出个体中所包含的全部Genepiece信息。
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer result=new StringBuffer();
		result.append("正常基因:");
		for(NormalGene normalGene:getNormalGeneList()){
			for(GenePiece piece:normalGene.getHeader().getContainedGenePieces())
				result.append(piece.getSymbol()+" ");
			for(GenePiece piece:normalGene.getTail().getContainedGenePieces())
				result.append(piece.getSymbol()+" ");
		}
		result.append("\n同源基因:");
		for(HomeoticGene homeoticGene:getHomeoticGeneList()){
			for(GenePiece piece:homeoticGene.getHeader().getContainedGenePieces())
				result.append(piece.getSymbol()+" ");
			for(GenePiece piece:homeoticGene.getTail().getContainedGenePieces())
				result.append(piece.getSymbol()+" ");
		}
		return result.toString();
	}
	/**
	 * 比较当前个体的适应值和用户指定的某个个体的适应值，并将此结果返回
	 * @param　用户提供的待比较的个体
	 * @return 如果当前个体适应值小于参数适应值，返回负数，如果当前个体适应值等于参数个体适应值，返回0，如果当前个体适应值大于参数个体适应值，返回正数
	 */
	@Override
	public int compareTo(Individual o) {
		// TODO Auto-generated method stub
		return Float.compare(fitness, o.getFitness());
	}
	/**
	 * 一个个体可能具有多个同源基因，一个个体内的多个同源基因可能产生多个不同的表达式，这个方法将返回在对某个确定的输入集求解的时候使用哪个同源基因翻译个体
	 * @return 被选择的同源基因的编号，从0开始，最大值为普通基因数量－1
	 */
	public int getSelectedHomeoticGeneNumber() {
		return selectedHomeoticGeneNumber;
	}
	/**
	 * 设置在求解输入集的时候将使用哪个同源基因上传个体。
	 * @param selectedHomeoticGeneNumber 同源基因编号
	 */
	public void setSelectedHomeoticGeneNumber(int selectedHomeoticGeneNumber) {
		this.selectedHomeoticGeneNumber = selectedHomeoticGeneNumber;
	}
	/**
	 * 返回当前个体的适应值
	 * @return 当前个体的适应值
	 */
	public Float getFitness(){
		return fitness;
	}
	/**
	 * 设置当前个体的适应值
	 * @param fitness 当前个体的适应值
	 */
	public void setFitness(Float fitness) {
		this.fitness = fitness;
	}
	/**
	 * 返回个体中所包含的普通基因的List
	 * @return 个体中的所有普通基因
	 */
	public List<NormalGene> getNormalGeneList() {
		return normalGeneList;
	}
	/**
	 * 返回个体中所包含的同源基因的List
	 * @return 个体中的所有同源基因
	 */
	public List<HomeoticGene> getHomeoticGeneList() {
		return homeoticGeneList;
	}
	/**
	 * 返回个体中包含的所有基因片段，即按照线性顺序遍历所有的普通基因和所有的同源基因，请不要对这个List的大小进行修改，否则可能会出现错误。
	 * 此为对这个List修改将直接影响对应的基因，反之也是一样。
	 * @return 包含个体中所有基因片段的list
	 */
	public List<GenePiece> getContainedGenePieces(){
		return containedGenePieces;
	}
	/**
	 * 返回普通基因中包含的所有基因片段，即按照线性顺序遍历所有的普通基因，请不要对这个List的大小进行修改，否则会抛出异常。
	 * 此为对这个List修改将直接影响对应的基因，反之也是一样。
	 * @return 包含普通基因中所有基因片段的list
	 */
	public List<GenePiece> getNormalGenePieces(){
		return normalGenePieces;
	}
	/**
	 * 返回同源基因中包含的所有基因片段，即按照线性顺序遍历所有的同源基因，请不要对这个List的大小进行修改，否则会抛出异常。
	 * 此为对这个List修改将直接影响对应的基因，反之也是一样。
	 * @return 包含同源基因中所有基因片段的list
	 */
	public List<GenePiece> getHomeoticGenePieces(){
		return homeoticGenePieces;
	}
	/**
	 * 此方法只需要在初始的时候调用，勿在其他的情况下调用，这个方法将自动产生个体所包含的普通基因和同源基因，并自动设置好基因的头部，尾部等信息。
	 * 对这些基因的修改将直接影响个体中的基因片段的List，反之依然。
	 * @param genePieces 个体中所有同源基因和所有非同源的基因片段
	 * @param normalGeneNumber 普通基因的数量
	 * @param normalGeneHeaderLength 普通基因的头部长度
	 * @param normalGeneLength 一个普通基因的长度，普通基因的长度等于普通基因的头长+普通基因的尾长
	 * @param homeoticGeneNumber 同源基因的数量
	 * @param homeoticGeneHeaderLength 同源基因的头部长度
	 * @param homeoticGeneLength 一个同源基因的长度，同源基因的长度等于同源基因的头长+同源基因的尾长
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
