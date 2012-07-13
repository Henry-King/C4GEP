package domain.iservice.algOutput;

import java.util.List;

import domain.core.algInputDataProcess.DataSet;
import domain.core.algOutput.FittedValue;
import domain.core.algOutput.GepAlgRun;
import domain.core.algOutput.Population;
import domain.core.algconfiguration.GepAlgConfiguration;

public interface IAlgRunStep {
	public GepAlgRun create(GepAlgConfiguration gepAlgConfiguration, DataSet dataSet);
	public List<FittedValue> calculateFittedValue(Population population);
	public List<Float> calculateFitness(Population population);
	public Population select(GepAlgRun gepAlgRun);
	public boolean mutate(Population population);
	public boolean isTransport(Population population);
	public boolean risTransport(Population population);
	public boolean geneTransport(Population population);
	public boolean onePointRecombine(Population population);
	public boolean twoPointRecombine(Population population);
	public boolean geneRecombine(Population population);
}
