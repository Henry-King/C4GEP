package domain.service.alg.userdefined;

import java.util.Deque;

import domain.core.algmodel.genecomponent.Function;

public class Minus extends Function {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3358438185571026193L;

	public Minus() {
		super("¼õ·¨", 2,"-");
		// TODO Auto-generated constructor stub
	}

	@Override
	public float operate(Deque<Float> operators) {
		// TODO Auto-generated method stub
		return operators.removeFirst()-operators.removeFirst();
	}

}
