package domain.iservice.algOutput;

import java.util.concurrent.Future;

import domain.core.algInputDataProcess.DataSet;
import domain.core.algOutput.GepAlgRun;
import domain.core.algconfiguration.GepAlgConfiguration;

public interface IAlgOutputService {
	public boolean setWriteToDB(boolean flag);
	public Future<GepAlgRun> run(GepAlgConfiguration gepAlgConfiguration, IAlgRunStep algRunStep, DataSet dataSet);
	public void shutdownAll();
}
