package tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MergeFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Iterator<File> allFile=Directory.walk("src",".+java").iterator();
		File mergedFile=new File("merged.txt");
		File nextFile;
		BufferedWriter bufferedWriter;
		String contextString;
		try {
			bufferedWriter=new BufferedWriter(new FileWriter(mergedFile));

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("Ŀ���ļ��޷���");
			e1.printStackTrace();
			return;
		}
		while(allFile.hasNext()){
			nextFile=allFile.next();
			if(nextFile.exists()){
				try {
					contextString=readFileToString(nextFile);
					writeToFile(bufferedWriter, contextString);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println("�ļ�������");
					e.printStackTrace();
					continue;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("�޷����ļ�");
					e.printStackTrace();
					continue;
				}
				//writeToFile(bufferedWriter, contextString);
			}
		}
	}
	private static String readFileToString(File file) throws IOException{
		BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
		String s;
		StringBuilder stringBuilder=new StringBuilder();
		stringBuilder.append(file.getPath().substring(4)+"\n");
		
		//Pattern pattern = Pattern.compile("([\t]{1}|\n+|\r+|\r\n+)");
		Pattern pattern = Pattern.compile("(\r?\n(\\s*\r?\n)+)");
		while(true){
			if((s=bufferedReader.readLine())!=null){
				/*if (s.equals("")||s==null||s.equals(null)||s.equals("\r")||s.equals("	 ")||s.equals("\t")||s.equals("\t\t")||s.equals("\t\t\t")||s.equals("\t\t"+"  ")){
					
					
				}else {
					stringBuilder.append(s+"\n");
					continue;
				}*/
				
				if (s.equals("")) {
					
				}
				else {
					
					Matcher matcher = pattern.matcher(s);
					if (matcher.find()) {
						//System.out.println(matcher.group());
						//s.replaceAll("(\r?\n(\\s*\r?\n)+)", "\r\n"); 
						//s = matcher.replaceAll("\n");	//\r\n
						//System.out.print(s);
						
						
					}else{
						//System.out.println("no found");
						stringBuilder.append(s+"\n");
					}
				}
				
			}else{
				stringBuilder.deleteCharAt(stringBuilder.length()-1);
				break;
			}
		}
		return stringBuilder.toString();
	}
	private static boolean writeToFile(BufferedWriter bufferedWriter,String context){
		boolean result=true;
		try {
			bufferedWriter.write(context+"\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			result=false;
		}
		return result;
	}
}
