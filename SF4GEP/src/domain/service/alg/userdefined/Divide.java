package domain.service.alg.userdefined;

import java.util.Deque;

import domain.core.algmodel.genepiece.Function;

public class Divide extends Function {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3356166328450491371L;

	public Divide() {
		super("³ý·¨", 2,"/");
		// TODO Auto-generated constructor stub
	}

	@Override
	public float operate(Deque<Float> operators) {
		// TODO Auto-generated method stub
		return operators.removeFirst()/operators.removeFirst();
	}

}
