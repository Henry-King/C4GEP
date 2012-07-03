package domain.core.algmodel.genecomponent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConstantList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6540245122718988174L;
	private List<Float> floatlist;
	public ConstantList(float start,float end,int num){
		floatlist=new ArrayList<Float>(num);
		Random random=new Random();
		float range=end-start;
		for(int i=0;i<num;i++)
			floatlist.add(start+random.nextFloat()*range);
		
	}
	public float getValue(int index){
		return floatlist.get(index);
	}
}
