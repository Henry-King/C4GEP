package domain.core.algconfiguration.function;

import java.util.Deque;

import domain.core.algconfiguration.Function;

public class Divide extends Function {

	private static final long serialVersionUID = -3356166328450491371L;
	public Divide() {
		// TODO Auto-generated constructor stub
		name="³ý·¨";
		symbol="/";
		arity=2;
	}
	@Override
	public float operate(Deque<Float> operators) {
		// TODO Auto-generated method stub
		return operators.removeFirst()/operators.removeFirst();
	}

}
