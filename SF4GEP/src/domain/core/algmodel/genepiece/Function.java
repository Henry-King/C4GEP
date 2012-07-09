package domain.core.algmodel.genepiece;

import java.util.Deque;


/**
 * ���������࣬��ȷ���κμ̳д�������඼����Ĭ�Ϲ��캯��������ϵͳ����ִ���
 */
public abstract class Function extends GenePiece{
	private static final long serialVersionUID = -5233529550458131848L;
	private final int arity;
	/**
	 * ���캯������Ҫ�ṩ�������������ķ��ű�ʾ��������Ŀ��
	 * @param name ������
	 * @param arity ������Ŀ����������������������ӷ���Ŀ��Ϊ2��ƽ������Ŀ��Ϊ1
	 * @param symbol �����ķ��ű�ʾ
	 */
	public Function(String name,int arity,String symbol){
		value=Float.NaN;
		this.name=name;
		this.arity=arity;
		this.symbol=symbol;
	}
	/**
	 * ��õ�ǰ������Ŀ��
	 */
	public int getArity() {
		return arity;
	}
	/**
	 * ���о���ĺ������㣬������������
	 * @param operators ���������У���ʹ��LIFO�ķ�ʽ���м��㣬����ϵͳ������
	 * @return �������������ķ���ֵ��
	 */
	public abstract float operate(Deque<Float> operators);
}
