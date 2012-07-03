package domain.service.alg.userdefined.creator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import common.ObjectCopy;

import domain.core.algmodel.configuration.GepAlgorithm;
import domain.core.algmodel.configuration.Individual;
import domain.core.algmodel.configuration.Population;
import domain.core.algmodel.genecomponent.Constant;
import domain.core.algmodel.genecomponent.ConstantList;
import domain.core.algmodel.genecomponent.Function;
import domain.core.algmodel.genecomponent.GenePiece;
import domain.core.algmodel.genecomponent.Variable;
import domain.service.alg.configuration.Creator;

public class DefaultCreator extends Creator{
	private GepAlgorithm algorithm;
	private int funcNumber;
	private int variableNumber;
	private int constantNumber;
	private int funcntionVariablConstantSum;
	private int variableConstantSum;
	private int functionConstantSum;
	public DefaultCreator() {
		super("随机生成种群");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create(GepAlgorithm algorithm){
		// TODO Auto-generated method stub
		
		this.algorithm=algorithm;
		Population firstPopulation=new Population(algorithm.getPopulationSize());
		Individual insertingIndividual;
		List<GenePiece> containedGenePieces;
		List<ConstantList> constantLists=new ArrayList<ConstantList>(algorithm.getNormalGeneNumber());
		
		funcNumber=getSelectedFunctions().size();
		variableNumber=getVariables().size();
		constantNumber=1;
		funcntionVariablConstantSum=funcNumber+variableNumber+constantNumber;
		variableConstantSum=variableNumber+constantNumber;
		functionConstantSum=funcNumber+constantNumber;		
		
		for(int i=0;i<algorithm.getPopulationSize();i++){
			insertingIndividual=new Individual();
			containedGenePieces=new ArrayList<GenePiece>(algorithm.getIndividualLength());
			for(int j=0;j<algorithm.getNormalGeneNumber();j++){
				ConstantList constantList=new ConstantList(getStart(), getEnd(), getConstanListSize());
				constantLists.add(constantList);
				fillNormalGeneHeader(containedGenePieces,constantList);
				fillNormalGeneTail(containedGenePieces,constantList);
			}
			for(int j=0;j<algorithm.getHomeoticGeneNumber();j++){
				fillHomeoticHeader(containedGenePieces);
				fillHomeoticGeneTail(containedGenePieces);
			}
			insertingIndividual.setContainedGenePieces(containedGenePieces, algorithm.getNormalGeneNumber(), algorithm.getNormalHeaderLength(), algorithm.getNormalGeneLength(), algorithm.getHomeoticGeneNumber(), algorithm.getHomeoticHeaderLength(), algorithm.getHomeoticGeneLength());
			for(int j=0;j<algorithm.getNormalGeneNumber();j++)
				insertingIndividual.getNormalGeneList().get(j).setConstantList(constantLists.get(j));
			firstPopulation.addIndividual(insertingIndividual);
		}
		algorithm.addPopulation(firstPopulation);
	}
	private void fillNormalGeneHeader(List<GenePiece> containedGenePieces,ConstantList constantList){
		Random functionRand=new Random();
		Random variableRand=new Random();
		Random typeRandom=new Random();
		int type;
		Constant constant;
		for(int i=0;i<algorithm.getNormalHeaderLength();i++){
			type=typeRandom.nextInt(funcntionVariablConstantSum);	
			if(type<funcNumber){
				//插入函数
				Function newFunction=(Function) ObjectCopy.newInstance(getSelectedFunctions().get(functionRand.nextInt(getSelectedFunctions().size())));
				containedGenePieces.add(newFunction);
			}
			else if(type>=funcNumber&&type<funcNumber+variableNumber){
				//插入变量
				Variable newVariable=(Variable)ObjectCopy.newInstance(getVariables().get(variableRand.nextInt(getVariables().size())));
				containedGenePieces.add(newVariable);
			}
			else {
				//插入常量
				constant=new Constant(constantList,getConstanListSize());
				containedGenePieces.add(constant);
			}
		}
	}
	private void fillNormalGeneTail(List<GenePiece> containedGenePieces,ConstantList constantList){
		Random variableRand=new Random();
		Random typeRandom=new Random();
		int type;
		Constant constant;
		for(int i=0;i<algorithm.getNormalTailLength();i++){
			type=typeRandom.nextInt(variableConstantSum);
			if(type<variableNumber){
				//插入变量
				Variable newVariable=(Variable)ObjectCopy.newInstance(getVariables().get(variableRand.nextInt(getVariables().size())));
				containedGenePieces.add(newVariable);
			}
			else {
				//插入常量
				constant=new Constant(constantList,getConstanListSize());
				containedGenePieces.add(constant);
			}	
		}
	}
	private void fillHomeoticHeader(List<GenePiece> containedGenePieces){
		Random functionRand=new Random();
		Random typeRandom=new Random();
		int type;
		Constant constant;
		//在头部先插入一个函数
		Function newFunction=(Function)ObjectCopy.newInstance( getSelectedFunctions().get(functionRand.nextInt(getSelectedFunctions().size())));
		containedGenePieces.add(newFunction);
		
		for(int i=1;i<algorithm.getHomeoticHeaderLength();i++){
			type=typeRandom.nextInt(functionConstantSum);
			if(type<funcNumber){
				//插入函数
				newFunction=(Function)ObjectCopy.newInstance(getSelectedFunctions().get(functionRand.nextInt(getSelectedFunctions().size()))) ;
				containedGenePieces.add(newFunction);
			}
			else {
				//插入常量
				constant=new Constant(algorithm.getNormalGeneNumber());
				containedGenePieces.add(constant);
			}
		}
	}
	private void fillHomeoticGeneTail(List<GenePiece> containedGenePieces){
		Constant constant;
		for(int i=0;i<algorithm.getHomeoticTailLength();i++){
			constant=new Constant(algorithm.getNormalGeneNumber());
			containedGenePieces.add(constant);
		}
	}
	
}
