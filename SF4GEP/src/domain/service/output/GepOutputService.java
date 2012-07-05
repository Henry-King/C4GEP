package domain.service.output;

import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;

import jxl.write.WriteException;


import domain.core.algmodel.configuration.Individual;
import domain.core.outputmodel.AlgInstance;
import domain.iservice.IgepOutputService;
import domain.service.alg.configuration.Calculator;

public class GepOutputService implements IgepOutputService {
	private IgepOutput igepOutput=new DefalutGepOutput();

	@Override
	public void setImplement(File path) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGeneration(long generation) {
		// TODO Auto-generated method stub
		igepOutput.setRange(generation);
	}

	@Override
	public void setGeneration(long start, long end) {
		// TODO Auto-generated method stub
		igepOutput.setRange(start, end);
	}

	@Override
	public void writeExcel(AlgInstance set, File path) throws IOException,
			WriteException {
		// TODO Auto-generated method stub
//		igepOutput.writeExcel(path, set);
	}

	@Override
	public ImageIcon drawImage(AlgInstance outPut) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImageIcon drawImage(Calculator calculator, Individual individual) {
		// TODO Auto-generated method stub
		return null;
	}

}
