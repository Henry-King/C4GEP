package domain.iservice.algConfiguration;

import java.util.List;

import domain.core.algInputDataProcess.DataSet;
import domain.core.algconfiguration.Function;
import domain.core.algconfiguration.GepAlgConfiguration;

public interface IgepConfigurationService {
	public List<GepAlgConfiguration> getAllGepAlgConfiguration();
	public boolean saveGepAlgConfiguration(GepAlgConfiguration gepAlgConfiguration);
	/**
	 * ������Ϣ����Щ�����м򵥵ĺ���������ϵ������b��a+1������ǰֻ��Ҫ���b���˷������Զ�����a��ֵ
	 * ǰ�����������ô˷���ǰ����ȷ��GepAlgConfiguration�����������Ծ�����������������ʽʹ��GepAlgConfiguration�����Ķ����ʱ�����ȵ��ô˷�����
	 * ��Ҫ���Ĳ�����name��maxGenerationNum,selectionRange,accuracy,OperatorConfiguration�е��������ԣ�
	 * individualConfiguration�е�individualNumber��GeneConfiguration�е�NormalGeneHeaderLength,NormalGeneNum,
	 * HomeoticGeneHeaderLength,HomeoticGeneNum����GeneConfiguration���������ĺ�����
	 * �˷�����ı䴫��Ĳ�����
	 * @param gepAlgConfiguration
	 * @return
	 */
	public GepAlgConfiguration setGepAlgConfiguration(GepAlgConfiguration gepAlgConfiguration,DataSet dataSet);
	public List<Function> getAvailableFunctions();
	public boolean upate(GepAlgConfiguration gepAlgConfiguration);
}
