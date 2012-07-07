package common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * ʵ�ֶ������ȸ���
 * @author ��Զ
 *
 */
public class ObjectCopy {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	/**
	 * ��ָ���Ķ�����һ�ݲ����أ�����ָ���Ķ���ʵ����ICopy����ӿڣ���������ֱ�ӵ���ICopy�е���Ӧ������
	 * ����û��ʵ��ICopy�ӿڣ���ʵ����Serializable�ӿڣ�����������ʹ�����л����ƶ���
	 * ���ָ���Ķ���û��ʵ���������ӿ��е�����һ����������������һ��nullֵ
	 * @param source �����ƵĶ���
	 * @return һ���µĶ�����Դ���������ͬ�ĳ�ֵ�����Ǿ��в�ͬ���ڴ��ַ��
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
