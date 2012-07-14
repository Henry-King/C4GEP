package domain.core.algconfiguration;

import java.io.Serializable;

public class IndividualConfiguration implements Serializable{
	private static final long serialVersionUID = 1312848752854264394L;
	private Integer individualNumber;
	private Integer totalGeneNumbers;
	private Integer normalGeneTotalLength;
	private Integer homeoticGeneTotalLength;
	private Integer geneTotalLength;
	private GeneConfiguration geneConfiguration;
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
}
