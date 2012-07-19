package domain.core.algOutput;

import java.io.Serializable;

import domain.core.algconfiguration.Function;

/**
 * ����Ƭ���࣬��ƻ���λ�࣬��ÿһ��ͷ������β���У����հ����Ķ��Ǵ���GenePiece
 * @author ��Զ
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
	 * ���ص�ǰ����Ƭ�εķ��ű�ʾ����String��ʾ
	 * @return ����Ƭ�η��ű�ʾ
	 */
	public String getSymbol() {
		return symbol;
	}
	/**
	 * ���õ�ǰ����Ƭ�εķ��ű�ʾ����String��ʾ
	 * @param symbol ����Ƭ�η��ű�ʾ
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	/**
	 * ���ص�ǰ����Ƭ�ε�����
	 * @return ����Ƭ�����Ƶ��ַ�����ʾ
	 */
	public String getName(){
		return name;
	}
	/**
	 * ���õ�ǰ����Ƭ�ε�����
	 * @param name ��ǰ����Ƭ�ε�����
	 */
	public void setName(String name){
		this.name=name;
	}
	/**
	 * ��õ�ǰ����Ƭ�ε�ֵ
	 * @return ��ǰ����Ƭ�ε�ֵ
	 */
	public Float getValue(){
		return value;
	}
	/**
	 * ���õ�ǰ����Ƭ�ε�ֵ
	 * @param value ����Ƭ�ε�ֵ
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
	 * Hibernateר�����,���������������
	 * @return
	 */
	public String getGenePieceTypeString(){
		return genePieceType.toString();
	}
	/**
	 * Hibernateר�����,���������������
	 * @param genePieceTypeString
	 */
	public void setGenePieceTypeString(String genePieceTypeString){
		genePieceType=GenePieceType.valueOf(genePieceTypeString);
	}
	/**
	 * Hibernateר�����,���������������
	 * @return
	 */
	public String getFuncString(){
		return func.getClass().toString();
	}
	/**
	 * Hibernateר�����,���������������
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
	 * ���ص�ǰ����Ƭ�ε����Ʊ�ʾ����������ֱ�ӵ���getName()��
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getName();
	}
	/**
	 * ��¡һ�ݵ�ǰ�����������������Object�е�clone������
	 * ����һ����ȿ�¡����Դ������¶��������ͬ�ĳ�ֵ�������в�ͬ���ڴ��ַ��
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
