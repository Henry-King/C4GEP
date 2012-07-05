package domain.service.alg.configuration;

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
	private float start;
	private float end;
	private int constanListSize;
	public Modifying(String name){
		this.name=name;
	}
	/**
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		// TODO Auto-generated method stub
		return name;
	}
	public float getMutateRate() {
		return mutateRate;
	}
	public void setMutateRate(float variateRate) {
		this.mutateRate = variateRate;
	}
	public float getIsTransportRate() {
		return isTransportRate;
	}
	public void setIsTransportRate(float isTransportRate) {
		this.isTransportRate = isTransportRate;
	}
	public List<Integer> getIsElements() {
		return isElements;
	}
	public void setIsElements(List<Integer> isLength) {
		this.isElements = isLength;
	}
	public float getRisTransportRate() {
		return risTransportRate;
	}
	public void setRisTransportRate(float risTransportRate) {
		this.risTransportRate = risTransportRate;
	}
	public List<Integer> getRisElements() {
		return risElements;
	}
	public void setRisElements(List<Integer> risLength) {
		this.risElements = risLength;
	}
	public float getGeneTransportRate() {
		return geneTransportRate;
	}
	public void setGeneTransportRate(float geneTransportRate) {
		this.geneTransportRate = geneTransportRate;
	}
	public float getOnePointRecombineRate() {
		return onePointRecombineRate;
	}
	public void setOnePointRecombineRate(float onePointRecombineRate) {
		this.onePointRecombineRate = onePointRecombineRate;
	}
	public float getTwoPointRecombineRate() {
		return twoPointRecombineRate;
	}
	public void setTwoPointRecombineRate(float twoPointRecombineRate) {
		this.twoPointRecombineRate = twoPointRecombineRate;
	}
	public float getGeneRecombineRate() {
		return geneRecombineRate;
	}
	public void setGeneRecombineRate(float geneRecombineRate) {
		this.geneRecombineRate = geneRecombineRate;
	}
	public abstract void run(GepAlgorithm myGepAlgorithm);
	public abstract void mutate(GepAlgorithm myGepAlgorithm);
	public abstract void isTransport(GepAlgorithm myGepAlgorithm);
	public abstract void risTransport(GepAlgorithm myGepAlgorithm);
	public abstract void geneTransport(GepAlgorithm myGepAlgorithm);
	public abstract void onePointRecombine(GepAlgorithm myGepAlgorithm);
	public abstract void twoPointRecombine(GepAlgorithm myGepAlgorithm);
	public abstract void geneRecombine(GepAlgorithm myGepAlgorithm);
	public float getStart() {
		return start;
	}
	public void setStart(float start) {
		this.start = start;
	}
	public float getEnd() {
		return end;
	}
	public void setEnd(float end) {
		this.end = end;
	}
	public int getConstanListSize() {
		return constanListSize;
	}
	public void setConstanListSize(int constanListSize) {
		this.constanListSize = constanListSize;
	}
}
