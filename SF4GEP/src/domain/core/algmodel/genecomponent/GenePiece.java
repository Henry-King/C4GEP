package domain.core.algmodel.genecomponent;

import java.io.Serializable;

public abstract class GenePiece implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7648117930489664221L;
	private String symbol;
	
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
}
