package domain.iservice.algOutput;

import java.util.List;

import domain.core.algInputDataProcess.DataSet;
import domain.core.algOutput.GepAlgRun;
import domain.core.algconfiguration.GepAlgConfiguration;

public interface IAlgOutputService {
	public GepAlgRun run(GepAlgConfiguration gepAlgConfiguration, IAlgRunStep algRunStep, DataSet dataSet);
	public List<Float> getMaxFitnessInEveryGeneration(GepAlgRun gepAlgRun);
	public List<Float> getMinFitnessInEveryGeneration(GepAlgRun gepAlgRun);
}
