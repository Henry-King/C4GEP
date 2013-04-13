package ui.conf.controller;


import java.io.File;
import java.io.IOException;

import jxl.read.biff.BiffException;
import data.dao.IHibernateDataContext;
import domain.core.algInputDataProcess.DataSet;
import domain.iservice.algInputDataProcess.IDataInputService;
import domain.service.algInputDataProcess.DataInputService;

public class InputDataController {
	
	private DataSet inputSet = null;
	private String inputPath = null;
	private IDataInputService dataInputService;
	
	
	public InputDataController(IHibernateDataContext hibernateDataContext){
		 dataInputService = new DataInputService(hibernateDataContext);
	}
	public String getInputFilePath(){
		if (inputPath==null) {
			return null;
		}else{
			return inputPath;
		}
	}
	
	public DataSet getInputSet(){
		if (inputSet==null) {
			return null;
		}else{
			return inputSet;
		}
	}
	
	public DataSet getInputSet(String inputPath){
		File inputFile = new File(inputPath);
		
		try {
			inputSet = dataInputService.processData(inputFile);
		} catch (BiffException e) {}
		catch (IOException e) {}
		
		return inputSet;
	}
	
	
	/*public void setInputSet(String inputPath,MainFrame parent){
		
		this.inputPath = inputPath;
		parent.inputSet = getInputSet(inputPath);
	}*/
	
	
	
	
	
	
}
