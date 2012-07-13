package domain.core.algconfiguration.function;

import java.util.Deque;

import domain.core.algconfiguration.Function;

public class Multiply extends Function{
	private static final long serialVersionUID = 6877986033210887194L;
	public Multiply() {
		// TODO Auto-generated constructor stub
		name="³Ë·¨";
		symbol="*";
		arity=2;
	}
	@Override
	public float operate(Deque<Float> operators) {
		// TODO Auto-generated method stub
		return operators.removeFirst()*operators.removeFirst();
	}

}
