package domain.core.algmodel.population;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import domain.core.algmodel.genepiece.Function;
import domain.core.algmodel.genepiece.Variable;

/**
 * 本类是算法子系统实体类的入口，本类中保存有两部分内容，第一部分是有关算法实体类的参数，第二部分是一个对种群队列的引用
 * @author 申远
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
	 * 空构造函数
	 */
	public GepAlgorithm(){
		
	}
	/**
	 * 返回种群大小，即种群中包含的个体的数量。
	 * @return 种群大小
	 */
	public int getPopulationSize() {
		return populationSize;
	}
	/**
	 * 设置种群大小为用户指定的种群大小。
	 * @param populationNumber 指定的种群大小
	 */
	public void setPopulationSize(int populationNumber) {
		this.populationSize = populationNumber;
	}
	/**
	 * 返回个体中普通基因的数量，所有种群中的所有个体里面普通基因数量均相同。
	 * @return 个体中普通基因的数量
	 */
	public int getNormalGeneNumber() {
		return normalGeneNumber;
	}
	/**
	 * 设置个体中普通基因的数量
	 * @param geneNumber 设置个体中普通基因的数量
	 */
	public void setNormalGeneNumber(int geneNumber) {
		this.normalGeneNumber = geneNumber;
	}
	/**
	 * 返回个体中普通基因的头部长度，所有种群中所有个体的普通基因长度均相同。
	 * @return 普通基因头部的长度
	 */
	public int getNormalHeaderLength() {
		return normalHeaderLength;
	}
	/**
	 * 设置个体中普通基因的头部长度
	 * @param headerLength 普通基因的头部长度
	 */
	public void setNormalHeaderLength(int headerLength) {
		this.normalHeaderLength = headerLength;
	}
	/**
	 * 返回个体中同源基因的数量，所有种群中的所有个体里同源基因数量均相同。
	 * @return 同源基因的数量
	 */
	public int getHomeoticGeneNumber() {
		return homeoticGeneNumber;
	}
	/**
	 * 设置个体中同源基因的数量，所有种群中所有个体的同源基因的数量均相同。
	 * @param homeoticGeneNumber 同源基因的数量
	 */
	public void setHomeoticGeneNumber(int homeoticGeneNumber) {
		this.homeoticGeneNumber = homeoticGeneNumber;
	}
	/**
	 * 返回同源基因的头部长度
	 * @return 同源基因的头部长度
	 */
	public int getHomeoticHeaderLength() {
		return homeoticHeaderLength;
	}
	/**
	 * 设置同源基因的头部长度，所有种群中所有个体的同源基因长度均相同。
	 * @param homeoticHeaderLength 同源基因长度
	 */
	public void setHomeoticHeaderLength(int homeoticHeaderLength) {
		this.homeoticHeaderLength = homeoticHeaderLength;
	}
	/**
	 * 返回个体的总长
	 * @return 个体总长
	 */
	public int getIndividualLength() {
		return individualLength;
	}
	/**
	 * 设置个体长度，所有种群中所有个体的长度相同，个体的长度等于普通基因总长度+同源基因总长度，请严格按照此公式设置，系统不会验证此输入是否正确，但是
	 * 如果不符合此公式的话系统运行的时候会出现异常。
	 * @param individualLength 个体的长度
	 */
	public void setIndividualLength(int individualLength){
		this.individualLength=individualLength;
	}
	/**
	 * 将新的种群添加到种群队列的队尾，种群队列中只会保留两个种群，如果因为添加新种群导致种群队列的长度大于2，那么
	 * 种群队列队首的种群将自动出队。
	 * @param myPopulation 待添加的种群
	 */
	public void addPopulation(Population myPopulation){
		populationQueue.addLast(myPopulation);
		if(populationQueue.size()>2)
			populationQueue.removeFirst();
	}
	/**
	 * 返回种群队列，此队列中只包含两个种群。
	 * @return 种群队列
	 */
	public Deque<Population> getPopulationQueue(){
		return populationQueue;
	}
	/**
	 * 返回普通基因的尾部长度
	 * @return 普通基因的尾部长度
	 */
	public int getNormalTailLength() {
		return normalTailLength;
	}
	/**
	 * 设置普通基因的尾部长度，普通基因的尾长＝普通基因头长+max｛A|参与运算的函数的目数｝，请严格按照此公式设置，系统不会验证此输入是否正确，但是
	 * 如果不符合此公式的话系统运行的时候会出现异常,在设置此参数的时候，系统会自动生成普通基因的长度。
	 * @param normalTailLength 普通基因的尾部长度
	 */
	public void setNormalTailLength(int normalTailLength) {
		this.normalTailLength = normalTailLength;
		normalGeneLength=normalHeaderLength+this.normalTailLength;
	}
	/**
	 * 返回同源基因的尾部长度
	 * @return 同源基因的尾部长度
	 */
	public int getHomeoticTailLength() {
		return homeoticTailLength;
	}
	/**
	 * 设置同源基因的尾部长度，同源基因的尾长＝同源基因头长+max｛A|参与运算的函数的目数｝，请严格按照此公式设置，系统不会验证此输入是否正确，但是
	 * 如果不符合此公式的话系统运行的时候会出现异常,在设置此参数的时候，系统会自动生成同源基因的长度。
	 * @param homeoticTailLength 同源基因的尾部长度
	 */
	public void setHomeoticTailLength(int homeoticTailLength) {
		this.homeoticTailLength = homeoticTailLength;
		homeoticGeneLength=homeoticHeaderLength+this.homeoticTailLength;
	}
	/**
	 * 返回普通基因的长度，普通基因的长度＝普通基因的头长+普通基因的尾长，所有种群中所有个体的普通基因长度均相同。
	 * @return 普通基因的长度
	 */
	public int getNormalGeneLength() {
		return normalGeneLength;
	}
	/**
	 * 返回同源基因的长度，同源基因的长度＝同源基因的头长+同源基因的尾长，所有种群中所有个体的同源基因长度均相同。
	 * @return　同源基因的长度
	 */
	public int getHomeoticGeneLength() {
		return homeoticGeneLength;
	}
	/**
	 * 返回用户设置的最大演化代数，达到此演化代数后，系统将自动停机。
	 * @return　最大演化代数
	 */
	public long getMaxGeneration() {
		return maxGeneration;
	}
	/**
	 * 设置最大演化代数，达到此演化代数后系统将自动停机。
	 * @param maxGeneration　最大演化代数
	 */
	public void setMaxGeneration(long maxGeneration) {
		this.maxGeneration = maxGeneration;
	}
	/**
	 * 返回最大适应值，当系统发现某个个体达到最大适应值后也将停机。
	 * @return 最大适应值
	 */
	public float getMaxFitness() {
		return maxFitness;
	}
	/**
	 * 设置最大适应值，最大适应值为选择范围×输入矩阵的行数，请按照此公式进行设置，系统将不会验证设置是否正确，但是如果错误的话，系统在运行的时候会抛出异常。
	 * @param maxFitness
	 */
	public void setMaxFitness(float maxFitness) {
		this.maxFitness = maxFitness;
	}
	/**
	 * 返回终点集，终点集即为变量的集合。
	 * @return 终点集
	 */
	public List<Variable> getVariableList() {
		return variableList;
	}
	/**
	 * 设置终点集，即参与运算的所有的变量，变量的数目应为输入矩阵的列数+1，如不满足次条件，系统将在运行期间抛出异常。
	 * @param variableList
	 */
	public void setVariableList(List<Variable> variableList) {
		this.variableList = variableList;
	}
	/**
	 * 返回函数集
	 * @return 函数集
	 */
	public List<Function> getFunctionList() {
		return functionList;
	}
	/**
	 * 通过此方法可以设置参与运算的所有的函数。
	 * @param functionList 参与运算的函数
	 */
	public void setFunctionList(List<Function> functionList) {
		this.functionList = functionList;
	}
}
