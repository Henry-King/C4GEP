package domain.core.algmodel.genecomponent;

/**
 * 变量类，用户输入的所有的变量信息都将变换为类
 * @author 申远
 *
 */
public class Variable extends Computable{
	private static final long serialVersionUID = 2304632426125416061L;
	/**
	 * 空构造函数，不进行任何操作
	 */
	public Variable() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 构造函数，用户可以指定变量名称
	 * @param name 变量名称
	 */
	public Variable(String name){
		this.name = name;
	}
}
