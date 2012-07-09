package test;

import java.util.ArrayList;
import java.util.List;

import domain.core.algmodel.configuration.GepAlgorithm;
import domain.core.algmodel.configuration.Individual;
import domain.core.algmodel.configuration.Population;
import domain.core.algmodel.genecomponent.Constant;
import domain.core.algmodel.genecomponent.GenePiece;
import domain.core.algmodel.genecomponent.Variable;
import domain.service.alg.baseclass.Creator;
import domain.service.alg.userdefined.Additioin;
import domain.service.alg.userdefined.Divide;
import domain.service.alg.userdefined.Minus;
import domain.service.alg.userdefined.Multiply;

/**
 * 需要把此类保存到对应的文件夹中才可运行
 */
public class CreatorTest extends Creator{

	public CreatorTest() {
		super("测试Create");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create(GepAlgorithm algorithm) {
		// TODO Auto-generated method stub
		Population population=new Population(algorithm.getPopulationSize());
		Individual individual=new Individual();
		List<GenePiece> genePieces=new ArrayList<GenePiece>();
		Variable variable=new Variable();
		variable.setName("a");
		variable.setSymbol("a");
		//正常基因
		genePieces.add(new Divide());
		genePieces.add(new Additioin());
		genePieces.add(new Minus());
		genePieces.add(new Multiply());
		genePieces.add(new Multiply());
		genePieces.add(new Multiply());
		genePieces.add(new Minus());
		genePieces.add(variable.copy());
		genePieces.add(variable.copy());
		genePieces.add(variable.copy());
		genePieces.add(variable.copy());
		genePieces.add(variable.copy());
		genePieces.add(variable.copy());
		genePieces.add(variable.copy());
		genePieces.add(variable.copy());
		genePieces.add(new Additioin());
		genePieces.add(variable.copy());
		genePieces.add(new Divide());
		genePieces.add(variable.copy());
		genePieces.add(new Divide());
		genePieces.add(new Additioin());
		genePieces.add(new Multiply());
		genePieces.add(variable.copy());
		genePieces.add(variable.copy());
		genePieces.add(variable.copy());
		genePieces.add(variable.copy());
		genePieces.add(variable.copy());
		genePieces.add(variable.copy());
		genePieces.add(variable.copy());
		genePieces.add(variable.copy());
		genePieces.add(new Multiply());
		genePieces.add(new Minus());
		genePieces.add(new Multiply());
		genePieces.add(new Minus());
		genePieces.add(variable.copy());
		genePieces.add(new Multiply());
		genePieces.add(new Minus());
		genePieces.add(variable.copy());
		genePieces.add(variable.copy());
		genePieces.add(variable.copy());
		genePieces.add(variable.copy());
		genePieces.add(variable.copy());
		genePieces.add(variable.copy());
		genePieces.add(variable.copy());
		genePieces.add(variable.copy());
		
		//同源基因
		genePieces.add(new Additioin());
		genePieces.add(new Additioin());
		Constant constant=new Constant(algorithm.getNormalGeneNumber());
		while(((int)constant.getValue())!=2)
			constant=new Constant(algorithm.getNormalGeneNumber());
		genePieces.add(constant);
		while(((int)constant.getValue())!=1)
			constant=new Constant(algorithm.getNormalGeneNumber());
		genePieces.add(constant);
		while(((int)constant.getValue())!=0)
			constant=new Constant(algorithm.getNormalGeneNumber());
		genePieces.add(constant);
		genePieces.add(new Constant(algorithm.getNormalGeneNumber()));
		genePieces.add(new Constant(algorithm.getNormalGeneNumber()));
		genePieces.add(new Constant(algorithm.getNormalGeneNumber()));
		genePieces.add(new Constant(algorithm.getNormalGeneNumber()));
		genePieces.add(new Constant(algorithm.getNormalGeneNumber()));
		genePieces.add(new Constant(algorithm.getNormalGeneNumber()));
		genePieces.add(new Constant(algorithm.getNormalGeneNumber()));
		genePieces.add(new Constant(algorithm.getNormalGeneNumber()));
		genePieces.add(new Constant(algorithm.getNormalGeneNumber()));
		genePieces.add(new Constant(algorithm.getNormalGeneNumber()));
		genePieces.add(new Constant(algorithm.getNormalGeneNumber()));
		genePieces.add(new Constant(algorithm.getNormalGeneNumber()));
		
		
		individual.setContainedGenePieces(genePieces, 
				algorithm.getNormalGeneNumber(), 
				algorithm.getNormalHeaderLength(), 
				algorithm.getNormalGeneLength(),
				algorithm.getHomeoticGeneNumber(), 
				algorithm.getHomeoticHeaderLength(), 
				algorithm.getHomeoticGeneLength());
		for(int i=0;i<algorithm.getPopulationSize();i++)
			population.addIndividual(individual.copy());
		algorithm.addPopulation(population);
	}

}
