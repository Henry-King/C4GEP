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
	public HomeoticGene(int headerLength,int tailLength,List<GenePiece> genePieces){
		super(genePieces);
		header=new HomeoticGeneHeader(genePieces.subList(0, headerLength));
		tail=new HomeoticGeneTail(genePieces.subList(headerLength, headerLength+tailLength));
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
