package domain.core.algmodel.individualcomponent;

import java.util.List;

import domain.core.algmodel.genecomponent.GenePiece;

public class HomeoticGene extends Gene{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4297205056477494966L;
	/**
	 * 
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