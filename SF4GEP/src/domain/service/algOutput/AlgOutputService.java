package domain.service.algOutput;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import data.dao.IHibernateDataContext;
import domain.core.algInputDataProcess.DataSet;
import domain.core.algOutput.GepAlgRun;
import domain.core.algOutput.Population;
import domain.core.algconfiguration.GepAlgConfiguration;
import domain.iservice.algOutput.IAlgOutputService;
import domain.iservice.algOutput.IAlgRunStep;

public class AlgOutputService implements IAlgOutputService {
	private IHibernateDataContext hibernateDataContext;
	private List<Float> maxFitnesses=new LinkedList<Float>();
	private List<Float>	minFitnesses=new LinkedList<Float>();
	public AlgOutputService(IHibernateDataContext hibernateDataContext) {
		// TODO Auto-generated constructor stub
		this.hibernateDataContext=hibernateDataContext;
	}
	@Override
	public GepAlgRun run(GepAlgConfiguration gepAlgConfiguration, IAlgRunStep algRunStep, DataSet dataSet) {
		// TODO Auto-generated method stub
		List<Float> fitnessFloats;
		Float maxFitness;
		Float minFitness;
		GepAlgRun gepAlgRun=algRunStep.create(gepAlgConfiguration, dataSet);
		Population newPopulation;
		maxFitnesses.clear();
		minFitnesses.clear();
		for(long i=0;i<gepAlgConfiguration.getMaxGeneration();i++){
			fitnessFloats=algRunStep.calculateFitness(gepAlgRun.getCurrentPopulation());
			maxFitness=Collections.max(fitnessFloats);
			minFitness=Collections.min(fitnessFloats);
			maxFitnesses.add(maxFitness);
			minFitnesses.add(minFitness);
//			commit(gepAlgRun.getCurrentPopulation());
			if(Math.abs(maxFitness-gepAlgConfiguration.getMaxFitness())<=gepAlgConfiguration.getAccuracy()||i==gepAlgConfiguration.getMaxGeneration()-2)
				break;
			newPopulation=algRunStep.select(gepAlgRun);
			newPopulation.setGenerationNum(i+1);
			gepAlgRun.getPopulations().add(newPopulation);
			algRunStep.mutate(gepAlgRun.getCurrentPopulation());
			algRunStep.isTransport(gepAlgRun.getCurrentPopulation());
			algRunStep.risTransport(gepAlgRun.getCurrentPopulation());
			algRunStep.geneTransport(gepAlgRun.getCurrentPopulation());
			algRunStep.onePointRecombine(gepAlgRun.getCurrentPopulation());
			algRunStep.twoPointRecombine(gepAlgRun.getCurrentPopulation());
			algRunStep.geneRecombine(gepAlgRun.getCurrentPopulation());
		}
//		commit(gepAlgRun);
		return gepAlgRun;
	}

	@Override
	public List<Float> getMaxFitnessInEveryGeneration(GepAlgRun gepAlgRun) {
		// TODO Auto-generated method stub
		return maxFitnesses;
	}

	@Override
	public List<Float> getMinFitnessInEveryGeneration(GepAlgRun gepAlgRun) {
		// TODO Auto-generated method stub
		return minFitnesses;
	}

	@Override
	public boolean writeToFile(File file) {
		// TODO Auto-generated method stub
		
		return false;
	}
	private boolean commit(Population population){
		boolean result=true;
		try {
			hibernateDataContext.save(population);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result=false;
		}
		return result;
	}
	private boolean commit(GepAlgRun gepAlgRun){
		boolean result=true;
		try {
			hibernateDataContext.save(gepAlgRun);	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result=false;
		}
		return result;
	}
}
