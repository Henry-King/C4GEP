package domain.core.algmodel.genecomponent;

import java.io.Serializable;

import common.ICopy;


public abstract class GenePiece implements Serializable,Cloneable,ICopy<GenePiece>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7648117930489664221L;
	protected String symbol;
	
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return symbol;
	}
	public abstract float getValue();
	@Override
	public GenePiece clone(){
		// TODO Auto-generated method stub
		GenePiece o=null;
		try {
			o=(GenePiece) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}
	@Override
	public GenePiece copy() {
		// TODO Auto-generated method stub
		return clone();
	}
}
