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
	protected String symbol;
	/**
	 * ���ص�ǰ����Ƭ�ε����ƣ���String��ʾ
	 * @return ����Ƭ������
	 */
	public String getSymbol() {
		return symbol;
	}
	/**
	 * ���õ�ǰ����Ƭ�ε����ƣ���String��ʾ
	 * @param symbol ����Ƭ������
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	/**
	 * ���ص�ǰ����Ƭ�ε��ַ�����ʾ����������ֱ�ӵ���getSymbol
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return symbol;
	}
	/**
	 * ��õ�ǰ����Ƭ�ε�ֵ
	 * @return ��ǰ����Ƭ�ε�ֵ
	 */
	public abstract float getValue();
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
