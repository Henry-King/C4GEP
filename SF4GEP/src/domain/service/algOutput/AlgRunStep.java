package domain.service.algOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import domain.core.algInputDataProcess.DataColumn;
import domain.core.algInputDataProcess.DataSet;
import domain.core.algOutput.FittedValue;
import domain.core.algOutput.Gene;
import domain.core.algOutput.GenePiece;
import domain.core.algOutput.GenePieceType;
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
		int j;
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
			for(j=0;j<geneConfiguration.getNormalGeneNumber();j++){
				individual.getGenes().get(j).getGenePieces().addAll(generateNormalHeaderPieces(geneConfiguration,dataSet));
				individual.getGenes().get(j).getGenePieces().addAll(generateNormalTailPieces(geneConfiguration,dataSet));
			}
			for(;j<geneConfiguration.getHomeoticGeneNumber();j++){
				individual.getGenes().get(j).getGenePieces().addAll(generateHomeoticHeaderPieces(geneConfiguration));
				individual.getGenes().get(j).getGenePieces().addAll(generateHomeoticTailPieces(geneConfiguration));
			}
			population.addIndividual(individual);
		}
		return gepAlgRun;
	}

	@Override
	public List<FittedValue> calculateFittedValue(Population population) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Float> calculateFitness(Population population) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Population select(GepAlgRun gepAlgRun) {
		// TODO Auto-generated method stub
		return null;
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
				addedGenePiece.setSymbol("");
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
			addedGenePiece.setSymbol("");
			genePieces.add(addedGenePiece);
		}
		return genePieces;
	}
}
