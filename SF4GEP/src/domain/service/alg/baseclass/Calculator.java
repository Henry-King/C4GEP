package domain.service.alg.baseclass;

import java.util.List;

import domain.core.algmodel.configuration.GepAlgorithm;
import domain.core.algmodel.configuration.Individual;
import domain.core.inputmodel.DataTable;
import exception.IllegalInputSet;

/**
 * 抽象类，所有的适应值计算方法均需继承此类，请确保继承此类的所有子类具有一个无参数的构造函数，否则会出现异常
 * @author 申远
 */
public abstract class Calculator {
	private float selectionRage;
	private float accuray;
	private final String name;
	private DataTable inputSet;
	/**
	 * 
	 * @param name 计算适应值类的的名字，这个名字会自动在界面上显示
	 */
	public Calculator(String name){
		this.name=name;
	}
	/**
	 * 返回选择范围
	 * @return 选择范围
	 */
	public float getSelectionRange(){
		return selectionRage;
	}
	/**
	 * 设置选择范围，选择范围是把函数值转化为适应值的时候所用到的重要参数，请确保选择范围大于0
	 * @param selectionRange 选择范围
	 */
	public void setSelectionRange(float selectionRange){
		this.selectionRage=selectionRange;
	}
	/**
	 * 返回求解精度，如0.01表示如果误差小于0.01，则忽略此误差
	 * @return 求解精度
	 */
	public float getAccuray(){
		return accuray;
	}
	/**
	 * 设置求解精度
	 * @param accuray 求解精度
	 */
	public void setAccuray(float accuray){
		this.accuray=accuray;
	}
	/**
	 * 返回输入集
	 * @return 输入集
	 */
	public DataTable getInputSet() {
		return inputSet;
	}
	/**
	 * 设置输入集
	 * @param inputSet 待设置的输入集
	 */
	public void setInputSet(DataTable inputSet) {
		this.inputSet = inputSet;
	}
	/**
	 * 计算适应值，只需要计算种群队列队尾种群中所有的适应值即可。
	 */
	public abstract void calculateFitness(GepAlgorithm gepAlgorithm);
	/**
	 * 用给定的个体计算给定的输入集的解，修改输入集通过调用setInputSet来完成
	 * @param individual 用于求解输入集的个体
	 * @return 保存有问题解的List
	 * @throws IllegalInputSet 输入集存在问题将会抛出此异常
	 */
	public abstract List<Float> calculateInputSet(Individual individual) throws IllegalInputSet;
	/**
	 * 返回求解方式的名称
	 * @return 求解名称的字符串表示。
	 */
	@Override
	public final String toString() {
		// TODO Auto-generated method stub
		return name;
	}

}
