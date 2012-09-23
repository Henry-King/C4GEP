package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import domain.core.algInputDataProcess.DataColumn;
import domain.core.algInputDataProcess.DataRow;
import domain.core.algInputDataProcess.DataSet;
import domain.core.algOutput.FittedValue;
import domain.core.algOutput.Gene;
import domain.core.algOutput.GenePiece;
import domain.core.algOutput.GenePieceType;
import domain.core.algOutput.GeneType;
import domain.core.algOutput.GepAlgRun;
import domain.core.algOutput.Individual;
import domain.core.algOutput.Population;
import domain.core.algconfiguration.Function;
import domain.core.algconfiguration.GeneConfiguration;
import domain.core.algconfiguration.GepAlgConfiguration;
import domain.core.algconfiguration.IndividualConfiguration;
import domain.core.algconfiguration.OperatorConfiguration;
import domain.core.algconfiguration.function.Addition;
import domain.core.algconfiguration.function.Divide;
import domain.core.algconfiguration.function.Minus;
import domain.core.algconfiguration.function.Multiply;
import domain.service.algOutput.AlgCpuRunStep;

/**
 * 测试计算适应值和变异检测通过， 但是计算适应值的测试输出结果为0.0， 没有找到出错的地方，可能是数据的问题
 * 
 * 变异通过无异常
 * 
 * 其他基因修饰方法均没有通过测试， debug没有找到出错的地方， 也没有提示， 问一下你写的时候内部是否添加了什么特别的参数
 */
public class AlgRunStepTest {

	private static AlgCpuRunStep algRunStep = null;
	public Population population;

