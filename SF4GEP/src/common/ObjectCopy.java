package common;

import com.rits.cloning.Cloner;

public class ObjectCopy {
	public static <T> T newInstance(T source){
		Cloner cloner=new Cloner();
		return cloner.deepClone(source);
	}
}
