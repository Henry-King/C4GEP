package test;

import java.util.ArrayList;
import java.util.List;

import domain.core.algmodel.genepiece.Constant;
import domain.core.algmodel.genepiece.GenePiece;
import domain.core.algmodel.genepiece.Variable;
import domain.core.algmodel.population.Individual;
import domain.service.alg.userdefined.Additioin;
import domain.service.alg.userdefined.Divide;
import domain.service.alg.userdefined.Minus;
import domain.service.alg.userdefined.Multiply;

public class IndividualCopyTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
		Constant constant=new Constant(3);
		while(((int)constant.getValue())!=2)
			constant=new Constant(3);
		genePieces.add(constant);
		while(((int)constant.getValue())!=1)
			constant=new Constant(3);
		genePieces.add(constant);
		while(((int)constant.getValue())!=0)
			constant=new Constant(3);
		genePieces.add(constant);
		genePieces.add(new Constant(3));
		genePieces.add(new Constant(3));
		genePieces.add(new Constant(3));
		genePieces.add(new Constant(3));
		genePieces.add(new Constant(3));
		genePieces.add(new Constant(3));
		genePieces.add(new Constant(3));
		genePieces.add(new Constant(3));
		genePieces.add(new Constant(3));
		genePieces.add(new Constant(3));
		genePieces.add(new Constant(3));
		genePieces.add(new Constant(3));
		individual.setContainedGenePieces(genePieces, 3, 7, 15, 1, 7, 15);
		System.out.println(individual);
		Individual individual2=individual.copy();
		individual.getContainedGenePieces().set(0, new Additioin());
		System.out.println(individual);
		System.out.println(individual2);
	}

}
