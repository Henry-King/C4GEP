package ui.conf.model;

import java.util.Hashtable;

public class AccuracyModel extends Model{
	private Long maxGeneration;		//最大求解代数
	private Float selectionRange;	//选择范围
	private Float accuracy;			//求解精度
	
	private Hashtable<String, Boolean> isDataFitedHashtable = new Hashtable<String, Boolean>();
	
	
	public AccuracyModel() {
		super();
		this.maxGeneration = Long.parseLong("0");
		this.selectionRange = Float.parseFloat("0.0");
		this.accuracy = Float.parseFloat("0.0");
		init();
	}
	
	public AccuracyModel(Long maxGeneration,Float selectionRange,Float accuracy){
		super();
		init();
		this.maxGeneration = maxGeneration;
		this.selectionRange = selectionRange;
		this.accuracy = accuracy;
	}
	
	private void init(){
		isDataFitedHashtable.put("maxGeneration", false);
		isDataFitedHashtable.put("selectionRange", false);
		isDataFitedHashtable.put("accuracy", false);
	}

	public Long getMaxGeneration() {
		return maxGeneration;
	}

	public void setMaxGeneration(Long maxGeneration) {
		this.maxGeneration = maxGeneration;
	}

	public Float getSelectionRange() {
		return selectionRange;
	}

	public void setSelectionRange(Float selectionRange) {
		this.selectionRange = selectionRange;
	}

	public Float getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(Float accuracy) {
		this.accuracy = accuracy;
	}



	public Hashtable<String, Boolean> getIsDataFitedHashtable() {
		return isDataFitedHashtable;
	}

	public void setIsDataFitedHashtable(
			Hashtable<String, Boolean> isDataFitedHashtable) {
		this.isDataFitedHashtable = isDataFitedHashtable;
	}
	
	
	
	
	
}
