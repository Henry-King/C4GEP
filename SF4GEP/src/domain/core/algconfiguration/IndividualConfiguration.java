package domain.core.algconfiguration;

import java.io.Serializable;

public class IndividualConfiguration implements Serializable{
	private static final long serialVersionUID = 1312848752854264394L;
	private Integer id;
	private Integer individualNumber;
	private Integer totalGeneNumbers;
	private Integer normalGeneTotalLength;
	private Integer homeoticGeneTotalLength;
	private Integer geneTotalLength;
	private GeneConfiguration geneConfiguration;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIndividualNumber() {
		return individualNumber;
	}
	public void setIndividualNumber(Integer individualNumber) {
		this.individualNumber = individualNumber;
	}
	public Integer getTotalGeneNumbers() {
		return totalGeneNumbers;
	}
	public void setTotalGeneNumbers(Integer totalGeneNumbers) {
		this.totalGeneNumbers = totalGeneNumbers;
	}
	public Integer getNormalGeneTotalLength() {
		return normalGeneTotalLength;
	}
	public void setNormalGeneTotalLength(Integer normalGeneTotalLength) {
		this.normalGeneTotalLength = normalGeneTotalLength;
	}
	public Integer getHomeoticGeneTotalLength() {
		return homeoticGeneTotalLength;
	}
	public void setHomeoticGeneTotalLength(Integer homeoticGeneTotalLength) {
		this.homeoticGeneTotalLength = homeoticGeneTotalLength;
	}
	public Integer getGeneTotalLength() {
		return geneTotalLength;
	}
	public void setGeneTotalLength(Integer geneTotalLength) {
		this.geneTotalLength = geneTotalLength;
	}
	public GeneConfiguration getGeneConfiguration() {
		return geneConfiguration;
	}
	public void setGeneConfiguration(GeneConfiguration geneConfiguration) {
		this.geneConfiguration = geneConfiguration;
	}
	@Override
	public int hashCode(){
		int result = 17;
		result = 37 * result + (int)individualNumber;
		result = 37 * result + (int)totalGeneNumbers;
		result = 37 * result + (int)normalGeneTotalLength;
		result = 37 * result + (int)homeoticGeneTotalLength;
		result = 37 * result + (int)geneTotalLength;
		result = 37 * result + geneConfiguration.hashCode();
		return result;
	}
	@Override
	public boolean equals(Object o){
		if(o instanceof IndividualConfiguration){
			IndividualConfiguration ic = (IndividualConfiguration)o;
			return ic.individualNumber .equals( individualNumber)
				&&	ic.totalGeneNumbers .equals( totalGeneNumbers)
				&&	ic.normalGeneTotalLength .equals( normalGeneTotalLength)
				&&	ic.homeoticGeneTotalLength .equals( homeoticGeneTotalLength)
				&&	ic.geneTotalLength .equals( geneTotalLength)
				&&	ic.geneConfiguration .equals( geneConfiguration);			
		}
		else {
			return false;
		}
	}
}
