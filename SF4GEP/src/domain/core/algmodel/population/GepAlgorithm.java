package domain.core.algmodel.population;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import domain.core.algmodel.genepiece.Function;
import domain.core.algmodel.genepiece.Variable;

/**
 * �������㷨��ϵͳʵ�������ڣ������б��������������ݣ���һ�������й��㷨ʵ����Ĳ������ڶ�������һ������Ⱥ���е�����
 * @author ��Զ
 * 
 */
public class GepAlgorithm implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9145263051145401068L;
	private int populationSize;
	private int normalGeneNumber;
	private int normalHeaderLength;
	private int normalTailLength;
	private int normalGeneLength;
	private int homeoticGeneNumber;
	private int homeoticHeaderLength;
	private int homeoticTailLength;
	private int homeoticGeneLength;
	private int individualLength;
	private Deque<Population> populationQueue=new ArrayDeque<Population>(2);
	private long maxGeneration;
	private float maxFitness;
	private List<Variable> variableList;
	private List<Function> functionList;
	
	/**
	 * �չ��캯��
	 */
	public GepAlgorithm(){
		
	}
	/**
	 * ������Ⱥ��С������Ⱥ�а����ĸ����������
	 * @return ��Ⱥ��С
	 */
	public int getPopulationSize() {
		return populationSize;
	}
	/**
	 * ������Ⱥ��СΪ�û�ָ������Ⱥ��С��
	 * @param populationNumber ָ������Ⱥ��С
	 */
	public void setPopulationSize(int populationNumber) {
		this.populationSize = populationNumber;
	}
	/**
	 * ���ظ�������ͨ�����������������Ⱥ�е����и���������ͨ������������ͬ��
	 * @return ��������ͨ���������
	 */
	public int getNormalGeneNumber() {
		return normalGeneNumber;
	}
	/**
	 * ���ø�������ͨ���������
	 * @param geneNumber ���ø�������ͨ���������
	 */
	public void setNormalGeneNumber(int geneNumber) {
		this.normalGeneNumber = geneNumber;
	}
	/**
	 * ���ظ�������ͨ�����ͷ�����ȣ�������Ⱥ�����и������ͨ���򳤶Ⱦ���ͬ��
	 * @return ��ͨ����ͷ���ĳ���
	 */
	public int getNormalHeaderLength() {
		return normalHeaderLength;
	}
	/**
	 * ���ø�������ͨ�����ͷ������
	 * @param headerLength ��ͨ�����ͷ������
	 */
	public void setNormalHeaderLength(int headerLength) {
		this.normalHeaderLength = headerLength;
	}
	/**
	 * ���ظ�����ͬԴ�����������������Ⱥ�е����и�����ͬԴ������������ͬ��
	 * @return ͬԴ���������
	 */
	public int getHomeoticGeneNumber() {
		return homeoticGeneNumber;
	}
	/**
	 * ���ø�����ͬԴ�����������������Ⱥ�����и����ͬԴ�������������ͬ��
	 * @param homeoticGeneNumber ͬԴ���������
	 */
	public void setHomeoticGeneNumber(int homeoticGeneNumber) {
		this.homeoticGeneNumber = homeoticGeneNumber;
	}
	/**
	 * ����ͬԴ�����ͷ������
	 * @return ͬԴ�����ͷ������
	 */
	public int getHomeoticHeaderLength() {
		return homeoticHeaderLength;
	}
	/**
	 * ����ͬԴ�����ͷ�����ȣ�������Ⱥ�����и����ͬԴ���򳤶Ⱦ���ͬ��
	 * @param homeoticHeaderLength ͬԴ���򳤶�
	 */
	public void setHomeoticHeaderLength(int homeoticHeaderLength) {
		this.homeoticHeaderLength = homeoticHeaderLength;
	}
	/**
	 * ���ظ�����ܳ�
	 * @return �����ܳ�
	 */
	public int getIndividualLength() {
		return individualLength;
	}
	/**
	 * ���ø��峤�ȣ�������Ⱥ�����и���ĳ�����ͬ������ĳ��ȵ�����ͨ�����ܳ���+ͬԴ�����ܳ��ȣ����ϸ��մ˹�ʽ���ã�ϵͳ������֤�������Ƿ���ȷ������
	 * ��������ϴ˹�ʽ�Ļ�ϵͳ���е�ʱ�������쳣��
	 * @param individualLength ����ĳ���
	 */
	public void setIndividualLength(int individualLength){
		this.individualLength=individualLength;
	}
	/**
	 * ���µ���Ⱥ��ӵ���Ⱥ���еĶ�β����Ⱥ������ֻ�ᱣ��������Ⱥ�������Ϊ�������Ⱥ������Ⱥ���еĳ��ȴ���2����ô
	 * ��Ⱥ���ж��׵���Ⱥ���Զ����ӡ�
	 * @param myPopulation ����ӵ���Ⱥ
	 */
	public void addPopulation(Population myPopulation){
		populationQueue.addLast(myPopulation);
		if(populationQueue.size()>2)
			populationQueue.removeFirst();
	}
	/**
	 * ������Ⱥ���У��˶�����ֻ����������Ⱥ��
	 * @return ��Ⱥ����
	 */
	public Deque<Population> getPopulationQueue(){
		return populationQueue;
	}
	/**
	 * ������ͨ�����β������
	 * @return ��ͨ�����β������
	 */
	public int getNormalTailLength() {
		return normalTailLength;
	}
	/**
	 * ������ͨ�����β�����ȣ���ͨ�����β������ͨ����ͷ��+max��A|��������ĺ�����Ŀ���������ϸ��մ˹�ʽ���ã�ϵͳ������֤�������Ƿ���ȷ������
	 * ��������ϴ˹�ʽ�Ļ�ϵͳ���е�ʱ�������쳣,�����ô˲�����ʱ��ϵͳ���Զ�������ͨ����ĳ��ȡ�
	 * @param normalTailLength ��ͨ�����β������
	 */
	public void setNormalTailLength(int normalTailLength) {
		this.normalTailLength = normalTailLength;
		normalGeneLength=normalHeaderLength+this.normalTailLength;
	}
	/**
	 * ����ͬԴ�����β������
	 * @return ͬԴ�����β������
	 */
	public int getHomeoticTailLength() {
		return homeoticTailLength;
	}
	/**
	 * ����ͬԴ�����β�����ȣ�ͬԴ�����β����ͬԴ����ͷ��+max��A|��������ĺ�����Ŀ���������ϸ��մ˹�ʽ���ã�ϵͳ������֤�������Ƿ���ȷ������
	 * ��������ϴ˹�ʽ�Ļ�ϵͳ���е�ʱ�������쳣,�����ô˲�����ʱ��ϵͳ���Զ�����ͬԴ����ĳ��ȡ�
	 * @param homeoticTailLength ͬԴ�����β������
	 */
	public void setHomeoticTailLength(int homeoticTailLength) {
		this.homeoticTailLength = homeoticTailLength;
		homeoticGeneLength=homeoticHeaderLength+this.homeoticTailLength;
	}
	/**
	 * ������ͨ����ĳ��ȣ���ͨ����ĳ��ȣ���ͨ�����ͷ��+��ͨ�����β����������Ⱥ�����и������ͨ���򳤶Ⱦ���ͬ��
	 * @return ��ͨ����ĳ���
	 */
	public int getNormalGeneLength() {
		return normalGeneLength;
	}
	/**
	 * ����ͬԴ����ĳ��ȣ�ͬԴ����ĳ��ȣ�ͬԴ�����ͷ��+ͬԴ�����β����������Ⱥ�����и����ͬԴ���򳤶Ⱦ���ͬ��
	 * @return��ͬԴ����ĳ���
	 */
	public int getHomeoticGeneLength() {
		return homeoticGeneLength;
	}
	/**
	 * �����û����õ�����ݻ��������ﵽ���ݻ�������ϵͳ���Զ�ͣ����
	 * @return������ݻ�����
	 */
	public long getMaxGeneration() {
		return maxGeneration;
	}
	/**
	 * ��������ݻ��������ﵽ���ݻ�������ϵͳ���Զ�ͣ����
	 * @param maxGeneration������ݻ�����
	 */
	public void setMaxGeneration(long maxGeneration) {
		this.maxGeneration = maxGeneration;
	}
	/**
	 * ���������Ӧֵ����ϵͳ����ĳ������ﵽ�����Ӧֵ��Ҳ��ͣ����
	 * @return �����Ӧֵ
	 */
	public float getMaxFitness() {
		return maxFitness;
	}
	/**
	 * ���������Ӧֵ�������ӦֵΪѡ��Χ�����������������밴�մ˹�ʽ�������ã�ϵͳ��������֤�����Ƿ���ȷ�������������Ļ���ϵͳ�����е�ʱ����׳��쳣��
	 * @param maxFitness
	 */
	public void setMaxFitness(float maxFitness) {
		this.maxFitness = maxFitness;
	}
	/**
	 * �����յ㼯���յ㼯��Ϊ�����ļ��ϡ�
	 * @return �յ㼯
	 */
	public List<Variable> getVariableList() {
		return variableList;
	}
	/**
	 * �����յ㼯����������������еı�������������ĿӦΪ������������+1���粻�����������ϵͳ���������ڼ��׳��쳣��
	 * @param variableList
	 */
	public void setVariableList(List<Variable> variableList) {
		this.variableList = variableList;
	}
	/**
	 * ���غ�����
	 * @return ������
	 */
	public List<Function> getFunctionList() {
		return functionList;
	}
	/**
	 * ͨ���˷����������ò�����������еĺ�����
	 * @param functionList ��������ĺ���
	 */
	public void setFunctionList(List<Function> functionList) {
		this.functionList = functionList;
	}
}
