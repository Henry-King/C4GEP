package domain.iservice;

import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;

import jxl.write.WriteException;

import domain.core.algmodel.population.Individual;
import domain.core.outputmodel.AlgInstance;
import domain.service.alg.baseclass.Calculator;

public interface IgepOutputService {
	/**
	 * 不用实现
	 */
	public void setImplement(File path);
	
	public void setGeneration(long generation);
	public void setGeneration(long start,long end);
	public void writeExcel (AlgInstance set,File path) throws IOException, WriteException;

	public ImageIcon drawImage(AlgInstance outPut);
	public ImageIcon drawImage(Calculator calculator,Individual individual);
}
