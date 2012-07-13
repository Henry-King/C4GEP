package ui.alginputdataprocess.model;

import java.io.File;

public class ModelForUploadInterface {
   
   String fileName;
   String interfacePath;
   File file;
   File dir;
   
   
   
   public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getInterfacePath() {
			return interfacePath;
	}
	public void setInterfacePath(String interfacePath) {
			this.interfacePath = interfacePath;
	}
	public File getFile() {
			return file;
    }
	public void setFile(File file) {
			this.file = file;
	}
	 public File getDir() {
			return dir;
	}
	public void setDir(File dir) {
			this.dir = dir;
    }
	
	
	
	public ModelForUploadInterface(File file,File dir,String fileName, String interfacePath) {
		super();
		this.file=file;
		this.dir=dir;
		this.fileName = fileName;
		this.interfacePath=interfacePath;
	}
	
}
