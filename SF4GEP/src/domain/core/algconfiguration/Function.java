package domain.core.algconfiguration;

import java.io.Serializable;

/**
 * ���������࣬��ȷ���κμ̳д�������඼����Ĭ�Ϲ��캯��������ϵͳ����ִ���
 */
public abstract class Function implements Serializable,Cloneable{
	private static final long serialVersionUID = 7888828895947830787L;
	protected final String name;
	protected final String symbol;
	protected final Integer arity;
	public Function(String name,String symbol,Integer arity) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.symbol=symbol;
		this.arity=arity;
	}
	public String getName() {
		return name;
	}
	public String getSymbol() {
		return symbol;
	}
	public Integer getArity() {
		return arity;
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
	 * @param operators �����������飬��һ������������ͷ��
	 * @return �������������ķ���ֵ��
	 */
	public abstract float operate(float[] operators);
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Function){
			Function r=(Function) obj;
			return r.getClass().equals(getClass());			
		}
		else {
			return false;
		}
	}
	@Override
	public int hashCode(){
		int result = 17;
		result = 37 * result + name.hashCode();
		result = 37 * result + symbol.hashCode();
		result = 37 * result + arity.hashCode();
		return result;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
}
