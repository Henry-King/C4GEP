package domain.service.algOutput;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import data.dao.IHibernateDataContext;
import domain.core.algInputDataProcess.DataSet;
import domain.core.algOutput.GepAlgRun;
import domain.core.algOutput.Population;
import domain.core.algconfiguration.GepAlgConfiguration;
import domain.iservice.algConfiguration.IgepConfigurationService;
import domain.iservice.algOutput.IAlgOutputService;
import domain.iservice.algOutput.IAlgRunStep;
import domain.service.algConfiguration.GepConfigurationService;

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
		ExecutorService executorService=Executors.newSingleThreadExecutor();
		List<Float> fitnessFloats;
		Float maxFitness;
		Float minFitness;
		GepAlgRun gepAlgRun=algRunStep.create(gepAlgConfiguration, dataSet);
		gepAlgRun.getPopulations().add(gepAlgRun.getCurrentPopulation().clone());
		commit(gepAlgRun);
		Population newPopulation;
		maxFitnesses.clear();
		minFitnesses.clear();
		for(long i=0;i<gepAlgConfiguration.getMaxGeneration();i++){
			fitnessFloats=algRunStep.calculateFitness(gepAlgRun.getCurrentPopulation());
			maxFitness=Collections.max(fitnessFloats);
			minFitness=Collections.min(fitnessFloats);
			maxFitnesses.add(maxFitness);
			minFitnesses.add(minFitness);
			if(Math.abs(maxFitness-gepAlgConfiguration.getMaxFitness())<=gepAlgConfiguration.getAccuracy()||i==gepAlgConfiguration.getMaxGeneration()-2)
				break;
			newPopulation=algRunStep.select(gepAlgRun);
			newPopulation.setGenerationNum(i+1);
			gepAlgRun.getPopulations().add(newPopulation);
			commit(gepAlgRun.getPrePopulation(),executorService);
			algRunStep.mutate(gepAlgRun.getCurrentPopulation());
			algRunStep.isTransport(gepAlgRun.getCurrentPopulation());
			algRunStep.risTransport(gepAlgRun.getCurrentPopulation());
			algRunStep.geneTransport(gepAlgRun.getCurrentPopulation());
			algRunStep.onePointRecombine(gepAlgRun.getCurrentPopulation());
			algRunStep.twoPointRecombine(gepAlgRun.getCurrentPopulation());
			algRunStep.geneRecombine(gepAlgRun.getCurrentPopulation());
		}
		commit(gepAlgRun.getCurrentPopulation(),executorService);
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
	private void commit(final Population population,ExecutorService executorService){
		class DdSave implements Runnable{
			private Population population;
			public DdSave(Population population) {
				// TODO Auto-generated constructor stub
				this.population=population;
			}
			@Override
			public void run() {
				// TODO Auto-generated method stub
				hibernateDataContext.save(population);
			}
		}
		executorService.execute(new DdSave(population));
	}
	private void commit(GepAlgRun gepAlgRun){
		List<? extends DataSet> dataSets=hibernateDataContext.findAll(DataSet.class);
		List<? extends GepAlgConfiguration>gepAlgConfigurations=hibernateDataContext.findAll(GepAlgConfiguration.class);
		IgepConfigurationService geConfigurationService=new GepConfigurationService(hibernateDataContext);
		int dataSetIndex=dataSets.indexOf(gepAlgRun.getDataSet());
		int confIndex=gepAlgConfigurations.indexOf(gepAlgRun.getGepAlgConfiguration());
		DataSet dataSet=gepAlgRun.getDataSet();
		GepAlgConfiguration gepAlgConfiguration=gepAlgRun.getGepAlgConfiguration();
		if(dataSetIndex!=-1){
			dataSet=dataSets.get(dataSetIndex);
			gepAlgRun.setDataSet(dataSet);
		}	
		else 
			hibernateDataContext.save(dataSet);	
		if(confIndex!=-1){
			gepAlgConfiguration=gepAlgConfigurations.get(confIndex);
			geConfigurationService.setGepAlgConfiguration(gepAlgConfiguration, dataSet);
			gepAlgRun.setGepAlgConfiguration(gepAlgConfiguration);
		}
		else{
			geConfigurationService.setGepAlgConfiguration(gepAlgConfiguration, dataSet);
			geConfigurationService.saveGepAlgConfiguration(gepAlgConfiguration);
		}			
		hibernateDataContext.save(gepAlgRun);	
	}
}
