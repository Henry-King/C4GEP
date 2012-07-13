package domain.core.algconfiguration;

import java.io.Serializable;
import java.util.Deque;

/**
 * ���������࣬��ȷ���κμ̳д�������඼����Ĭ�Ϲ��캯��������ϵͳ����ִ���
 */
public abstract class Function implements Serializable,Cloneable{
	private static final long serialVersionUID = -5233529550458131848L;
	protected String name;
	protected String symbol;
	protected Integer arity;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public Integer getArity() {
		return arity;
	}
	public void setArity(Integer arity) {
		this.arity = arity;
	}
	@Override
	public Function clone() {
		// TODO Auto-generated method stub
		Function o=null;
		try {
			o = (Function) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}
	/**
	 * ���о���ĺ������㣬������������
	 * @param operators �����������У���һ�������ڶ���
	 * @return �������������ķ���ֵ��
	 */
	public abstract float operate(Deque<Float> operators);
}
