package test;

import domain.iservice.algConfiguration.IgepConfigurationService;
import domain.service.algConfiguration.GepConfigurationService;

public class GetFuncitonTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IgepConfigurationService gEConfigurationService=new GepConfigurationService();
		System.out.println(gEConfigurationService.getAvailableFunctions().get(0).getName());
	}

}
