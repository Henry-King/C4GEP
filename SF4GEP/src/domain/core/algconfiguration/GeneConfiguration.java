package domain.core.algconfiguration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class GeneConfiguration implements Serializable{
	private static final long serialVersionUID = -4801304275798018482L;
	private Integer id;
	private Integer normalGeneNumber;
	private Integer normalGeneHeaderLength;
	private Integer normalGeneTailLength;
	private Integer normalGeneLength;
	private Integer homeoticGeneNumber;
	private Integer homeoticGeneHeaderLength;
	private Integer homeoticGeneTailLength;
	private Integer homeoticGeneLength;
	private List<Function> functionUsed;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNormalGeneNumber() {
		return normalGeneNumber;
	}
	public void setNormalGeneNumber(Integer normalGeneNumber) {
		this.normalGeneNumber = normalGeneNumber;
	}
	public Integer getNormalGeneHeaderLength() {
		return normalGeneHeaderLength;
	}
	public void setNormalGeneHeaderLength(Integer normalGeneHeaderLength) {
		this.normalGeneHeaderLength = normalGeneHeaderLength;
	}
	public Integer getNormalGeneTailLength() {
		return normalGeneTailLength;
	}
	public void setNormalGeneTailLength(Integer normalGeneTailLength) {
		this.normalGeneTailLength = normalGeneTailLength;
	}
	public Integer getNormalGeneLength() {
		return normalGeneLength;
	}
	public void setNormalGeneLength(Integer normalGeneLength) {
		this.normalGeneLength = normalGeneLength;
	}
	public Integer getHomeoticGeneNumber() {
		return homeoticGeneNumber;
	}
	public void setHomeoticGeneNumber(Integer homeoticGeneNumber) {
		this.homeoticGeneNumber = homeoticGeneNumber;
	}
	public Integer getHomeoticGeneHeaderLength() {
		return homeoticGeneHeaderLength;
	}
	public void setHomeoticGeneHeaderLength(Integer homeoticGeneHeaderLength) {
		this.homeoticGeneHeaderLength = homeoticGeneHeaderLength;
	}
	public Integer getHomeoticGeneTailLength() {
		return homeoticGeneTailLength;
	}
	public void setHomeoticGeneTailLength(Integer homeoticGeneTailLength) {
		this.homeoticGeneTailLength = homeoticGeneTailLength;
	}
	public Integer getHomeoticGeneLength() {
		return homeoticGeneLength;
	}
	public void setHomeoticGeneLength(Integer homeoticGeneLength) {
		this.homeoticGeneLength = homeoticGeneLength;
	}
	public List<Function> getFunctionUsed() {
		return functionUsed;
	}
	public void setFunctionUsed(List<Function> functionUsed) {
		this.functionUsed = functionUsed;
	}
	/**
	 * Hiberante专用接口，请勿调用
	 * @return
	 */
	public String getFunctionClass(){
		StringBuilder stringBuilder=new StringBuilder();
		for(int i=0;i<functionUsed.size();i++)
			stringBuilder.append(functionUsed.get(i).getClass().getName()+",");
		stringBuilder.deleteCharAt(stringBuilder.length()-1);
		return stringBuilder.toString();
	}
	/**
	 * Hiberante专用接口，请勿调用
	 * @param functionString
	 */
	public void setFunctionClass(String functionString){
		String[] funStrings=functionString.split(",");
		functionUsed=new ArrayList<Function>(funStrings.length);
		for(int i=0;i<funStrings.length;i++)
			try {
				functionUsed.add(Function.class.cast(Class.forName(funStrings[i]).newInstance()));
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	}
	@Override
	public int hashCode(){
		int result = 17;
		result = 37 * result + (int)normalGeneNumber;
		result = 37 * result + (int)normalGeneHeaderLength;
		result = 37 * result + (int)homeoticGeneNumber;
		result = 37 * result + (int)homeoticGeneHeaderLength;
		result = 37 * result + functionUsed.hashCode();
		return result;
	}
	@Override
	public boolean equals(Object o){
		if(o instanceof GeneConfiguration){
		GeneConfiguration gc = (GeneConfiguration)o;
		return gc.getNormalGeneNumber() .equals( normalGeneNumber)
			&&	gc.getNormalGeneHeaderLength() .equals( normalGeneHeaderLength)
			&&	gc.getHomeoticGeneNumber() .equals( homeoticGeneNumber)
			&&	gc.getHomeoticGeneHeaderLength() .equals( homeoticGeneHeaderLength)
			&&	gc.getFunctionUsed() .equals( functionUsed);			
		}
		else {
			return false;
		}
	}	
}
