package domain.service.alg.baseclass;

import java.util.LinkedList;
import java.util.List;

import domain.core.algmodel.configuration.GepAlgorithm;

/**
 * 请确保子类有默认构造函数，否则会出现错误
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
	 * 遗传算子名称
	 * @param name 遗传算子名称
	 */
	public Modifying(String name){
		this.name=name;
	}
	/**
	 * 返回遗传算子的名称
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		// TODO Auto-generated method stub
		return name;
	}
	/**
	 * 返回变异概率
	 * @return 变异概率，大于0小于1
	 */
	public float getMutateRate() {
		return mutateRate;
	}
	/**
	 * 设置变异概率
	 * @param variateRate 变异概率
	 */
	public void setMutateRate(float variateRate) {
		this.mutateRate = variateRate;
	}
	/**
	 * 返回IS转座概率
	 * @return IS转座概率
	 */
	public float getIsTransportRate() {
		return isTransportRate;
	}
	/**
	 * 设置IS转座概率
	 * @param isTransportRate IS转座概率，大于0小于1
	 */
	public void setIsTransportRate(float isTransportRate) {
		this.isTransportRate = isTransportRate;
	}
	/**
	 * 返回IS转座的可能长度
	 * @return 由IS转座可能长度组成的List
	 */
	public List<Integer> getIsElements() {
		return isElements;
	}
	/**
	 * 设置IS转座的可能长度
	 * @param isLength IS转座的可能长度的List
	 */
	public void setIsElements(List<Integer> isLength) {
		this.isElements = isLength;
	}
	/**
	 * 返回RIS转座概率
	 * @return 返回RIS转座概率
	 */
	public float getRisTransportRate() {
		return risTransportRate;
	}
	/**
	 * 设置RIS转座概率，大于0小于1
	 * @param risTransportRate RIS转座概率
	 */
	public void setRisTransportRate(float risTransportRate) {
		this.risTransportRate = risTransportRate;
	}
	/**
	 * 返回RIS转座的可能长度
	 * @return RIS转座的长度List
	 */
	public List<Integer> getRisElements() {
		return risElements;
	}
	/**
	 * 设置RIS转座可能长度
	 * @param risLength list里面保存RIS转座可能长度
	 */
	public void setRisElements(List<Integer> risLength) {
		this.risElements = risLength;
	}
	/**
	 * 返回基因转座的概率
	 * @return 基因转座概率
	 */
	public float getGeneTransportRate() {
		return geneTransportRate;
	}
	/**
	 * 设置基因转座概率，大于0小于1
	 * @param geneTransportRate 基因转座概率
	 */
	public void setGeneTransportRate(float geneTransportRate) {
		this.geneTransportRate = geneTransportRate;
	}
	/**
	 * 返回单点重组概率
	 * @return 单点重组概率
	 */
	public float getOnePointRecombineRate() {
		return onePointRecombineRate;
	}
	/**
	 * 设置单点重组概率
	 * @param onePointRecombineRate 单点重组概率，大于0小于1
	 */
	public void setOnePointRecombineRate(float onePointRecombineRate) {
		this.onePointRecombineRate = onePointRecombineRate;
	}
	/**
	 * 返回两点重组概率
	 * @return 两点重组概率
	 */
	public float getTwoPointRecombineRate() {
		return twoPointRecombineRate;
	}
	/**
	 * 设置两点重组概率
	 * @param twoPointRecombineRate 两点重组概率,大于0小于1
	 */
	public void setTwoPointRecombineRate(float twoPointRecombineRate) {
		this.twoPointRecombineRate = twoPointRecombineRate;
	}
	/**
	 * 返回基因重组概率
	 * @return 基因重组概率
	 */
	public float getGeneRecombineRate() {
		return geneRecombineRate;
	}
	/**
	 * 设置基因重组概率，大于0小于1
	 * @param geneRecombineRate 基因重组概率
	 */
	public void setGeneRecombineRate(float geneRecombineRate) {
		this.geneRecombineRate = geneRecombineRate;
	}
	/**
	 * 整个算法的入口，只需要操作种群队列中的队尾即可。
	 * @param myGepAlgorithm 算法集，保存有种群和各种参数
	 */
	public abstract void run(GepAlgorithm myGepAlgorithm);
	/**
	 * 对操作种群队列中队尾的种群进行变异
	 * @param myGepAlgorithm 算法集，保存有种群和各种参数
	 */
	public abstract void mutate(GepAlgorithm myGepAlgorithm);
	/**
	 * 对操作种群队列中队尾的种群进行IS转座
	 * @param myGepAlgorithm 算法集，保存有种群和各种参数
	 */
	public abstract void isTransport(GepAlgorithm myGepAlgorithm);
	/**
	 * 对操作种群队列中队尾的种群进行RIS转座
	 * @param myGepAlgorithm 算法集，保存有种群和各种参数
	 */
	public abstract void risTransport(GepAlgorithm myGepAlgorithm);
	/**
	 * 对操作种群队列中队尾的种群进行基因转座
	 * @param myGepAlgorithm 算法集，保存有种群和各种参数
	 */
	public abstract void geneTransport(GepAlgorithm myGepAlgorithm);
	/**
	 * 对操作种群队列中队尾的种群进行单点重组
	 * @param myGepAlgorithm 算法集，保存有种群和各种参数
	 */
	public abstract void onePointRecombine(GepAlgorithm myGepAlgorithm);
	/**
	 * 对操作种群队列中队尾的种群进行两点重组
	 * @param myGepAlgorithm 算法集，保存有种群和各种参数
	 */
	public abstract void twoPointRecombine(GepAlgorithm myGepAlgorithm);
	/**
	 * 对操作种群队列中队尾的种群进行基因重组
	 * @param myGepAlgorithm 算法集，保存有种群和各种参数
	 */
	public abstract void geneRecombine(GepAlgorithm myGepAlgorithm);
}
