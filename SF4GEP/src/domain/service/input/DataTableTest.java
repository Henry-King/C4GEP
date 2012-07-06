package domain.service.input;
import java.util.List;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.io.File;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import domain.core.algmodel.genecomponent.Variable;
import domain.core.inputmodel.FieldRow;
import domain.core.inputmodel.InputSet;
import domain.core.inputmodel.ResultFieldItem;
import domain.core.inputmodel.VariableFiledItem;
import domain.core.inputmodel.VariableRow;

public class DataTableTest {
	static VariableRow vRow;
	static Variable variable;
	static FieldRow fRow;
	static VariableFiledItem vFiledItem;
	static ResultFieldItem rFieldItem;
	static int Rows;
	static int Cols;
	static InputSet input=new InputSet();
	public static void main(String args[]) {
		 DataTable table = new DataTable(); //�½�һ�����ݱ���󣬲������캯������

		 fillTable(table);//�������
		 printTable(table);
		 System.out.print("------------------------------------------------------------------------------------------------------------------------\n");
		 table = new DataTable("TestTable");//�½�һ�����ݱ���󣬴�����Ϊ����
		 //�����������
		
		 fillTable(table);//�������
		
		 printTable(table);
		 System.out.print(table.getValue(0,0));
		 System.out.println(table.getColumnName(1));
		 System.out.println(table.getColumnName(2.176));
		 
	     }
	private static void fillTable(DataTable table) {
        try {
        	File file=new File("InputDemo.xls");
        	read(file);
         //���20������
		      for (int i = 0; i <vRow.getVariableList().size(); i++) {
		    	 table.addColumn(vRow.getVariableList().get(i).getName());
		       }
		 //���100������
		  for (int i = 0; i < Rows-1; i++) {
		     DataRow row = table.newRow();
		       for (int j = 0; j<Cols; j++) {
		                       //ѭ��ÿ�м�������
		              row.setValue(j,input.getFieldRowList().get(i).getFieldItemList().get(j).getValue());
		     }
		      table.addRow(row);
		   }
		 } catch (Exception e) {
             e.printStackTrace();
		 }
   }
	private static void printTable(DataTable table) {
		   
		  for (int i = 0; i < table.getTotalCount(); i++) {
	           for (int j = 0; j < table.getColumns().size(); j++) {
		             System.out.print(table.getValue(i, j));
		                   if (j < 19)
		                     System.out.print(" , ");
		        }
	         System.out.print("\n");
	         }
	}
	public static  void read(File path) throws BiffException, IOException {
		// TODO Auto-generated method stub
		InputStream is = new FileInputStream(path);

		// ����һ��������
		Workbook rwb = Workbook.getWorkbook(is);

		// ��ù������ĸ���
		rwb.getNumberOfSheets();

		// ��Excel�ĵ��У���һ�Ź������ȱʡ������0
		Sheet st = rwb.getSheet(0);

		// ͨ�õĻ�ȡcellֵ�ķ�ʽ,getCell(int column, int row) �к���
	    Rows = st.getRows();
	    Cols = st.getColumns();
		vRow=new VariableRow(Cols);
		
		for (int i = 0; i < Cols; i++) {
			variable = new Variable();
			variable.setName(st.getCell(i, 0).getContents());
			vRow.add(variable);
		}
		//input.setVariableRow(vRow);
		
		
		for (int i = 1; i < Rows; i++) {
			fRow=new FieldRow(Cols);
			for (int j = 0; j < Cols-1; j++) {
				vFiledItem=new VariableFiledItem(Float.valueOf(st.getCell(j, i).getContents()));
				vFiledItem.setName(vRow.getVariableList().get(j).getName());
				fRow.addItem(vFiledItem);
			}
			rFieldItem=new ResultFieldItem(Float.valueOf(st.getCell(Cols-1 ,i).getContents()));
			rFieldItem.setName(vRow.getVariableList().get(Cols-1).getName());
			fRow.addItem(rFieldItem);
			input.getFieldRowList().add(fRow);
		}
	}

}
