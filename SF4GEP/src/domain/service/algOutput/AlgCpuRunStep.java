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
import domain.core.algconfiguration.IndividualConfiguration;
import domain.core.algconfiguration.OperatorConfiguration;
import domain.iservice.algOutput.IAlgRunStep;
import domain.iservice.algOutput.IGenerator;

public class AlgCpuRunStep implements IAlgRunStep {
	private IGenerator generator;
	enum TransportEnum{
		IS,RIS,GENE;
		float rate;
		Integer[] transportElement;
		public void setRate(float rate){
			this.rate=rate;
		}
		public float getRate(){
			return rate;
		}
		public void setTransportElement(Integer[] transportElement){
			this.transportElement=transportElement;
		}
		public Integer[] getTransportElement(){
			return transportElement;
		}
	}
	enum Recombine{
		OnePoint,TwoPoint,GENE;
		float rate;
		public void setRate(float rate){
			this.rate=rate;
		}
		public float getRate(){
			return rate;
		}
	}
	@Override
	public GepAlgRun create(GepAlgConfiguration gepAlgConfiguration, DataSet dataSet) {
		// TODO Auto-generated method stub
		Individual individual;
		Gene addedGene;
		Population population;
		GeneConfiguration geneConfiguration=gepAlgConfiguration.getIndividualConfiguration().getGeneConfiguration();
		int dataColumns=dataSet.getVariableUsed().size();
		GepAlgRun gepAlgRun=generateGepAlgRun(dataSet, gepAlgConfiguration);
		population=gepAlgRun.getCurrentPopulation();
		generator=new Generator(geneConfiguration.getFunctionUsed(), dataSet.getVariableUsed(), geneConfiguration.getNormalGeneNumber());
		for(int i=0;i<gepAlgConfiguration.getIndividualConfiguration().getIndividualNumber();i++){
			individual=new Individual();
			individual.setGenes(new ArrayList<Gene>(gepAlgConfiguration.getIndividualConfiguration().getTotalGeneNumbers()));
			for(int j=0;j<geneConfiguration.getNormalGeneNumber();j++){
				addedGene=nextGene(GeneType.NormalGene, geneConfiguration, dataColumns);
				individual.getGenes().add(addedGene);
			}
			for(int j=0;j<geneConfiguration.getHomeoticGeneNumber();j++){
				addedGene=nextGene(GeneType.HomeoticGene, geneConfiguration, dataColumns);
				individual.getGenes().add(addedGene);
			}
			population.addIndividual(individual);
		}
		return gepAlgRun;
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
				individualsValues[i]=calcFittedValue(individual, dataSet.getDataRows().get(i), geneConfiguration);
				oneRowFitnesses=calcFitness(individualsValues[i], dataSet.getDataRows().get(i).getResultColumn(),gepAlgConfiguration);
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
		GeneConfiguration geneConfiguration=population.getGepAlgRun().getGepAlgConfiguration().getIndividualConfiguration().getGeneConfiguration();
		int functionListSize=geneConfiguration.getFunctionUsed().size();
		int maxNormalGeneType=functionListSize+population.getGepAlgRun().getDataSet().getVariableUsed().size();
		int maxHomeoticGeneType=functionListSize+geneConfiguration.getNormalGeneNumber();
		float mutateRate=population.getGepAlgRun().getGepAlgConfiguration().getOperatorConfiguration().getMutateRate();
		GenePiece mutatedGenePiece;
		Random mutateRandom=new Random();
		for(Individual mutatingIndividual:population.getIndividuals()){
			for(Gene gene:mutatingIndividual.getGenes()){
				if(gene.getGeneType()==GeneType.NormalGene){
					for(int i=0;i<geneConfiguration.getNormalGeneHeaderLength();i++){
						if(mutateRandom.nextFloat()<mutateRate){
							mutatedGenePiece=nextGenePiece(GeneType.NormalGene, true, maxNormalGeneType, functionListSize);
							gene.getGenePieces().set(i, mutatedGenePiece);
						}
					}
					for(int i=0;i<geneConfiguration.getNormalGeneTailLength();i++){
						if(mutateRandom.nextFloat()<mutateRate){
							mutatedGenePiece=nextGenePiece(GeneType.NormalGene, false, maxNormalGeneType, functionListSize);
							gene.getGenePieces().set(i+geneConfiguration.getNormalGeneHeaderLength(),mutatedGenePiece);
						}
					}
				}
				else if(gene.getGeneType()==GeneType.HomeoticGene) {
					if(geneConfiguration.getUseHomeoticGene()){
						if(mutateRandom.nextFloat()<mutateRate){
							mutatedGenePiece=nextGenePiece(GeneType.HomeoticGene, true, maxHomeoticGeneType, functionListSize);
							gene.getGenePieces().set(0, mutatedGenePiece);
						}
						for(int i=1;i<geneConfiguration.getHomeoticGeneHeaderLength();i++){
							if(geneConfiguration.getUseHomeoticGene()){
								mutatedGenePiece=nextGenePiece(GeneType.HomeoticGene, true, maxHomeoticGeneType, functionListSize);
								gene.getGenePieces().set(i, mutatedGenePiece);
							}
						}
						for(int i=0;i<geneConfiguration.getHomeoticGeneTailLength();i++){
							if(geneConfiguration.getUseHomeoticGene()){
								mutatedGenePiece=nextGenePiece(GeneType.HomeoticGene, false, maxHomeoticGeneType, functionListSize);
								gene.getGenePieces().set(i+geneConfiguration.getHomeoticGeneHeaderLength(), mutatedGenePiece);
							}
						}
					}
				}
			}
		}
		return true;
	}

