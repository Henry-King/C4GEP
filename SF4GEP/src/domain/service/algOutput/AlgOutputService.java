package domain.service.algOutput;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import data.dao.IHibernateDataContext;
import domain.core.algInputDataProcess.DataSet;
import domain.core.algOutput.GepAlgRun;
import domain.core.algOutput.Population;
import domain.core.algconfiguration.GepAlgConfiguration;
import domain.iservice.algConfiguration.IgepConfigurationService;
import domain.iservice.algInputDataProcess.IDataInputService;
import domain.iservice.algOutput.IAlgOutputService;
import domain.iservice.algOutput.IAlgRunStep;
import domain.service.algConfiguration.GepConfigurationService;
import domain.service.algInputDataProcess.DataInputService;

public class AlgOutputService implements IAlgOutputService {
	private class DbSave<T> implements Runnable{
		private T data;
		public DbSave(T data) {
			// TODO Auto-generated constructor stub
			this.data=data;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			hibernateDataContext.save(data);
		}
	}
	private boolean flag;
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
		long start=System.nanoTime();
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
		long end=System.nanoTime();
		long period=TimeUnit.MILLISECONDS.convert(end-start, TimeUnit.NANOSECONDS);
		gepAlgRun.setPeriod(period);
		commit(gepAlgRun.getCurrentPopulation(), executorService);
		executorService.shutdown();
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
		executorService.execute(new DbSave<Population>(population));
	}
	private void commit(GepAlgRun gepAlgRun){
		IgepConfigurationService gepConfigurationService=new GepConfigurationService(hibernateDataContext);
		IDataInputService dataInputService=new DataInputService(hibernateDataContext);
		gepAlgRun.setDataSet(dataInputService.save(gepAlgRun.getDataSet()));
		gepAlgRun.setGepAlgConfiguration(gepConfigurationService.save(gepAlgRun.getGepAlgConfiguration()));
		hibernateDataContext.save(gepAlgRun);
	}
	@Override
	public boolean writeToDB(boolean flag) {
		// TODO Auto-generated method stub
		boolean original=this.flag;
		this.flag=flag;
		return original;
	}
}
