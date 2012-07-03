package domain.core.algmodel.individualcomponent;

import java.util.List;

import domain.core.algmodel.genecomponent.Computable;
import domain.core.algmodel.genecomponent.GenePiece;

public class HomeoticGeneTail extends Tail{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3919750383832279100L;

	@SuppressWarnings("unchecked")
	public HomeoticGeneTail(List<GenePiece> computable) {
		super((List<Computable>)(Object)computable);
		// TODO Auto-generated constructor stub
	}

}
