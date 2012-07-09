package domain.core.algmodel.gene;

import java.util.List;

import domain.core.algmodel.genepiece.GenePiece;

/**
 * 普通基因实体类
 * @author 申远
 *
 */
public class NormalGene extends Gene{
	private static final long serialVersionUID = -4798878563976876499L;
	/**
	 * 创建普通基因的构造函数，用户需要在当前基因对应的个体的全部基因片段中指定当前基因从基因片段的哪里开始，哪里结束。
	 * @param headerLength 普通基因的头部长度
	 * @param tailLength 普通基因的尾部长度
	 * @param genePieces 个体中所包含的全部基因片段
	 * @param start 当前基因在个体基因片段中开始的地方，包括这个地址
	 * @param end 当前基因在个体基因片段中结束的地方，不包括这个地址
	 */
	public NormalGene(int headerLength,int tailLength,List<GenePiece> genePieces,int start,int end){
		super(genePieces,start,end);
		header=new NormalGeneHeader(genePieces,start,start+headerLength);
		tail=new NormalGeneTail(genePieces,start+headerLength, end);
	}

	@Override
	public NormalGeneHeader getHeader() {
		// TODO Auto-generated method stub
		return (NormalGeneHeader) header;
	}
	@Override
	public NormalGeneTail getTail() {
		// TODO Auto-generated method stub
		return (NormalGeneTail) tail;
	}

}
