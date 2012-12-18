package domain.core.algconfiguration.function;

import domain.core.algconfiguration.Function;

public class Exp extends Function {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5223257217559469511L;

	public Exp() {
		super("以自然对数为底的指数函数", "exrp", 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public float operate(float[] operators) {
		// TODO Auto-generated method stub
		return (float) Math.exp(operators[0]);
	}

}
