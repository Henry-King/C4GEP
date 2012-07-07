package domain.core.algmodel.individualcomponent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import common.ICopy;

import domain.core.algmodel.genecomponent.GenePiece;

/**
 * 基因头部实体的基类，是个抽象类
 * @author 申远
 *
 */
public abstract class Header implements Serializable,ICopy<Header>{
	private static final long serialVersionUID = -4787817859125343269L;
	protected List<GenePiece> containedGenePieces;
	private List<GenePiece> genePieces;
	private int start;
	private int end;
	/**
	 * 创建基因头部的构造函数，用户需要提供个体或或者基因中的基因片段List，并确定头部从哪个地址开始，到哪个地址结束
	 * @param genePieces 基因片段List
	 * @param start 基因头部开始的地址，包括
	 * @param end 基因头部结束的地址，不包括
	 */
	public Header(List<GenePiece> genePieces,int start,int end){
		this.genePieces=genePieces;
		this.start=start;
		this.end=end;
		this.containedGenePieces=genePieces.subList(start, end);
	}
	/**
	 * 返回头部所包含的基因片段。
	 * @return 基因片段List
	 */
	public List<GenePiece> getContainedGenePieces(){
		return containedGenePieces;
	}
	@SuppressWarnings("unchecked")
	private void readObject(ObjectInputStream in){
		try {
			genePieces=(List<GenePiece>) in.readObject();
			start=in.readInt();
			end=in.readInt();
			containedGenePieces=genePieces.subList(start, end);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
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
	public Header copy() {
		// TODO Auto-generated method stub
		Header header=null;
		if(this instanceof NormalGeneHeader){
			header=new NormalGeneHeader(genePieces, start, end);
		}
		else if(this instanceof HomeoticGeneHeader) {
			header=new HomeoticGeneHeader(genePieces, start, end);
		}
		return header;
	}
}
