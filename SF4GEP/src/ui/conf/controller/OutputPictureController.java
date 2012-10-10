package ui.conf.controller;

import java.util.ArrayList;
import java.util.List;

import ui.algoutput.controller.OutputController;
import ui.conf.model.Model;
import ui.conf.model.OutputPictureModel;
import ui.conf.view.Observer;
import ui.conf.view.OutputPicturePanel;

import com.sun.org.apache.xml.internal.security.Init;
import com.wolfram.jlink.MathCanvas;

import domain.core.algInputDataProcess.DataSet;
import domain.core.algOutput.FittedValue;
import domain.core.algOutput.GepAlgRun;
import domain.core.algOutput.Individual;

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
