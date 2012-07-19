package domain.core.algOutput;

import java.io.Serializable;

import domain.core.algconfiguration.Function;

/**
 * 基因片段类，或称基因位类，在每一个头部或者尾部中，最终包含的都是此类GenePiece
 * @author 申远
 *
 */
public class GenePiece implements Serializable,Cloneable{
	private Integer id;
	private static final long serialVersionUID = 7648117930489664221L;
	private String symbol;
	private String name;
	private Float value;
	private int variableIndex;
	private GenePieceType genePieceType;
	private Function func;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	 * 获得当前基因片段的值
	 * @return 当前基因片段的值
	 */
	public Float getValue(){
		return value;
	}
	/**
	 * 设置当前基因片段的值
	 * @param value 基因片段的值
	 */
	public void setValue(Float value){
		this.value=value;
	}
	public int getVariableIndex() {
		return variableIndex;
	}
	public void setVariableIndex(int variableIndex) {
		this.variableIndex = variableIndex;
	}
	public GenePieceType getGenePieceType() {
		return genePieceType;
	}
	public void setGenePieceType(GenePieceType genePieceType) {
		this.genePieceType = genePieceType;
	}
	public Function getFunc() {
		return func;
	}
	public void setFunc(Function func) {
		this.func = func;
	}
	/**
	 * Hibernate专用入口,其他函数请勿调用
	 * @return
	 */
	public String getGenePieceTypeString(){
		return genePieceType.toString();
	}
	/**
	 * Hibernate专用入口,其他函数请勿调用
	 * @param genePieceTypeString
	 */
	public void setGenePieceTypeString(String genePieceTypeString){
		genePieceType=GenePieceType.valueOf(genePieceTypeString);
	}
	/**
	 * Hibernate专用入口,其他函数请勿调用
	 * @return
	 */
	public String getFuncString(){
		return func.getClass().toString();
	}
	/**
	 * Hibernate专用入口,其他函数请勿调用
	 * @param funcString
	 */
	public void setFuncString(String funcString){
		try {
			func=(Function) Class.forName(funcString).newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	 * 克隆一份当前对象，这个方法调用了Object中的clone方法。
	 * 这是一个深度克隆，即源对象和新对象具有相同的初值，但具有不同的内存地址。
	 */
	@Override
	public GenePiece clone(){
		// TODO Auto-generated method stub
		GenePiece o=null;
		try {
			o=(GenePiece) super.clone();
			if(func!=null)
				o.func=func.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}
}
