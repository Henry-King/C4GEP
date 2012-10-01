package ui.conf;

public class NewGEPData {
	private String projectName = null;
	private String projectPath = null;
	private String frameworkType = "´®ÐÐÊ½";
	private String profileName = "default";
	private String profilePath = "defaultProfile.txt";
	private String inputDataPath = null;

	public void setProjectName(String projectName){
		this.projectName = projectName;
	}
	public String getProjectName(){
		return this.projectName;
	}
	
	public void setProjectPath(String projectPath){
		this.projectPath = projectPath;
	}
	public String getProjectPath(){
		return this.projectPath;
	}
	
	public void setFrameworkType(String frameworkType){
		this.frameworkType = frameworkType;
	}
	public String getFrameworkType(){
		return this.frameworkType;
	}
	
	public void setProfileName(String profileName){
		this.profileName = profileName;
	}
	public String getProfileName(){
		return this.profileName;
	}
	
	public void setProfilePath(String profilePath){
		this.profilePath = profilePath;
	}
	public String getProfilePath(){
		return this.profilePath;
	}
	
	public void setInputDataPath(String inputDataPath){
		this.inputDataPath = inputDataPath;
	}
	public String getInputDataPath(){
		return this.inputDataPath;
	}
	
	
}
