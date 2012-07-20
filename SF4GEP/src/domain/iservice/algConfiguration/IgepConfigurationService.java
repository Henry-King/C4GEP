package domain.iservice.algConfiguration;

import java.util.List;

import domain.core.algInputDataProcess.DataSet;
import domain.core.algconfiguration.Function;
import domain.core.algconfiguration.GepAlgConfiguration;

public interface IgepConfigurationService {
	public List<GepAlgConfiguration> getAllGepAlgConfiguration();
	public boolean saveGepAlgConfiguration(GepAlgConfiguration gepAlgConfiguration);
	/**
	 * 配置信息中有些属性有简单的函数决定关系，例如b＝a+1，调用前只需要填充b，此方法会自动计算a的值
	 * 前置条件：调用此方法前，请确定GepAlgConfiguration的所有子属性均填充完整。因此在正式使用GepAlgConfiguration这个类的对象的时候，请先调用此方法。
	 * 需要填充的参数有name，maxGenerationNum,selectionRange,accuracy,OperatorConfiguration中的所有属性，
	 * individualConfiguration中的individualNumber，GeneConfiguration中的NormalGeneHeaderLength,NormalGeneNum,
	 * HomeoticGeneHeaderLength,HomeoticGeneNum，及GeneConfiguration中所包含的函数集
	 * 此方法会改变传入的参数。
	 * @param gepAlgConfiguration
	 * @return
	 */
	public GepAlgConfiguration setGepAlgConfiguration(GepAlgConfiguration gepAlgConfiguration,DataSet dataSet);
	public List<Function> getAvailableFunctions();
	public boolean upate(GepAlgConfiguration gepAlgConfiguration);
}
