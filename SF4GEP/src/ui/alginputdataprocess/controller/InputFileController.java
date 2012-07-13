package ui.alginputdataprocess.controller;


import java.io.File;
import java.io.IOException;

import jxl.read.biff.BiffException;

import domain.core.algInputDataProcess.*;
import domain.iservice.algInputDataProcess.IDataInputService;
import domain.service.algInputDataProcess.DataInputService;

public class InputFileController {
	
	DataSet inputSet = null;
	String inputPath = null;
	IDataInputService dataInputService = new DataInputService();
	
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
			inputSet = dataInputService.processInputDataSet(inputFile);
		} catch (BiffException e) {}
		catch (IOException e) {}
		
		return inputSet;
	}
	
	
	
	
	
	
	
}
