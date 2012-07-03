package domain.service.alg.configuration;

import java.util.Deque;

import domain.core.algmodel.configuration.Population;

/**
 * ��ȷ��������Ĭ�Ϲ��캯�����������ִ���
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
	 * ����Ҫ���������Ⱥ�����������¸���Ϳ���
	 * @return TODO
	 */
	public abstract Population select(Deque<Population> population);

}
