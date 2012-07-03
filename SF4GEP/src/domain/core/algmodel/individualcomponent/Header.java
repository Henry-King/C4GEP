package domain.core.algmodel.individualcomponent;

import java.io.Serializable;
import java.util.List;

import domain.core.algmodel.genecomponent.GenePiece;

public abstract class Header implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4787817859125343269L;
	protected List<GenePiece> genePieces;
	public Header(List<GenePiece> genePieces){
		this.genePieces=genePieces;
	}
	public List<GenePiece> getGenePieces(){
		return genePieces;
	}
	public void addGenePiece(GenePiece genePiece){
		genePieces.add(genePiece);
	}
}
