package test;

import java.util.Arrays;
import java.util.List;

import data.dao.HibernateDataContext;
import data.dao.IHibernateDataContext;
import domain.core.algconfiguration.GeneConfiguration;
import domain.core.algconfiguration.function.Addition;
import domain.core.algconfiguration.function.Divide;
import domain.core.algconfiguration.function.Minus;
import domain.core.algconfiguration.function.Multiply;

public class HibernateGeneConfigurationTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GeneConfiguration geneConfiguration=new GeneConfiguration();
		geneConfiguration.setHomeoticGeneHeaderLength(5);
		geneConfiguration.setHomeoticGeneNumber(1);
		geneConfiguration.setNormalGeneHeaderLength(7);
		geneConfiguration.setNormalGeneNumber(3);
		geneConfiguration.setFunctionUsed(Arrays.asList(new Addition(),new Minus(),new Multiply(),new Divide()));
		IHibernateDataContext hibernateDataContext=new HibernateDataContext();
		hibernateDataContext.save(geneConfiguration);
		List<GeneConfiguration> operatorConfigurations=hibernateDataContext.findAll(GeneConfiguration.class);
		System.out.println(operatorConfigurations.get(0).getFunctionUsed().size());
	}

}
