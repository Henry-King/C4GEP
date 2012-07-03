package domain.core.algmodel.individualcomponent;

import java.io.Serializable;
import java.util.List;

import domain.core.algmodel.genecomponent.Computable;

public abstract class Tail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4667068182615591355L;
	protected List<Computable> computable;
	public Tail(List<Computable> computable){
		this.computable=computable;
	}
	public List<Computable> getComputable(){
		return computable;
	}
	public void addComputable(Computable piece){
		computable.add(piece);
	}
}
