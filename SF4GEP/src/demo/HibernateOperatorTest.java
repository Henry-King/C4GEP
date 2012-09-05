package demo;

import java.util.List;

import data.dao.HibernateDataContext;
import data.dao.IHibernateDataContext;
import domain.core.algconfiguration.OperatorConfiguration;

public class HibernateOperatorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OperatorConfiguration operatorConfiguration=new OperatorConfiguration();
		operatorConfiguration.setGeneRecombineRate((float) 0.1);
		operatorConfiguration.setGeneTransportRate((float) 0.1);
		operatorConfiguration.setIsElement(new Integer[]{1,2,3});
		operatorConfiguration.setIsTransportRate((float) 0.1);
		operatorConfiguration.setMutateRate((float) 0.0444);
		operatorConfiguration.setOnePointRecombineRate((float) 0.4);
		operatorConfiguration.setRisElement(new Integer[]{1,2,3});
		operatorConfiguration.setRisTransportRate((float) 0.1);
		operatorConfiguration.setTwoPointRecombineRate((float) 0.2);
		IHibernateDataContext hibernateDataContext=new HibernateDataContext();
		hibernateDataContext.save(operatorConfiguration);
		List<OperatorConfiguration> operatorConfigurations=hibernateDataContext.findAll(OperatorConfiguration.class);
		System.out.println(operatorConfigurations.get(0).getRisElement().length);
	}

}
