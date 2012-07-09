package domain.service.alg.userdefined;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import common.ObjectCopy;

import domain.core.algmodel.configuration.GepAlgorithm;
import domain.core.algmodel.configuration.Individual;
import domain.core.algmodel.configuration.Population;
import domain.core.algmodel.genecomponent.Constant;
import domain.core.algmodel.genecomponent.Function;
import domain.core.algmodel.genecomponent.GenePiece;
import domain.core.algmodel.genecomponent.Variable;
import domain.service.alg.configuration.Creator;

public class DefaultCreator extends Creator{
	private GepAlgorithm algorithm;
	private int funcNumber;
	private int variableNumber;
	private int funcntionVariablNum;
	private int functionConstantNum;
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
		
		funcNumber=getSelectedFunctions().size();
		variableNumber=getVariables().size();
		funcntionVariablNum=funcNumber+variableNumber;
		functionConstantNum=funcNumber+1;
		
		for(int i=0;i<algorithm.getPopulationSize();i++){
			insertingIndividual=new Individual();
			containedGenePieces=new ArrayList<GenePiece>(algorithm.getIndividualLength());
			for(int j=0;j<algorithm.getNormalGeneNumber();j++){
				fillNormalGeneHeader(containedGenePieces);
				fillNormalGeneTail(containedGenePieces);
			}
			for(int j=0;j<algorithm.getHomeoticGeneNumber();j++){
				fillHomeoticHeader(containedGenePieces);
				fillHomeoticGeneTail(containedGenePieces);
			}
			insertingIndividual.setContainedGenePieces(containedGenePieces, algorithm.getNormalGeneNumber(), algorithm.getNormalHeaderLength(), algorithm.getNormalGeneLength(), algorithm.getHomeoticGeneNumber(), algorithm.getHomeoticHeaderLength(), algorithm.getHomeoticGeneLength());
			firstPopulation.addIndividual(insertingIndividual);
		}
		algorithm.addPopulation(firstPopulation);
	}
	private void fillNormalGeneHeader(List<GenePiece> containedGenePieces){
		Random functionRand=new Random();
		Random variableRand=new Random();
		Random typeRandom=new Random();
		int type;
		for(int i=0;i<algorithm.getNormalHeaderLength();i++){
			type=typeRandom.nextInt(funcntionVariablNum);	
			if(type<funcNumber){
				//插入函数
				Function newFunction=(Function) ObjectCopy.newInstance(getSelectedFunctions().get(functionRand.nextInt(getSelectedFunctions().size())));
				containedGenePieces.add(newFunction);
			}
			else {
				//插入变量
				Variable newVariable=(Variable)ObjectCopy.newInstance(getVariables().get(variableRand.nextInt(getVariables().size())));
				containedGenePieces.add(newVariable);
			}

		}
	}
	private void fillNormalGeneTail(List<GenePiece> containedGenePieces){
		Random variableRand=new Random();
		for(int i=0;i<algorithm.getNormalTailLength();i++){
			//插入变量
			Variable newVariable=(Variable)ObjectCopy.newInstance(getVariables().get(variableRand.nextInt(getVariables().size())));
			containedGenePieces.add(newVariable);
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
			type=typeRandom.nextInt(functionConstantNum);
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
