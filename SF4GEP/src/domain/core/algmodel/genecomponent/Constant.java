package domain.core.algmodel.genecomponent;

import java.util.Random;


public class Constant extends Computable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5428175735104183458L;
	private int value;
	public Constant(int max){
		Random random=new Random();
		value=random.nextInt(max);
	}
	@Override
	public float getValue(){
		return value;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return Float.toString(getValue());
	}
	
}
