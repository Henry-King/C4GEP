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

import ui.input.model.ModelForUploadInterface;


public class UploadInterfaceController {

   private static String fileName;
   private static boolean available;
   private static String defaultFileSavePath=".\\bin\\domain\\service\\input\\";
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
	       
	     
	    	 buffer.append("package domain.service.input;"+"\n"); 
	    	//会报错
	       
	       
	       int j=1;//当第一次读时替换package
	       
	       while((data=reader.readLine())!=null){
	    	 if(j!=1&&j!=2){
	    	   buffer.append(data+"\n");
	    	   fileName=file.getName().substring(0,file.getName().indexOf(".java"));//得到类名
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
		  
		  
		  
		
		  
		 //检查指定路径是否存在
		 File newInterface=new File(defaultFileSavePath+file.getName());
		 File[] fileList;
		 if(!saveDir.exists()&&!saveDir.isDirectory()){//检查文件夹是否存在，不存在就生成
			 newInterface.mkdirs();
			 
		 }
		 fileList=saveDir.listFiles();
		 for(int i = 0; i< fileList.length;i++){
			
		       if(file.getName().equals(fileList[i].getName())){
		    	  
		    	 //相同文件名的文件已存在
		    	   
					 System.out.println("文件已存在");
				   return -1;//文件已存在
		       }
		   }
			   
         
	     
		 
		
		 
	     BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newInterface),"GBK"));
	     writer.write(buffer.toString());
		 writer.flush();
	     writer.close();
	     //生成。class文件
	     JavaCompiler complier=ToolProvider.getSystemJavaCompiler();
	     //通过TOOlProvider类的静态方法getSystemJavaCompiler来得到一个JAvaCompiler接口的实例,不能有效的控制输入输出
	     StandardJavaFileManager fileMgr=complier.getStandardFileManager(null, null, null);
	     //
	     Iterable units=fileMgr.getJavaFileObjects(defaultFileSavePath+file.getName());
	     System.out.print(defaultFileSavePath+file.getName());
	     CompilationTask t=complier.getTask(null,fileMgr,null,null,null,units);
	     //writer out：用于输出错误的流，默认为System。err
	     //javaFileManager fileManager：标准的文件管理
	     //DiagnosticListener diagnosticListener:编译器的默认行为
	     //iterable option:编译器选项
	     //iterable class:参与编译的文件
	     //iterable compiliationUnis不能为null,保存编译的java文件
	     t.call();//编译源程序
	     fileMgr.close();
	    
		
		try {
			fileName=file.getName().substring(0,file.getName().indexOf(".java"));//得到类名
			String filePathString=defaultFileSavePath;
			
			File classfiles=new File(filePathString);
			System.out.println(filePathString);
			System.out.println(classfiles.toString());
			//---检查实现的接口
			List<T> resultList=new ArrayList<T>(classfiles.list().length);
			Class<?> myClass=Class.forName("domain.service.input."+fileName);
			for(String string:classfiles.list()){
				
				available=myClass.isInstance(string);
			}
			if(available==false){
				return -2;//没有实现接口
				
			}
			int i=0;
			//检查实现构造函数
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
				return -3;//没有默认构造函数
			}
		 } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		
	    
	     return 0;//可以上传
	   }
	    
	 
 
	  }
	  

