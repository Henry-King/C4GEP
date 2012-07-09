package domain.core.algmodel.genepiece;

import java.util.Deque;


/**
 * 函数抽象类，请确保任何继承此类的子类都具有默认构造函数，否则系统会出现错误
 */
public abstract class Function extends GenePiece{
	private static final long serialVersionUID = -5233529550458131848L;
	private final int arity;
	/**
	 * 构造函数，需要提供函数名，函数的符号表示，函数的目数
	 * @param name 函数名
	 * @param arity 函数的目数，即操作符数量，例如加法的目数为2，平方根的目数为1
	 * @param symbol 函数的符号表示
	 */
	public Function(String name,int arity,String symbol){
		value=Float.NaN;
		this.name=name;
		this.arity=arity;
		this.symbol=symbol;
	}
	/**
	 * 获得当前函数的目数
	 */
	public int getArity() {
		return arity;
	}
	/**
	 * 进行具体的函数运算，并返回运算结果
	 * @param operators 操作符队列，请使用LIFO的方式进行计算，否则系统将出错
	 * @return 函数进行运算后的返回值。
	 */
	public abstract float operate(Deque<Float> operators);
}
