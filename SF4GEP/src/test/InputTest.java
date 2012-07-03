package test;

import java.io.File;
import java.io.IOException;

import jxl.read.biff.BiffException;

import domain.core.inputmodel.InputSet;
import domain.service.input.DefaultGepInput;
import domain.service.input.IgepInput;

public class InputTest {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws BiffException 
	 */
	public static void main(String[] args) throws BiffException, IOException {
		// TODO Auto-generated method stub
		IgepInput input =new DefaultGepInput();
		InputSet is = new InputSet();
		File path=new File("InputDemo.xls");
		input.setFile(path);
		input.read(is);
		int row=is.getFieldRowList().size();
		int col=is.getVariableRow().getColumns();	
		for (int i=0;i<col;i++)
		{
			System.out.print(is.getVariableRow().getVariableList().get(i).getName()+" ");
		}
		System.out.println();
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++)
				{
				System.out.print(is.getFieldRowList().get(i).getFieldItemList().get(j).getValue()+" "+is.getFieldRowList().get(i).getFieldItemList().get(j).getName()+"\t");
				}
			System.out.println();
		}
	}

}
