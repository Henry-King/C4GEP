package demo;

import java.io.File;
import java.io.IOException;

import jxl.read.biff.BiffException;

import data.dao.HibernateDataContext;
import data.dao.IHibernateDataContext;
import domain.core.algInputDataProcess.DataSet;
import domain.iservice.algInputDataProcess.IDataInputService;
import domain.service.algInputDataProcess.DataInputService;

public class DataSetHibernateTest {

	public static void main(String[] args) throws BiffException, IOException {
		// TODO Auto-generated method stub
		IHibernateDataContext hibernateDataContext=new HibernateDataContext();
		IDataInputService dataInputService=new DataInputService(hibernateDataContext);
		DataSet dataSet=dataInputService.processData(new File("InputDemo.xls"));
		dataInputService.save(dataSet);
		System.out.println(dataInputService.getDataSets().get(0).getDataRows().get(1).getResultColumn().getValue());
	}

}
