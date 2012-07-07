package domain.core.algmodel.genecomponent;

import java.io.Serializable;

import common.ICopy;

/**
 * 基因片段类，或称基因位类，在每一个头部或者尾部中，最终包含的都是此类GenePiece
 * @author 申远
 *
 */
public abstract class GenePiece implements Serializable,Cloneable,ICopy<GenePiece>{
	private static final long serialVersionUID = 7648117930489664221L;
	protected String symbol=new String();
	protected String name=new String();
	protected float value;
	/**
	 * 返回当前基因片段的符号表示，用String表示
	 * @return 基因片段符号表示
	 */
	public String getSymbol() {
		return symbol;
	}
	/**
	 * 设置当前基因片段的符号表示，用String表示
	 * @param symbol 基因片段符号表示
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	/**
	 * 返回当前基因片段的名称
	 * @return 基因片段名称的字符串表示
	 */
	public String getName(){
		return name;
	}
	/**
	 * 设置当前基因片段的名称
	 * @param name 当前基因片段的名称
	 */
	public void setName(String name){
		this.name=name;
	}
	/**
	 * 返回当前基因片段的名称表示，本方法将直接调用getName()。
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getName();
	}
	/**
	 * 获得当前基因片段的值
	 * @return 当前基因片段的值
	 */
	public float getValue(){
		return value;
	}
	/**
	 * 设置当前基因片段的值
	 * @param value 基因片段的值
	 */
	public void setValue(float value){
		this.value=value;
	}
	/**
	 * 克隆一份当前对象，这个方法调用了Object中的clone方法。
	 * 这是一个深度克隆，即源对象和新对象具有相同的初值，但具有不同的内存地址。
	 */
	@Override
	public GenePiece clone(){
		// TODO Auto-generated method stub
		GenePiece o=null;
		try {
			o=(GenePiece) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}
	@Override
	/**
	 * 这个copy方法将直接调用clone方法实现深度复制
	 */
	public GenePiece copy() {
		// TODO Auto-generated method stub
		return clone();
	}
}
