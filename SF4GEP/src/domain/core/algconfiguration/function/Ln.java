package domain.core.algconfiguration.function;

import domain.core.algconfiguration.Function;

public class Ln extends Function {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3599922495039010030L;

	public Ln() {
		super("自然对数", "ln", 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public float operate(float[] operators) {
		// TODO Auto-generated method stub
		return (float) Math.log(operators[0]);
	}

}
