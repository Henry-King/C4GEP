package test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import jxl.read.biff.BiffException;
import domain.core.algmodel.configuration.GepAlgorithm;
import domain.core.algmodel.configuration.Individual;
import domain.iservice.IgepAlgService;
import domain.service.alg.configuration.GepAlgService;

public class FitnessTest {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 * @throws BiffException 
	 */
	public static void main(String[] args) throws BiffException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
		// TODO Auto-generated method stub
		IgepAlgService myGepService=ConfigurationTest.iniAlg();
		myGepService.run();
		Field[] myGepAlgFields=GepAlgService.class.getDeclaredFields();
		for(Field myGepAlg:myGepAlgFields)
			if(myGepAlg.getName().equals("myGepAlgorithm")){
				myGepAlg.setAccessible(true);
				test((GepAlgorithm)myGepAlg.get(myGepService));
				break;
			}
	}
	private static void test(GepAlgorithm gepAlgorithm){
		System.out.println(gepAlgorithm.getPopulationQueue().getFirst().getBestIndividual().getFitness());
		List<Individual> individuals=gepAlgorithm.getPopulationQueue().getFirst().getIndividuals();
		System.out.println("_______________________________");
		for(Individual individual:individuals){
			System.out.println(individual.getFitness());
		}
		System.out.println("_______________________________");
	}
	
}
