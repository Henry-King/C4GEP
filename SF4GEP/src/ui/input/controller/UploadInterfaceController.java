package ui.input.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

import jxl.read.biff.BiffException;
import domain.core.outputmodel.GepConfiguration;
import domain.iservice.IgepAlgService;

import ui.alg.view.HostPanel;
import ui.alg.view.JPanelForFunction;
import ui.alg.view.JPanelForGene;
import ui.alg.view.JPanelForStopSetting;
import ui.alg.view.JPanelForPopulation;
import ui.input.model.ModelForUploadInterface;


public class UploadInterfaceController {

   private static String fileName;
   private static boolean available;
   private static String defaultFileSavePath=".\\bin\\domain\\service\\alg\\userdefined\\";
   private static File file;
   private static File saveDir;
   static String packagePath;
   static StringBuffer buffer;
   public static int btnUploadController(ModelForUploadInterface uploadInterface) throws IOException{
	   file=uploadInterface.getFile();
	   saveDir=new File(defaultFileSavePath);
	   try {
		buffer=ReadFile(file,saveDir);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return WriteFile(buffer,file.getName());
   }





   public static StringBuffer ReadFile(File file,File saveDir)throws IOException{
		
	     try{
	       
	       BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
	       String data=null;
	       StringBuffer buffer=new StringBuffer();
	       
	     
	    	 buffer.append("package domain.service.alg.userdefined;"+"\n"); 
	    	//�ᱨ��
	       
	       
	       int j=1;//����һ�ζ�ʱ�滻package
	       
	       while((data=reader.readLine())!=null){
	    	 if(j!=1&&j!=2){
	    	   buffer.append(data+"\n");
	    	   fileName=file.getName().substring(0,file.getName().indexOf(".java"));//�õ�����
	    	   char[] dataToChar =new char[data.length()];
	    	   String dataStr=new String();
	    	   for(int i=0;i<data.length();i++ ){
	    		   if(data.charAt(i)!=' '&&data.charAt(i)!='	'){
	    			   dataToChar[i]=data.charAt(i);
	    			   dataStr=dataStr+dataToChar[i];
	    		   }
	    		   
	    	   }
	    	 
	    	   
	    	 }
	    	 j++;
	       }
	        reader.close();
	        return buffer;
	       }catch(IOException e){
		     throw new RuntimeException();
	      }
	  }
	   
	   
	   
	   
	  public static <T> int WriteFile(StringBuffer buffer,String fileName) throws IOException{
		  
		  
		  
		
		  
		 //���ָ��·���Ƿ����
		 File newInterface=new File(defaultFileSavePath+file.getName());
		 
		 if(!saveDir.exists()&&!saveDir.isDirectory()){//����ļ����Ƿ���ڣ������ھ�����
			 newInterface.mkdirs();
			 
		 }
		 File[] filesBeforeUpload=saveDir.listFiles();
		 
		 List<File> filesListBeforeUpload=new ArrayList<File>();
		 for(int i = 0; i< filesBeforeUpload.length;i++){
			   filesListBeforeUpload.add(filesBeforeUpload[i]);
		       if(file.getName().equals(filesBeforeUpload[i].getName())){
		    	  
		    	 //��ͬ�ļ������ļ��Ѵ���
		    	   
					 System.out.println("�ļ��Ѵ���");
					 newInterface.delete();
				   return -1;//�ļ��Ѵ���
		       }
		   }
			   
         
	     
		 
		
		 
	     BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newInterface),"GBK"));
	     writer.write(buffer.toString());
		 writer.flush();
	     writer.close();
	     //���ɡ�class�ļ�
	     JavaCompiler complier=ToolProvider.getSystemJavaCompiler();
	     //ͨ��TOOlProvider��ľ�̬����getSystemJavaCompiler���õ�һ��JAvaCompiler�ӿڵ�ʵ��,������Ч�Ŀ����������
	     StandardJavaFileManager fileMgr=complier.getStandardFileManager(null, null, null);
	     //
	     Iterable units=fileMgr.getJavaFileObjects(defaultFileSavePath+file.getName());
	     System.out.print(defaultFileSavePath+file.getName());
	     CompilationTask t=complier.getTask(null,fileMgr,null,null,null,units);
	     //writer out������������������Ĭ��ΪSystem��err
	     //javaFileManager fileManager����׼���ļ�����
	     //DiagnosticListener diagnosticListener:��������Ĭ����Ϊ
	     //iterable option:������ѡ��
	     //iterable class:���������ļ�
	     //iterable compiliationUnis����Ϊnull,��������java�ļ�
	     t.call();//����Դ����
	     fileMgr.close();
	    
		
		try {
			fileName=file.getName().substring(0,file.getName().indexOf(".java"));//�õ�����
			String filePathString=defaultFileSavePath;
			
			File classfiles=new File(filePathString);
			System.out.println(filePathString);
			System.out.println(classfiles.toString());
			//---���ʵ�ֵĽӿ�
			List<T> resultList=new ArrayList<T>(classfiles.list().length);
			Class<?> myClass=Class.forName("domain.service.alg.userdefined"+fileName);
			for(String string:classfiles.list()){
				
				available=myClass.isInstance(string);
			}
			if(available==false){
				File[] filesAfterUpload=saveDir.listFiles();
				
				for(int i = 0; i< filesAfterUpload.length;i++){
					if(!filesListBeforeUpload.contains(filesAfterUpload[i])){
						filesAfterUpload[i].delete();
					 }
				}
				
				return -2;//û��ʵ�ֽӿ�
				
			}
			int i=0;
			//���ʵ�ֹ��캯��
			Constructor<?>[] constructorArray=myClass.getConstructors();
			for(Constructor<?> constructor:constructorArray){
				if(constructor.getParameterTypes().length==0){
					break;
				}
				else{
					i++;
				}
				
			}
			if(i==constructorArray.length){
				File[] filesAfterUpload=saveDir.listFiles();
				for(int j = 0; j< filesAfterUpload.length;i++){
					if(!filesListBeforeUpload.contains(filesAfterUpload[j])){
						filesAfterUpload[j].delete();
					 }
				}
				return -3;//û��Ĭ�Ϲ��캯��
				
			}
		 } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		
	    
	     return 0;//�����ϴ�
	   }
}
	  

