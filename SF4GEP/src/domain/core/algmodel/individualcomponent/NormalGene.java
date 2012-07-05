package domain.core.algmodel.individualcomponent;

import java.util.List;

import domain.core.algmodel.genecomponent.GenePiece;

public class NormalGene extends Gene{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4798878563976876499L;
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
