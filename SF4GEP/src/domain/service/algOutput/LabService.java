package domain.service.algOutput;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
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

public class LabService implements IAlgOutputService {
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
	private ExecutorService exec=Executors.newCachedThreadPool();
	public LabService(IHibernateDataContext hibernateDataContext) {
		// TODO Auto-generated constructor stub
		this.hibernateDataContext=hibernateDataContext;
	}
	@Override
	public synchronized Future<GepAlgRun> run(final GepAlgConfiguration gepAlgConfiguration, final IAlgRunStep algRunStep, final DataSet dataSet) {
		// TODO Auto-generated method stub
		return exec.submit(new Callable<GepAlgRun>() {

			@Override
			public GepAlgRun call() {
				// TODO Auto-generated method stub
				ExecutorService execDB=Executors.newSingleThreadExecutor();
				List<Float> fitnessFloats;
				Float maxFitness;
				Float minFitness;
				GepAlgRun gepAlgRun=algRunStep.create(gepAlgConfiguration, dataSet);
				gepAlgRun.getPopulations().add(gepAlgRun.getCurrentPopulation());
				if(flag)
					commit(gepAlgRun);
				Population newPopulation;
				gepAlgRun.getMaxFitnesses().clear();
				gepAlgRun.getMinFitnesses().clear();
				long start=System.nanoTime();
				for(long i=0;i<gepAlgConfiguration.getMaxGeneration();i++){
					fitnessFloats=algRunStep.calculateFitness(gepAlgRun.getCurrentPopulation());
					maxFitness=Collections.max(fitnessFloats);
					minFitness=Collections.min(fitnessFloats);
					gepAlgRun.getMaxFitnesses().add(maxFitness);
					gepAlgRun.getMinFitnesses().add(minFitness);
					if(Math.abs(maxFitness-gepAlgConfiguration.getMaxFitness())<=gepAlgConfiguration.getAccuracy()||i==gepAlgConfiguration.getMaxGeneration()-1)
						break;
					newPopulation=algRunStep.select(gepAlgRun);
					newPopulation.setGenerationNum(i+1);
					gepAlgRun.getPopulations().add(newPopulation);
					if(flag)
						commit(gepAlgRun.getPrePopulation(),execDB);
					algRunStep.mutate(gepAlgRun.getCurrentPopulation());
					algRunStep.isTransport(gepAlgRun.getCurrentPopulation());
					if(gepAlgConfiguration.getOperatorConfiguration().getOnePointRecombineRate()>0)
						algRunStep.onePointRecombine(gepAlgRun.getCurrentPopulation());
					if(gepAlgConfiguration.getOperatorConfiguration().getTwoPointRecombineRate()>0)
						algRunStep.twoPointRecombine(gepAlgRun.getCurrentPopulation());
				}
				long end=System.nanoTime();
				long period=TimeUnit.MILLISECONDS.convert(end-start, TimeUnit.NANOSECONDS);
				gepAlgRun.setPeriod(period);
				if(flag)
					commit(gepAlgRun.getCurrentPopulation(), execDB);
				execDB.shutdown();
				return gepAlgRun;
			}
		});
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
	public synchronized boolean setWriteToDB(boolean flag) {
		// TODO Auto-generated method stub
		boolean original=this.flag;
		this.flag=flag;
		return original;
	}
	@Override
	public synchronized void shutdownAll() {
		// TODO Auto-generated method stub
		exec.shutdown();
	}
}
