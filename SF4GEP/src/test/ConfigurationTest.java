package test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import jxl.read.biff.BiffException;

import data.dao.HibernateDataContext;
import data.dao.IHibernateDataContext;
import domain.core.algInputDataProcess.DataSet;
import domain.core.algOutput.GepAlgRun;
import domain.core.algconfiguration.GeneConfiguration;
import domain.core.algconfiguration.GepAlgConfiguration;
import domain.core.algconfiguration.IndividualConfiguration;
import domain.core.algconfiguration.OperatorConfiguration;
import domain.core.algconfiguration.function.Additioin;
import domain.core.algconfiguration.function.Divide;
import domain.core.algconfiguration.function.Minus;
import domain.core.algconfiguration.function.Multiply;
import domain.iservice.algConfiguration.IgepConfigurationService;
import domain.iservice.algInputDataProcess.IDataInputService;
import domain.iservice.algOutput.IAlgOutputService;
import domain.iservice.algOutput.IAlgRunStep;
import domain.service.algConfiguration.GepConfigurationService;
import domain.service.algInputDataProcess.DataInputService;
import domain.service.algOutput.AlgOutputService;
import domain.service.algOutput.AlgRunStep;

public class ConfigurationTest {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws BiffException 
	 */
	public static void main(String[] args) throws BiffException, IOException {
		// TODO Auto-generated method stub
		IHibernateDataContext hibernateDataContext=null;//new HibernateDataContext();
		IDataInputService dataInputService=new DataInputService(hibernateDataContext);
		DataSet dataSet=dataInputService.processInputDataSet(new File("InputDemo.xls"));
		GepAlgConfiguration gepAlgConfiguration=new GepAlgConfiguration();
		gepAlgConfiguration.setAccuracy((float) 0.01);
		gepAlgConfiguration.setSelectionRange((float) 100);
		gepAlgConfiguration.setName("≤‚ ‘");
		gepAlgConfiguration.setMaxGeneration((long) 1000000000);
		IndividualConfiguration individualConfiguration=new IndividualConfiguration();
		individualConfiguration.setIndividualNumber(20);
		GeneConfiguration geneConfiguration=new GeneConfiguration();
		geneConfiguration.setHomeoticGeneHeaderLength(5);
		geneConfiguration.setHomeoticGeneNumber(10);
		geneConfiguration.setNormalGeneHeaderLength(7);
		geneConfiguration.setNormalGeneNumber(3);
		geneConfiguration.setFunctionUsed(Arrays.asList(new Additioin(),new Minus(),new Multiply(),new Divide()));
		individualConfiguration.setGeneConfiguration(geneConfiguration);
		gepAlgConfiguration.setIndividualConfiguration(individualConfiguration);	
		OperatorConfiguration operatorConfiguration=new OperatorConfiguration();
		operatorConfiguration.setGeneRecombineRate((float) 0.1);
		operatorConfiguration.setGeneTransportRate((float) 0.1);
		operatorConfiguration.setIsElement(new Integer[]{1,2,3});
		operatorConfiguration.setIsTransportRate((float) 0.1);
		operatorConfiguration.setMutateRate((float) 0.0444);
		operatorConfiguration.setOnePointRecombineRate((float) 0.4);
		operatorConfiguration.setRisElement(new Integer[]{1,2,3});
		operatorConfiguration.setRisTransportRate((float) 0.1);
		operatorConfiguration.setTwoPointRecombineRate((float) 0.2);
		gepAlgConfiguration.setOperatorConfiguration(operatorConfiguration);
		IgepConfigurationService gepConfigurationService=new GepConfigurationService(hibernateDataContext);
		gepAlgConfiguration=gepConfigurationService.setGepAlgConfiguration(gepAlgConfiguration, dataSet);
		//gepConfigurationService.saveGepAlgConfiguration(gepAlgConfiguration);
		run(gepAlgConfiguration,dataSet,hibernateDataContext);
	}
	private static void run(GepAlgConfiguration gepAlgConfiguration,DataSet dataSet,IHibernateDataContext hibernateDataContext){
		IAlgOutputService algOutputService=new AlgOutputService(hibernateDataContext);
		IAlgRunStep runStep=new AlgRunStep();
		GepAlgRun gepAlgRun=algOutputService.run(gepAlgConfiguration, runStep, dataSet);
		System.out.println(gepAlgRun.getBestIndividual().getFitness());
	}
}
