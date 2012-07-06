package common;

public interface ICopy<T extends ICopy<T>> {
	public T copy();
}
