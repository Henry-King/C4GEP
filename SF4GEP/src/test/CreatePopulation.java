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
		 * дexcel�ļ�
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
		System.out.println("ͬԴȾɫ�峤��"+algorithm.getHomeoticGeneLength());
		System.out.println("ͬԴȾɫ������"+algorithm.getHomeoticGeneNumber());
		System.out.println("ͬԴȾɫ��ͷ��"+algorithm.getHomeoticHeaderLength());
		System.out.println("ͬԴȾɫ��β��"+algorithm.getHomeoticTailLength());
		System.out.println("�������򳣳���"+algorithm.getNormalGeneLength());
		System.out.println("������������"+algorithm.getNormalGeneNumber());
		System.out.println("��������ͷ��"+algorithm.getNormalHeaderLength());
		System.out.println("��������β��"+algorithm.getNormalTailLength());
		Population myPopulation=algorithm.getPopulationQueue().getLast();
		System.out.println("����"+myPopulation.getGeneration());
		for(Individual individual:myPopulation.getIndividuals())
			System.out.println(individual);
	}
}
