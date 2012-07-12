package domain.core.algconfiguration;

public class IndividualConfiguration {
	private int individualNumber;
	private int normalGeneTotalLength;
	private int homeoticGeneTotalLength;
	private int geneTotalLenght;
	private GeneConfiguration geneConfiguration;
	public int getIndividualNumber() {
		return individualNumber;
	}
	public void setIndividualNumber(int individualNumber) {
		this.individualNumber = individualNumber;
	}
	public int getNormalGeneTotalLength() {
		return normalGeneTotalLength;
	}
	public void setNormalGeneTotalLength(int normalGeneTotalLength) {
		this.normalGeneTotalLength = normalGeneTotalLength;
	}
	public int getHomeoticGeneTotalLength() {
		return homeoticGeneTotalLength;
	}
	public void setHomeoticGeneTotalLength(int homeoticGeneTotalLength) {
		this.homeoticGeneTotalLength = homeoticGeneTotalLength;
	}
	public int getGeneTotalLenght() {
		return geneTotalLenght;
	}
	public void setGeneTotalLenght(int geneTotalLenght) {
		this.geneTotalLenght = geneTotalLenght;
	}
	public GeneConfiguration getGeneConfiguration() {
		return geneConfiguration;
	}
	public void setGeneConfiguration(GeneConfiguration geneConfiguration) {
		this.geneConfiguration = geneConfiguration;
	}
}
