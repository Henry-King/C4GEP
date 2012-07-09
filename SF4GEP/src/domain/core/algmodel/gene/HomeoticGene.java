package domain.core.algmodel.gene;

import java.util.List;

import domain.core.algmodel.genepiece.GenePiece;

/**
 * 同源基因实体类
 * @author 申远
 * 
 */
public class HomeoticGene extends Gene{
	private static final long serialVersionUID = 4297205056477494966L;
	/**
	 * 创建同源基因的构造函数，用户需要在当前基因对应的个体的全部基因片段中指定当前基因从基因片段的哪里开始，哪里结束。
	 * @param headerLength 同源基因的头部长度
	 * @param tailLength 同源基因的尾部长度
	 * @param genePieces 个体中所包含的全部基因片段
	 * @param start 当前基因在个体基因片段中开始的地方，包括这个地址
	 * @param end 当前基因在个体基因片段中结束的地方，不包括这个地址
	 */
	public HomeoticGene(int headerLength,int tailLength,List<GenePiece> genePieces,int start,int end){
		super(genePieces,start,end);
		header=new HomeoticGeneHeader(genePieces,start,start+headerLength);
		tail=new HomeoticGeneTail(genePieces,start+headerLength, end);
	}
	@Override
	public HomeoticGeneHeader getHeader() {
		// TODO Auto-generated method stub
		return (HomeoticGeneHeader) header;
	}
	@Override
	public HomeoticGeneTail getTail() {
		// TODO Auto-generated method stub
		return (HomeoticGeneTail) tail;
	}


}