	@Override
	public boolean isTransport(Population population) {
		// TODO Auto-generated method stub
		OperatorConfiguration operatorConfiguration=population.getGepAlgRun().getGepAlgConfiguration().getOperatorConfiguration();
		TransportEnum isTransportEnum=TransportEnum.IS;
		isTransportEnum.setRate(operatorConfiguration.getIsTransportRate());
		isTransportEnum.setTransportElement(operatorConfiguration.getIsElement());
		iterateGeneInTransport(population, isTransportEnum);
		return true;
	}

	@Override
	public boolean risTransport(Population population) {
		// TODO Auto-generated method stub
		OperatorConfiguration operatorConfiguration=population.getGepAlgRun().getGepAlgConfiguration().getOperatorConfiguration();
		TransportEnum risTransportEnum=TransportEnum.RIS;
		risTransportEnum.setRate(operatorConfiguration.getRisTransportRate());
		risTransportEnum.setTransportElement(operatorConfiguration.getRisElement());
		iterateGeneInTransport(population, risTransportEnum);
		return true;
	}

	@Override
	public boolean geneTransport(Population population) {
		// TODO Auto-generated method stub
		OperatorConfiguration operatorConfiguration=population.getGepAlgRun().getGepAlgConfiguration().getOperatorConfiguration();
		TransportEnum geneTransportEnum=TransportEnum.GENE;
		geneTransportEnum.setRate(operatorConfiguration.getGeneTransportRate());
		iterateGeneInTransport(population, geneTransportEnum);
		return true;
	}

	@Override
	public boolean onePointRecombine(Population population) {
		// TODO Auto-generated method stub
		OperatorConfiguration operatorConfiguration=population.getGepAlgRun().getGepAlgConfiguration().getOperatorConfiguration();
		Recombine recombine=Recombine.OnePoint;
		recombine.setRate(operatorConfiguration.getOnePointRecombineRate());
		iterateGeneInRecombine(population.getGepAlgRun().getGepAlgConfiguration().getIndividualConfiguration(), population, recombine);
		return true;
	}

	@Override
	public boolean twoPointRecombine(Population population) {
		// TODO Auto-generated method stub
		OperatorConfiguration operatorConfiguration=population.getGepAlgRun().getGepAlgConfiguration().getOperatorConfiguration();
		Recombine recombine=Recombine.TwoPoint;
		recombine.setRate(operatorConfiguration.getTwoPointRecombineRate());
		iterateGeneInRecombine(population.getGepAlgRun().getGepAlgConfiguration().getIndividualConfiguration(), population, recombine);
		return true;
	}

