package domain.core.algmodel.individualcomponent;

import java.util.List;

import domain.core.algmodel.genecomponent.Computable;
import domain.core.algmodel.genecomponent.GenePiece;

public class NormalGeneTail extends Tail {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3087000575324792050L;

	@SuppressWarnings("unchecked")
	public NormalGeneTail(List<GenePiece> computable) {
		super((List<Computable>)(Object)computable);
		// TODO Auto-generated constructor stub
	}

}
