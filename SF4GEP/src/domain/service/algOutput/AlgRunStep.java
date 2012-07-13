package domain.service.algOutput;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
import domain.iservice.algOutput.IAlgRunStep;

public class AlgRunStep implements IAlgRunStep {

	@Override
	public GepAlgRun create(GepAlgConfiguration gepAlgConfiguration, DataSet dataSet) {
		// TODO Auto-generated method stub
		Individual individual;
		Gene addedGene;
		GeneConfiguration geneConfiguration=gepAlgConfiguration.getIndividualConfiguration().getGeneConfiguration();
		GepAlgRun gepAlgRun=new GepAlgRun();
		gepAlgRun.setCurrentGenerationNum(0);
		gepAlgRun.setDataSet(dataSet);
		gepAlgRun.setGepAlgConfiguration(gepAlgConfiguration);
		Population population=new Population();
		population.setIndividuals(new ArrayList<Individual>(gepAlgConfiguration.getIndividualConfiguration().getIndividualNumber()));
		population.setGenerationNum(0);
		population.setGepAlgRun(gepAlgRun);
		gepAlgRun.setCurrentPopulation(population);
		for(int i=0;i<gepAlgConfiguration.getIndividualConfiguration().getIndividualNumber();i++){
			individual=new Individual();
			individual.setGenes(new ArrayList<Gene>(gepAlgConfiguration.getIndividualConfiguration().getTotalGeneNumbers()));
			for(int j=0;j<geneConfiguration.getNormalGeneNumber();j++){
				addedGene=new Gene();
				addedGene.setGeneType(GeneType.NormalGene);
				addedGene.setGenePieces(new ArrayList<GenePiece>(geneConfiguration.getNormalGeneLength()));
				addedGene.getGenePieces().addAll(generateNormalHeaderPieces(geneConfiguration,dataSet));
				addedGene.getGenePieces().addAll(generateNormalTailPieces(geneConfiguration,dataSet));
				individual.getGenes().add(addedGene);
			}
			for(int j=0;j<geneConfiguration.getHomeoticGeneNumber();j++){
				addedGene=new Gene();
				addedGene.setGeneType(GeneType.HomeoticGene);
				addedGene.setGenePieces(new ArrayList<GenePiece>(geneConfiguration.getHomeoticGeneLength()));
				addedGene.getGenePieces().addAll(generateHomeoticHeaderPieces(geneConfiguration));
				addedGene.getGenePieces().addAll(generateHomeoticTailPieces(geneConfiguration));
				individual.getGenes().add(addedGene);
			}
			population.addIndividual(individual);
		}
		return gepAlgRun;
	}

	@Override
	public List<FittedValue> calculateFittedValue(Individual individual, DataSet dataSet) {
		// TODO Auto-generated method stub
		
		return null;
	}
	@Override
	public List<Float> calculateFitness(Population population) {
		// TODO Auto-generated method stub
		GepAlgConfiguration gepAlgConfiguration=population.getGepAlgRun().getGepAlgConfiguration();
		GeneConfiguration geneConfiguration=gepAlgConfiguration.getIndividualConfiguration().getGeneConfiguration();
		int homeoticGeneNum=geneConfiguration.getHomeoticGeneNumber();
		DataSet dataSet=population.getGepAlgRun().getDataSet();
		List<Float> individualFitness=new ArrayList<Float>();
		float[] sumFitness=new float[homeoticGeneNum];
		float[][] individualsValues=new float[dataSet.getRowNum()][];
		float[] oneRowFitnesses;
		int bestHomeoticIndex;
		for(Individual individual:population.getIndividuals()){
			Arrays.fill(sumFitness, 0);
			for(int i=0;i<dataSet.getRowNum();i++){
				clearFunctionFlag(individual);
				individualsValues[i]=calcFittedValue(individual, dataSet.getDataRow().get(i), geneConfiguration);
				oneRowFitnesses=calcFitness(individualsValues[i], dataSet.getDataRow().get(i).getResultColumn(),gepAlgConfiguration);
				sumFitness=addToSumFitness(sumFitness, oneRowFitnesses);
			}
			bestHomeoticIndex=findBestHomeoticIndex(sumFitness);
			setIndividual(bestHomeoticIndex, individual, individualsValues, sumFitness,dataSet);
			individualFitness.add(individual.getFitness());
		}
		return individualFitness;
	}