	@Override
	public boolean geneRecombine(Population population) {
		// TODO Auto-generated method stub
		OperatorConfiguration operatorConfiguration=population.getGepAlgRun().getGepAlgConfiguration().getOperatorConfiguration();
		Recombine recombine=Recombine.GENE;
		recombine.setRate(operatorConfiguration.getGeneRecombineRate());
		iterateGeneInRecombine(population.getGepAlgRun().getGepAlgConfiguration().getIndividualConfiguration(), population, recombine);
		return true;
	}
	private GepAlgRun generateGepAlgRun(DataSet dataSet,GepAlgConfiguration gepAlgConfiguration){
		GepAlgRun gepAlgRun=new GepAlgRun();
		gepAlgRun.setDataSet(dataSet);
		gepAlgRun.setGepAlgConfiguration(gepAlgConfiguration);
		Population population=new Population();
		population.setIndividuals(new ArrayList<Individual>(gepAlgConfiguration.getIndividualConfiguration().getIndividualNumber()));
		population.setGenerationNum((long) 0);
		population.setGepAlgRun(gepAlgRun);
		gepAlgRun.getPopulations().add(population);
		return gepAlgRun;
	}
	private Gene nextGene(GeneType geneType,GeneConfiguration geneConfiguration,int dataColumns){
		int geneLength;
		int headerLength;
		int tailLength;
		int maxType;
		GenePiece genePiece=null;
		int j;
		if(geneType==GeneType.NormalGene){
			geneLength=geneConfiguration.getNormalGeneLength();
			headerLength=geneConfiguration.getNormalGeneHeaderLength();
			tailLength=geneConfiguration.getNormalGeneTailLength();
			maxType=dataColumns+geneConfiguration.getFunctionUsed().size();
			j=0;
		}
		else {
			geneLength=geneConfiguration.getHomeoticGeneLength();
			headerLength=geneConfiguration.getNormalGeneHeaderLength();
			tailLength=geneConfiguration.getNormalGeneTailLength();
			maxType=geneConfiguration.getFunctionUsed().size()+geneConfiguration.getNormalGeneNumber();
			j=1;
			genePiece=generator.nextFunction();
		}
		Gene result=new Gene();
		result.setGeneType(geneType);
		List<GenePiece> genePiecesList=new ArrayList<GenePiece>(geneLength);
		if(genePiece!=null)
			genePiecesList.add(genePiece);
		for(;j<headerLength;j++){
			genePiece=nextGenePiece(geneType, true, maxType,geneConfiguration.getFunctionUsed().size());
			genePiecesList.add(genePiece);				
		}
		for(j=0;j<tailLength;j++){
			genePiece=nextGenePiece(geneType, false, maxType,geneConfiguration.getFunctionUsed().size());
			genePiecesList.add(genePiece);			
		}
		result.setGenePieces(genePiecesList);
		return result;
	}
	private GenePiece nextGenePiece(GeneType geneType, boolean header,int maxType,int functionListSize) {
		// TODO Auto-generated method stub
		Random typeRandom=new Random();
		GenePiece genePiece;
		int type;
		if(geneType==GeneType.NormalGene){
			if(header){
				type=typeRandom.nextInt(maxType);
				if(type<functionListSize)
					genePiece=generator.nextFunction();
				else
					genePiece=generator.nextVariable();
			}
			else {
				genePiece=generator.nextVariable();
			}
		}
		else {
			if(header){
				type=typeRandom.nextInt(maxType);
				if(type<functionListSize)
					genePiece=generator.nextFunction();
				else
					genePiece=generator.nextNoramlGeneNum();
			}
			else {
				genePiece=generator.nextNoramlGeneNum();
			}
		}
		return genePiece;
	}
	/**
	 * ��Ϊÿһ�������ж��ͬԴ�������ÿһ��ͬԴ���򶼿��Բ���һ�����ֵ�����ﷵ��һ�������������飬����ÿ��ͬԴ�������������ֵ��
	 * Ŀǰ������ƿ��������ʱ����15%��20%
	 * @param individual ������ĸ���
	 * @param row ��Ҫ�������ֵ����
	 * @return ���ֵ����
	 */
	private float [] calcFittedValue(Individual individual,DataRow row,GeneConfiguration geneConfiguration){
		int normalGeneNum=geneConfiguration.getNormalGeneNumber();
		int homeoticGeneNum=geneConfiguration.getHomeoticGeneNumber();
		for(int i=0;i<normalGeneNum;i++){
			assignValueToVariable(individual.getGenes().get(i), row);
			calculateGeneValue(individual.getGenes().get(i), individual);
		}
		float result;
		float[] resultList=new float[homeoticGeneNum];
		if(geneConfiguration.getNormalGeneNumber()==1&&geneConfiguration.getHomeoticGeneNumber()==1){
			resultList[0]=individual.getGenes().get(0).getValue();
		}
		else {
			for(int i=0;i<homeoticGeneNum;i++){
				result=calculateGeneValue(individual.getGenes().get(i+normalGeneNum), individual);
				resultList[i]=result;		
			}			
		}
		return resultList;
	}
	
	
	/**
	 * �������������е���Ч�����ڵ�ÿһ��������ֵ
	 * @param gene ����ֵ�Ļ���
	 * @param row һ�������ű���ֵ��������
	 */
	private void assignValueToVariable(Gene gene, DataRow row) {
		// TODO Auto-generated method stub
		GenePiece genePiece;
		int effectiveLength=gene.getEffictiveLength();
		List<GenePiece> genePieces=gene.getGenePieces();
		List<DataColumn> dataColumns=row.getDataColumns();
		for(int i=0;i<effectiveLength;i++){
			if((genePiece=genePieces.get(i)).getGenePieceType()==GenePieceType.Variable){
				genePiece.setValue(dataColumns.get(genePiece.getVariableIndex()).getValue());
			}
		}
	}
	/**
	 * ������������ֵ����������������ͨ����Ҳ������ͬԴ���򣬲���������浽�������Ӧ����������
	 * @param gene ������Ļ���
	 * @param individual ���������ĸ���
	 * @return �����ֵ��������ͬ��Ҳ�ᱻ���浽�����Ӧ����������
	 */
	private float calculateGeneValue(Gene gene, Individual individual) {
		// TODO Auto-generated method stub
		int length=gene.getEffictiveLength();
		GenePiece lastNonTerminate;
		int arity;
		while(length>1){
			lastNonTerminate=gene.getGenePieces().get(gene.getLastNonTerminate(length));
			arity=lastNonTerminate.getFunc().getArity();
			execMathFunction(gene, individual,lastNonTerminate,length);
			length-=arity;
		}
		float value=gene.getGenePieces().get(0).getValue();
		gene.setValue(value);
		return value;
	}
	/**
	 * ִ��ĳ����ѧ���������������Ľ��
	 * @param gene ��ִ�еĺ������ڵĻ���
	 * @param individual ��ִ�еĺ������ڵĸ���
	 * @param computedGenePiece ��ִ�еĺ������ڻ���λ
	 * @param efficientLength ��Ч����
	 * @return ��ѧ����������Ľ��
	 */
	private float execMathFunction(Gene gene,Individual individual,GenePiece computedGenePiece,int efficientLength){
		Function function=computedGenePiece.getFunc();
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
		computedGenePiece.setUsed(true);
		return value;
	}

