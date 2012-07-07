package domain.core.algmodel.genecomponent;

import java.io.Serializable;

import common.ICopy;

/**
 * ����Ƭ���࣬��ƻ���λ�࣬��ÿһ��ͷ������β���У����հ����Ķ��Ǵ���GenePiece
 * @author ��Զ
 *
 */
public abstract class GenePiece implements Serializable,Cloneable,ICopy<GenePiece>{
	private static final long serialVersionUID = 7648117930489664221L;
	protected String symbol=new String();
	protected String name=new String();
	protected float value;
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
	 * ���ص�ǰ����Ƭ�ε����Ʊ�ʾ����������ֱ�ӵ���getName()��
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getName();
	}
	/**
	 * ��õ�ǰ����Ƭ�ε�ֵ
	 * @return ��ǰ����Ƭ�ε�ֵ
	 */
	public float getValue(){
		return value;
	}
	/**
	 * ���õ�ǰ����Ƭ�ε�ֵ
	 * @param value ����Ƭ�ε�ֵ
	 */
	public void setValue(float value){
		this.value=value;
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
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}
	@Override
	/**
	 * ���copy������ֱ�ӵ���clone����ʵ����ȸ���
	 */
	public GenePiece copy() {
		// TODO Auto-generated method stub
		return clone();
	}
}
