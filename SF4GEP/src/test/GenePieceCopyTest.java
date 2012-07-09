package test;

import domain.core.algmodel.genepiece.GenePiece;
import domain.service.alg.userdefined.Additioin;

public class GenePieceCopyTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GenePiece genePiece=new Additioin();
		genePiece.setSymbol("00");
		GenePiece genePiece2=genePiece.copy();
		genePiece2.setSymbol("10");
		System.out.println(genePiece.getSymbol());
		System.out.println(genePiece2.getSymbol());
	}

}
