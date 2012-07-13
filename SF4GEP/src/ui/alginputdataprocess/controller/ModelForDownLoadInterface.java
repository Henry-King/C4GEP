package ui.alginputdataprocess.controller;

import java.io.File;

public class ModelForDownLoadInterface {
	   String saveDirName;
	   String interfaceName;
	   File saveDir;
	   
	   public String getSaveDirName() {
			return saveDirName;
		}
		public void setSaveDirName(String fileName) {
			this.saveDirName = fileName;
		}
		
		public String getInterfaceName() {
				return interfaceName;
		}
		public void setInterfaceName(String interfacePath) {
				this.interfaceName = interfacePath;
		}
		
		
		 public File getSaveDir() {
				return saveDir;
		}
		public void setDir(File dir) {
				this.saveDir = dir;
	    }
		
		public ModelForDownLoadInterface(File dir,String interfaceName, String interfacePath) {
			super();
			this.saveDir=dir;
            this.saveDirName =interfacePath;
			this.interfaceName=interfaceName;
		}
		
		
}
