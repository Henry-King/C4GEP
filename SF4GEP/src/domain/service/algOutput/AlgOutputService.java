package domain.service.algOutput;

import java.io.File;
import java.util.Collections;
import java.util.List;

import domain.core.algOutput.GepAlgRun;
import domain.core.algOutput.Individual;
import domain.core.algOutput.Population;
import domain.core.algconfiguration.GepAlgConfiguration;
import domain.iservice.algOutput.IAlgOutputService;
import domain.iservice.algOutput.IAlgRunStep;

public class AlgOutputService implements IAlgOutputService {

	@Override
	public Individual run(GepAlgConfiguration gepAlgConfiguration, IAlgRunStep algRunStep) {
		// TODO Auto-generated method stub
		Individual bestIndividual=null;
		List<Float> fitnessFloats;
		Float maxFitness;
		GepAlgRun gepAlgRun=algRunStep.create(gepAlgConfiguration, null);
		for(long i=0;i<gepAlgConfiguration.getMaxGeneration();i++){
			fitnessFloats=algRunStep.calculateFitness(gepAlgRun.getCurrentPopulation());
			maxFitness=Collections.max(fitnessFloats);
			bestIndividual=gepAlgRun.getCurrentPopulation().getIndividuals().get(fitnessFloats.indexOf(maxFitness));
			commit(gepAlgRun.getCurrentPopulation());
			if(Math.abs(maxFitness-gepAlgConfiguration.getMaxFitness())<=gepAlgConfiguration.getAccuracy()||i==gepAlgConfiguration.getMaxGeneration()-2)
				break;
			gepAlgRun.getPopulations().add(algRunStep.select(gepAlgRun));
			algRunStep.mutate(gepAlgRun.getCurrentPopulation());
			algRunStep.isTransport(gepAlgRun.getCurrentPopulation());
			algRunStep.risTransport(gepAlgRun.getCurrentPopulation());
			algRunStep.geneTransport(gepAlgRun.getCurrentPopulation());
			algRunStep.onePointRecombine(gepAlgRun.getCurrentPopulation());
			algRunStep.twoPointRecombine(gepAlgRun.getCurrentPopulation());
			algRunStep.geneRecombine(gepAlgRun.getCurrentPopulation());
		}
		setFittedValue(bestIndividual);
		commit(gepAlgRun);
		return bestIndividual;
	}

	@Override
	public List<Float> getMaxFitnessInEveryGeneration(GepAlgRun gepAlgRun) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Float> getMinFitnessInEveryGeneration(GepAlgRun gepAlgRun) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean writeToFile(File file) {
		// TODO Auto-generated method stub
		return false;
	}
	private boolean commit(Population population){
		return true;
	}
	private boolean commit(GepAlgRun gepAlgRun){
		return true;
	}
	private void setFittedValue(Individual individual){
		
	}
}
