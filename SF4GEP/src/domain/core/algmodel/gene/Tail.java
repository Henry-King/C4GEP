package domain.core.algmodel.gene;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import common.ICopy;

import domain.core.algmodel.genepiece.Computable;
import domain.core.algmodel.genepiece.GenePiece;

/**
 * 基因尾部实体的基类，是一个抽象类
 * @author 申远
 *
 */
public abstract class Tail implements Serializable,ICopy<Tail>{
	private static final long serialVersionUID = -4667068182615591355L;
	protected List<Computable> computable;
	private List<GenePiece> genePieces;
	private int start;
	private int end;

	/**
	 * 创建基因尾部的构造函数，用户需要提供个体或或者基因中的基因片段List，并确定头部从哪个地址开始，到哪个地址结束
	 * @param genePieces 基因片段List
	 * @param start 基因头部开始的地址，包括
	 * @param end 基因头部结束的地址，不包括
	 */
	@SuppressWarnings("unchecked")
	public Tail(List<GenePiece> genePieces,int start,int end){
		this.genePieces=genePieces;
		this.start=start;
		this.end=end;
		this.computable=(List<Computable>)(Object)genePieces.subList(start, end);
	}
	/**
	 * 基因尾部所包含的全部基因片段
	 * @return 包含有尾部基因片段的List
	 */
	public List<Computable> getContainedGenePieces(){
		return computable;
	}
	@SuppressWarnings("unchecked")
	private void readObject(ObjectInputStream in){
		try {
			genePieces=(List<GenePiece>) in.readObject();
			start=in.readInt();
			end=in.readInt();
			computable=(List<Computable>)(Object)genePieces.subList(start, end);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	private void writeObject(ObjectOutputStream out){
		try {
			out.writeObject(genePieces);
			out.writeInt(start);
			out.writeInt(end);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public Tail copy() {
		// TODO Auto-generated method stub
		Tail tail=null;
		if(this instanceof NormalGeneTail){
			tail=new NormalGeneTail(genePieces, start, end);
		}
		else if (this instanceof HomeoticGeneTail) {
			tail=new HomeoticGeneTail(genePieces, start, end);
		}
		return tail;
	}
}
