package domain.core.algconfiguration.function;


import domain.core.algconfiguration.Function;

public class Divide extends Function {

	private static final long serialVersionUID = -3356166328450491371L;
	public Divide() {
		// TODO Auto-generated constructor stub
		name="����";
		symbol="/";
		arity=2;
	}
	@Override
	public float operate(float[] operators) {
		// TODO Auto-generated method stub
		return operators[0]/operators[1];
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "����";
	}
}
