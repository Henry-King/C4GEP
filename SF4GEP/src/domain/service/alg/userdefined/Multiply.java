package domain.service.alg.userdefined;

import java.util.Deque;

import domain.core.algmodel.genecomponent.Function;

public class Multiply extends Function{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6877986033210887194L;

	public Multiply() {
		super("*",2,"*");
		// TODO Auto-generated constructor stub
	}

	@Override
	public float operate(Deque<Float> operators) {
		// TODO Auto-generated method stub
		return operators.removeFirst()*operators.removeFirst();
	}

}
