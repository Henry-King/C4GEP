package domain.core.algconfiguration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class GeneConfiguration implements Serializable{
	private static final long serialVersionUID = -4801304275798018482L;
	private Integer id;
	private Boolean useHomeoticGene;
	private Integer normalGeneNumber;
	private Integer normalGeneHeaderLength;
	private Integer normalGeneTailLength;
	private Integer normalGeneLength;
	private Integer homeoticGeneNumber;
	private Integer homeoticGeneHeaderLength;
	private Integer homeoticGeneTailLength;
	private Integer homeoticGeneLength;
	private List<Function> functionUsed;
	private Function connectionFunction;
	/**
	 * 返回基因配置信息的ID，此ID仅用来在数据库中做主键，请不要用作它途
	 * @return 配置信息ID
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置基因配置信息的ID，此ID仅用来在数据库中做主键，请不要用作它途
	 * @param id 配置信息ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 是否使用同源基因，如果使用同源基因，则连接函数被自动禁用，同源基因长度和同源基因个数用户手动设置无效,
	 *系统自动生成这两个参数，如果用户有手动设置这两个参数，这两个参数会被覆盖掉。
	 * @return true表示使用同源基因，false表示使用连接函数
	 */
	public Boolean getUseHomeoticGene() {
		return useHomeoticGene;
	}
	/**
	 * 设置是否使用同源基因，如果使用同源基因，则连接函数被自动禁用，同源基因长度和同源基因个数用户手动设置无效,
	 *系统自动生成这两个参数，如果用户有手动设置这两个参数，这两个参数会被覆盖掉。
	 * @param useHomeoticGene 
	 */
	public void setUseHomeoticGene(Boolean useHomeoticGene) {
		this.useHomeoticGene = useHomeoticGene;
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
	public Function getConnectionFunction() {
		return connectionFunction;
	}
	public void setConnectionFunction(Function connectionFunction) {
		this.connectionFunction = connectionFunction;
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
	public void setConnectionFunctionString(String connectionFunctionString){
		try {
			connectionFunction=Function.class.cast(Class.forName(connectionFunctionString).newInstance());
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getConnectionFunctionString(){
		return connectionFunction.getClass().getName();
	}
	@Override
	public int hashCode(){
		int result = 17;
		result = 37 * result + (int)normalGeneNumber;
		result = 37 * result + (int)normalGeneHeaderLength;
		result = 37 *result +useHomeoticGene.hashCode();
		if(useHomeoticGene){
			result = 37 * result + (int)homeoticGeneNumber;
			result = 37 * result + (int)homeoticGeneHeaderLength;
		}
		else {
			result=37*result+connectionFunction.hashCode();
		}
		result = 37 * result + functionUsed.hashCode();
		return result;
	}
	@Override
	public boolean equals(Object o){
		if(o instanceof GeneConfiguration){
		GeneConfiguration gc = (GeneConfiguration)o;
		return gc.getNormalGeneNumber() .equals( normalGeneNumber)
			&&	gc.getNormalGeneHeaderLength() .equals( normalGeneHeaderLength)
			&&	gc.getFunctionUsed() .equals(functionUsed)
			&&  gc.getUseHomeoticGene().equals(useHomeoticGene)
			&&	(gc.getUseHomeoticGene()
			?(gc.getHomeoticGeneNumber().equals(homeoticGeneNumber)&&gc.getHomeoticGeneHeaderLength().equals( homeoticGeneHeaderLength))
			:(gc.getConnectionFunction().equals(connectionFunction)));
		}
		else {
			return false;
		}
	}	
}
