package domain.core.algmodel.genepiece;

import java.util.Random;

/**
 * ���ͳ����࣬�������Ҫ������ΪͬԴ�����к�����
 * @author ��Զ
 *
 */
public class Constant extends Computable {
	private static final long serialVersionUID = 5428175735104183458L;
	/**
	 * ��ʼ�����ͳ����࣬��ʼ����˷������Զ�����valueֵ��������ͨ����ı��
	 * @param max ��ͨ���������
	 */
	public Constant(int max){
		Random random=new Random();
		value=random.nextInt(max);
	}
	/**
	 * ���ͳ���ֵ�����趨���벻Ҫ���ô˷��������ú������׳��쳣
	 * @param value �趨����ֵ�����趨��Ч�����һ��׳��쳣
	 */
	@Deprecated
	@Override
	public void setValue(float value) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("���ͳ������ֵ�ɳ����Զ����ɣ��û������ֶ�����");
	}
	/**
	 * ���͵ķ��ű�ʾ�����趨���벻Ҫ���ô˷��������ú������׳��쳣
	 * @param symbol �趨���ű�ʾ�����趨��Ч�����һ��׳��쳣
	 */
	@Deprecated
	@Override
	public void setSymbol(String symbol) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("�����ֶ��趨���ͳ����ķ��ű�ʾ��������ű�ʾ���Ǵ����ͳ�����ֵ���ɳ����Զ�����");
	}
	/**
	 * �������ͳ����ķ��ű�ʾ����Ϊ���ͳ�����ֵ�ķ��ű�ʾ
	 */
	@Override
	public String getSymbol() {
		return Integer.toString((int)value);
	}
	/**
	 * �������ͳ������ַ�����ʾ�����������Զ�����getSymbol����
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getSymbol();
	}
}
