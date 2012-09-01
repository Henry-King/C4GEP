package domain.core.algconfiguration.function;

import domain.core.algconfiguration.Function;

public class Sin extends Function {

	private static final long serialVersionUID = 2793469654664264671L;

	public Sin() {
		super("ÕýÏÒ","sin",1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public float operate(float[] operators) {
		// TODO Auto-generated method stub
		return (float) Math.sin(operators[0]);
	}

}
