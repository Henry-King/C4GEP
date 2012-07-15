package test;

import data.dao.HibernateDataContext;
import domain.iservice.algConfiguration.IgepConfigurationService;
import domain.service.algConfiguration.GepConfigurationService;

public class GetFuncitonTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IgepConfigurationService gEConfigurationService=new GepConfigurationService(new HibernateDataContext());
		System.out.println(gEConfigurationService.getAvailableFunctions().size());
	}

}
