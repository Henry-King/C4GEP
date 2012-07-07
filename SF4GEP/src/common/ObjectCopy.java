package common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 实现对象的深度复制
 * @author 申远
 *
 */
public class ObjectCopy {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	/**
	 * 将指定的对象复制一份并返回，若果指定的对象实现了ICopy这个接口，本方法将直接调用ICopy中的相应方法。
	 * 若果没有实现ICopy接口，但实现了Serializable接口，方法将尝试使用序列化复制对象。
	 * 如果指定的对象没有实现这两个接口中的任意一个，本方法将返回一个null值
	 * @param source 待复制的对象
	 * @return 一个新的对象，与源对象具有相同的初值，但是具有不同的内存地址。
	 */
	public static <T> T newInstance(T source){
		T result=null;
		if(source instanceof ICopy){
			result=(T) ((ICopy) source).copy();
		}
		else if(source instanceof Serializable){
			try {
				System.out.println("shit");
				ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
				ObjectOutputStream objectOutputStream=new ObjectOutputStream(byteArrayOutputStream);
				objectOutputStream.writeObject(source);
				ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
				ObjectInputStream objectInputStream=new ObjectInputStream(byteArrayInputStream);
				result=(T) objectInputStream.readObject();
				objectInputStream.close();
				byteArrayInputStream.close();
				objectOutputStream.close();
				byteArrayOutputStream.close();
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		return result;
	}
}