	@Before
	public void setUp() throws Exception {
		algRunStep = new AlgCpuRunStep();

		/** GepAlgConfiguration **/

		GepAlgConfiguration gepAlgConfiguration = new GepAlgConfiguration();
		gepAlgConfiguration.setId(1);
		gepAlgConfiguration.setName("gac");
		gepAlgConfiguration.setMaxGeneration(4l);
		gepAlgConfiguration.setMaxFitness(10f);
		gepAlgConfiguration.setSelectionRange(10f);
		gepAlgConfiguration.setAccuracy(4f);

		IndividualConfiguration individualConfiguration = new IndividualConfiguration();
		individualConfiguration.setId(1);
		individualConfiguration.setIndividualNumber(2);
		individualConfiguration.setTotalGeneNumbers(4);
		individualConfiguration.setNormalGeneTotalLength(18);
		individualConfiguration.setHomeoticGeneTotalLength(18);
		individualConfiguration.setGeneTotalLength(36);

		
		GeneConfiguration geneConfiguration = new GeneConfiguration();
		geneConfiguration.setUseHomeoticGene(false);
		geneConfiguration.setId(1);
		geneConfiguration.setNormalGeneNumber(1);
		geneConfiguration.setNormalGeneHeaderLength(3);
		geneConfiguration.setNormalGeneTailLength(4);
		geneConfiguration.setNormalGeneLength(7);
		geneConfiguration.setHomeoticGeneNumber(1);
		geneConfiguration.setHomeoticGeneHeaderLength(3);
		geneConfiguration.setHomeoticGeneTailLength(4);
		geneConfiguration.setHomeoticGeneLength(7);

		List<Function> functionUsed = new ArrayList<Function>(4);
		Function addFunction = new Addition();
		Function minusFunction = new Minus();
		Function mulFunction = new Multiply();
		Function devideFunction = new Divide();
		functionUsed.add(addFunction);
		functionUsed.add(minusFunction);
		functionUsed.add(mulFunction);
		functionUsed.add(devideFunction);

		geneConfiguration.setFunctionUsed(functionUsed);

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

		individualConfiguration.setGeneConfiguration(geneConfiguration);
		gepAlgConfiguration.setIndividualConfiguration(individualConfiguration);
		gepAlgConfiguration.setOperatorConfiguration(operatorConfiguration);

		/** DataSet **/
		DataSet ds = new DataSet(1, 1, "test");

		List<DataRow> dataRows = new ArrayList<DataRow>();
		DataRow dr = new DataRow();

		List<DataColumn> dataColumns = new ArrayList<DataColumn>();
		DataColumn dc = new DataColumn();
		dc.setId(1);
		dc.setColumnName("a");
		dc.setValue(1f);
		dataColumns.add(dc);

		DataColumn resultColumn = new DataColumn();
		resultColumn.setId(2);
		resultColumn.setColumnName("f(a)");
		resultColumn.setValue(2f);

		dr.setDataColumns(dataColumns);
		dr.setResultColumn(resultColumn);
		dataRows.add(dr);
		ds.setDataRows(dataRows);

		GepAlgRun gepAlgRun = new GepAlgRun();
		gepAlgRun.setId(1);
		gepAlgRun.setGepAlgConfiguration(gepAlgConfiguration);
		gepAlgRun.setDataSet(ds);

		population = new Population();
		population.setId(1);
		population.setGepAlgRun(gepAlgRun);

		List<Individual> individuals = new ArrayList<Individual>();
		Individual individual = new Individual();
		individual.setId(1);
		List<Gene> genes = new ArrayList<Gene>();

		Gene gene = new Gene();
		gene.setId(1);
		gene.setValue(2f);
		gene.setGeneType(GeneType.NormalGene);

		Gene hGene = new Gene();
		hGene.setId(1);
		hGene.setValue(2f);
		hGene.setGeneType(GeneType.HomeoticGene);

		List<GenePiece> genePieces = new ArrayList<GenePiece>();
		GenePiece gp = new GenePiece();
		gp.setId(1);
		gp.setSymbol("+");
		gp.setName("加");
		gp.setValue(12f);
		gp.setVariableIndex(1);
		gp.setGenePieceType(GenePieceType.Constant);
		gp.setFunc(new Addition());

		genePieces.add(gp);
		genePieces.add(gp);
		genePieces.add(gp);
		genePieces.add(gp);
		genePieces.add(gp);
		genePieces.add(gp);
		genePieces.add(gp);
		genePieces.add(gp);
		genePieces.add(gp);
		genePieces.add(gp);
		genePieces.add(gp);
		genePieces.add(gp);
		genePieces.add(gp);
		genePieces.add(gp);
		genePieces.add(gp);
		genePieces.add(gp);
		gene.setGenePieces(genePieces);
		hGene.setGenePieces(genePieces);
		genes.add(gene);
		genes.add(hGene);
		List<FittedValue> fittedValues = new ArrayList<FittedValue>();
		FittedValue fv = new FittedValue();
		fv.setFittedValue(10f);
		fv.setId(1);
		fv.setDataRow(dr);
		fittedValues.add(fv);

		individual.setGenes(genes);
		individual.setFittedValues(fittedValues);
		individual.setFitness(100f);
		individuals.add(individual);

		population.setIndividuals(individuals);
		population.setGenerationNum(2l);
		List<Population> populations = new ArrayList<Population>(2);
		populations.add(population);
		gepAlgRun.setPopulations(populations);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Ignore("Not yet implemented")
	@Test
	public void testCreate() {
		/**
		 * Create 返回设置了DataSet和GepAlgConfiguration后的GepAlgRun对象 疑问：
		 * 该函数的内部变量Population 有设置操作， 却没有向外部传参
		 */
		DataSet ds = new DataSet();
		GepAlgConfiguration gepAlgConfiguration = new GepAlgConfiguration();

		GepAlgRun gepAlgRun = new GepAlgRun();
		gepAlgRun.setDataSet(ds);
		gepAlgRun.setGepAlgConfiguration(gepAlgConfiguration);

	}

	@Ignore("Not yet implemented")
	@Test
	public void testCalculateFittedValue() {

	}

	@Test
	public void testCalculateFitness() {

		/**
		 * 测试计算适应值 适应值的结果每次都在变化
		 */

		List<Float> test = algRunStep.calculateFitness(population);

		for (Float float1 : test) {
			System.out.println(float1);
		}

	}

	@Ignore("Not yet implemented")
	@Test
	public void testSelect() {
		/**
		 * 无法测试
		 */
		/*
		 * Population expect = new Population(); float sumFitness = 100f;
		 * List<Float> probability = new ArrayList<Float>();
		 * probability.add(10f/sumFitness); probability.add(20f/sumFitness);
		 * probability.add(10f/sumFitness); probability.add(30f/sumFitness);
		 * probability.add(30f/sumFitness);
		 * 
		 * float sum=0; for(int i=0;i<probability.size();i++){
		 * sum+=probability.get(i); probability.set(i, sum); } Population
		 * newPopulation=createNewPopulation(lastPopulation, probability);
		 */

	}

	@Test
	public void testMutate() {
		population.getGepAlgRun().getGepAlgConfiguration()
				.getOperatorConfiguration().setMutateRate(1f);
		Population changedPopulation = population.clone();
		algRunStep.mutate(changedPopulation);
		//assertFalse(population.equals(changedPopulation));
		assertEquals(population, changedPopulation);
	}

	@Test
	public void testIsTransport() {
		population.getGepAlgRun().getGepAlgConfiguration()
				.getOperatorConfiguration().setIsTransportRate(1f);
		Population changedPopulation = population.clone();
		algRunStep.isTransport(changedPopulation);
		assertEquals(population, changedPopulation);
	}

	@Test
	public void testRisTransport() {
		population.getGepAlgRun().getGepAlgConfiguration()
				.getOperatorConfiguration().setRisTransportRate(1f);
		Population changedPopulation = population.clone();
		algRunStep.risTransport(changedPopulation);
		assertEquals(population, changedPopulation);
	}

	@Test
	public void testGeneTransport() {
		population.getGepAlgRun().getGepAlgConfiguration()
				.getOperatorConfiguration().setGeneTransportRate(1f);
		Population changedPopulation = population.clone();
		algRunStep.geneTransport(changedPopulation);
		assertEquals(population, changedPopulation);
	}

	@Test
	public void testOnePointRecombine() {
		population.getGepAlgRun().getGepAlgConfiguration()
				.getOperatorConfiguration().setOnePointRecombineRate(1f);
		Population changedPopulation = population.clone();
		algRunStep.onePointRecombine(changedPopulation);
		assertEquals(population, changedPopulation);
	}

	@Test
	public void testTwoPointRecombine() {
		population.getGepAlgRun().getGepAlgConfiguration()
				.getOperatorConfiguration().setTwoPointRecombineRate(1f);
		Population changedPopulation = population.clone();
		algRunStep.twoPointRecombine(changedPopulation);
		assertEquals(population, changedPopulation);
	}

	@Test
	public void testGeneRecombine() {
		population.getGepAlgRun().getGepAlgConfiguration()
				.getOperatorConfiguration().setGeneRecombineRate(1f);
		Population changedPopulation = population.clone();
		algRunStep.geneRecombine(changedPopulation);
		assertEquals(population, changedPopulation);
	}

}
