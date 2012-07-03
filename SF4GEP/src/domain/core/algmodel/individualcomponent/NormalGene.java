package domain.core.algmodel.individualcomponent;

import java.util.List;

import domain.core.algmodel.genecomponent.ConstantList;
import domain.core.algmodel.genecomponent.GenePiece;

public class NormalGene extends Gene{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4798878563976876499L;
	private ConstantList constantList;
	public NormalGene(int headerLength,int tailLength,List<GenePiece> genePieces){
		super(genePieces);
		header=new NormalGeneHeader(genePieces.subList(0, headerLength));
		tail=new NormalGeneTail(genePieces.subList(headerLength, headerLength+tailLength));
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

	public ConstantList getConstantList() {
		return constantList;
	}

	public void setConstantList(ConstantList constantList) {
		this.constantList = constantList;
	}

}
