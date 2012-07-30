package test;

import java.io.File;
import java.io.IOException;

import jxl.read.biff.BiffException;
import data.dao.HibernateDataContext;
import data.dao.IHibernateDataContext;
import domain.core.algInputDataProcess.DataSet;
import domain.core.algconfiguration.GepAlgConfiguration;
import domain.iservice.algConfiguration.IgepConfigurationService;
import domain.iservice.algInputDataProcess.IDataInputService;
import domain.service.algConfiguration.GepConfigurationService;
import domain.service.algInputDataProcess.DataInputService;

public class HibernateTest {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws BiffException 
	 */
	public static void main(String[] args) throws BiffException, IOException {
		// TODO Auto-generated method stub
		IHibernateDataContext hibernateDataContext=new HibernateDataContext();
		IDataInputService dataInputService=new DataInputService(hibernateDataContext);
		DataSet dataSet=dataInputService.processInputDataSet(new File("InputDemo.xls"));
		GepAlgConfiguration gepAlgConfiguration;
		IgepConfigurationService gepConfigurationService=new GepConfigurationService(hibernateDataContext);
		gepAlgConfiguration=gepConfigurationService.getAllGepAlgConfiguration().get(0);
		gepAlgConfiguration=gepConfigurationService.setGepAlgConfiguration(gepAlgConfiguration, dataSet);
		System.out.println(gepAlgConfiguration.getIndividualConfiguration().getGeneConfiguration().getUseHomeoticGene());
	}

}
