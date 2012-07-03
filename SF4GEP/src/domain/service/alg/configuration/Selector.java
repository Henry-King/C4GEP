package domain.service.alg.configuration;

import java.util.Deque;

import domain.core.algmodel.configuration.Population;

/**
 * 请确保子类有默认构造函数，否则会出现错误
 */
public abstract class Selector {
	private final String name;
	public Selector(String name){
		this.name=name;
	}
	@Override
	public final String toString() {
		// TODO Auto-generated method stub
		return name;
	}
	/**
	 * 不需要负责更新种群代数，插入新个体就可以
	 * @return TODO
	 */
	public abstract Population select(Deque<Population> population);

}
