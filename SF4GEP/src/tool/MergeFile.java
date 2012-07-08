package tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

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
			System.out.println("目标文件无法打开");
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
					System.out.println("文件不存在");
					e.printStackTrace();
					continue;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("无法打开文件");
					e.printStackTrace();
					continue;
				}
				writeToFile(bufferedWriter, contextString);
			}
		}
	}
	private static String readFileToString(File file) throws IOException{
		BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
		String s;
		StringBuilder stringBuilder=new StringBuilder();
		stringBuilder.append(file.getPath().substring(4)+"\n");
		while((s=bufferedReader.readLine())!=null){
			stringBuilder.append(s+"\n");
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
