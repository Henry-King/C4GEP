package test;

import java.io.File;
import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;

import domain.core.algmodel.population.GepAlgorithm;
import domain.core.algmodel.population.Individual;
import domain.core.algmodel.population.Population;
import domain.iservice.IgepAlgService;
import domain.iservice.IgepOutputService;
import domain.service.output.GepOutputService;

public class CreatePopulation {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws BiffException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 * @throws WriteException 
	 */
	public static void main(String[] args) throws BiffException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, WriteException {
		// TODO Auto-generated method stub
		IgepAlgService myGepService=ConfigurationTest.iniAlg();
		myGepService.run();
		IgepOutputService output=new GepOutputService();
		/*
		 * 写excel文件
		 */
		output.setGeneration(0,10);
		output.writeExcel(myGepService.getMyAlgInstance(), new File("Outputdemo.xls"));
		myGepService.getMyAlgInstance();
		/*
		Field[] myGepAlgFields=GepAlgService.class.getDeclaredFields();
		for(Field myGepAlg:myGepAlgFields)
			if(myGepAlg.getName().equals("myGepAlgorithm")){
				myGepAlg.setAccessible(true);
				test((GepAlgorithm)myGepAlg.get(myGepService));
				break;
			}
			*/
	}
	static void test(GepAlgorithm algorithm){
		System.out.println("同源染色体长度"+algorithm.getHomeoticGeneLength());
		System.out.println("同源染色体数量"+algorithm.getHomeoticGeneNumber());
		System.out.println("同源染色体头长"+algorithm.getHomeoticHeaderLength());
		System.out.println("同源染色体尾长"+algorithm.getHomeoticTailLength());
		System.out.println("正常基因常长度"+algorithm.getNormalGeneLength());
		System.out.println("正常基因数量"+algorithm.getNormalGeneNumber());
		System.out.println("正常基因头长"+algorithm.getNormalHeaderLength());
		System.out.println("正常基因尾长"+algorithm.getNormalTailLength());
		Population myPopulation=algorithm.getPopulationQueue().getLast();
		System.out.println("代数"+myPopulation.getGeneration());
		for(Individual individual:myPopulation.getIndividuals())
			System.out.println(individual);
	}
}