	@Override
	public Population select(GepAlgRun gepAlgRun) {
		// TODO Auto-generated method stub
		Population lastPopulation=gepAlgRun.getCurrentPopulation();
		float sumFitness=addFitness(lastPopulation);
		List<Float> probability=calculateProbability(lastPopulation, sumFitness);
		calculateCumulative(probability);
		Population newPopulation=createNewPopulation(lastPopulation, probability);
		return newPopulation;
	}

	@Override
	public boolean mutate(Population population) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTransport(Population population) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean risTransport(Population population) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean geneTransport(Population population) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onePointRecombine(Population population) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean twoPointRecombine(Population population) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean geneRecombine(Population population) {
		// TODO Auto-generated method stub
		return false;
	}
	private List<GenePiece> generateNormalHeaderPieces(GeneConfiguration geneConfiguration,DataSet dataSet){
		List<GenePiece> genePieces=new ArrayList<GenePiece>();
		Random functionRandom=new Random();
		Random variableRandom=new Random();
		Random typeRandom=new Random();
		DataColumn dataColumn;
		Function function;
		int type;
		GenePiece addedGenePiece;
		for(int i=0;i<geneConfiguration.getNormalGeneHeaderLength();i++){
			type=typeRandom.nextInt(dataSet.getVariableUsed().size()+geneConfiguration.getFunctionUsed().size());
			addedGenePiece=new GenePiece();
			if(type<dataSet.getVariableUsed().size()){
				addedGenePiece.setGenePieceType(GenePieceType.Variable);
				dataColumn=dataSet.getVariableUsed().get(variableRandom.nextInt(dataSet.getVariableUsed().size()));
				addedGenePiece.setName(dataColumn.getColumnName());
				addedGenePiece.setSymbol(dataColumn.getColumnName());
			}
			else {
				addedGenePiece.setGenePieceType(GenePieceType.Function);
				function=geneConfiguration.getFunctionUsed().get(functionRandom.nextInt(geneConfiguration.getFunctionUsed().size())).clone();
				addedGenePiece.setFunction(function);
				addedGenePiece.setName(function.getName());
				addedGenePiece.setSymbol(function.getSymbol());
			}
			genePieces.add(addedGenePiece);
		}
		return genePieces;
	}
	private List<GenePiece> generateNormalTailPieces(GeneConfiguration geneConfiguration,DataSet dataSet){
		List<GenePiece> genePieces=new ArrayList<GenePiece>();
		Random variableRandom=new Random();
		DataColumn dataColumn;
		GenePiece addedGenePiece;
		for(int i=0;i<geneConfiguration.getNormalGeneTailLength();i++){
			addedGenePiece=new GenePiece();
			addedGenePiece.setGenePieceType(GenePieceType.Variable);
			dataColumn=dataSet.getVariableUsed().get(variableRandom.nextInt(dataSet.getVariableUsed().size()));
			addedGenePiece.setName(dataColumn.getColumnName());
			addedGenePiece.setSymbol(dataColumn.getColumnName());
			genePieces.add(addedGenePiece);
		}
		return genePieces;
	}
	private List<GenePiece> generateHomeoticHeaderPieces(GeneConfiguration geneConfiguration){
		List<GenePiece> genePieces=new ArrayList<GenePiece>();
		Random functionRandom=new Random();
		Random constantRandom=new Random();
		Random typeRandom=new Random();
		Function function;
		int type;
		GenePiece addedGenePiece;
		addedGenePiece=new GenePiece();
		addedGenePiece.setGenePieceType(GenePieceType.Function);
		function=geneConfiguration.getFunctionUsed().get(functionRandom.nextInt(geneConfiguration.getFunctionUsed().size())).clone();
		addedGenePiece.setFunction(function);
		addedGenePiece.setName(function.getName());
		addedGenePiece.setSymbol(function.getSymbol());
		genePieces.add(addedGenePiece);
		for(int i=1;i<geneConfiguration.getHomeoticGeneHeaderLength();i++){
			addedGenePiece=new GenePiece();
			type=typeRandom.nextInt(geneConfiguration.getFunctionUsed().size()+geneConfiguration.getNormalGeneNumber());
			if(type<geneConfiguration.getFunctionUsed().size()){
				addedGenePiece.setGenePieceType(GenePieceType.Function);
				function=geneConfiguration.getFunctionUsed().get(functionRandom.nextInt(geneConfiguration.getFunctionUsed().size())).clone();
				addedGenePiece.setFunction(function);
				addedGenePiece.setName(function.getName());
				addedGenePiece.setSymbol(function.getSymbol());
			}
			else {
				addedGenePiece.setGenePieceType(GenePieceType.Constant);
				addedGenePiece.setValue((float) constantRandom.nextInt(geneConfiguration.getFunctionUsed().size()));
				addedGenePiece.setName("");
				addedGenePiece.setSymbol(addedGenePiece.getValue().toString());
			}
			genePieces.add(addedGenePiece);
		}
		return genePieces;
	}
	private List<GenePiece> generateHomeoticTailPieces(GeneConfiguration geneConfiguration){
		List<GenePiece> genePieces=new ArrayList<GenePiece>();
		Random constantRandom=new Random();
		GenePiece addedGenePiece;
		
		for(int i=0;i<geneConfiguration.getHomeoticGeneTailLength();i++){
			addedGenePiece=new GenePiece();
			addedGenePiece.setGenePieceType(GenePieceType.Constant);
			addedGenePiece.setValue((float) constantRandom.nextInt(geneConfiguration.getFunctionUsed().size()));
			addedGenePiece.setName("");
			addedGenePiece.setSymbol(addedGenePiece.getValue().toString());
			genePieces.add(addedGenePiece);
		}
		return genePieces;
	}
	/**
	 * 因为每一个个体有多个同源基因，因此每一个同源基因都可以产生一个拟合值，这里返回一个包含个体数组，里面每个同源基因计算出的拟合值
	 * @param individual 待计算的个体
	 * @param row 需要产生拟合值的行
	 * @return 拟合值数组
	 */
	private float [] calcFittedValue(Individual individual,DataRow row,GeneConfiguration geneConfiguration){
		int normalGeneNum=geneConfiguration.getNormalGeneNumber();
		int homeoticGeneNum=geneConfiguration.getHomeoticGeneNumber();
		for(int i=0;i<normalGeneNum;i++){
			assignValueToVariable(individual.getGenes().get(i), row);
			calculateGeneValue(individual.getGenes().get(i), individual);
		}
		float result;
		float[] resulList=new float[homeoticGeneNum];
		for(int i=0;i<homeoticGeneNum;i++){
			result=calculateGeneValue(individual.getGenes().get(i+normalGeneNum), individual);
			resulList[i]=result;
		}
		return resulList;
	}
	/**
	 * 本方法给基因中的每一个变量赋值
	 * @param gene 待赋值的基因
	 * @param row 一个保存着变量值的输入行
	 */
	private void assignValueToVariable(Gene gene, DataRow row) {
		// TODO Auto-generated method stub
		GenePiece genePiece;
		DataColumn dataColumn;
		int geneLength=gene.getGenePieces().size();
		int columns=row.getDataColumns().size();
		List<GenePiece> genePieces=gene.getGenePieces();
		List<DataColumn> dataColumns=row.getDataColumns();
		for(int i=0;i<geneLength;i++){
			if((genePiece=genePieces.get(i)).getGenePieceType()==GenePieceType.Variable){
				for(int j=0;j<columns;j++)
					if((dataColumn=dataColumns.get(j)).getColumnName().equals(genePiece.getSymbol())){
						genePiece.setValue(dataColumn.getValue());
						break;
					}
			}
		}
	}
	/**
	 * 计算给定基因的值，这个基因可能是普通基因，也可能是同源基因，并将结果保存到到基因对应的属性里面
	 * @param gene 待计算的基因
	 * @param individual 基因所属的个体
	 * @return 基因的值，这个结果同样也会被保存到基因对应的属性里面
	 */
	private float calculateGeneValue(Gene gene, Individual individual) {
		// TODO Auto-generated method stub
		int length=calcEfficientLength(gene);
		GenePiece lastNonTerminate;
		int arity;
		while(length>1){
			lastNonTerminate=findLastNonTerminate(gene, length);
			arity=lastNonTerminate.getFunction().getArity();
			execMathFunction(gene, individual,lastNonTerminate,length);
			length-=arity;
		}
		float value=gene.getGenePieces().get(0).getValue();
		gene.setValue(value);
		return value;
	}
	
