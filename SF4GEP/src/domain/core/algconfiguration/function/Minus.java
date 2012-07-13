package domain.core.algconfiguration.function;

import java.util.Deque;

import domain.core.algconfiguration.Function;

public class Minus extends Function {

	private static final long serialVersionUID = 3358438185571026193L;
	public Minus() {
		// TODO Auto-generated constructor stub
		name="¼õ·¨";
		symbol="-";
		arity=2;
	}
	
	@Override
	public float operate(Deque<Float> operators) {
		// TODO Auto-generated method stub
		return operators.removeFirst()-operators.removeFirst();
	}

}
