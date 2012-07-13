package ui.algconfiguration.model;

public class ModelForJPanelConfig {
    String configName;
    public ModelForJPanelConfig(String configName){
    	this.configName=configName;
    }
    public void setConfig(String config){
       configName=config;
    }
    public String getConfig(){
    	return this.configName;
    }
}
