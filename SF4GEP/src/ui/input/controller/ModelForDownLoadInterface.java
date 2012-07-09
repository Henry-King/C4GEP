package ui.input.controller;

import java.io.File;

public class ModelForDownLoadInterface {
	   String fileName;
	   String interfacePath;
	   File file;
	   File saveDir;
	   
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
		
		public void setFile(File file) {
				this.file = file;
		}
		public File getFile() {
			   return file;
		}
		 public File getSaveDir() {
				return saveDir;
		}
		public void setDir(File dir) {
				this.saveDir = dir;
	    }
		
		public ModelForDownLoadInterface(File file,File dir,String fileName, String interfacePath) {
			super();
			this.file=file;
			this.saveDir=dir;
			this.fileName = fileName;
			this.interfacePath=interfacePath;
		}
		
}
