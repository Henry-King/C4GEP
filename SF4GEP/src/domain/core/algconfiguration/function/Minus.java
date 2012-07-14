package domain.core.algconfiguration.function;


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
	public float operate(float[] operators) {
		// TODO Auto-generated method stub
		return operators[0]-operators[1];
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "¼õ·¨";
	}
}
