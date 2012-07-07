package ui.alg.view;

public class ModelForJPanelInputPath {
    String inputPath;
    String maxGeneration;
    String accuracy;
    public ModelForJPanelInputPath(String inputPath,String maxGeneration,String accuracy){
    	this.inputPath=inputPath;
    	this.maxGeneration=maxGeneration;
    	this.accuracy=accuracy;
    	
    }
    public void setInputPath(String inputPath){
    	this.inputPath=inputPath;
    }
    public void setMaxGeneration(String maxGeneration){
    	this.maxGeneration=maxGeneration;
    }
    public void setAccuracy(String accuracy){
    	this.accuracy=accuracy;
    }
    public String getInputPath(){
        return inputPath;
    }
    public String getMaxgeneration(){
    	return maxGeneration;
    }
    public String getAccuracy(){
    	return accuracy;
    }
}
