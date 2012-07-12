package domain.iservice.algConfiguration;

import domain.core.algconfiguration.GepAlgConfiguration;

public interface IgepConfigurationService {
	public GepAlgConfiguration getGepAlgConfigurationByName();
	public boolean saveGepAlgConfiguration(GepAlgConfiguration gepAlgConfiguration);
}
