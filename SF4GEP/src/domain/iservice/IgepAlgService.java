package domain.iservice;

import java.io.IOException;
import java.util.List;

import jxl.read.biff.BiffException;

import domain.core.algmodel.configuration.GepAlgorithm;
import domain.core.algmodel.genecomponent.Function;
import domain.core.outputmodel.AlgInstance;
import domain.core.outputmodel.GepConfiguration;
import domain.service.alg.baseclass.Calculator;
import domain.service.alg.baseclass.Creator;
import domain.service.alg.baseclass.Modifying;
import domain.service.alg.baseclass.Selector;
import exception.Duplicated;

public interface IgepAlgService {
	public List<GepConfiguration> readArgumentsFromDb();
	public void setParameters(GepConfiguration myConfiguration) throws InstantiationException, IllegalAccessException, ClassNotFoundException, BiffException, IOException;
	public void saveArgumentsToDb(GepConfiguration myParameter) throws Duplicated;//这个异常是由于某种原因无法保存到数据库，最可能的原因是名字重复
	public AlgInstance getMyAlgInstance();
	public List<Creator> getAvailableCreator() throws ClassNotFoundException, InstantiationException, IllegalAccessException;	
	public List<Function> getAvailableFunctions() throws ClassNotFoundException, InstantiationException, IllegalAccessException;	
	public List<Selector> getAvailableSelector() throws ClassNotFoundException, InstantiationException, IllegalAccessException;	
	public List<Calculator> getAvailableCalculator() throws ClassNotFoundException, InstantiationException, IllegalAccessException;	
	public List<Modifying> getAvailableModifyings() throws ClassNotFoundException, InstantiationException, IllegalAccessException;
	public void run();
	
	/**
	 * 为了画拟合曲线图添加的参数
	 * @return
	 */
	public Calculator getCalculator();
	public GepAlgorithm getMyGepAlgorithm();
	
}
