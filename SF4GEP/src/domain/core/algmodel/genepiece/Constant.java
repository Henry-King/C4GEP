package domain.core.algmodel.genepiece;

import java.util.Random;

/**
 * 整型常数类，这个类主要用来做为同源基因中函数的
 * @author 申远
 *
 */
public class Constant extends Computable {
	private static final long serialVersionUID = 5428175735104183458L;
	/**
	 * 初始化整型常数类，初始化后此方法将自动生成value值，代表普通基因的编号
	 * @param max 普通基因的数量
	 */
	public Constant(int max){
		Random random=new Random();
		value=random.nextInt(max);
	}
	/**
	 * 整型常数值不可设定，请不要调用此方法，调用后程序会抛出异常
	 * @param value 设定常数值，此设定无效，而且会抛出异常
	 */
	@Deprecated
	@Override
	public void setValue(float value) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("整型常数类的值由程序自动生成，用户不能手动设置");
	}
	/**
	 * 整型的符号表示不可设定，请不要调用此方法，调用后程序会抛出异常
	 * @param symbol 设定符号表示，此设定无效，而且会抛出异常
	 */
	@Deprecated
	@Override
	public void setSymbol(String symbol) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("不能手动设定整型常数的符号表示，这个符号表示就是此整型常数的值，由程序自动生成");
	}
	/**
	 * 返回整型常数的符号表示，即为整型常数的值的符号表示
	 */
	@Override
	public String getSymbol() {
		return Integer.toString((int)value);
	}
	/**
	 * 返回整型常数的字符串表示，本方法将自动调用getSymbol方法
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getSymbol();
	}
}
