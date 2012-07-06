package domain.core.algmodel.genecomponent;

import java.util.Deque;


/**
 * ��ȷ��������Ĭ�Ϲ��캯�����������ִ���
 */
public abstract class Function extends GenePiece{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5233529550458131848L;
	private final String name;
	private final int arity;
	private final String symbol;
	private float value=Float.NaN;
	public Function(String name,int arity,String symbol){
		this.name=name;
		this.arity=arity;
		this.symbol=symbol;
	}
	public int getArity() {
		return arity;
	}
	@Override
	public final String toString() {
		// TODO Auto-generated method stub
		return name;
	}
	/**
	 * ��ʹ��LIFO�ķ�ʽ���м��㣬����ϵͳ������
	 */
	public abstract float operate(Deque<Float> operators);
	public String getSymbol() {
		return symbol;
	}
	@Override
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
}
