package demo;

import java.io.File;
import java.io.IOException;

import jxl.read.biff.BiffException;

import data.dao.HibernateDataContext;
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
		IDataInputService dataInputService=new DataInputService(new HibernateDataContext());
		DataSet dataSet=dataInputService.processData(new File("InputDemo.xls"));
		System.out.println(dataSet.getDataRows().get(9).getDataColumns().get(0).getValue());
		
	}

}
