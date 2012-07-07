package domain.iservice;

import java.io.File;
import java.io.IOException;

import jxl.read.biff.BiffException;

import domain.core.inputmodel.DataTable;

public interface IgepInputService {
	public void uploadFile (DataTable set,File file) throws IOException, BiffException;
}