package domain.core.algconfiguration;

import java.io.Serializable;

/**
 * 函数抽象类，请确保任何继承此类的子类都具有默认构造函数，否则系统会出现错误
 */
public abstract class Function implements Serializable,Cloneable{
	private static final long serialVersionUID = -5233529550458131848L;
	private Integer id;
	private boolean used=false;
	protected String name;
	protected String symbol;
	protected Integer arity;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public boolean isUsed() {
		return used;
	}
	public void setUsed(boolean used) {
		this.used = used;
	}
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
	 * 进行具体的函数运算，并返回运算结果
	 * @param operators 函数参数数组，第一个参数在数组头部
	 * @return 函数进行运算后的返回值。
	 */
	public abstract float operate(float[] operators);
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Function){
			Function r=(Function) obj;
			return r.getClass().equals(r.getClass());			
		}
		else {
			return false;
		}
	}
	@Override
	public int hashCode(){
		int result = 17;
		result = 37 * result + (used ? 0 : 1);
		result = 37 * result + name.hashCode();
		result = 37 * result + symbol.hashCode();
		result = 37 * result + arity.hashCode();
		return result;
	}
}
