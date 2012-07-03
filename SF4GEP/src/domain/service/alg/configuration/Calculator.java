package domain.service.alg.configuration;

import domain.core.algmodel.configuration.GepAlgorithm;
import domain.core.algmodel.configuration.Individual;
import domain.core.inputmodel.InputSet;
import exception.IllegalInputSet;

/**
 * ��ȷ��������Ĭ�Ϲ��캯�����������ִ���
 */
public abstract class Calculator {
	private float selectionRage;
	private float accuray;
	private final String name;
	private InputSet inputSet;
	public Calculator(String name){
		this.name=name;
	}
	public float getSelectionRange(){
		return selectionRage;
	}
	public void setSelectionRange(float selectionRange){
		this.selectionRage=selectionRange;
	}
	public float getAccuray(){
		return accuray;
	}
	public void setAccuray(float accuray){
		this.accuray=accuray;
	}

	public InputSet getInputSet() {
		return inputSet;
	}
	public void setInputSet(InputSet inputSet) {
		this.inputSet = inputSet;
	}
	/**
	 * Ĭ�ϵ������㷨ֻ�����Population�����ж�βԪ�أ������������Ԫ��
	 */
	public abstract void calculateFitness(GepAlgorithm gepAlgorithm);
	public abstract void calculateInputSet(Individual individual) throws IllegalInputSet;
	@Override
	public final String toString() {
		// TODO Auto-generated method stub
		return name;
	}

}