	/**
	 * 这个方法计算每个基因的有效长度，前置条件是如果一个基因位上有相关联的函数,本方法只能在计算基因值之前被调用一次，不可在计算基因值过程中被调用
	 * @param gene 被计算的基因
	 * @return 基因的有效长度
	 */
	private int calcEfficientLength(Gene gene){
		int length=1;
		int arity;
		GenePiece genePiece;
		for(int i=0;i<length;i++){
			genePiece=gene.getGenePieces().get(i);
			if(genePiece.getGenePieceType()==GenePieceType.Function){
				arity=genePiece.getFunction().getArity();
				length+=arity;
			}
									
		}
		return length;
	}
	/**
	 * 找到最后一个非终结字符，即有效长度内最后一个函数，前置条件是被计算过的函数所对应的GenePiece的used要被置为true，没有被计算过的被置为false
	 * @param gene 所需要搜索的基因
	 * @param efficientLength 有效长度
	 * @return 基因有效长度内最后一个函数所在的基因位
	 */
	private GenePiece findLastNonTerminate(Gene gene,int efficientLength){
		GenePiece genePiece=null;
		for(int i=efficientLength-1;i>=0;i--){
			genePiece=gene.getGenePieces().get(i);
			if(genePiece.getGenePieceType()==GenePieceType.Function){
				if(genePiece.getFunction().isUsed()==false){
					break;
				}
			}
		}
		return genePiece;
	}
	/**
	 * 执行某个数学函数，并返回求解的结果
	 * @param gene 待执行的函数所在的基因
	 * @param individual 待执行的函数所在的个体
	 * @param computedGenePiece 待执行的函数所在基因位
	 * @param efficientLength 有效长度
	 * @return 数学函数计算出的结果
	 */
	private float execMathFunction(Gene gene,Individual individual,GenePiece computedGenePiece,int efficientLength){
		Function function=computedGenePiece.getFunction();
		int arity=function.getArity();
		float[] parameterArray=new float[arity];
		GenePiece[] genePieces=new GenePiece[arity];
		for(int i=0;i<arity;i++)
			genePieces[i]=gene.getGenePieces().get(efficientLength-i-1);
		if(gene.getGeneType()==GeneType.HomeoticGene){
			int index;
			GenePiece genePiece;
			for(int i=0;i<genePieces.length;i++){
				genePiece=genePieces[genePieces.length-1-i];
				if(genePiece.getGenePieceType()==GenePieceType.Function)
					parameterArray[i]=genePiece.getValue();
				else {
					index=(int)(float)genePiece.getValue();
					parameterArray[i]=individual.getGenes().get(index).getValue();
				}
			}		
		}
		else {
			for(int i=0;i<genePieces.length;i++)
				parameterArray[i]=genePieces[genePieces.length-1-i].getValue();
		}
		float value=function.operate(parameterArray);
		computedGenePiece.setValue(value);
		function.setUsed(true);
		return value;
	}

