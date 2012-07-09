package domain.service.alg.baseclass;

import java.util.List;

import domain.core.algmodel.configuration.GepAlgorithm;
import domain.core.algmodel.configuration.Individual;
import domain.core.inputmodel.DataTable;
import exception.IllegalInputSet;

/**
 * �����࣬���е���Ӧֵ���㷽������̳д��࣬��ȷ���̳д���������������һ���޲����Ĺ��캯�������������쳣
 * @author ��Զ
 */
public abstract class Calculator {
	private float selectionRage;
	private float accuray;
	private final String name;
	private DataTable inputSet;
	/**
	 * 
	 * @param name ������Ӧֵ��ĵ����֣�������ֻ��Զ��ڽ�������ʾ
	 */
	public Calculator(String name){
		this.name=name;
	}
	/**
	 * ����ѡ��Χ
	 * @return ѡ��Χ
	 */
	public float getSelectionRange(){
		return selectionRage;
	}
	/**
	 * ����ѡ��Χ��ѡ��Χ�ǰѺ���ֵת��Ϊ��Ӧֵ��ʱ�����õ�����Ҫ��������ȷ��ѡ��Χ����0
	 * @param selectionRange ѡ��Χ
	 */
	public void setSelectionRange(float selectionRange){
		this.selectionRage=selectionRange;
	}
	/**
	 * ������⾫�ȣ���0.01��ʾ������С��0.01������Դ����
	 * @return ��⾫��
	 */
	public float getAccuray(){
		return accuray;
	}
	/**
	 * ������⾫��
	 * @param accuray ��⾫��
	 */
	public void setAccuray(float accuray){
		this.accuray=accuray;
	}
	/**
	 * �������뼯
	 * @return ���뼯
	 */
	public DataTable getInputSet() {
		return inputSet;
	}
	/**
	 * �������뼯
	 * @param inputSet �����õ����뼯
	 */
	public void setInputSet(DataTable inputSet) {
		this.inputSet = inputSet;
	}
	/**
	 * ������Ӧֵ��ֻ��Ҫ������Ⱥ���ж�β��Ⱥ�����е���Ӧֵ���ɡ�
	 */
	public abstract void calculateFitness(GepAlgorithm gepAlgorithm);
	/**
	 * �ø����ĸ��������������뼯�Ľ⣬�޸����뼯ͨ������setInputSet�����
	 * @param individual ����������뼯�ĸ���
	 * @return ������������List
	 * @throws IllegalInputSet ���뼯�������⽫���׳����쳣
	 */
	public abstract List<Float> calculateInputSet(Individual individual) throws IllegalInputSet;
	/**
	 * ������ⷽʽ������
	 * @return ������Ƶ��ַ�����ʾ��
	 */
	@Override
	public final String toString() {
		// TODO Auto-generated method stub
		return name;
	}

}
