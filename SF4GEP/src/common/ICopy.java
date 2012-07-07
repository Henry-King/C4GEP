package common;

/**
 * 本接口表示当前对象可被快速复制，不需要提供序列化接口，但速度要远快于序列化。
 * @author 申远
 *
 * @param <T> 待复制对象的类型
 */
public interface ICopy<T extends ICopy<T>> {
	/**
	 * 复制某个对象，这是一个深度复制，源对象和复制后的对象将具有相同的初值，但是具有不同的内存地址
	 * @return 一个复制后的对象的引用
	 */
	public T copy();
}
