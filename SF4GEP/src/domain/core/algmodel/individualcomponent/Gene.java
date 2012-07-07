package domain.core.algmodel.individualcomponent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import common.ICopy;

import domain.core.algmodel.genecomponent.GenePiece;

/**
 * 基因类，此类为抽象类，提供了基因的通用的信息
 * @author 申远
 *
 */
public abstract class Gene implements Serializable,ICopy<Gene> {
	private static final long serialVersionUID = 7104627974958183372L;
	private float value;
	private int start;
	private int end;
	private List<GenePiece> originalGenePiece;
	protected List<GenePiece> containedGenePieces;
	protected Header header;
	protected Tail tail;
	/**
	 * 构造函数,创建一个新的基因，用户需要在当前基因对应的个体的全部基因片段中指定当前基因从基因片段的哪里开始，哪里结束。
	 * @param origGenePieces 当前基因对应的个体中全部的基因片段
	 * @param start 当前基因在个体基因片段中开始的地方，包括这个地址
	 * @param end 当前基因在个体基因片段中结束的地方，不包括这个地址
	 */
	public Gene(List<GenePiece> origGenePieces,int start,int end){
		this.originalGenePiece=origGenePieces;
		this.start=start;
		this.end=end;
		containedGenePieces=origGenePieces.subList(start, end);
	}
	/**
	 * 返回当前基因的头部
	 * @return 当前基因的头部
	 */
	public abstract Header getHeader();
	/**
	 * 返回当前基因的尾部
	 * @return 当前基因的尾部
	 */
	public abstract Tail getTail();
	/**
	 * 返回当前基因的值，通过使用同源基因连接各个基因的值，可以求得个体对某个输入集的解，通过对解集进行某种数学变换，可以得到个体的适应值。
	 * @return 当前基因的值
	 */
	public float getValue() {
		return value;
	}
	/**
	 * 设置当前基因的值
	 * @param value 当前基因的值
	 */
	public void setValue(float value) {
		this.value = value;
	}
	/**
	 * 返回当前基因所对应的基因片段
	 * @return 包含当前基因所对应的基因片段的List
	 */
	public List<GenePiece> getContainedGenePieces(){
		return containedGenePieces;
	}
	@SuppressWarnings("unchecked")
	private void readObject(ObjectInputStream in){
		try {
			value=in.readFloat();
			start=in.readInt();
			end=in.readInt();
			originalGenePiece=(List<GenePiece>) in.readObject();
			containedGenePieces=originalGenePiece.subList(start, end);
			header=(Header) in.readObject();
			tail=(Tail) in.readObject();
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void writeObject(ObjectOutputStream out){
		try {
			out.writeFloat(value);
			out.writeInt(start);
			out.writeInt(end);
			out.writeObject(originalGenePiece);
			out.writeObject(header);
			out.writeObject(tail);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public Gene copy(){
		// TODO Auto-generated method stub
		Gene gene=null;
		if(this instanceof NormalGene){
			NormalGene normalGene=(NormalGene)this;
			gene=new NormalGene(normalGene.getHeader().getContainedGenePieces().size(), normalGene.getTail().getContainedGenePieces().size(), originalGenePiece, start, end);
		}
		else if (this instanceof HomeoticGene) {
			HomeoticGene homeoticGene=(HomeoticGene)this;
			gene=new HomeoticGene(homeoticGene.getHeader().getContainedGenePieces().size(), homeoticGene.getTail().getContainedGenePieces().size(), originalGenePiece, start, end);
		}
		return gene;
	}
}
