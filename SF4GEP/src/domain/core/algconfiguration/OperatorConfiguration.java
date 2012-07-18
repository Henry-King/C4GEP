package domain.core.algconfiguration;

import java.io.Serializable;
import java.util.Arrays;

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
	/**
	 * Hibernate专用接口，请勿调用
	 * @return
	 */
	public String getIsElementString(){
		String result=Arrays.toString(isElement);
		return result.substring(1,result.length()-1);
	}
	/**
	 * Hibernate专用接口，请勿调用
	 * @param isElementString
	 */
	public void setIsElementString(String isElementString){
		String [] stringArray=isElementString.split(", ");
		isElement=new Integer[stringArray.length];
		for(int i=0;i<stringArray.length;i++)
			isElement[i]=Integer.valueOf(stringArray[i]);
	}
	
	/**
	 * Hibernate专用接口，请勿调用
	 * @return
	 */
	public String getRisElementString(){
		String result=Arrays.toString(isElement);
		return result.substring(1,result.length()-1);
	}
	/**
	 * Hibernate专用接口，请勿调用
	 * @param risElementString
	 */
	public void setRisElementString(String risElementString){
		String [] stringArray=risElementString.split(", ");
		risElement=new Integer[stringArray.length];
		for(int i=0;i<stringArray.length;i++)
			risElement[i]=Integer.valueOf(stringArray[i]);
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof OperatorConfiguration) {
			OperatorConfiguration o=(OperatorConfiguration) obj;
			return (o.geneRecombineRate.equals(geneRecombineRate)
					&&o.geneTransportRate.equals(geneTransportRate)
					&&o.id.equals(id)
					&&Arrays.equals(o.isElement, isElement)
					&&Arrays.equals(risElement, o.risElement)
					&&o.mutateRate.equals(mutateRate)
					&&o.onePointRecombineRate.equals(onePointRecombineRate)
					&&o.isTransportRate.equals(isTransportRate)
					&&o.risTransportRate.equals(risTransportRate)
					&&o.twoPointRecombineRate.equals(twoPointRecombineRate));
		}
		return false;
	}
}
