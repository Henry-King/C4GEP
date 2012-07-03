package domain.core.algmodel.individualcomponent;

import java.io.Serializable;
import java.util.List;

import domain.core.algmodel.genecomponent.GenePiece;

public abstract class Gene implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7104627974958183372L;
	/**
	 * 
	 */

	private float value;
	protected List<GenePiece> containedGenePieces;
	protected Header header;
	protected Tail tail;

	public Gene(List<GenePiece> genePieces){
		containedGenePieces=genePieces;
	}
	public abstract Header getHeader();
	public abstract Tail getTail();
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public List<GenePiece> getContainedGenePieces(){
		return containedGenePieces;
	}
}
