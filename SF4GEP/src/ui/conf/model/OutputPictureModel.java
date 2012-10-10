package ui.conf.model;

import domain.core.algOutput.GepAlgRun;

public class OutputPictureModel extends Model{
	
	private GepAlgRun gepAlgRun;
	
	public OutputPictureModel() {
		
	}

	public GepAlgRun getGepAlgRun() {
		return gepAlgRun;
	}

	public void setGepAlgRun(GepAlgRun gepAlgRun) {
		this.gepAlgRun = gepAlgRun;
		this.changeModel();
	}
	
	
	
}
