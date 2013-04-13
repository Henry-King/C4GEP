package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import data.dao.HibernateDataContext;
import domain.core.algInputDataProcess.DataSet;
import domain.core.algconfiguration.Function;
import domain.core.algconfiguration.GeneConfiguration;
import domain.core.algconfiguration.GepAlgConfiguration;
import domain.core.algconfiguration.IndividualConfiguration;
import domain.core.algconfiguration.OperatorConfiguration;
import domain.core.algconfiguration.function.Addition;
import domain.core.algconfiguration.function.Cos;
import domain.core.algconfiguration.function.Divide;
import domain.core.algconfiguration.function.Minus;
import domain.core.algconfiguration.function.Multiply;
import domain.core.algconfiguration.function.Sin;
import domain.service.algConfiguration.GepConfigurationService;

public class GepConfigurationServiceTest {

	
	private static GepConfigurationService gepConfigurationService = null;
	
	@Before
	public void setUp() throws Exception {
		HibernateDataContext hibernateDataContext = new HibernateDataContext();
		gepConfigurationService = new GepConfigurationService(hibernateDataContext);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Ignore("Not yet implemented")
	@Test
	public void testGepConfigurationService() {
		fail("Not yet implemented");
	}

	@Ignore("Not yet implemented")
	@Test
	public void testGetAllGepAlgConfiguration() {
		fail("Not yet implemented");
	}

	@Ignore("Not yet implemented")
	@Test
	public void testSaveGepAlgConfiguration() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetGepAlgConfiguration() {
		
		/**
		 * ע�͵�һЩ����Ҫ�Ĳ���
		 * ����OperatorConfiguration��
		 * �ᵼ�²��Ե�ʱ�������
		 */
		GepAlgConfiguration gepAlgConfiguration = new GepAlgConfiguration();
		IndividualConfiguration individualConfiguration = new IndividualConfiguration();
		GeneConfiguration geneConfiguration = new GeneConfiguration();
		geneConfiguration.setUseHomeoticGene(true);
		
		OperatorConfiguration operatorConfiguration = new OperatorConfiguration();
		operatorConfiguration.setId(1);
		operatorConfiguration.setMutateRate(0.133f);
		operatorConfiguration.setIsTransportRate(0.133f);
		operatorConfiguration.setIsElement(new Integer[] { 1 });
		operatorConfiguration.setRisTransportRate(0.133f);
		operatorConfiguration.setRisElement(new Integer[] { 1 });
		operatorConfiguration.setGeneTransportRate(0.133f);
		operatorConfiguration.setOnePointRecombineRate(0.133f);
		operatorConfiguration.setTwoPointRecombineRate(0.133f);
		operatorConfiguration.setGeneRecombineRate(0.133f);
		gepAlgConfiguration.setOperatorConfiguration(operatorConfiguration);
		
		
		
		
		gepAlgConfiguration.setIndividualConfiguration(individualConfiguration);
		individualConfiguration.setGeneConfiguration(geneConfiguration);
		individualConfiguration.setIndividualNumber(1);
		DataSet ds = new DataSet();
		ds.setRowNum(2);
		int maxArity =  2;
		List<Function> functionUsed = new ArrayList<Function>(4);
		Function addFunction = new Addition();
		Function minusFunction = new Minus();
		Function mulFunction = new Multiply();
		Function devideFunction = new Divide();
		Function sinFunction = new Sin();
		Function cosFunction = new Cos();
		functionUsed.add(addFunction);
		functionUsed.add(cosFunction);
		functionUsed.add(minusFunction);
		functionUsed.add(mulFunction);
		functionUsed.add(devideFunction);
		functionUsed.add(sinFunction);
		geneConfiguration.setFunctionUsed(functionUsed);
		geneConfiguration.setHomeoticGeneHeaderLength(8);
		//geneConfiguration.setHomeoticGeneTailLength(9);
		geneConfiguration.setNormalGeneHeaderLength(8);
		//geneConfiguration.setNormalGeneTailLength(9);
		geneConfiguration.setNormalGeneNumber(4);
		geneConfiguration.setHomeoticGeneNumber(4);
		gepAlgConfiguration.setSelectionRange(4f);
		
		gepAlgConfiguration.setName("test");
		gepAlgConfiguration.setAccuracy(1f);
		gepAlgConfiguration.setMaxGeneration(1l);
		gepAlgConfiguration.setMaxFitness(1f);
		/***********************************************/
		
		GepAlgConfiguration expect  = new GepAlgConfiguration();
		expect.setName("test");
		expect.setAccuracy(1f);
		expect.setMaxGeneration(1l);
		expect.setMaxFitness(1f);
		expect.setOperatorConfiguration(operatorConfiguration);
		
		
		expect.setIndividualConfiguration(individualConfiguration);
		expect.setSelectionRange(4f);
		expect.getIndividualConfiguration().getGeneConfiguration().setHomeoticGeneHeaderLength(8);
		expect.getIndividualConfiguration().getGeneConfiguration().setNormalGeneHeaderLength(8);
		expect.getIndividualConfiguration().getGeneConfiguration().setNormalGeneNumber(4);
		expect.getIndividualConfiguration().getGeneConfiguration().setHomeoticGeneNumber(4);
		//expect.getIndividualConfiguration().getGeneConfiguration()
		expect.getIndividualConfiguration().getGeneConfiguration().setHomeoticGeneTailLength(geneConfiguration.getHomeoticGeneHeaderLength()*(maxArity-1)+1);
		expect.getIndividualConfiguration().getGeneConfiguration().setHomeoticGeneLength(geneConfiguration.getHomeoticGeneHeaderLength()+geneConfiguration.getHomeoticGeneTailLength());
		expect.getIndividualConfiguration().getGeneConfiguration().setNormalGeneTailLength(geneConfiguration.getNormalGeneHeaderLength()*(maxArity-1)+1);
		expect.getIndividualConfiguration().getGeneConfiguration().setNormalGeneLength(geneConfiguration.getNormalGeneHeaderLength()+geneConfiguration.getNormalGeneTailLength());
		expect.getIndividualConfiguration().setTotalGeneNumbers(geneConfiguration.getNormalGeneNumber()+geneConfiguration.getHomeoticGeneNumber());
		expect.getIndividualConfiguration().setNormalGeneTotalLength(geneConfiguration.getNormalGeneLength()*geneConfiguration.getNormalGeneNumber());
		expect.getIndividualConfiguration().setHomeoticGeneTotalLength(geneConfiguration.getHomeoticGeneLength()*geneConfiguration.getHomeoticGeneNumber());
		expect.getIndividualConfiguration().setGeneTotalLength(individualConfiguration.getHomeoticGeneTotalLength()+individualConfiguration.getNormalGeneTotalLength());
		expect.setMaxFitness(expect.getSelectionRange()*ds.getRowNum());

		
		GepAlgConfiguration result = gepConfigurationService.processConf(gepAlgConfiguration, ds);
		assertEquals(result, expect);
		
		
	}

	
	@Test
	public void testGetAvailableFunctions() {
		/**
		 * expect�У�
		 * ������ӵĺ����˳���Ӱ����Խ��
		 * ��⺯��ִ�еĽ��ص�List�У�
		 * �õ��ĺ���˳���Ǽӳ���ˣ�
		 */
		List<Function> expect = new ArrayList<Function>(4);
		Function addFunction = new Addition();
		Function minusFunction = new Minus();
		Function mulFunction = new Multiply();
		Function devideFunction = new Divide();
		Function sinFunction = new Sin();
		Function cosFunction = new Cos();
		expect.add(addFunction);
		expect.add(cosFunction);
		expect.add(devideFunction);
		expect.add(minusFunction);
		expect.add(mulFunction);
		expect.add(sinFunction);
		
		List<Function> result = gepConfigurationService.getAvailableFunctions();
		
		assertEquals(result, expect);
		
	}

}
