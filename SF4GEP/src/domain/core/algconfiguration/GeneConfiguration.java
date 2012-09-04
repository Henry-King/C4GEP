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
	 * ���ػ���������Ϣ��ID����ID�����������ݿ������������벻Ҫ������;
	 * @return ������ϢID
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * ���û���������Ϣ��ID����ID�����������ݿ������������벻Ҫ������;
	 * @param id ������ϢID
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * �Ƿ�ʹ��ͬԴ�������ʹ��ͬԴ���������Ӻ������Զ����ã�ͬԴ���򳤶Ⱥ�ͬԴ��������û��ֶ�������Ч,
	 *ϵͳ�Զ���������������������û����ֶ����������������������������ᱻ���ǵ���
	 * @return true��ʾʹ��ͬԴ����false��ʾʹ�����Ӻ���
	 */
	public Boolean getUseHomeoticGene() {
		return useHomeoticGene;
	}
	/**
	 * �����Ƿ�ʹ��ͬԴ�������ʹ��ͬԴ���������Ӻ������Զ����ã�ͬԴ���򳤶Ⱥ�ͬԴ��������û��ֶ�������Ч,
	 *ϵͳ�Զ���������������������û����ֶ����������������������������ᱻ���ǵ���
	 * @param useHomeoticGene 
	 */
	public void setUseHomeoticGene(Boolean useHomeoticGene) {
		this.useHomeoticGene = useHomeoticGene;
	}
	/**
	 * ������ͨ���������
	 * @return ��ͨ���������
	 */
	public Integer getNormalGeneNumber() {
		return normalGeneNumber;
	}
	/**
	 * ������ͨ���������
	 * @param normalGeneNumber ��ͨ���������
	 */
	public void setNormalGeneNumber(Integer normalGeneNumber) {
		this.normalGeneNumber = normalGeneNumber;
	}
	/**
	 * �����ͨ�����ͷ������
	 * @return ��ͨ�����ͷ������
	 */
	public Integer getNormalGeneHeaderLength() {
		return normalGeneHeaderLength;
	}
	/**
	 * ������ͨ����ͷ������
	 * @param normalGeneHeaderLength ��ͨ����ͷ������
	 */
	public void setNormalGeneHeaderLength(Integer normalGeneHeaderLength) {
		this.normalGeneHeaderLength = normalGeneHeaderLength;
	}
	/**
	 * ��ȡ��ͨ�����β�����ȣ��˳��ȿ���Service���Զ�����ó�,
	 *β������=ͷ������*(Max{�����Ĳ�������|������})+1
	 * @return ��ͨ�����β������
	 */
	public Integer getNormalGeneTailLength() {
		return normalGeneTailLength;
	}
	/**
	 * ������ͨ�����β�����ȣ��˳��ȿ���Service���Զ�����ó�,�û���Ҫ�ֶ����ã�������ܷ����쳣��
	 *β������=ͷ������*(Max{�����Ĳ�������|������})+1
	 * @param normalGeneTailLength ��ͨ����β������
	 */
	public void setNormalGeneTailLength(Integer normalGeneTailLength) {
		this.normalGeneTailLength = normalGeneTailLength;
	}
	/**
	 * ����һ����ͨ����ĳ��ȣ��䳤�ȵ���=��ͨ�����ͷ��+��ͨ�����β��
	 * @return һ����ͨ����ĳ���
	 */
	public Integer getNormalGeneLength() {
		return normalGeneLength;
	}
	/**
	 * ����һ����ͨ����ĳ��ȣ��䳤�ȵ���=��ͨ�����ͷ��+��ͨ�����β�����û���Ҫ�ֶ����ã�������ܷ����쳣
	 * @param normalGeneLength ��ͨ���򳤶�
	 */
	public void setNormalGeneLength(Integer normalGeneLength) {
		this.normalGeneLength = normalGeneLength;
	}
	/**
	 * ����ͬԴ��������
	 * @return ͬԴ���������
	 */
	public Integer getHomeoticGeneNumber() {
		return homeoticGeneNumber;
	}
	/**
	 * ����ͬԴ��������
	 * @param homeoticGeneNumber ͬԴ��������
	 */
	public void setHomeoticGeneNumber(Integer homeoticGeneNumber) {
		this.homeoticGeneNumber = homeoticGeneNumber;
	}
	/**
	 * ��ȡͬԴ����ͷ������
	 * @return ͬԴ����ͷ������
	 */
	public Integer getHomeoticGeneHeaderLength() {
		return homeoticGeneHeaderLength;
	}
	/**
	 * ����ͬԴ����ͷ������
	 * @param homeoticGeneHeaderLength ͬԴ����ͷ������
	 */
	public void setHomeoticGeneHeaderLength(Integer homeoticGeneHeaderLength) {
		this.homeoticGeneHeaderLength = homeoticGeneHeaderLength;
	}
	/**
	 * ��ȡͬԴ�����β�����ȣ��˳��ȿ���Service���Զ�����ó�,
	 *β������=ͷ������*(Max{�����Ĳ�������|������})+1
	 * @return ͬԴ�����β������
	 */
	public Integer getHomeoticGeneTailLength() {
		return homeoticGeneTailLength;
	}
	/**
	 * ����ͬԴ�����β�����ȣ��˳��ȿ���Service���Զ�����ó�,�û���Ҫ�ֶ����ã�������ܷ����쳣��
	 *β������=ͷ������*(Max{�����Ĳ�������|������})+1
	 * @param homeoticGeneTailLength ͬԴ����β������
	 */
	public void setHomeoticGeneTailLength(Integer homeoticGeneTailLength) {
		this.homeoticGeneTailLength = homeoticGeneTailLength;
	}
	/**
	 * ����һ��ͬԴ����ĳ��ȣ��䳤�ȵ���=ͬԴ�����ͷ��+ͬԴ�����β��
	 * @return һ��ͬԴ����ĳ���
	 */
	public Integer getHomeoticGeneLength() {
		return homeoticGeneLength;
	}
	/**
	 * ����һ��ͬԴ����ĳ��ȣ��䳤�ȵ���=ͬԴ�����ͷ��+ͬԴ�����β�����û���Ҫ�ֶ����ã�������ܷ����쳣
	 * @param homeoticGeneLength ͬԴ����ĳ���
	 */
	public void setHomeoticGeneLength(Integer homeoticGeneLength) {
		this.homeoticGeneLength = homeoticGeneLength;
	}
	/**
	 * ���ر�ʹ�õĺ�����
	 * @return ��ʹ�õĺ�������ɵ�List
	 */
	public List<Function> getFunctionUsed() {
		return functionUsed;
	}
	/**
	 * ���ñ�ʹ�õĺ�����
	 * @param functionUsed ��ʹ�õĺ�����
	 */
	public void setFunctionUsed(List<Function> functionUsed) {
		this.functionUsed = functionUsed;
	}
	/**
	 * �������Ӻ���,���û��ʹ�����Ӻ������򷵻�null
	 * @return ���Ӻ���
	 */
	public Function getConnectionFunction() {
		if(useHomeoticGene)
			return connectionFunction;
		else
			return null;
	}
	/**
	 * �������Ӻ���������useHomeoticGeneΪtrueʱ��������Ч
	 * @param connectionFunction ���Ӻ���
	 */
	public void setConnectionFunction(Function connectionFunction) {
		this.connectionFunction = connectionFunction;
	}
	/**
	 * Hiberanteר�ýӿڣ�������ã�ͨ�����ô˷���������Ѻ����������еĺ�������ȫ�޶������ַ�����ʾ��ʽ����������ÿ��
	 *�ַ���֮���á���������
	 * @return ���еĺ�������ȫ�޶������ַ�����ʾ��ʽ
	 */
	public String getFunctionClass(){
		StringBuilder stringBuilder=new StringBuilder();
		for(int i=0;i<functionUsed.size();i++)
			stringBuilder.append(functionUsed.get(i).getClass().getName()+",");
		stringBuilder.deleteCharAt(stringBuilder.length()-1);
		return stringBuilder.toString();
	}
	/**
	 * Hiberanteר�ýӿڣ�������ã���������еĺ�������ȫ�޶������ַ�����ʾ��ʽ��ԭ�ɺ���List
	 * @param functionString ���еĺ�������ȫ�޶������ַ�����ʾ��ʽ
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
	 * Hiberanteר�ýӿڣ�������ã��������Ӻ������ַ�����ʾ��ʽ
	 * @param connectionFunctionString ���Ӻ�������ȫ�޶���
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
	 * Hiberanteר�ýӿڣ�������ã��������Ӻ�������ȫ�޶���
	 * @return ���Ӻ�������ȫ�޶���
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
