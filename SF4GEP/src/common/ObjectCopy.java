package common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectCopy {
	@SuppressWarnings("unchecked")
	public static <T> T newInstance(T source){
		T result=null;
		try {
			ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream=new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(source);
			ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
			ObjectInputStream objectInputStream=new ObjectInputStream(byteArrayInputStream);
			result=(T) objectInputStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
