package domain.core.algconfiguration.function;


import domain.core.algconfiguration.Function;

public class Multiply extends Function{
	private static final long serialVersionUID = 6877986033210887194L;
	public Multiply() {
		// TODO Auto-generated constructor stub
		super("³Ë·¨", "*", 2);
	}
	@Override
	public float operate(float[] operators) {
		// TODO Auto-generated method stub
		return operators[0]*operators[1];
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "³Ë·¨";
	}
}
