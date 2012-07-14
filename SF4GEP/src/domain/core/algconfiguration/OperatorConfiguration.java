package domain.core.algconfiguration;

import java.io.Serializable;

public class OperatorConfiguration implements Serializable{
	private static final long serialVersionUID = -49819413626669298L;
	private Integer id;
	private Float mutateRate;
	private Float isTransportRate;
	private Integer[] isElement;
	private Float risTransportRate;
	private Integer[] risElement;
	private Float geneTransportRate;
	private Float onePointRecombineRate;
	private Float twoPointRecombineRate;
	private Float geneRecombineRate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Float getMutateRate() {
		return mutateRate;
	}
	public void setMutateRate(Float mutateRate) {
		this.mutateRate = mutateRate;
	}
	public Float getIsTransportRate() {
		return isTransportRate;
	}
	public void setIsTransportRate(Float isTransportRate) {
		this.isTransportRate = isTransportRate;
	}
	public Integer[] getIsElement() {
		return isElement;
	}
	public void setIsElement(Integer[] isElement) {
		this.isElement = isElement;
	}
	public Float getRisTransportRate() {
		return risTransportRate;
	}
	public void setRisTransportRate(Float risTransportRate) {
		this.risTransportRate = risTransportRate;
	}
	public Integer[] getRisElement() {
		return risElement;
	}
	public void setRisElement(Integer[] risElement) {
		this.risElement = risElement;
	}
	public Float getGeneTransportRate() {
		return geneTransportRate;
	}
	public void setGeneTransportRate(Float geneTransportRate) {
		this.geneTransportRate = geneTransportRate;
	}
	public Float getOnePointRecombineRate() {
		return onePointRecombineRate;
	}
	public void setOnePointRecombineRate(Float onePointRecombineRate) {
		this.onePointRecombineRate = onePointRecombineRate;
	}
	public Float getTwoPointRecombineRate() {
		return twoPointRecombineRate;
	}
	public void setTwoPointRecombineRate(Float twoPointRecombineRate) {
		this.twoPointRecombineRate = twoPointRecombineRate;
	}
	public Float getGeneRecombineRate() {
		return geneRecombineRate;
	}
	public void setGeneRecombineRate(Float geneRecombineRate) {
		this.geneRecombineRate = geneRecombineRate;
	}
}
