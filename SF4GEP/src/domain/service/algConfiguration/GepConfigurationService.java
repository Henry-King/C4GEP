package domain.service.algConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import data.dao.IHibernateDataContext;
import domain.core.algInputDataProcess.DataSet;
import domain.core.algconfiguration.Function;
import domain.core.algconfiguration.GeneConfiguration;
import domain.core.algconfiguration.GepAlgConfiguration;
import domain.core.algconfiguration.IndividualConfiguration;
import domain.iservice.algConfiguration.IgepConfigurationService;

public class GepConfigurationService implements IgepConfigurationService {
	private IHibernateDataContext hibernateDataContext;
	public GepConfigurationService(IHibernateDataContext hibernateDataContext) {
		// TODO Auto-generated constructor stub
		this.hibernateDataContext=hibernateDataContext;
	}
	@Override
	public List<GepAlgConfiguration> getAllGepAlgConfiguration() {
		return hibernateDataContext.findAll(GepAlgConfiguration.class);
	}

	@Override
	public boolean saveGepAlgConfiguration(
			GepAlgConfiguration gepAlgConfiguration) {
		// TODO Auto-generated method stub
		boolean result=true;
		try {
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
		File file=new File("bin\\domain\\core\\algconfiguration\\function");
		File result[]=file.listFiles();
		List<Function> functions=new ArrayList<Function>(result.length);
		String path;
		for(int i=0;i<result.length;i++){
			path=result[i].getPath();
			try {
				functions.add((Function) Class.forName(path.substring(4,path.length()-6).replace("\\", ".")).newInstance());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return functions;
	}
	@Override
	public boolean upate(GepAlgConfiguration gepAlgConfiguration) {
		// TODO Auto-generated method stub
		boolean result=true;
		try {
			hibernateDataContext.update(gepAlgConfiguration);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result=false;
		}
		return result;
	}
	
}
