package domain.service.alg.userdefined.calculator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import domain.core.algmodel.configuration.GepAlgorithm;
import domain.core.algmodel.configuration.Individual;
import domain.core.algmodel.genecomponent.Constant;
import domain.core.algmodel.genecomponent.Function;
import domain.core.algmodel.genecomponent.GenePiece;
import domain.core.algmodel.genecomponent.Variable;
import domain.core.algmodel.individualcomponent.Gene;
import domain.core.algmodel.individualcomponent.HomeoticGene;
import domain.core.algmodel.individualcomponent.NormalGene;
import domain.core.inputmodel.FieldItem;
import domain.core.inputmodel.FieldRow;
import domain.service.alg.configuration.Calculator;
import exception.IllegalInputSet;

public class AbsoluteCalculator extends Calculator{
	private GepAlgorithm gepAlgorithm;
	public AbsoluteCalculator(){
		super("绝对计算方式");
	}
	@Override
	public void calculateFitness(GepAlgorithm gepAlgorithm) {
		// TODO Auto-generated method stub
		this.gepAlgorithm=gepAlgorithm;
		List<Individual> individuals=gepAlgorithm.getPopulationQueue().getLast().getIndividuals();
		List<FieldRow> fieldRowList=getInputSet().getFieldRowList();
		List<Float> individualValue;
		List<Float> oneRowFitness;
		List<Float> sumFitness=new ArrayList<Float>(gepAlgorithm.getHomeoticGeneNumber());
		float target;
		int homeoticGeneIndex;
		for(Individual individual:individuals){
			for(int i=0;i<fieldRowList.size();i++)
				sumFitness.add((float) 0);
			for(FieldRow fieldRow:fieldRowList){
				individualValue=calculateIndividualValue(individual,fieldRow);
				target=fieldRow.getFieldItemList().get(fieldRow.getColumns()-1).getValue();
				oneRowFitness=calculateOneRowFitness(individualValue,target);
				addToFitnessSum(oneRowFitness, sumFitness);
				clearFunctionFlag(individual);
			}
			homeoticGeneIndex=selectIndividualFitness(sumFitness);
			if(homeoticGeneIndex==-1){
				individual.setFitness((float) 0);
				individual.setSelectedHomeoticGeneNumber(-1);
			}
			else {
				individual.setFitness(sumFitness.get(homeoticGeneIndex));
				individual.setSelectedHomeoticGeneNumber(homeoticGeneIndex);				
			}
			sumFitness.clear();
		}
	}
	@Override
	public void calculateInputSet(Individual individual) throws IllegalInputSet{
		// TODO Auto-generated method stub
		List<FieldRow> fieldRowList=getInputSet().getFieldRowList();
		float value;
		for(FieldRow fieldRow:fieldRowList){
			try {
				value=calculateIndividualValue(individual, fieldRow).get(individual.getSelectedHomeoticGeneNumber());
				fieldRow.getFieldItemList().get(fieldRow.getColumns()-1).setValue(value);
			} catch (ArithmeticException e) {
				// TODO Auto-generated catch block
				IllegalInputSet illegalInputSet=new IllegalInputSet();
				illegalInputSet.initCause(e);
				throw illegalInputSet;
			}
		}
	}
	private List<Float> calculateIndividualValue(Individual individual,FieldRow fieldRow){
		for(NormalGene gene:individual.getNormalGeneList()){
			assignValueToVariable(gene, fieldRow.getFieldItemList());
			calculateGeneValue(gene, individual);
		}
		List<Float> resulList=new ArrayList<Float>(gepAlgorithm.getHomeoticGeneNumber());
		float result;
		for(HomeoticGene homeoticGene:individual.getHomeoticGeneList()){
			result=calculateGeneValue(homeoticGene, individual);
			resulList.add(result);
		}
		return resulList;
	}
	private List<Float> calculateOneRowFitness(List<Float> fList,float target){
		List<Float> result=new ArrayList<Float>(fList.size());
		float minus;
		float abs;
		for(float expected:fList){
			minus=expected-target;
			abs=Math.abs(minus);
			if(abs<getAccuray())
				abs=0;
			result.add(getSelectionRange()-abs);
		}
		return result;
	}
	private void addToFitnessSum(List<Float>  row,List<Float> sum){

		for(int i=0;i<row.size();i++)
			sum.set(i, row.get(i)+sum.get(i));
			
	}
	private int selectIndividualFitness(List<Float> sumFitness){
		int index=-1;
		float max=0;
		float itemFitness;
		for(int i=0;i<sumFitness.size();i++){
			itemFitness=sumFitness.get(i);
			if(Float.isInfinite(itemFitness)||Float.isNaN(itemFitness)||itemFitness<=0)
				continue;
			else if(max<sumFitness.get(i)){
				index=i;
				max=itemFitness;
			}
		}
		return index;
	}
	private void assignValueToVariable(NormalGene gene,List<FieldItem> fList){
		for(GenePiece genePiece:gene.getContainedGenePieces()){
			if(genePiece.getClass().equals(Variable.class)){
				Variable variable=(Variable) genePiece;
				for(FieldItem item:fList){
					if(item.getName().equals(variable.getName())){
						variable.setValue(item.getValue());
						break;
					}	
				}
			}
		}
	}
	private float calculateGeneValue(Gene gene,Individual individual){
		int length=calcEfficientLength(gene);
		int lastNonTerminate;
		int arity;
		while(length>1){
			lastNonTerminate=findLastNonTerminate(gene, length);
			Function function=(Function)gene.getContainedGenePieces().get(lastNonTerminate);
			arity=function.getArity();
			execMathFunction(gene, individual,function,length,arity);
			length-=arity;
		}
		float value=((GenePiece)gene.getContainedGenePieces().get(0)).getValue();
		gene.setValue(value);
		return value;
	}
	private int calcEfficientLength(Gene gene){
		int length=1;
		int arity;
		GenePiece genePiece;
		for(int i=0;i<length;i++){
			genePiece=gene.getContainedGenePieces().get(i);
			if(Function.class.isInstance(genePiece)){
				if(Float.isNaN(((Function)genePiece).getValue())){
					arity=((Function)genePiece).getArity();
					length+=arity;					
				}
			}
		}
		return length;
	}
	private int findLastNonTerminate(Gene gene,int efficientLength){
		GenePiece genePiece;
		int result=0;
		for(int i=efficientLength-1;i>=0;i--){
			genePiece=gene.getContainedGenePieces().get(i);
			if(Function.class.isInstance(genePiece)){
				if(Float.isNaN(((Function)genePiece).getValue())){
					result=i;
					break;
				}
			}
		}
		return result;
	}
	private void execMathFunction(Gene gene,Individual individual,Function function,int efficientLength,int arity){
		Deque<GenePiece> genePieces=new ArrayDeque<GenePiece>(arity);
		Deque<Float> valueDeque=new ArrayDeque<Float>(arity);
		for(int i=0;i<arity;i++)
			genePieces.addFirst(gene.getContainedGenePieces().get(efficientLength-i-1));		
		if(gene.getClass().equals(HomeoticGene.class)){
			int index;
			while(!genePieces.isEmpty()){
				GenePiece valueSource=(GenePiece)genePieces.removeLast();
				if(Function.class.isInstance(valueSource)){
					valueDeque.addFirst(valueSource.getValue());
				}
				else {
					index=Math.round(((Constant)valueSource).getValue());
					valueDeque.addFirst(individual.getNormalGeneList().get(index).getValue());
				}
			}			
		}
		else {
			while(!genePieces.isEmpty()){
				float value=((GenePiece)genePieces.removeLast()).getValue();
				valueDeque.addFirst(value);
			}
		}
		float value=function.operate(valueDeque);
		function.setValue(value);
	}
	private void clearFunctionFlag(Individual individual){
		for(NormalGene normalGene:individual.getNormalGeneList()){
			for(GenePiece genePiece:normalGene.getContainedGenePieces())
				if(Function.class.isInstance(genePiece))
					((Function)genePiece).setValue(Float.NaN);
		}
		for(HomeoticGene homeoticGene:individual.getHomeoticGeneList()){
			for(GenePiece genePiece:homeoticGene.getContainedGenePieces())
				if(Function.class.isInstance(genePiece))
					((Function)genePiece).setValue(Float.NaN);
		}
	}
}
