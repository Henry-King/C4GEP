package domain.core.algconfiguration.function;

import domain.core.algconfiguration.Function;

public class Cos extends Function {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2464313723376766801L;

	public Cos() {
		super("”‡œ“", "cos", 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public float operate(float[] operators) {
		// TODO Auto-generated method stub
		return (float) Math.cos(operators[0]);
	}
}
