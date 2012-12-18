package domain.core.algconfiguration.function;

import domain.core.algconfiguration.Function;

public class Sqrt extends Function {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2983985422861855229L;

	public Sqrt() {
		super("Æ½·½¸ù", "¡Ì", 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public float operate(float[] operators) {
		// TODO Auto-generated method stub
		return (float) Math.sqrt(operators[0]);
	}

}
