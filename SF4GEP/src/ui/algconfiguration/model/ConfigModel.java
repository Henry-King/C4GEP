package ui.algconfiguration.model;

public class ConfigModel {
    String configName;
    public ConfigModel(String configName){
    	this.configName=configName;
    }
    public void setConfig(String config){
       configName=config;
    }
    public String getConfig(){
    	return this.configName;
    }
}
