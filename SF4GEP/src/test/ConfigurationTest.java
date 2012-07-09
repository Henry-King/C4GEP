package test;

import java.io.IOException;
import java.util.List;

import jxl.read.biff.BiffException;

import domain.core.algmodel.genepiece.Function;
import domain.core.outputmodel.GepConfiguration;
import domain.iservice.IgepAlgService;
import domain.service.alg.baseclass.GepAlgService;

public class ConfigurationTest {
	public static void main(String argv[]) throws BiffException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException{
		IgepAlgService myGepService=iniAlg();
		System.out.println(myGepService.getAvailableCalculator().get(0).toString());
		System.out.println(myGepService.getAvailableCreator().get(0).toString());
		System.out.println(myGepService.getAvailableFunctions().get(0).toString());
		System.out.println(myGepService.getAvailableFunctions().get(1).toString());
		System.out.println(myGepService.getAvailableFunctions().get(2).toString());
		System.out.println(myGepService.getAvailableFunctions().get(3).toString());
		System.out.println(myGepService.getAvailableModifyings().get(0).toString());
		System.out.println(myGepService.getAvailableSelector().get(0).toString());
	}
	static IgepAlgService iniAlg() throws BiffException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException{
		GepConfiguration myParameter=new GepConfiguration();
		myParameter.setInputFile("InputDemo.xls");
		myParameter.setName("≤‚ ‘123333");
		myParameter.setAccuray("0.01");
		myParameter.setNormalGeneNumber("3");
		myParameter.setGeneRecombineRate("0.1");
		myParameter.setGeneTransportRate("0.1");
		myParameter.setNormalHeaderLength("7");
		myParameter.setIsTransportRate("0.1");
		myParameter.setMaxGeneration("100000");
		myParameter.setOnePointRecombineRate("0.4");
		myParameter.setPopulationSize("20");
		myParameter.setRisTransportRate("0.1");
		myParameter.setSelectionRange("100");
		myParameter.setTwoPointRecombineRate("0.2");
		myParameter.setMutateRate("0.044");
		myParameter.setIsElement("1,2,3");
		myParameter.setRisElement("1,2,3");
		myParameter.setHomeoticGeneNumber("1");
		myParameter.setHomeoticHeaderLength("5");
		myParameter.setRandomConstantStart("0");
		myParameter.setRandomConstantEnd("1");
		myParameter.setConstantListSize("10");
		IgepAlgService myGepService=new GepAlgService();	
		myParameter.setCreator(myGepService.getAvailableCreator().get(0).getClass().getName());
		myParameter.setCalculator(myGepService.getAvailableCalculator().get(0).getClass().getName());
		myParameter.setModify(myGepService.getAvailableModifyings().get(0).getClass().getName());
		myParameter.setFunctionList(getFunctionList(myGepService));
		myParameter.setSelector(myGepService.getAvailableSelector().get(0).getClass().getName());
		myGepService.setParameters(myParameter);
		return myGepService;
	}
	private static String getFunctionList(IgepAlgService myGepService) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		List<Function> myFunction=myGepService.getAvailableFunctions();
		StringBuffer result=new StringBuffer();
		for(Function function:myFunction)
			result.append(function.getClass().getName()+",");
		return result.toString();
	}
}
