package domain.service.algOutput;

import java.util.ArrayList;
import java.util.List;

import domain.core.algOutput.GepAlgRun;
import domain.core.algOutput.Individual;
import domain.core.algOutput.Population;

public class AlgGpuRunStep extends AlgCpuRunStep{
	public AlgGpuRunStep() {
		// TODO Auto-generated constructor stub
		
	}
	@Override
	public List<Float> calculateFitness(Population population){
		GepAlgRun gepAlgRun=population.getGepAlgRun();
		calcOnCuda(gepAlgRun);
		printResult(population);
		List<Float> fList=new ArrayList<Float>(population.getIndividuals().size());
		for(Individual individual:population.getIndividuals())
			fList.add(individual.getFitness());
		return fList;
	}
	private void printAllInfo(Population population){
		printOneInfo("normalGeneType", population.getNormalGeneType());
		printOneInfo("normalGeneIndex", population.getNormalGeneIndex());
		printOneInfo("homeoticGeneType", population.getHomeoticGeneType());
		printOneInfo("homeoticGeneIndex", population.getHomeoticGeneIndex());
	}
	private void printOneInfo(String name,char[][] value){
		System.out.println(name);
		for(char one[]:value){
			for(char x:one){
				System.out.print((int)x+" ");
			}
			System.out.println();
		}
	}
	private void printResult(Population population){
		System.out.println("------------------");
		System.out.println("代数 : "+population.getGenerationNum());
		System.out.println("最佳个体 : "+population.getBestIndividual().getFitness());
		for(Individual individual:population.getIndividuals())
			if(individual.getFitness()>1000)
				throw new RuntimeException("Oh,God that's not unreasonable");
			else
			System.out.println("适应值 : "+individual.getFitness());
	}
	private native void calcOnCuda(GepAlgRun gepAlgRun);
}