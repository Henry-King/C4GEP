package domain.core.algconfiguration.function;

import java.util.Deque;

import domain.core.algconfiguration.Function;

public class Additioin extends Function {
	private static final long serialVersionUID = 8408096285882718047L;
	public Additioin() {
		// TODO Auto-generated constructor stub
		name="¼Ó·¨";
		symbol="+";
		arity=2;
	}
	@Override
	public float operate(Deque<Float> operators) {
		// TODO Auto-generated method stub
		return operators.removeFirst()+operators.removeFirst();
	}

}
