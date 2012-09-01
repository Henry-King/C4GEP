package domain.core.algconfiguration.function;


import domain.core.algconfiguration.Function;

public class Addition extends Function {
	private static final long serialVersionUID = 8408096285882718047L;
	public Addition() {
		// TODO Auto-generated constructor stub
		super("¼Ó·¨", "+", 2);
	}
	@Override
	public float operate(float[] operators) {
		// TODO Auto-generated method stub
		return operators[0]+operators[1];
	}
}
