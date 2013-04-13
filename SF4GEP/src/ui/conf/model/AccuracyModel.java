package ui.conf.model;

import java.util.HashMap;
import java.util.Map;

public class AccuracyModel extends Model{
	private Long maxGeneration;		//���������
	private Float selectionRange;	//ѡ��Χ
	private Float accuracy;			//��⾫��
	
	private Map<String, Boolean> isDataFitedMap = new HashMap<String, Boolean>();
	
	
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
		isDataFitedMap.put("maxGeneration", false);
		isDataFitedMap.put("selectionRange", false);
		isDataFitedMap.put("accuracy", false);
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



	public Map<String, Boolean> getIsDataFitedMap() {
		return isDataFitedMap;
	}

	public void setisDataFitedMap(
			Map<String, Boolean> isDataFitedMap) {
		this.isDataFitedMap = isDataFitedMap;
	}
	
	
	
	
	
}