	/**
	 * ��������������ֵ����Ӧֵ��ת������Ϊ����ͬԴ��������һ�������Ӧ������ֵ�����һ������Ҳ��Ӧ�����Ӧֵ������ֻ������Ӧֵ�����һ��
	 * @param individualsValues ���ֵ����
	 * @param y �����ź���ֵ����
	 * @return �õ�����Ӧֵ�����е�ĳһ��
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
	 * �������������Ӧֵ�����ÿһ����ͣ����õ�һ���о��󣬱�������Ӧֵ������ÿ�еĺ�
	 * @param sumFitness ����͵�����
	 * @param rowValue ĳһ�е���Ӧֵ
	 * @return ��ͺ������
	 */
	private float[] addToSumFitness(float[] sumFitness,float[] rowValue){
		for(int i=0;i<sumFitness.length;i++)
			sumFitness[i]=rowValue[i]+sumFitness[i];
		return sumFitness;
	}
	/**
	 * ����������������к����ϵ���ʹ�õı��
	 * @param individual ���������
	 */
	private void clearFunctionFlag(Individual individual){
		int geneNum=individual.getGenes().size();
		List<Gene> genes=individual.getGenes();
		Gene gene;
		for(int i=0;i<geneNum;i++){
			gene=genes.get(i);
			gene.clearFunctionFlag(false);
		}
	}
	/**
	 * ��������ָ����Ӧ�Ⱦ����е����ֵ�����������ֵ�����е�����
	 * @param sumFitness
	 * @return �����Ӧֵ���ڵ��У���������Ӧֵ�����Ϸ����򷵻أ�1
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
	 * �����������ø��屻ѡ���ͬԴ���򣬸������Ӧֵ����������ֵ
	 * @param index �����Ӧֵ���ڵ���
	 * @param individual �����ø���
	 * @param individualVaule ���ֵ����
	 * @param fitness ��Ӧֵ��
	 * @param dataSet �������ݼ�
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
				fittedValue.setDataRow(dataSet.getDataRows().get(i));
				fittedValues.add(fittedValue);
			}
			individual.setFittedValues(fittedValues);
		}
		return individual;
	}
	/**
	 * ������Ⱥ������Ӧֵ
	 * @param population ���������Ⱥ
	 * @return ��Ⱥ����Ӧֵ
	 */
	private float addFitness(Population population){
		float sum=0;
		for(Individual individual:population.getIndividuals())
			sum+=individual.getFitness();
		return sum;
	}
	/**
	 * ������������Ӧֵ����ÿ����������Ⱥ��Ӧֵ����ռ�ı���
	 * @param population ��������Ⱥ
	 * @param sum ��Ⱥ��Ӧֵ֮��
	 * @return �����и��������Ӧֵ��List
	 */
	private List<Float> calculateProbability(Population population,float sum){
		float divide;
		List<Float> floatList=new ArrayList<Float>(population.getIndividuals().size());
		for(Individual individual:population.getIndividuals()){
			divide=individual.getFitness()/sum;
			floatList.add(divide);
		}
		return floatList;
	}
	/**
	 * ��������ۼ���Ӧֵ������n����ۼ���Ӧֵ����ǰn����������Ӧֵ֮��
	 * @param probability ���������Ӧֵ֮��
	 * @return �����ۼ���Ӧֵ
	 */
	private List<Float> calculateCumulative(List<Float> probability){
		float sum=0;
		for(int i=0;i<probability.size();i++){
			sum+=probability.get(i);
			probability.set(i, sum);
		}
		return probability;
	}
	/**
	 * ����һ���µ���Ⱥ��ʹ�����̶�+�ھ�����
	 * @param original ԭʼ��Ⱥ
	 * @param cumulativeProbability �����ۼ���Ӧֵ
	 * @return �µ���Ⱥ
	 */
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
	/**
	 * �������ۼ���Ӧֵ�У�ĳ��С��ֵ���������ĸ���Χ�����ط�Χ�����ޣ���ʹ�������ޣ�
	 * @param floats �����ۼ���Ӧֵ
	 * @param random ����ѯ��С��
	 * @return �ۼ���Ӧֵ������ĸ����index
	 */
	private int search(List<Float> floats,float random){
		for(int i=0;i<floats.size();i++){
			if(random<=floats.get(i))
				return i;
		}
		return floats.size()-1;
	}
	/**
	 * ����ÿ�����򣬾����Ƿ����ת�����������ת�����������һ������
	 * @param population Ҫ����ת������Ⱥ
	 * @param transportEnum ת��ö����
	 */
	private void iterateGeneInTransport(Population population,TransportEnum transportEnum){
		Random transportRandom=new Random();
		GeneConfiguration geneConfiguration=population.getGepAlgRun().getGepAlgConfiguration().getIndividualConfiguration().getGeneConfiguration();
		int headerLength;
		int tailLength;
		Gene gene;
		for(Individual individual:population.getIndividuals()){
			for(int i=0;i<individual.getGenes().size();i++){
				gene=individual.getGenes().get(i);
				if(transportRandom.nextFloat()<transportEnum.getRate()){
					if(transportEnum==TransportEnum.GENE){
						if(gene.getGeneType()==GeneType.HomeoticGene)
							break;
						else {
							transportParaDetermination(individual.getGenes(), i);
							break;
						}
					}
					else {
						if(gene.getGeneType()==GeneType.NormalGene){
							headerLength=geneConfiguration.getNormalGeneHeaderLength();
							tailLength=geneConfiguration.getNormalGeneTailLength();
						}
						else {
							if(geneConfiguration.getUseHomeoticGene()){
								headerLength=geneConfiguration.getHomeoticGeneHeaderLength();
								tailLength=geneConfiguration.getHomeoticGeneTailLength();
							}
							else {
								break;
							}
						}
						transportParaDetermination(gene, transportEnum, headerLength, tailLength);
						break;
					}
				}			
			}
		}
	}
	/**
	 * ��ͨ����Ļ���ת��������ָ���������������λ����Ϊ��һ������
	 * @param genes ���������еĻ���
	 * @param sourceIndex ��Ҫ����ͷ���Ļ���
	 */
	private void transportParaDetermination(List<Gene> genes,int sourceIndex){
		Gene gene=genes.remove(sourceIndex);
		genes.add(0, gene);
	}
	/**
	 * ȷ��IS��RISת����صĲ�������ͨ�������йط�����ʼת��
	 * @param gene ��ת������
	 * @param transportEnum ת������ö����
	 * @param headerLength ת������ͷ��
	 * @param tailLength ת������β��
	 */
	private void transportParaDetermination(Gene gene,TransportEnum transportEnum,int headerLength,int tailLength){
		Random sourceLocRandom=new Random();
		Random destLocRandom=new Random();
		Random elementLengthRandom=new Random();
		int elementLength=0;
		int sourceLoc=0;
		int destLoc=0;
		switch (transportEnum) {
		case IS:
			elementLength=transportEnum.transportElement[elementLengthRandom.nextInt(transportEnum.transportElement.length)];
			sourceLoc=sourceLocRandom.nextInt(headerLength+tailLength-elementLength);
			destLoc=destLocRandom.nextInt(headerLength-elementLength-1)+1;
			break;
		case RIS:
			elementLength=transportEnum.transportElement[elementLengthRandom.nextInt(transportEnum.transportElement.length)];
			sourceLoc=searchFunction(gene, destLocRandom.nextInt(headerLength));
			destLoc=0;
			break;
		}
		if(sourceLoc!=-1){
			transportBegin(gene.getGenePieces(), sourceLoc, destLoc, elementLength, headerLength, transportEnum);
		}
	}
	/**
	 * �ڸ����Ļ�����ָ��Ϊλ�ú���Ѱ�ҵ�һ�����������ҵ��򷵻غ�����index�����Ҳ����򷵻أ�1
	 * @param gene �������Ļ���
	 * @param index ��ʼ������λ��
	 * @return ���
	 */
	private int searchFunction(Gene gene,int index){
		int result=-1;
		for(int i=index;i<gene.getGenePieces().size();i++){
			if(gene.getGenePieces().get(i).getGenePieceType()==GenePieceType.Function){
				result=i;
				break;
			}
		}
		return result;
	}
	/**
	 * ��ʼת��
	 * @param genePieces ��ת���Ļ���Ƭ��
	 * @param source ת����ʼ�ĵط�
	 * @param dest ת����Ŀ��
	 * @param length ת���ĳ���
	 * @param headerLength ����ͷ��
	 * @param transportEnum ת��ö������
	 */
	private void transportBegin(List<GenePiece> genePieces,int source,int dest,int length,int headerLength,TransportEnum transportEnum){
		List<GenePiece> copiedSource=new ArrayList<GenePiece>(length);
		for(int i=0;i<length;i++)
			copiedSource.add((genePieces.get(i+source).clone()));
		int forLength=headerLength-dest-length;
		for(int i=0;i<forLength;i++){
			genePieces.set(headerLength-i-1, genePieces.get(headerLength-i-1-length));
		}
		for(int i=0;i<length;i++){
			genePieces.set(dest+i, copiedSource.get(i));
		}
	}
	/**
	 * ������Ⱥ�е�ÿһ�����壬�������飬����������飬ÿ������ֻ���ڱ����������з���һ������
	 * @param individualConfiguration ����������Ϣ
	 * @param population ��Ⱥ
	 * @param recombine ����ö������
	 */
	private void iterateGeneInRecombine(IndividualConfiguration individualConfiguration,Population population,Recombine recombine){
		Random recombineOneRandom=new Random();
		Random recombineOtherRandom=new Random();
		for(int i=0;i<population.getIndividuals().size()-1;i++){
			if(recombineOneRandom.nextFloat()<recombine.getRate()){
				for(int j=i+1;j<population.getIndividuals().size();j++){
					if(recombineOtherRandom.nextFloat()<recombine.getRate()){
						recombineParaDetermination(population.getIndividuals().get(i), population.getIndividuals().get(j), recombine, individualConfiguration);
						break;
					}
				}
			}
		}
	}
	/**
	 * ȷ������ĸ��ֲ���
	 * @param a �μ�����ĸ���a
	 * @param b �μ�����ĸ���b
	 * @param recombine �����ö�ٱ���
	 * @param individualConfiguration ����������Ϣ
	 */
	private void recombineParaDetermination(Individual a,Individual b,Recombine recombine,IndividualConfiguration individualConfiguration){
		Random startRandom=new Random();
		Random endRandom=new Random();
		GeneConfiguration geneConfiguration=individualConfiguration.getGeneConfiguration();
		int start=-1;
		int end=-1;
		switch (recombine) {
		case OnePoint:
			end=individualConfiguration.getNormalGeneTotalLength();
			start=startRandom.nextInt(end);
			break;
		case TwoPoint:
			start=startRandom.nextInt(individualConfiguration.getNormalGeneTotalLength());
			end=endRandom.nextInt(individualConfiguration.getNormalGeneTotalLength());
			if(start>end){
				int temp=start;
				start=end;
				end=temp;
			}
			if (start==end) {
				return;
			}
			break;
		case GENE:
			int geneNo=startRandom.nextInt(geneConfiguration.getNormalGeneNumber());
			start=geneNo*geneConfiguration.getNormalGeneLength();
			end=start+geneConfiguration.getNormalGeneLength();
			break;
		}
		beginRecombine(start, end, a, b,geneConfiguration);
	}
	/**
	 * ��ʼ��ʽ��������
	 * @param start ���鿪ʼ��λ�ã����λ�����ڸ������л���λ�����index
	 * @param end ���������λ�ã����λ�����ڸ������л���λ�����index
	 * @param a �μ�����ĸ���a
	 * @param b �μ�����ĸ���b
	 * @param geneConfiguration ����������Ϣ
	 */
	private void beginRecombine(int start,int end,Individual a,Individual b,GeneConfiguration geneConfiguration){
		Gene gene;
		GenePiece genePiece;
		List<GenePiece> aGenePieces;
		List<GenePiece> bGenePieces;
		int startGeneNo=start/geneConfiguration.getNormalGeneLength();
		int startGenePieceNo=start%geneConfiguration.getNormalGeneLength();
		int endGeneNo=end/geneConfiguration.getNormalGeneLength();
		int endGenePieceNo=end%geneConfiguration.getNormalGeneLength();
		if(endGenePieceNo==0){
			endGeneNo--;
			endGenePieceNo=geneConfiguration.getNormalGeneLength();
		}
		aGenePieces=a.getGenes().get(startGeneNo).getGenePieces();
		bGenePieces=b.getGenes().get(startGeneNo).getGenePieces();
		for(int i=startGenePieceNo;i<geneConfiguration.getNormalGeneLength();i++){
			genePiece=aGenePieces.get(i);
			aGenePieces.set(i , bGenePieces.get(i));
			bGenePieces.set(i, genePiece);
		}
		if(startGeneNo!=endGeneNo){
			for(int i=0;i<endGeneNo-startGeneNo-1;i++){
				gene=a.getGenes().get(i+startGeneNo+1);
				a.getGenes().set(i+startGeneNo+1, b.getGenes().get(i+startGeneNo+1));
				b.getGenes().set(i+startGeneNo+1, gene);
			}
			aGenePieces=a.getGenes().get(endGeneNo).getGenePieces();
			bGenePieces=b.getGenes().get(endGeneNo).getGenePieces();
			for(int i=0;i<endGenePieceNo;i++){
				genePiece=aGenePieces.get(i);
				aGenePieces.set(i, bGenePieces.get(i));
				bGenePieces.set(i, genePiece);
			}			
		}
	}
}
