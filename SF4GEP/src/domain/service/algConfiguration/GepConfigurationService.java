package domain.service.algConfiguration;

import java.util.List;

import data.dao.HibernateDataContext;
import data.dao.IHibernateDataContext;
import domain.core.algInputDataProcess.DataSet;
import domain.core.algconfiguration.Function;
import domain.core.algconfiguration.GeneConfiguration;
import domain.core.algconfiguration.GepAlgConfiguration;
import domain.core.algconfiguration.IndividualConfiguration;
import domain.iservice.algConfiguration.IgepConfigurationService;

public class GepConfigurationService implements IgepConfigurationService {

	@Override
	public List<GepAlgConfiguration> getAllGepAlgConfiguration() {
		// TODO Auto-generated method stub
		IHibernateDataContext hibernateDataContext=new HibernateDataContext();
		return hibernateDataContext.findAll(GepAlgConfiguration.class);
	}

	@Override
	public boolean saveGepAlgConfiguration(
			GepAlgConfiguration gepAlgConfiguration) {
		// TODO Auto-generated method stub
		boolean result=true;
		try {
			IHibernateDataContext hibernateDataContext=new HibernateDataContext();
			hibernateDataContext.save(gepAlgConfiguration.getOperatorConfiguration());
			hibernateDataContext.save(gepAlgConfiguration.getIndividualConfiguration().getGeneConfiguration());
			hibernateDataContext.save(gepAlgConfiguration.getIndividualConfiguration());
			hibernateDataContext.save(gepAlgConfiguration);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result=false;
		}
		return result;
	}

	@Override
	public GepAlgConfiguration setGepAlgConfiguration(
			GepAlgConfiguration gepAlgConfiguration,DataSet dataSet) {
		// TODO Auto-generated method stub
		IndividualConfiguration individualConfiguration=gepAlgConfiguration.getIndividualConfiguration();
		GeneConfiguration geneConfiguration=individualConfiguration.getGeneConfiguration();
		int maxArity=maxArity(geneConfiguration.getFunctionUsed());
		geneConfiguration.setHomeoticGeneTailLength(geneConfiguration.getHomeoticGeneHeaderLength()*(maxArity-1)+1);
		geneConfiguration.setHomeoticGeneLength(geneConfiguration.getHomeoticGeneHeaderLength()+geneConfiguration.getHomeoticGeneTailLength());
		geneConfiguration.setNormalGeneTailLength(geneConfiguration.getNormalGeneHeaderLength()*(maxArity-1)+1);
		geneConfiguration.setNormalGeneLength(geneConfiguration.getNormalGeneHeaderLength()+geneConfiguration.getNormalGeneTailLength());
		individualConfiguration.setTotalGeneNumbers(geneConfiguration.getNormalGeneNumber()+geneConfiguration.getHomeoticGeneNumber());
		individualConfiguration.setNormalGeneTotalLength(geneConfiguration.getNormalGeneLength()*geneConfiguration.getNormalGeneNumber());
		individualConfiguration.setHomeoticGeneTotalLength(geneConfiguration.getHomeoticGeneLength()*geneConfiguration.getHomeoticGeneNumber());
		individualConfiguration.setGeneTotalLength(individualConfiguration.getHomeoticGeneTotalLength()+individualConfiguration.getNormalGeneTotalLength());
		gepAlgConfiguration.setMaxFitness(gepAlgConfiguration.getSelectionRange()*dataSet.getRowNum());
		return gepAlgConfiguration;
	}
	private int maxArity(List<Function> functions){
		int max=0;
		for(Function function:functions)
			if(function.getArity()>max)
				max=function.getArity();
		return max;
	}

	@Override
	public List<Function> getAvailableFunctions() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
