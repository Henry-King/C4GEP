package domain.core.algconfiguration;

public class OperatorConfiguration {
	private float mutateRate;
	private float isTransportRate;
	private int[] isElement;
	private float risTransportRate;
	private int[] risElement;
	private float geneTransportRate;
	private float onePointRecombineRate;
	private float twoPointRecombineRate;
	private float geneRecombineRate;
	public float getMutateRate() {
		return mutateRate;
	}
	public void setMutateRate(float mutateRate) {
		this.mutateRate = mutateRate;
	}
	public float getIsTransportRate() {
		return isTransportRate;
	}
	public void setIsTransportRate(float isTransportRate) {
		this.isTransportRate = isTransportRate;
	}
	public int[] getIsElement() {
		return isElement;
	}
	public void setIsElement(int[] isElement) {
		this.isElement = isElement;
	}
	public float getRisTransportRate() {
		return risTransportRate;
	}
	public void setRisTransportRate(float risTransportRate) {
		this.risTransportRate = risTransportRate;
	}
	public int[] getRisElement() {
		return risElement;
	}
	public void setRisElement(int[] risElement) {
		this.risElement = risElement;
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
}
