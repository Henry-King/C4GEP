package domain.service.algConfiguration;

import java.util.List;

import domain.core.algInputDataProcess.DataSet;
import domain.core.algconfiguration.Function;
import domain.core.algconfiguration.GeneConfiguration;
import domain.core.algconfiguration.GepAlgConfiguration;
import domain.core.algconfiguration.IndividualConfiguration;
import domain.iservice.algConfiguration.IgepConfigurationService;

public class GepConfigurationService implements IgepConfigurationService {

	@Override
	public GepAlgConfiguration getGepAlgConfigurationByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveGepAlgConfiguration(
			GepAlgConfiguration gepAlgConfiguration) {
		// TODO Auto-generated method stub
		return false;
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
		return 0;
	}
}
