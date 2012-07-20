package domain.iservice.algInputDataProcess;


import java.io.File;
import java.io.IOException;
import java.util.List;

import jxl.read.biff.BiffException;

import domain.core.algInputDataProcess.DataSet;

public interface IDataInputService {
	public DataSet processInputDataSet(File path) throws  BiffException, IOException;
	public boolean commit(DataSet dataSet);
	public boolean update(DataSet dataSet);
	public List<DataSet> getDataSets();
}
