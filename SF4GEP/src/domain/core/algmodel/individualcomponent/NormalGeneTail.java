package domain.core.algmodel.individualcomponent;

import java.util.List;

import domain.core.algmodel.genecomponent.GenePiece;

/**
 * 普通基因尾部的实体类
 * @author 申远
 *
 */
public class NormalGeneTail extends Tail {

	private static final long serialVersionUID = -3087000575324792050L;
	/**
	 * 创建基因尾部的构造函数，用户需要提供个体或或者基因中的基因片段List，并确定头部从哪个地址开始，到哪个地址结束
	 * @param genePieces 基因片段List
	 * @param start 基因头部开始的地址，包括
	 * @param end 基因头部结束的地址，不包括
	 */
	public NormalGeneTail(List<GenePiece> genePieces,int start,int end) {
		super(genePieces,start,end);
		// TODO Auto-generated constructor stub
	}

}
