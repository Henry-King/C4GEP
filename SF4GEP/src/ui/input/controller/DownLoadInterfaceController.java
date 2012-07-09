package ui.input.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;


public class DownLoadInterfaceController {
	   private static String fileName;
	   private static File saveDir;
	   static StringBuffer buffer;
	   static File file;
	   public static int btnDownLoadController(ModelForDownLoadInterface downLoadInterface) throws IOException{
		   fileName=downLoadInterface.getInterfaceName();
		   System.out.print("fileName"+fileName);
		   saveDir=downLoadInterface.getSaveDir();
		   System.out.println("saveDir"+saveDir.getName());
		   buffer=ReadFile(fileName,saveDir);
		   return WriteFile(buffer,file.getName());
	   }
	
	private static StringBuffer ReadFile(String interfaceName, File saveDir) {
		
		if(interfaceName.equals("Calculator")){
			 file=new File(".\\src\\domain\\service\\alg\\baseclass\\Calculator.java");
		}
		else if(interfaceName.equals("Creator")){
			file=new File(".\\src\\domain\\service\\alg\\baseclass\\Creator.java");
		}
		else if(interfaceName.equals("Selector")){
		    file=new File(".\\src\\domain\\service\\alg\\baseclass\\Selector.java");
		}
		else if(interfaceName.equals("Modifying")){
			 file=new File(".\\src\\domain\\service\\alg\\baseclass\\Modifying.java");
		}
		else{
			 file=new File(".\\src\\domain\\core\\algmodel\\genepiece\\Function.java");
		}
		System.out.println(file.toString());
		try{
		       
		       BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
		       String data=null;
		       StringBuffer buffer=new StringBuffer();
		       while((data=reader.readLine())!=null){
		    	
		    	   buffer.append(data+"\n");
		       }
		        reader.close();
		        return buffer;
		 }catch(IOException e){
			     throw new RuntimeException();
		}
    }
	private static int WriteFile(StringBuffer buffer, String name) throws IOException {
		 //检查指定路径是否存在
		 File newInterface=new File(saveDir.toString()+"\\"+file.getName());
		 
		 if(!saveDir.exists()&&!saveDir.isDirectory()){//检查文件夹是否存在，不存在就生成
			 saveDir.mkdirs();
			 
		 }
		 System.out.println(newInterface.toString());
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newInterface),"GBK"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generate d catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     writer.write(buffer.toString());
		 writer.flush();
	     writer.close();
	     return 0;
	}
}
