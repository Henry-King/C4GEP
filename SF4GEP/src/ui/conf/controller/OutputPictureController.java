package ui.conf.controller;

import ui.conf.model.OutputPictureModel;
import ui.conf.view.OutputPicturePanel;
import domain.core.algOutput.GepAlgRun;

public class OutputPictureController{
	
	private OutputPictureModel outputPictureModel = null;
	private OutputPicturePanel outputPicturePanel = null;
	
	private GepAlgRun gepAlgRun;
	
	
	
	
	public OutputPictureController(OutputPictureModel outputPictureModel,OutputPicturePanel outputPicturePanel) {
		super();
		this.outputPictureModel = outputPictureModel;
		this.outputPicturePanel = outputPicturePanel;
		init();
	}
	public void init(){
		outputPictureModel.registerObserver(outputPicturePanel);
	}
	
		
	

	

	public OutputPictureModel getOutputPictureModel() {
		return outputPictureModel;
	}


	public OutputPicturePanel getOutputPicturePanel() {
		return outputPicturePanel;
	}

	public void setOutputPicturePanel(OutputPicturePanel outputPicturePanel) {
		this.outputPicturePanel = outputPicturePanel;
	}

	public GepAlgRun getGepAlgRun() {
		return gepAlgRun;
	}

	public void setGepAlgRun(GepAlgRun gepAlgRun) {
		this.gepAlgRun = gepAlgRun;
		outputPictureModel.setGepAlgRun(gepAlgRun);
	}

	
	
	
}
