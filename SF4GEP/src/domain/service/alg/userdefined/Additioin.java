package domain.service.alg.userdefined;

import java.util.Deque;

import domain.core.algmodel.genepiece.Function;

public class Additioin extends Function {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8408096285882718047L;

	public Additioin() {
		super("¼Ó·¨", 2,"+");
		// TODO Auto-generated constructor stub
	}

	@Override
	public float operate(Deque<Float> operators) {
		// TODO Auto-generated method stub
		return operators.removeFirst()+operators.removeFirst();
	}

}
