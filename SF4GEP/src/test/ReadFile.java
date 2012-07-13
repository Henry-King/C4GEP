package test;

import java.io.File;
import java.io.IOException;

import jxl.read.biff.BiffException;

import domain.core.algInputDataProcess.DataSet;
import domain.iservice.algInputDataProcess.IDataInputService;
import domain.service.algInputDataProcess.DataInputService;

public class ReadFile {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws BiffException 
	 */
	public static void main(String[] args) throws BiffException, IOException {
		// TODO Auto-generated method stub
		IDataInputService dataInputService=new DataInputService();
		DataSet dataSet=dataInputService.processInputDataSet(new File("InputDemo.xls"));
		System.out.println(dataSet.getDataRow().get(9).getResultColumn().getColumnName());
		
	}

}
