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
	/**
	 * 返回普通基因的数量
	 * @return 普通基因的数量
	 */
	public Integer getNormalGeneNumber() {
		return normalGeneNumber;
	}
	/**
	 * 设置普通基因的数量
	 * @param normalGeneNumber 普通基因的数量
	 */
	public void setNormalGeneNumber(Integer normalGeneNumber) {
		this.normalGeneNumber = normalGeneNumber;
	}
	/**
	 * 获得普通基因的头部长度
	 * @return 普通基因的头部长度
	 */
	public Integer getNormalGeneHeaderLength() {
		return normalGeneHeaderLength;
	}
	/**
	 * 设置普通基因头部长度
	 * @param normalGeneHeaderLength 普通基因头部长度
	 */
	public void setNormalGeneHeaderLength(Integer normalGeneHeaderLength) {
		this.normalGeneHeaderLength = normalGeneHeaderLength;
	}
	/**
	 * 获取普通基因的尾部长度，此长度可由Service类自动计算得出,
	 *尾部长度=头部长度*(Max{函数的参数个数|函数集})+1
	 * @return 普通基因的尾部长度
	 */
	public Integer getNormalGeneTailLength() {
		return normalGeneTailLength;
	}
	/**
	 * 设置普通基因的尾部长度，此长度可由Service类自动计算得出,用户不要手动设置，否则可能发生异常，
	 *尾部长度=头部长度*(Max{函数的参数个数|函数集})+1
	 * @param normalGeneTailLength 普通基因尾部长度
	 */
	public void setNormalGeneTailLength(Integer normalGeneTailLength) {
		this.normalGeneTailLength = normalGeneTailLength;
	}
	/**
	 * 返回一个普通基因的长度，其长度等于=普通基因的头长+普通基因的尾长
	 * @return 一个普通基因的长度
	 */
	public Integer getNormalGeneLength() {
		return normalGeneLength;
	}
	/**
	 * 设置一个普通基因的长度，其长度等于=普通基因的头长+普通基因的尾长，用户不要手动设置，否则可能发生异常
	 * @param normalGeneLength 普通基因长度
	 */
	public void setNormalGeneLength(Integer normalGeneLength) {
		this.normalGeneLength = normalGeneLength;
	}
	/**
	 * 返回同源基因数量
	 * @return 同源基因的数量
	 */
	public Integer getHomeoticGeneNumber() {
		return homeoticGeneNumber;
	}
	/**
	 * 设置同源基因数量
	 * @param homeoticGeneNumber 同源基因数量
	 */
	public void setHomeoticGeneNumber(Integer homeoticGeneNumber) {
		this.homeoticGeneNumber = homeoticGeneNumber;
	}
	/**
	 * 获取同源基因头部长度
	 * @return 同源基因头部长度
	 */
	public Integer getHomeoticGeneHeaderLength() {
		return homeoticGeneHeaderLength;
	}
	/**
	 * 设置同源基因头部长度
	 * @param homeoticGeneHeaderLength 同源基因头部长度
	 */
	public void setHomeoticGeneHeaderLength(Integer homeoticGeneHeaderLength) {
		this.homeoticGeneHeaderLength = homeoticGeneHeaderLength;
	}
	/**
	 * 获取同源基因的尾部长度，此长度可由Service类自动计算得出,
	 *尾部长度=头部长度*(Max{函数的参数个数|函数集})+1
	 * @return 同源基因的尾部长度
	 */
	public Integer getHomeoticGeneTailLength() {
		return homeoticGeneTailLength;
	}
	/**
	 * 设置同源基因的尾部长度，此长度可由Service类自动计算得出,用户不要手动设置，否则可能发生异常，
	 *尾部长度=头部长度*(Max{函数的参数个数|函数集})+1
	 * @param homeoticGeneTailLength 同源基因尾部长度
	 */
	public void setHomeoticGeneTailLength(Integer homeoticGeneTailLength) {
		this.homeoticGeneTailLength = homeoticGeneTailLength;
	}
	/**
	 * 返回一个同源基因的长度，其长度等于=同源基因的头长+同源基因的尾长
	 * @return 一个同源基因的长度
	 */
	public Integer getHomeoticGeneLength() {
		return homeoticGeneLength;
	}
	/**
	 * 设置一个同源基因的长度，其长度等于=同源基因的头长+同源基因的尾长，用户不要手动设置，否则可能发生异常
	 * @param homeoticGeneLength 同源基因的长度
	 */
	public void setHomeoticGeneLength(Integer homeoticGeneLength) {
		this.homeoticGeneLength = homeoticGeneLength;
	}
	/**
	 * 返回被使用的函数集
	 * @return 被使用的函数所组成的List
	 */
	public List<Function> getFunctionUsed() {
		return functionUsed;
	}
	/**
	 * 设置被使用的函数集
	 * @param functionUsed 被使用的函数集
	 */
	public void setFunctionUsed(List<Function> functionUsed) {
		this.functionUsed = functionUsed;
	}
	/**
	 * 返回连接函数,如果没有使用连接函数，则返回null
	 * @return 连接函数
	 */
	public Function getConnectionFunction() {
		if(useHomeoticGene)
			return connectionFunction;
		else
			return null;
	}
	/**
	 * 设置连接函数，仅当useHomeoticGene为true时此设置有效
	 * @param connectionFunction 连接函数
	 */
	public void setConnectionFunction(Function connectionFunction) {
		this.connectionFunction = connectionFunction;
	}
	/**
	 * Hiberante专用接口，请勿调用，通过调用此方法，将会把函数集中所有的函数的完全限定名的字符串表示形式串联起来，每个
	 *字符串之间用“，”隔开
	 * @return 所有的函数的完全限定名的字符串表示形式
	 */
	public String getFunctionClass(){
		StringBuilder stringBuilder=new StringBuilder();
		for(int i=0;i<functionUsed.size();i++)
			stringBuilder.append(functionUsed.get(i).getClass().getName()+",");
		stringBuilder.deleteCharAt(stringBuilder.length()-1);
		return stringBuilder.toString();
	}
	/**
	 * Hiberante专用接口，请勿调用，将会把所有的函数的完全限定名的字符串表示形式还原成函数List
	 * @param functionString 所有的函数的完全限定名的字符串表示形式
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
	/**
	 * Hiberante专用接口，请勿调用，设置连接函数的字符串表示形式
	 * @param connectionFunctionString 连接函数的完全限定名
	 */
	public void setConnectionFunctionString(String connectionFunctionString){
		try {
			connectionFunction=Function.class.cast(Class.forName(connectionFunctionString).newInstance());
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException|NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Hiberante专用接口，请勿调用，返回连接函数的完全限定名
	 * @return 连接函数的完全限定名
	 */
	public String getConnectionFunctionString(){
		if(useHomeoticGene)
			return connectionFunction.getClass().getName();
		else
			return null;
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
