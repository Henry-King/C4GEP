package domain.iservice;

import java.io.File;
import java.io.IOException;

import jxl.read.biff.BiffException;

import domain.core.inputmodel.InputSet;

public interface IgepInputService {
	public void uploadFile (InputSet set,File file) throws IOException, BiffException;
}
