package domain.core.algmodel.genecomponent;

import java.util.Random;


public class Constant extends Computable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5428175735104183458L;
	private int index;
	private ConstantList constantList;
	private int max;
	public Constant(ConstantList constantList,int max){
		this.constantList=constantList;
		this.max=max;
		generateConstantIndex();
	}
	public Constant(int max){
		this.max=max;
		generateConstantIndex();
	}
	public ConstantList getConstantList(){
		return constantList;
	}
	public final void generateConstantIndex(){
		Random random=new Random();
		index=random.nextInt(max);
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	@Override
	public float getValue(){
		if(constantList!=null)
			return constantList.getValue(index);
		else 
			return index;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return Float.toString(getValue());
	}
	
}
