package domain.service.alg.baseclass;

import java.util.LinkedList;
import java.util.List;

import domain.core.algmodel.configuration.GepAlgorithm;

/**
 * ��ȷ��������Ĭ�Ϲ��캯�����������ִ���
 */
public abstract class Modifying {
	private final String name;
	private float mutateRate;
	private float isTransportRate;
	private List<Integer> isElements=new LinkedList<Integer>();
	private float risTransportRate;
	private List<Integer> risElements=new LinkedList<Integer>();
	private float geneTransportRate;
	private float onePointRecombineRate;
	private float twoPointRecombineRate;
	private float geneRecombineRate;
	/**
	 * �Ŵ���������
	 * @param name �Ŵ���������
	 */
	public Modifying(String name){
		this.name=name;
	}
	/**
	 * �����Ŵ����ӵ�����
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		// TODO Auto-generated method stub
		return name;
	}
	/**
	 * ���ر������
	 * @return ������ʣ�����0С��1
	 */
	public float getMutateRate() {
		return mutateRate;
	}
	/**
	 * ���ñ������
	 * @param variateRate �������
	 */
	public void setMutateRate(float variateRate) {
		this.mutateRate = variateRate;
	}
	/**
	 * ����ISת������
	 * @return ISת������
	 */
	public float getIsTransportRate() {
		return isTransportRate;
	}
	/**
	 * ����ISת������
	 * @param isTransportRate ISת�����ʣ�����0С��1
	 */
	public void setIsTransportRate(float isTransportRate) {
		this.isTransportRate = isTransportRate;
	}
	/**
	 * ����ISת���Ŀ��ܳ���
	 * @return ��ISת�����ܳ�����ɵ�List
	 */
	public List<Integer> getIsElements() {
		return isElements;
	}
	/**
	 * ����ISת���Ŀ��ܳ���
	 * @param isLength ISת���Ŀ��ܳ��ȵ�List
	 */
	public void setIsElements(List<Integer> isLength) {
		this.isElements = isLength;
	}
	/**
	 * ����RISת������
	 * @return ����RISת������
	 */
	public float getRisTransportRate() {
		return risTransportRate;
	}
	/**
	 * ����RISת�����ʣ�����0С��1
	 * @param risTransportRate RISת������
	 */
	public void setRisTransportRate(float risTransportRate) {
		this.risTransportRate = risTransportRate;
	}
	/**
	 * ����RISת���Ŀ��ܳ���
	 * @return RISת���ĳ���List
	 */
	public List<Integer> getRisElements() {
		return risElements;
	}
	/**
	 * ����RISת�����ܳ���
	 * @param risLength list���汣��RISת�����ܳ���
	 */
	public void setRisElements(List<Integer> risLength) {
		this.risElements = risLength;
	}
	/**
	 * ���ػ���ת���ĸ���
	 * @return ����ת������
	 */
	public float getGeneTransportRate() {
		return geneTransportRate;
	}
	/**
	 * ���û���ת�����ʣ�����0С��1
	 * @param geneTransportRate ����ת������
	 */
	public void setGeneTransportRate(float geneTransportRate) {
		this.geneTransportRate = geneTransportRate;
	}
	/**
	 * ���ص����������
	 * @return �����������
	 */
	public float getOnePointRecombineRate() {
		return onePointRecombineRate;
	}
	/**
	 * ���õ����������
	 * @param onePointRecombineRate ����������ʣ�����0С��1
	 */
	public void setOnePointRecombineRate(float onePointRecombineRate) {
		this.onePointRecombineRate = onePointRecombineRate;
	}
	/**
	 * ���������������
	 * @return �����������
	 */
	public float getTwoPointRecombineRate() {
		return twoPointRecombineRate;
	}
	/**
	 * ���������������
	 * @param twoPointRecombineRate �����������,����0С��1
	 */
	public void setTwoPointRecombineRate(float twoPointRecombineRate) {
		this.twoPointRecombineRate = twoPointRecombineRate;
	}
	/**
	 * ���ػ����������
	 * @return �����������
	 */
	public float getGeneRecombineRate() {
		return geneRecombineRate;
	}
	/**
	 * ���û���������ʣ�����0С��1
	 * @param geneRecombineRate �����������
	 */
	public void setGeneRecombineRate(float geneRecombineRate) {
		this.geneRecombineRate = geneRecombineRate;
	}
	/**
	 * �����㷨����ڣ�ֻ��Ҫ������Ⱥ�����еĶ�β���ɡ�
	 * @param myGepAlgorithm �㷨������������Ⱥ�͸��ֲ���
	 */
	public abstract void run(GepAlgorithm myGepAlgorithm);
	/**
	 * �Բ�����Ⱥ�����ж�β����Ⱥ���б���
	 * @param myGepAlgorithm �㷨������������Ⱥ�͸��ֲ���
	 */
	public abstract void mutate(GepAlgorithm myGepAlgorithm);
	/**
	 * �Բ�����Ⱥ�����ж�β����Ⱥ����ISת��
	 * @param myGepAlgorithm �㷨������������Ⱥ�͸��ֲ���
	 */
	public abstract void isTransport(GepAlgorithm myGepAlgorithm);
	/**
	 * �Բ�����Ⱥ�����ж�β����Ⱥ����RISת��
	 * @param myGepAlgorithm �㷨������������Ⱥ�͸��ֲ���
	 */
	public abstract void risTransport(GepAlgorithm myGepAlgorithm);
	/**
	 * �Բ�����Ⱥ�����ж�β����Ⱥ���л���ת��
	 * @param myGepAlgorithm �㷨������������Ⱥ�͸��ֲ���
	 */
	public abstract void geneTransport(GepAlgorithm myGepAlgorithm);
	/**
	 * �Բ�����Ⱥ�����ж�β����Ⱥ���е�������
	 * @param myGepAlgorithm �㷨������������Ⱥ�͸��ֲ���
	 */
	public abstract void onePointRecombine(GepAlgorithm myGepAlgorithm);
	/**
	 * �Բ�����Ⱥ�����ж�β����Ⱥ������������
	 * @param myGepAlgorithm �㷨������������Ⱥ�͸��ֲ���
	 */
	public abstract void twoPointRecombine(GepAlgorithm myGepAlgorithm);
	/**
	 * �Բ�����Ⱥ�����ж�β����Ⱥ���л�������
	 * @param myGepAlgorithm �㷨������������Ⱥ�͸��ֲ���
	 */
	public abstract void geneRecombine(GepAlgorithm myGepAlgorithm);
}
