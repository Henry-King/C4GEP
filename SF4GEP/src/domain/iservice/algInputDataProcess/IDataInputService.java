package domain.iservice.algInputDataProcess;


import java.io.File;
import java.io.IOException;

import jxl.read.biff.BiffException;

import domain.core.algInputDataProcess.DataSet;

public interface IDataInputService {
	public DataSet processInputDataSet(File path) throws  BiffException, IOException;
	public boolean commit(DataSet dataSet);
}