	/**
	 * 本方法负责从拟合值到适应值的转换，因为存在同源基因，所以一个个体对应多个拟合值，因此一个个体也对应多个适应值，这里只计算适应值矩阵的一行
	 * @param individualsValues 拟合值数组
	 * @param y 保存着函数值的列
	 * @return 得到的适应值矩阵中的某一行
	 */
	private float[] calcFitness(float[] individualsValues,DataColumn y,GepAlgConfiguration gepAlgConfiguration){
		float[] result=new float[individualsValues.length];
		float minus;
		float abs;
		for(int i=0;i<result.length;i++){
			minus=individualsValues[i]-y.getValue();
			abs=Math.abs(minus);
			if(abs<gepAlgConfiguration.getAccuracy())
				abs=0;
			result[i]=gepAlgConfiguration.getSelectionRange()-abs;
		}
		return result;
	}
	/**
	 * 本方法负责对适应值矩阵的每一列求和，最后得到一个行矩阵，保存这适应值矩阵中每列的和
	 * @param sumFitness 保存和的数组
	 * @param rowValue 某一行的适应值
	 * @return 求和后的数组
	 */
	private float[] addToSumFitness(float[] sumFitness,float[] rowValue){
		for(int i=0;i<sumFitness.length;i++)
			sumFitness[i]=rowValue[i]+sumFitness[i];
		return sumFitness;
	}
	/**
	 * 本方法负责清楚所有函数上的已使用的标记
	 * @param individual 待清除个体
	 */
	private void clearFunctionFlag(Individual individual){
		int geneNum=individual.getGenes().size();
		List<Gene> genes=individual.getGenes();
		List<GenePiece> genePieces;
		Gene gene;
		GenePiece genePiece;
		int geneLength;
		for(int i=0;i<geneNum;i++){
			gene=genes.get(i);
			genePieces=gene.getGenePieces();
			geneLength=genePieces.size();
			for(int j=0;j<geneLength;j++)
				if((genePiece=genePieces.get(j)).getGenePieceType()==GenePieceType.Function)
					genePiece.getFunction().setUsed(false);
		}

	}
	/**
	 * 本方法求指定适应度矩阵行的最大值，并返回最大值所在列的索引
	 * @param sumFitness
	 * @return 最大适应值所在的列，若所有适应值均不合法，则返回－1
	 */
	private int findBestHomeoticIndex(float[] sumFitness){
		int k=-1;
		float value=-1;
		float fitness;
		for(int i=0;i<sumFitness.length;i++){
			fitness=sumFitness[i];
			if(Float.isInfinite(fitness)||Float.isInfinite(fitness)||fitness<=0)
				continue;
			else if(fitness>value){
				value=fitness;
				k=i;
			}		
		}

		return k;
	}
	/**
	 * 本方法将设置个体被选择的同源基因，个体的适应值，个体的拟合值
	 * @param index 最佳适应值所在的列
	 * @param individual 待设置个体
	 * @param individualVaule 拟合值矩阵
	 * @param fitness 适应值行
	 * @param dataSet 输入数据集
	 * @return
	 */
	private Individual setIndividual(int index,Individual individual,float[][] individualVaule,float[] fitness,DataSet dataSet){
		if(index==-1){
			individual.setFitness((float) 0);
			individual.setSelectedHomeoticGeneNumber(-1);
			individual.setFittedValues(null);
		}
		else {
			individual.setFitness(fitness[index]);
			individual.setSelectedHomeoticGeneNumber(index);
			List<FittedValue> fittedValues=new ArrayList<FittedValue>(individualVaule.length);
			FittedValue fittedValue;
			for(int i=0;i<individualVaule.length;i++){
				fittedValue=new FittedValue();
				fittedValue.setFittedValue(individualVaule[i][index]);
				fittedValue.setDataRow(dataSet.getDataRow().get(i));
				fittedValues.add(fittedValue);
			}
			individual.setFittedValues(fittedValues);
		}
		return individual;
	}
	private float addFitness(Population population){
		float sum=0;
		for(Individual individual:population.getIndividuals())
			sum+=individual.getFitness();
		return sum;
	}
	private List<Float> calculateProbability(Population population,float sum){
		float divide;
		List<Float> floatList=new ArrayList<Float>(population.getIndividuals().size());
		for(Individual individual:population.getIndividuals()){
			divide=individual.getFitness()/sum;
			floatList.add(divide);
		}
		return floatList;
	}
	private List<Float> calculateCumulative(List<Float> probability){
		float sum=0;
		for(int i=0;i<probability.size();i++){
			sum+=probability.get(i);
			probability.set(i, sum);
		}
		return probability;
	}
	private Population createNewPopulation(Population original,List<Float> cumulativeProbability){
		Population resultPopulation=new Population(original.getIndividuals().size());
		Individual bestIndividual=original.getBestIndividual().clone();
		resultPopulation.addIndividual(bestIndividual);
		Random random=new Random();
		int position;
		Individual insertedIndividual;
		for(int i=1;i<cumulativeProbability.size();i++){
			position=search(cumulativeProbability, random.nextFloat());
			insertedIndividual=original.getIndividuals().get(position).clone();
			resultPopulation.addIndividual(insertedIndividual);
		}
		resultPopulation.setGepAlgRun(original.getGepAlgRun());
		return resultPopulation;
	}
	private int search(List<Float> floats,float random){
		for(int i=0;i<floats.size();i++){
			if(random<=floats.get(i))
				return i;
		}
		return floats.size()-1;
	}
}
